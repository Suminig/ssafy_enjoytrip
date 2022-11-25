<template>
  <section id="findPw" style="pb-0" data-aos="zoom-in-up">
    <div class="container" style="min-height: 750px">
      <div class="section-header">
        <h2>비밀번호 찾기</h2>
      </div>

      <div class="d-flex p-3 m-3 justify-content-center align-items-center">
        <form class="text-center" id="login-form" style="width: 50%">
          <input
            type="text"
            class="login-form-item form-control border rounded-5 p-3 mb-3"
            placeholder="아이디"
            v-model="id"
          />
          <button type="button" class="btn-default mt-4 mb-4 w-100" @click="findPw">비밀번호 찾기</button>
        </form>
      </div>
    </div>
  </section>
</template>

<script>
import { mapState, mapActions } from "vuex";

export default {
  name: "UserFindPw",
  data() {
    return {
      id: null,
    };
  },
  computed: {
    ...mapState("userStore", ["tempUser", "tempPw"]),
  },
  methods: {
    ...mapActions("userStore", ["getUidForPw", "sendFindPwEmail", "changeTempPw"]),

    async findPw() {
      await this.getUidForPw(this.id);

      if (!this.tempUser) {
        alert("해당 아이디로 가입한 사용자 정보가 없습니다!!");
      } else {
        await this.sendFindPwEmail(this.tempUser.email);

        if (!this.tempPw) {
          alert("처리중 오류 발생!!");
        } else {
          await this.changeTempPw({ uid: this.tempUser.uid, pwd: this.tempPw });
          alert("임시 비밀번호가 회원님의 이메일에 전송되었습니다.");

          this.$router.push({ name: "login" });
        }
      }
    },
  },
};
</script>

<style></style>
