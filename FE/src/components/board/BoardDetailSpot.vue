<template>
  <li class="timeline-item mb-4" v-if="spot" @click="showDetail">
    <span class="timeline-icon">{{ index }}</span>
    <h5 class="fw-bold">{{ spot.title }}</h5>
    <p class="text-muted mb-2 fw-bold">
      <span class="pe-2">[{{ theme(spot.theme) }}]</span>{{ spot.address }}
    </p>
    <div class="text-secondary">{{ spot.memo }}</div>
  </li>
</template>

<script>
import { mapActions, mapMutations } from "vuex";

export default {
  name: "BoardDetailSpot",
  props: {
    index: Number,
    spot: Object,
  },
  methods: {
    ...mapMutations("spotStore", ["CLEAR_DETAIL_SPOT"]),
    ...mapActions("spotStore", ["getSpotDetail"]),

    theme(tCode) {
      var themes = {
        12: "관광지",
        14: "문화시설",
        15: "행사/공연/축제",
        28: "레포츠",
        32: "숙박",
        38: "쇼핑",
        39: "음식점",
      };
      return themes[tCode];
    },

    showDetail() {
      this.CLEAR_DETAIL_SPOT();
      this.getSpotDetail(this.spot.spotid);

      this.$router.push({ name: "spotDetail" });
    },
  },
};
</script>

<style scoped>
li :hover {
  cursor: pointer;
}
</style>
