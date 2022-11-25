<template>
  <div id="map"></div>
</template>

<script>
export default {
  name: "KakaoMap",
  data() {
    return {
      map: null,
      marker: null,
    };
  },
  props: {
    spot: Object,
  },
  methods: {
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(this.spot ? this.spot.lat : 37.5559, this.spot ? this.spot.lang : 126.9723),
        level: 3,
      };

      this.map = new kakao.maps.Map(container, options);

      const markerPosition = new kakao.maps.LatLng(this.spot.lat, this.spot.lang);
      this.marker = new kakao.maps.Marker({ map: this.map, position: markerPosition });
    },
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const mapScript = document.createElement("script");
      /* global kakao */
      mapScript.onload = () => kakao.maps.load(this.initMap);
      mapScript.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${process.env.VUE_APP_KAKAO_MAP_API_KEY}`;
      document.head.appendChild(mapScript);
    }
  },
};
</script>

<style></style>
