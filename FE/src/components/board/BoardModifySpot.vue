<template>
  <li class="timeline-item mb-4" v-if="boardSpots">
    <span class="timeline-icon"> {{ index + 1 }} </span>
    <h5 class="fw-bold">{{ boardSpots[index].title }}</h5>
    <p class="text-muted mb-2 fw-bold">
      <span class="me-1" style="font-size: smaller">[{{ theme(boardSpots[index].theme) }}]</span
      >{{ boardSpots[index].address }}
    </p>
    <input type="text" class="form-control" name="memo" placeholder="메모..." v-model="boardSpots[index].memo" />
  </li>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "BoardModifySpot",
  props: {
    index: Number,
  },
  computed: {
    ...mapState("boardStore", ["boardSpots"]),
  },
  methods: {
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
  },
};
</script>

<style></style>
