<template>
  <div id="app">
    <nav-bar></nav-bar>
    <router-view></router-view>
    <the-footer></the-footer>
  </div>
</template>
w
<script>
import NavBar from "@/components/layout/NavBar";
import TheFooter from "@/components/layout/TheFooter.vue";

export default {
  name: "App",
  components: {
    NavBar,
    TheFooter,
  },
};
</script>

<style></style>
