<template>
  <section id="findId" style="pb-0" data-aos="zoom-in-up">
    <div class="container" style="min-height: 750px">
      <div class="section-header">
        <h2>아이디 찾기</h2>
      </div>

      <div class="d-flex p-3 m-3 justify-content-center align-items-center">
        <form class="text-center" id="login-form" style="width: 50%">
          <input
            type="text"
            class="login-form-item form-control border rounded-5 p-3 mb-3"
            placeholder="이메일"
            v-model="email"
          />
          <button type="button" class="btn-default mt-4 mb-4 w-100" @click="findId">아이디 찾기</button>
        </form>
      </div>
    </div>
  </section>
</template>

<script>
import { mapState, mapActions } from "vuex";

export default {
  name: "UserFindId",
  data() {
    return {
      email: null,
    };
  },
  computed: {
    ...mapState("userStore", ["userid"]),
  },
  methods: {
    ...mapActions("userStore", ["getIdByEmail", "sendFindIdEmail"]),

    async findId() {
      await this.getIdByEmail(this.email);

      if (!this.userid) {
        alert("해당 이메일로 가입한 사용자 정보가 없습니다!!");
        return;
      } else {
        this.sendFindIdEmail({ email: this.email, id: this.userid });

        alert("해당 이메일 주소로 이메일이 발송되었습니다!!");
        this.$router.push({ name: "login" });
      }
    },
  },
};
</script>

<style></style>
