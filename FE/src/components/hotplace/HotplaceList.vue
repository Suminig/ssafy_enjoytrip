<template>
  <section id="portfolio" class="portfolio" data-aos="fade-up" style="pb-0">
    <div class="container-fluid" style="min-height: 750px">
      <div class="section-header">
        <h2>핫 플레이스</h2>
        <p>나만의 핫 플레이스를 공유하고 다른 사람의 핫 플레이스를 여행해보세요!</p>
      </div>

      <div class="row" style="margin-bottom: 50px">
        <a
          class="btn-default"
          style="position: absolute; right: 18px"
          v-if="userInfo"
          @click="shareHotplace"
          >핫 플레이스 공유하기</a
        >
      </div>
      <div class="container-fluid mt-3" data-aos="fade-up" data-aos-delay="200">
        <div class="row g-0 portfolio-container">
          <hotplace-list-item
            v-for="hotplace in hotplaces"
            :key="hotplace.hotplaceid"
            :hotplace="hotplace"
          ></hotplace-list-item>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import { mapState, mapActions } from "vuex";
import HotplaceListItem from "@/components/hotplace/HotplaceListItem";

export default {
  name: "HotplaceList",
  components: {
    HotplaceListItem,
  },
  computed: {
    ...mapState("userStore", ["userInfo"]),
    ...mapState("hotplaceStore", ["hotplaces"]),
  },
  methods: {
    ...mapActions("hotplaceStore", ["getHotplaceList"]),

    shareHotplace() {
      this.$router.push({ name: "hotplaceShare" });
    },
  },
  mounted() {
    this.getHotplaceList();
  },
};
</script>

<style scoped></style>
