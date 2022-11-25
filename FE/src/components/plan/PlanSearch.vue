<template>
  <section id="search_spots" class="pb-0 mb-5">
    <div class="container" style="min-height: 700px">
      <div class="section-header">
        <h2>여행 계획 짜기</h2>
        <p>관광지 검색 후 <strong>더블 클릭</strong>해서 여행 계획에 추가해 보세요!</p>
      </div>
      <div class="map_wrap">
        <plan-map ref="map"></plan-map>
        <div id="menu_wrap" class="bg_white">
          <div class="option">
            <div class="d-flex align-items-center">
              <input
                class="form-control pl-2"
                type="text"
                value=""
                id="keyword"
                placeholder="키워드 입력"
                style="line-height: 28px"
                @keyup.enter="searchLocs()"
              />
              <button
                id="keyword_search"
                class="btn btn-default p-1"
                style="width: 20%"
                @click="searchLocs()"
              >
                <b-icon icon="search"></b-icon>
              </button>
            </div>
          </div>
          <hr />
          <ul id="placesList">
            <plan-search-item
              v-for="spot in searchSpots"
              :key="spot.spotid"
              :spot="spot"
              @click.native="selected(spot)"
              @dblclick.native="addSchedule(spot)"
            ></plan-search-item>
          </ul>
        </div>

        <button id="btn-hide-markers" class="btn btn-circle btn-sm" @click="hideMarkers()">
          <b-icon
            icon="eye-slash-fill
"
          ></b-icon>
        </button>
        <button
          id="btn-show-markers"
          class="btn btn-circle btn-sm"
          @click="showMarkers()"
          style="display: none"
        >
          <b-icon icon="eye-fill"></b-icon>
        </button>
      </div>
    </div>
  </section>
</template>

<script>
import { mapState, mapMutations, mapActions } from "vuex";
import PlanMap from "@/components/map/PlanMap";
import PlanSearchItem from "@/components/plan/PlanSearchItem";

export default {
  name: "PlanSearch",
  data() {
    return {};
  },
  components: {
    PlanMap,
    PlanSearchItem,
  },
  computed: {
    ...mapState("planStore", ["searchSpots"]),
  },
  methods: {
    ...mapMutations("planStore", ["CLEAR_SEARCHSPOT_LIST", "CLEAR_SCHEDULE_LIST"]),
    ...mapActions("planStore", ["searchSpot"]),

    searchLocs() {
      this.CLEAR_SEARCHSPOT_LIST();
      var keyword = document.querySelector("#keyword").value;
      this.searchSpot(keyword);
    },
    selected(spot) {
      this.$refs.map.callInfowindow(spot);
    },
    addSchedule(spot) {
      this.$refs.map.addSchedule(spot);
    },
    hideMarkers() {
      document.querySelector("#btn-hide-markers").style.display = "none";
      document.querySelector("#btn-show-markers").style.display = "";
      this.$refs.map.hideMarkers();
    },
    showMarkers() {
      document.querySelector("#btn-hide-markers").style.display = "";
      document.querySelector("#btn-show-markers").style.display = "none";
      this.$refs.map.showMarkers();
    },
    upSchedule(spotid) {
      this.$refs.map.upSchedule(spotid);
    },
    downSchedule(spotid) {
      this.$refs.map.downSchedule(spotid);
    },
    deleteSchedule(spotid) {
      this.$refs.map.deleteSchedule(spotid);
    },
  },
  created() {
    this.CLEAR_SEARCHSPOT_LIST();
    this.CLEAR_SCHEDULE_LIST();
  },
  mounted() {
    window.scrollTo(0, 0);
  },
};
</script>

<style scoped deep>
#map {
  width: 100%;
  height: 100%;
  position: relative;
  overflow: hidden;
}

.map_wrap,
.map_wrap * {
  margin: 0;
  padding: 0;
  font-family: "Malgun Gothic", dotum, "돋움", sans-serif;
  font-size: 12px;
}
.map_wrap a,
.map_wrap a:hover,
.map_wrap a:active {
  color: #000;
  text-decoration: none;
}
.map_wrap {
  position: relative;
  width: 100%;
  height: 600px;
}
.map_wrap > button {
  position: absolute;
  bottom: 10px;
  right: 10px;
  z-index: 1;
  background: var(--color-primary);
  color: white;
}
.map_wrap > button:hover {
  color: var(--color-primary);
  background: white;
}
#menu_wrap {
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  width: 300px;
  margin: 10px 0 30px 10px;
  padding: 5px;
  overflow-y: auto;
  background: rgba(255, 255, 255, 0.7);
  z-index: 1;
  font-size: 12px;
  border-radius: 10px;
}
.bg_white {
  background: #fff;
}
#menu_wrap hr {
  display: block;
  height: 1px;
  border: 0;
  border-top: 2px solid #5f5f5f;
  margin: 3px 0;
}
#menu_wrap .option {
  text-align: center;
}
#menu_wrap .option p {
  margin: 10px 0;
}
#menu_wrap .option button {
  margin-left: 5px;
}
#pagination {
  margin: 10px auto;
  text-align: center;
}
</style>
