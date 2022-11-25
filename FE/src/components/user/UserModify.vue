<template>
  <section id="modifyProfile" style="pb-0" data-aos="zoom-in-out" v-if="userInfo">
    <div class="container" style="min-height: 650px">
      <div class="section-header">
        <h2>프로필 수정</h2>
      </div>

      <div class="d-flex p-3 m-3 justify-content-center align-items-center">
        <form class="text-center" id="modify-form" method="POST" style="width: 50%">
          <input
            type="text"
            class="login-form-item form-control border rounded-5 p-3 mb-3 input-success"
            id="modify-form-id"
            name="id"
            placeholder="아이디"
            v-model="userInfo.id"
            disabled
          />
          <input
            type="text"
            class="login-form-item form-control border rounded-5 p-3 mb-3"
            :class="nameValid"
            id="modify-form-name"
            name="name"
            placeholder="이름"
            v-model="userInfo.name"
          />
          <input
            type="email"
            class="login-form-item form-control border rounded-5 p-3 mb-3"
            :class="emailValid"
            id="modify-form-email"
            name="email"
            placeholder="E-mail"
            v-model="userInfo.email"
          />
          <div id="emailcheck-result"></div>
          <input
            type="password"
            class="login-form-item form-control border rounded-5 p-3 mb-3"
            :class="pwdValid"
            id="modify-form-pwd"
            name="pwd"
            placeholder="비밀번호"
            v-model="pwd"
          /><input
            type="password"
            class="login-form-item form-control border rounded-5 p-3 mb-3"
            :class="pwdCheckValid"
            id="modify-form-pwd-confirm"
            placeholder="비밀번호 확인"
            v-model="pwdCheck"
          />
          <input
            type="file"
            accept="image/*"
            class="login-form-item form-control border rounded-5 mb-3"
            style="height: 45px"
            placeholder="프로필 사진"
            @change="handleFileChange"
          />

          <button type="button" class="btn-default ms-2 mt-4 mb-4 w-100" id="btn-modify" @click="modifyUser">
            회원정보 수정
          </button>
        </form>
      </div>
    </div>
  </section>
</template>

<script>
import { mapState, mapActions } from "vuex";

export default {
  name: "UserModify",
  data() {
    return {
      pwd: null,
      pwdCheck: null,
      nameValid: "input-success",
      emailValid: "input-success",
      pwdValid: null,
      pwdCheckValid: null,
      file: null,
      isEmail: true,
      pwdConfirm: false,
      /* eslint-disable-next-line */
      reg: /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,24}))$/,
    };
  },
  computed: {
    ...mapState("userStore", ["userInfo", "isLogin"]),
  },
  methods: {
    ...mapActions("userStore", ["userModify", "userConfirm", "getUserInfo"]),

    handleFileChange(e) {
      this.file = e.target.files[0];
    },
    async modifyUser() {
      if (!this.userInfo.name) {
        alert("이름을 입력해주세요!!");
        return;
      } else if (!this.userInfo.email) {
        alert("이메일을 입력해주세요!!");
        return;
      } else if (!this.pwd) {
        alert("비밀번호를 입력해주세요!!");
        return;
      } else if (!this.pwdCheck) {
        alert("비밀번호 확인을 입력해주세요!!");
        return;
      } else if (!this.isEmail) {
        alert("올바른 이메일 형식이 아닙니다!!");
        return;
      } else if (!this.pwdConfirm) {
        alert("비밀번호가 일치하지 않습니다!!");
        return;
      } else if (!this.file) {
        alert("프로필 이미지 파일을 선택해주세요!!");
        return;
      } else {
        let data = new FormData();
        data.append("uid", this.userInfo.uid);
        data.append("id", this.userInfo.id);
        data.append("name", this.userInfo.name);
        data.append("email", this.userInfo.email);
        data.append("pwd", this.pwd);
        data.append("image", this.userInfo.image);
        data.append("upfile", this.file);

        await this.userModify(data);

        await this.userConfirm({ id: this.userInfo.id, pwd: this.pwd });
        let token = sessionStorage.getItem("access-token");
        if (this.isLogin) {
          await this.getUserInfo(token);
          this.$router.push({ name: "profile" });
        }
      }
    },
  },
  watch: {
    "userInfo.name": function () {
      if (!this.userInfo.name) {
        this.nameValid = null;
      } else {
        this.nameValid = "input-success";
      }
    },

    "userInfo.email": function () {
      if (!this.userInfo.email) {
        this.emailValid = null;
        this.isEmail = false;
      } else if (!this.reg.test(this.userInfo.email)) {
        this.emailValid = "input-fail";
        this.isEmail = false;
      } else {
        this.emailValid = "input-success";
        this.isEmail = true;
      }
    },

    pwd() {
      if (!this.userInfo.pwd) {
        this.pwdValid = null;
      }
    },
    pwdCheck() {
      if (!this.pwdCheck) {
        this.pwdValid = null;
        this.pwdCheckValid = null;
        this.pwdConfirm = false;
      }
      if (this.pwdCheck == this.pwd) {
        this.pwdValid = "input-success";
        this.pwdCheckValid = "input-success";
        this.pwdConfirm = true;
      } else {
        this.pwdValid = "input-fail";
        this.pwdCheckValid = "input-fail";
        this.pwdConfirm = false;
      }
    },
  },
};
</script>

<style scoped>
.input-success {
  box-shadow: 0 0 5px green;
}

.input-fail {
  box-shadow: 0 0 5px red;
}
</style>
