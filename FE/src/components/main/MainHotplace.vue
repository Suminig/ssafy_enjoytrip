<template>
  <section id="portfolio" class="portfolio" data-aos="fade-up">
    <div class="container">
      <div class="section-header">
        <h2>핫 플레이스</h2>
        <p>지금 인기 있는 핫 플레이스를 사용자가 직접 남긴 사진으로 확인해보세요!</p>
      </div>
    </div>

    <div class="container-fluid" data-aos="fade-up" data-aos-delay="200">
      <div class="row g-0 portfolio-container">
        <hotplace-list-item
          v-for="hotplace in recentHotplaces"
          :key="hotplace.hotplaceid"
          :hotplace="hotplace"
        ></hotplace-list-item>
      </div>
    </div>
  </section>
</template>

<script>
import HotplaceListItem from "@/components/hotplace/HotplaceListItem";
import { mapState, mapMutations, mapActions } from "vuex";

export default {
  name: "MainHotplace",
  components: {
    HotplaceListItem,
  },
  computed: {
    ...mapState("hotplaceStore", ["recentHotplaces"]),
  },
  methods: {
    ...mapMutations("hotplaceStore", ["CLEAR_RECENT_HOTPLACE_LIST"]),
    ...mapActions("hotplaceStore", ["getRecentHotplaceList"]),
  },
  mounted() {
    this.CLEAR_RECENT_HOTPLACE_LIST();
    this.getRecentHotplaceList();
  },
};
</script>

<style></style>
