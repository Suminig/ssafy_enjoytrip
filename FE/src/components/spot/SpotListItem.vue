<template>
  <div class="col-lg-3 mb-5 portfolio-item" :class="category(spotItem.theme)">
    <div class="post-box">
      <div class="post-img text-center" style="width: 250px; height: 200px; margin: auto">
        <b-img
          v-if="spotItem.image"
          :src="spotItem.image"
          fluid
          :alt="spotItem.title"
          style="max-width: 100%; max-height: 100%; margin: auto"
        />
        <b-img
          v-else
          :src="require('@/assets/img/logo_color.png')"
          fluid
          :alt="spotItem.title"
          style="max-width: 100%; max-height: 100%; margin: auto"
        />
      </div>
      <h3 class="post-title">{{ spotItem.title }}</h3>
      <p>{{ spotItem.address }}</p>
      <a class="readmore stretched-link" @click="spotDetail">자세히 보기 <b-icon icon="arrow-right"></b-icon> </a>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";

export default {
  name: "SpotListItem",
  props: {
    spotItem: Object,
  },
  methods: {
    ...mapActions("spotStore", ["getSpotDetail", "getSpotWid"]),
    ...mapMutations("spotStore", ["SET_DETAIL_SPOT", "CLEAR_DETAIL_SPOT", "CLEAR_SPOT_WID"]),

    spotDetail() {
      this.CLEAR_DETAIL_SPOT();
      this.CLEAR_SPOT_WID();
      if (this.spotItem) {
        this.getSpotDetail(this.spotItem.spotid);
        this.getSpotWid({
          lat: this.spotItem.lat,
          lang: this.spotItem.lang,
          key: process.env.VUE_APP_OPEN_WEATHER_API_KEY,
        });
      }
      this.$router.push({ name: "spotDetail" }).catch(() => {});
    },
    category(theme) {
      let categories = {
        12: "hotspot",
        14: "culture",
        15: "festival",
        25: "course",
        28: "sports",
        32: "hotel",
        38: "shopping",
        39: "food",
      };
      return "filter-" + categories[theme];
    },
  },
  computed: {
    ...mapState("spotStore", ["spot"]),
  },
};
</script>

<style></style>
