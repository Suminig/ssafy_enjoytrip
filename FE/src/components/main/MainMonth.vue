<template>
  <section id="cta" class="cta" v-if="monthInfo">
    <div class="container" data-aos="zoom-out">
      <div class="row">
        <div class="col-lg-8 col-md-6 content d-flex flex-column justify-content-center order-last order-md-first">
          <h3 style="font-size: 38px">EnjoyTrip이 추천하는 이달의 여행지</h3>
          <br />
          <h3>
            <em>{{ monthInfo.title }}</em>
          </h3>
          <p>{{ monthInfo.address }}</p>
          <p>{{ monthInfo.desc }}</p>
          <router-link :to="{ name: 'plan' }" class="cta-btn align-self-start">여행 계획 짜러가기</router-link>
        </div>
        <div class="col-lg-4 col-md-6 order-first order-md-last d-flex align-items-center">
          <div class="img">
            <b-img :src="monthInfo.image" alt="" class="img-fluid" />
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import { mapState, mapMutations, mapActions } from "vuex";

export default {
  name: "MainMonth",
  computed: {
    ...mapState("mainStore", ["monthInfo"]),
  },
  methods: {
    ...mapMutations("mainStore", ["SET_MAINMONTH_INFO"]),
    ...mapActions("mainStore", ["getMonthInfo"]),
  },
  mounted() {
    this.SET_MAINMONTH_INFO();
    this.getMonthInfo();
  },
};
</script>

<style></style>
