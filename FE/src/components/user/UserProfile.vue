<template>
  <section id="profile" style="pb-0" data-aos="zoom-in-up" v-if="userInfo">
    <div class="container" style="min-height: 650px">
      <div class="section-header">
        <h2>프로필</h2>
      </div>

      <div class="row d-flex justify-content-center align-items-center h-100 mt-5">
        <div class="col-md-12 col-xl-4">
          <div class="card" style="border-radius: 15px">
            <div class="card-body text-center">
              <div class="mt-3 mb-4">
                <img
                  v-if="userInfo.image"
                  :src="'http://localhost/upload/file/' + userInfo.image"
                  class="rounded-circle border shadow-4"
                  style="width: 100px"
                  alt="Avatar"
                />
                <img
                  v-if="!userInfo.image"
                  src="@/assets/img/noavatar.png"
                  class="rounded-circle border shadow-4"
                  style="width: 100px"
                  alt="Avatar"
                />
              </div>
              <h4 class="mb-2">{{ userInfo.name }}</h4>
              <p class="text-muted mb-4">(@{{ userInfo.id }})</p>
              <p class="text-muted mb-4">{{ userInfo.email }}</p>
              <a class="btn btn-default btn-rounded mr-3 mb-4" @click="modifyUser">프로필 수정</a>
              <a class="btn btn-default-danger btn-rounded mb-4" @click="deleteUser">회원 탈퇴</a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import { mapActions, mapState, mapMutations } from "vuex";

export default {
  name: "UserProfile",
  computed: {
    ...mapState("userStore", ["userInfo"]),
  },
  methods: {
    ...mapMutations("userStore", ["SET_IS_LOGIN", "SET_USER_INFO", "SET_IS_VALID_TOKEN"]),
    ...mapActions("userStore", ["userDelete"]),

    modifyUser() {
      this.$router.push({ name: "modify" });
    },

    async deleteUser() {
      if (confirm("회원을 탈퇴하시겠습니까?? \n삭제된 회원 정보는 다시 불러올 수 없습니다!!")) {
        await this.userDelete(this.userInfo.uid);

        alert("회원 정보가 삭제되었습니다!!!");
        this.SET_USER_INFO(null);
        this.SET_IS_LOGIN(false);
        this.SET_IS_VALID_TOKEN(false);
        this.$router.push({ name: "home" });
      }
    },
  },
};
</script>

<style></style>
