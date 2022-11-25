<template>
  <section id="join" style="pb-0" data-aos="zoom-in-up">
    <div class="container" style="min-height: 750px">
      <div class="section-header">
        <h2>회원 가입</h2>
        <p>EnjoyTrip에 가입하고 더 많은 여행 정보를 얻어보세요</p>
      </div>

      <div class="d-flex p-3 m-3 justify-content-center align-items-center">
        <form class="text-center" id="signup-form" style="width: 50%">
          <input
            type="text"
            class="login-form-item form-control border rounded-5 p-3 mb-3"
            :class="idValid"
            id="signup-form-id"
            name="id"
            placeholder="아이디"
            v-model="user.id"
            @keyup="checkId"
          />
          <div id="idcheck-result"></div>
          <input
            type="text"
            class="login-form-item form-control border rounded-5 p-3 mb-3"
            :class="nameValid"
            id="signup-form-name"
            name="name"
            placeholder="이름"
            v-model="user.name"
          />
          <div id="emailcheck-result"></div>
          <input
            type="email"
            class="login-form-item form-control border rounded-5 p-3 mb-3"
            :class="emailValid"
            id="signup-form-email"
            name="email"
            placeholder="E-mail"
            v-model="user.email"
          />
          <input
            type="password"
            class="login-form-item form-control border rounded-5 p-3 mb-3"
            :class="pwdValid"
            id="signup-form-pwd"
            name="pwd"
            placeholder="비밀번호"
            v-model="user.pwd"
          /><input
            type="password"
            class="login-form-item form-control border rounded-5 p-3 mb-3"
            :class="pwdCheckValid"
            id="signup-form-pwd-confirm"
            placeholder="비밀번호 확인"
            v-model="pwdCheck"
            @keyup.enter="userJoin"
          />
          <div id="pwdcheck-result"></div>

          <button type="button" class="btn-default ms-2 mt-4 mb-4 w-100" id="btn-signup" @click="userJoin">
            회원가입
          </button>
        </form>
      </div>
    </div>
  </section>
</template>

<script>
import { mapState, mapGetters, mapActions } from "vuex";

export default {
  name: "UserJoin",
  data() {
    return {
      idValid: null,
      nameValid: null,
      emailValid: null,
      pwdValid: null,
      pwdCheckValid: null,
      isUseId: false,
      isEmail: false,
      pwdConfirm: false,
      user: {
        id: null,
        name: null,
        email: null,
        pwd: null,
      },
      pwdCheck: null,
      /* eslint-disable-next-line */
      reg: /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,24}))$/,
    };
  },
  computed: {
    ...mapState("userStore", ["idCheck"]),
  },
  methods: {
    ...mapGetters("userStore", ["checkIdCheck"]),
    ...mapActions("userStore", ["getIdCheck", "userSignup"]),

    checkId() {
      this.getIdCheck(this.user.id);
    },

    userJoin() {
      if (!this.user.id) {
        alert("아이디를 입력해주세요!!");
        return;
      } else if (!this.user.name) {
        alert("이름을 입력해주세요!!");
        return;
      } else if (!this.user.email) {
        alert("이메일을 입력해주세요!!");
        return;
      } else if (!this.user.pwd) {
        alert("비밀번호를 입력해주세요!!");
        return;
      } else if (!this.pwdCheck) {
        alert("비밀번호 확인을 입력해주세요!!");
        return;
      } else if (!this.isUseId) {
        alert("이미 사용중인 아이디입니다!!");
        return;
      } else if (!this.isEmail) {
        alert("올바른 이메일 형식이 아닙니다!!");
        return;
      } else if (!this.pwdConfirm) {
        alert("비밀번호가 일치하지 않습니다!!");
        return;
      } else {
        this.userSignup(this.user);
      }
    },
  },
  watch: {
    "user.id": function () {
      if (!this.user.id) {
        this.idValid = null;
      } else if (this.user.id.length < 5 || this.user.id.length > 16) {
        this.idValid = "input-fail";
      } else {
        this.idValid = "input-success";
      }
    },
    idCheck() {
      if (this.idCheck == 0) {
        this.idValid = "input-success";
        this.isUseId = true;
      } else {
        this.idValid = "input-fail";
        this.isUseId = false;
      }
    },

    "user.name": function () {
      if (!this.user.name) {
        this.nameValid = null;
      } else {
        this.nameValid = "input-success";
      }
    },

    "user.email": function () {
      if (!this.user.email) {
        this.emailValid = null;
        this.isEmail = false;
      } else if (!this.reg.test(this.user.email)) {
        this.emailValid = "input-fail";
        this.isEmail = false;
      } else {
        this.emailValid = "input-success";
        this.isEmail = true;
      }
    },

    "user.pwd": function () {
      if (!this.user.pwd) {
        this.pwdValid = null;
      }
    },
    pwdCheck() {
      if (!this.pwdCheck) {
        this.pwdCheckValid = null;
        this.pwdConfirm = false;
      }
      if (this.pwdCheck == this.user.pwd) {
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
