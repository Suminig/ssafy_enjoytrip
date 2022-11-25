<template>
  <section id="spot_detail" data-aos="zoom-in-up">
    <div v-if="spot" class="container" style="min-height: 750px">
      <div class="section-header">
        <h2 id="spot_title">{{ spot.title }}</h2>
      </div>

      <div class="row" id="spot_detail_info">
        <div class="col-4 d-flex">
          <b-img
            v-if="spot.image"
            :src="spot.image"
            fluid
            :alt="spot.title"
            style="max-width: 100%; max-height: 100%; margin: auto"
          />
          <b-img
            v-else
            :src="require('@/assets/img/logo_color.png')"
            fluid
            :alt="spot.title"
            style="max-width: 100%; max-height: 100%; margin: auto"
          />
        </div>
        <div class="mb-4 col-8 d-flex flex-wrap align-items-center ps-3" id="info">
          <div class="mt-2">
            <p class="text-start" style="color: black">
              <b-icon icon="telephone-fill"></b-icon> <b>Tel :</b> {{ spot.tel ? spot.tel : "-" }}<br />
              <b-icon icon="pin-map-fill"></b-icon> <b>Address :</b> {{ spot.address }}<br />
              <b-icon icon="lightbulb-fill"></b-icon> <b>Details</b><br />{{ spot.desc }}
            </p>
          </div>
        </div>
      </div>
      <detail-map :spot="spot"></detail-map>
    </div>
  </section>
</template>

<script>
import { mapState } from "vuex";
import DetailMap from "@/components/map/DetailMap";

export default {
  name: "SpotDetail",
  components: {
    DetailMap,
  },
  data() {
    return {};
  },
  computed: {
    ...mapState("spotStore", ["spot"]),
  },
};
</script>

<style scoped>
#map {
  margin-top: 50px;
  height: 400px;
  width: 100%;
}

.weather-widget {
  margin-bottom: 20px;
}
</style>
