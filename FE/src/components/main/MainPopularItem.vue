<template>
  <div>
    <div class="mr-3 mb-5" data-aos="zoom-in" data-aos-delay="200">
      <div class="service-item">
        <div class="img">
          <img :src="findImg(paths)" width="416px" height="280px" @click="moveBoard" />
        </div>
        <div class="details position-relative">
          <a to="/boardDetail" class="stretched-link" @click="moveBoard">
            <h3 style="color: var(--color-secondary)">
              {{ schedule.title }}
            </h3>
          </a>
          <div v-for="(path, num) in paths" :key="path.spotid">
            <div v-if="num == paths.length - 1">
              <p>{{ path.title }}</p>
            </div>
            <div v-else>
              <p>{{ path.title }} ➔</p>
            </div>
          </div>

          <h6 class="mt-2 pe-3 text-end">by. {{ schedule.author }}</h6>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapMutations, mapActions } from "vuex";

export default {
  name: "MainPopularItem",
  data() {
    return {
      paths: [],
    };
  },
  props: {
    schedule: Object,
  },
  computed: {
    ...mapState("mainStore", ["popularDetailInfo"]),
  },
  methods: {
    ...mapActions("boardStore", ["getBoard", "getBoardspotList"]),
    ...mapMutations("mainStore", ["CLEAR_POPULAR_DETAIL_INFO"]),
    ...mapActions("mainStore", ["getPopularDetailInfo"]),

    moveBoard() {
      this.getBoard(this.schedule.boardid);
      this.getBoardspotList(this.schedule.boardid);
      this.$router.push({ name: "boardDetail" });
    },
    findImg() {
      for (var i = 0; i < this.paths.length; i++) {
        if (this.paths[i].image) {
          return this.paths[i].image;
        }
      }
    },
  },
  async mounted() {
    await this.getPopularDetailInfo(this.schedule.boardid);
    this.paths = this.popularDetailInfo;
  },
};
</script>

<style></style>
