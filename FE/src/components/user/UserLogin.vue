<template>
  <section id="login" style="pb-0" data-aos="zoom-in-up">
    <div class="container" style="min-height: 750px">
      <div class="section-header">
        <h2>로그인</h2>
        <p>EnjoyTrip와 함께 새로운 여행을 떠나보세요</p>
      </div>

      <div class="d-flex p-3 m-3 justify-content-center align-items-center">
        <form class="text-center" id="login-form" method="POST" style="width: 50%">
          <b-alert show variant="danger" v-if="isLoginError"
            >아이디 또는 비밀번호를 확인하세요.</b-alert
          >
          <input
            type="text"
            class="login-form-item form-control border rounded-5 p-3 mb-3"
            id="login-form-id"
            name="id"
            placeholder="아이디"
            v-model="user.id"
            @keyup.enter="confirm"
          />
          <input
            type="password"
            class="login-form-item form-control border rounded-5 p-3 mb-3"
            id="login-form-pw"
            name="pwd"
            placeholder="비밀번호"
            v-model="user.pwd"
            @keyup.enter="confirm"
          />
          <button
            type="button"
            class="btn-default me-2 mt-4 mb-4 w-100"
            id="btn-login"
            @click="confirm"
          >
            로그인
          </button>
          <br />
          <div class="mt-3">
            <a class="login-options" @click="moveJoin">회원 가입 </a> |
            <a class="login-options" @click="moveFindId">아이디 찾기</a> |
            <a class="login-options" @click="moveFindPw">비밀번호 찾기</a><br />
          </div>
        </form>
      </div>
    </div>
  </section>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";

export default {
  name: "UserLogin",
  data() {
    return {
      user: {
        id: null,
        pwd: null,
      },
    };
  },
  computed: {
    ...mapState("userStore", ["isLogin", "isLoginError", "userInfo"]),
  },
  methods: {
    ...mapMutations("userStore", ["SET_IS_LOGIN_ERROR"]),
    ...mapActions("userStore", ["userConfirm", "getUserInfo"]),

    async confirm() {
      await this.userConfirm(this.user);
      let token = sessionStorage.getItem("access-token");
      if (this.isLogin) {
        await this.getUserInfo(token);
        this.$router.push({ name: "home" });
      }
    },
    moveJoin() {
      this.$router.push({ name: "join" });
    },
    moveFindId() {
      this.$router.push({ name: "findId" });
    },
    moveFindPw() {
      this.$router.push({ name: "findPw" });
    },
  },
  mounted() {
    this.SET_IS_LOGIN_ERROR(false);
  },
};
</script>

<style>
.login-options:hover {
  cursor: pointer;
}
</style>
