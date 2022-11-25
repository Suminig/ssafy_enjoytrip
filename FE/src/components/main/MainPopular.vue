<template>
  <section id="services" class="services" v-if="popularInfo">
    <div class="container" data-aos="fade-up">
      <div class="section-header">
        <h2>인기 여행 계획</h2>
        <p>지금 다른 사람들은 이렇게 여행을 떠나요, 같이 떠나 보실까요?</p>
      </div>
      <div class="row">
        <main-popular-item
          v-for="schedule in popularInfo"
          :key="schedule.boardid"
          :schedule="schedule"
        ></main-popular-item>
      </div>
    </div>
  </section>
</template>

<script>
import { mapState, mapActions } from "vuex";
import MainPopularItem from "@/components/main/MainPopularItem";

export default {
  name: "MainPopular",
  components: { MainPopularItem },
  data() {
    return {};
  },
  computed: {
    ...mapState("mainStore", ["popularInfo"]),
  },
  methods: {
    ...mapActions("mainStore", ["getPopularInfo"]),
  },
  mounted() {
    this.getPopularInfo();
  },
};
</script>

<style></style>
