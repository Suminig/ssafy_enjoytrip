<template>
  <section id="spot_search" class="pb-0" data-aos="zoom-in-up">
    <div class="container">
      <div class="section-header">
        <h2>관광지 검색</h2>
        <p>지역별 관광지 정보를 확인하세요</p>
      </div>

      <div class="row col-md-12 justify-content-center mb-2">
        <div class="form-group col-md-2">
          <b-form-select
            class="bg-secondary text-light"
            v-model="sidoCode"
            :options="sidos"
            @change="gugunList"
          >
          </b-form-select>
        </div>
        <div class="form-group col-md-2">
          <b-form-select
            class="bg-secondary text-light"
            v-model="gugunCode"
            :options="guguns"
          ></b-form-select>
        </div>
        <div class="form-group col-md-2">
          <button type="button" id="list-btn" class="btn btn-default" @click="searchSpot">
            조회하기
          </button>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";

export default {
  name: "SpotList",
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
    };
  },
  computed: {
    ...mapState("spotStore", ["sidos", "guguns", "spots"]),
  },
  created() {
    this.CLEAR_SIDO_LIST();
    this.CLEAR_GUGUN_LIST();
    this.CLEAR_SPOT_LIST();
    this.getSido();
  },
  methods: {
    ...mapActions("spotStore", ["getSido", "getGugun", "getSpotList"]),
    ...mapMutations("spotStore", [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_SPOT_LIST",
      "CLEAR_FILTER_LIST",
    ]),
    gugunList() {
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    searchSpot() {
      this.CLEAR_SPOT_LIST();
      this.CLEAR_FILTER_LIST();
      if (this.sidoCode && this.gugunCode)
        this.getSpotList({ sidoCode: this.sidoCode, gugunCode: this.gugunCode });
    },
  },
};
</script>

<style></style>
