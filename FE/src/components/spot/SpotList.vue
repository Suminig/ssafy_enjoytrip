<template>
  <section id="recent-blog-posts" class="recent-blog-posts portfolio pt-3 pb-5" style="min-height: 500px">
    <div
      class="container portfolio-isotope"
      data-portfolio-filter="*"
      data-portfolio-layout="fitRows"
      data-portfolio-sort="original-order"
      data-aos="fade-up"
      v-if="spots && spots.length != 0"
    >
      <ul class="portfolio-flters mb-5">
        <li data-filter="*" class="filter-active" id="filter-0" @click="filter('0')">전체</li>
        <li data-filter=".filter-hotspot" id="filter-12" @click="filter('12')">관광지</li>
        <li data-filter=".filter-culture" id="filter-14" @click="filter('14')">문화시설</li>
        <li data-filter=".filter-festival" id="filter-15" @click="filter('15')">행사</li>
        <li data-filter=".filter-course" id="filter-25" @click="filter('25')">여행코스</li>
        <li data-filter=".filter-sports" id="filter-28" @click="filter('28')">레포츠</li>
        <li data-filter=".filter-hotel" id="filter-32" @click="filter('32')">숙박</li>
        <li data-filter=".filter-shopping" id="filter-38" @click="filter('38')">쇼핑</li>
        <li data-filter=".filter-food" id="filter-39" @click="filter('39')">음식점</li>
      </ul>
      <div id="spot_list" class="row portfolio-container" style="margin-top: 50px">
        <spot-list-item
          ref="cpt"
          v-for="spot in filters"
          :key="spot.spotid"
          :spotItem="spot"
          data-aos="zoom-in-up"
        ></spot-list-item>
      </div>
    </div>
  </section>
</template>

<script>
import SpotListItem from "@/components/spot/SpotListItem";
import { mapState, mapMutations } from "vuex";

export default {
  name: "SpotList",
  data() {
    return {};
  },
  methods: {
    ...mapMutations("spotStore", ["FILTER_SPOTS_THEME"]),

    filter(theme) {
      document.querySelector(".portfolio-isotope .portfolio-flters .filter-active").classList.remove("filter-active");
      document.querySelector(`#filter-${theme}`).classList.add("filter-active");

      this.FILTER_SPOTS_THEME(theme);
    },
  },
  components: {
    SpotListItem,
  },
  computed: {
    ...mapState("spotStore", ["spots", "filters"]),
  },
};
</script>

<style></style>
