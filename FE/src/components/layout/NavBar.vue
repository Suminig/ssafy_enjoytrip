<template>
  <b-navbar id="header" class="header fixed-top" data-scrollto-offset="0">
    <div class="container d-flex align-items-center justify-content-between">
      <a href="/" class="logo d-flex align-items-center scrollto me-auto me-lg-0">
        <h1>Enjoy<span>Trip</span></h1>
      </a>

      <nav id="navbar" class="navbar">
        <ul>
          <li>
            <router-link to="/">홈</router-link>
          </li>
          <li>
            <router-link to="/spot">관광지 검색</router-link>
          </li>
          <li>
            <router-link to="/plan">여행계획 짜기</router-link>
          </li>
          <li>
            <router-link to="/board">여행정보 공유</router-link>
          </li>
          <li>
            <router-link to="/hotplace/list">핫 플레이스</router-link>
          </li>
          <li v-if="userInfo">
            <router-link to="/user/profile">내 프로필</router-link>
          </li>
        </ul>
        <b-icon icon="list" class="mobile-nav-toggle d-none"></b-icon>
      </nav>

      <a class="btn-getstarted scrollto" @click="logout" id="btn-logout" v-if="userInfo"
        >로그아웃</a
      >
      <router-link to="/user/login" class="btn-getstarted scrollto" v-if="!userInfo"
        >로그인</router-link
      >
    </div>
  </b-navbar>
</template>

<script>
import { mapState, mapActions } from "vuex";

export default {
  name: "NavBar",
  computed: {
    ...mapState("userStore", ["isLogin", "userInfo"]),
  },
  methods: {
    ...mapActions("userStore", ["userLogout"]),
    logout() {
      this.userLogout(this.userInfo.id);
      sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
      sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
      if (this.$route.path != "/") this.$router.push({ name: "home" });
    },
  },
};
</script>

<style scoped>
a:hover {
  cursor: pointer;
}
</style>
