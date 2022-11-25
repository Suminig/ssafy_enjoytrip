<template>
  <div id="map"></div>
</template>

<script>
import { mapState, mapMutations } from "vuex";

export default {
  name: "KakaoMap",
  data() {
    return {
      map: null,
      clusterer: null,
      polyline: null,
      infowindow: null,
      bounds: null,
      markers: [],
      linePath: [],
      sMarkers: [],
      sSchedules: [],
    };
  },
  computed: {
    ...mapState("planStore", ["searchSpots", "schedules"]),
  },
  methods: {
    ...mapMutations("planStore", ["SET_SCHEDULE_LIST", "CLEAR_SCHEDULE_LIST"]),

    initMap() {
      var vm = this;
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(37.5559, 126.9723),
        level: 7,
      };
      this.map = new kakao.maps.Map(container, options);

      // HTML5의 geolocation으로 사용할 수 있는지 확인합니다
      if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {
          var lat = position.coords.latitude,
            lon = position.coords.longitude;
          var locPosition = new kakao.maps.LatLng(lat, lon);
          vm.map.setCenter(locPosition);
          vm.map.setLevel(7);
        });
      }

      this.clusterer = new kakao.maps.MarkerClusterer({
        map: vm.map, // 마커들을 클러스터로 관리하고 표시할 지도 객체
        averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정
        minLevel: 7, // 클러스터 할 최소 지도 레벨
      });

      this.polyline = new kakao.maps.Polyline({
        path: vm.linePath, // 선을 구성하는 좌표배열 입니다
        strokeWeight: 5, // 선의 두께 입니다
        strokeColor: "#FF0000", // 선의 색깔입니다
        strokeOpacity: 0.7, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
        strokeStyle: "solid", // 선의 스타일입니다
      });

      this.infowindow = new kakao.maps.InfoWindow({ zIndex: 100 });
      this.bounds = new kakao.maps.LatLngBounds();
    },
    addMarker(position) {
      var marker = new kakao.maps.Marker({
        position: position, // 마커의 위치
      });

      this.markers.push(marker); // 배열에 생성된 마커를 추가합니다

      return marker;
    },
    removeMarker() {
      for (var i = 0; i < this.markers.length; i++) {
        this.markers[i].setMap(null);
      }
      this.markers = [];
    },
    displayInfowindow(marker, spot) {
      var content = `<div class="overlay_info">
						<a><strong>${spot.title}</strong></a>
							<div class="desc">`;
      if (spot.image) content += `<img src="${spot.image}" alt="${spot.title}" width = "50px" height = "50px"/>`;
      else
        content += ` <img src="http://www.billking.co.kr/index/skin/board/basic_support/img/noimage.gif" alt="${spot.title}" width = "50px" height = "50px"/>`;
      content += `<span class="address">${spot.address}</span></div>
						</div>`;

      this.infowindow.setContent(content);
      this.infowindow.open(this.map, marker);
    },
    callInfowindow(spot) {
      var marker = new kakao.maps.Marker({
        position: new kakao.maps.LatLng(spot.lat, spot.lang), // 마커의 위치
      });

      var content = `<div class="overlay_info">
						<a><strong>${spot.title}</strong></a>
							<div class="desc">`;
      if (spot.image) content += `<img src="${spot.image}" alt="${spot.title}" width = "50px" height = "50px"/>`;
      else
        content += ` <img src="http://www.billking.co.kr/index/skin/board/basic_support/img/noimage.gif" alt="${spot.title}" width = "50px" height = "50px"/>`;
      content += `<span class="address">${spot.address}</span></div>
						</div>`;

      this.map.setCenter(new kakao.maps.LatLng(spot.lat, spot.lang));
      this.map.setLevel(6);
      this.infowindow.setContent(content);
      this.infowindow.open(this.map, marker);
    },
    closeOverlay() {
      this.infowindow.setMap(null);
    },
    addSchedule(spot) {
      let flag = true;
      for (let i = 0; i < this.sSchedules.length; i++) {
        if (this.sSchedules[i].spotid == spot.spotid) {
          flag = false;
        }
      }

      if (flag) {
        let schedule = {
          spotid: spot.spotid,
          title: spot.title,
          address: spot.address,
          image: spot.image,
          mapx: spot.lat,
          mapy: spot.lang,
          theme: spot.theme,
        };

        this.sSchedules.push(schedule);
        this.SET_SCHEDULE_LIST(this.sSchedules);
        this.makeMarker();
        this.makeLine();
      } else {
        alert("이미 추가한 장소입니다.");
      }
    },
    makeMarker() {
      let vm = this;
      this.sSchedules.forEach((schedule) => {
        var latlang = new kakao.maps.LatLng(schedule.mapx, schedule.mapy);
        var marker = new kakao.maps.Marker({
          position: latlang,
          image: new kakao.maps.MarkerImage(
            "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png",
            new kakao.maps.Size(29, 42),
          ),
        });
        marker.setMap(this.map);
        marker.setZIndex(50);
        kakao.maps.event.addListener(marker, "mouseover", function () {
          vm.displayInfowindow(marker, schedule);
        });
        this.sMarkers.push(marker);
      });
    },
    makeLine() {
      this.polyline.setMap(null);
      this.linePath = [];
      this.sSchedules.forEach((schedule) => {
        this.linePath.push(new kakao.maps.LatLng(schedule.mapx, schedule.mapy));
      });
      this.polyline = new kakao.maps.Polyline({
        path: this.linePath, // 선을 구성하는 좌표배열 입니다
        strokeWeight: 5, // 선의 두께 입니다
        strokeColor: "#0EA2BD", // 선의 색깔입니다
        strokeOpacity: 1, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
        strokeStyle: "solid", // 선의 스타일입니다
      });
      this.polyline.setMap(this.map);
    },
    hideMarkers() {
      this.infowindow.close();
      this.clusterer.clear();
    },

    showMarkers() {
      this.clusterer.addMarkers(this.markers);
    },
    resetMarkers() {
      for (var i = 0; i < this.sMarkers.length; i++) {
        this.sMarkers[i].setMap(null);
      }
      this.sMarkers = [];
    },
    upSchedule(spotid) {
      for (var i = 1; i < this.sSchedules.length; i++) {
        if (this.sSchedules[i].spotid == spotid) {
          let temp = this.sSchedules[i];
          this.sSchedules[i] = this.sSchedules[i - 1];
          this.sSchedules[i - 1] = temp;
          break;
        }
      }

      this.CLEAR_SCHEDULE_LIST();
      this.SET_SCHEDULE_LIST(this.sSchedules);
      this.makeLine();
    },
    downSchedule(spotid) {
      for (var i = 0; i < this.sSchedules.length - 1; i++) {
        if (this.sSchedules[i].spotid == spotid) {
          let temp = this.sSchedules[i];
          this.sSchedules[i] = this.sSchedules[i + 1];
          this.sSchedules[i + 1] = temp;
          break;
        }
      }

      this.CLEAR_SCHEDULE_LIST();
      this.SET_SCHEDULE_LIST(this.sSchedules);
      this.makeLine();
    },
    deleteSchedule(spotid) {
      this.polyline.setMap(null);
      for (var i = 0; i < this.sSchedules.length; i++) {
        if (this.sSchedules[i].spotid == spotid) {
          this.sSchedules.splice(i, 1);
          i--;
        }
      }

      this.CLEAR_SCHEDULE_LIST();
      this.SET_SCHEDULE_LIST(this.sSchedules);
      this.resetMarkers();
      this.makeMarker();
      this.makeLine();
    },
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const mapScript = document.createElement("script");
      /* global kakao */
      mapScript.onload = () => kakao.maps.load(this.initMap);
      mapScript.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${process.env.VUE_APP_KAKAO_MAP_API_KEY}&libraries=clusterer,drawing`;
      document.head.appendChild(mapScript);
    }
  },
  watch: {
    searchSpots() {
      var vm = this;
      if (!this.clusterer) this.clusterer.clear();
      this.closeOverlay();
      this.removeMarker();

      this.searchSpots.forEach((spot) => {
        var placePosition = new kakao.maps.LatLng(spot.lat, spot.lang),
          marker = this.addMarker(placePosition);
        this.bounds.extend(placePosition);

        ((marker, spot) => {
          kakao.maps.event.addListener(marker, "mouseover", function () {
            vm.displayInfowindow(marker, spot);
          });

          kakao.maps.event.addListener(marker, "click", function () {
            vm.addSchedule(spot);
          });

          kakao.maps.event.addListener(marker, "mouseout", function () {
            vm.infowindow.close();
          });
          kakao.maps.event.addListener(this.map, "mousedown", function () {
            vm.infowindow.close();
          });
        })(marker, spot);
      });

      this.clusterer.addMarkers(this.markers);
      this.map.setBounds(this.bounds);
    },
  },
};
</script>

<style>
.overlay_info {
  border-radius: 6px;
  padding-bottom: 20px;
  float: left;
  position: relative;
  border: 1px solid #ccc;
  border-bottom: 2px solid #ddd;
  background-color: #fff;
}
.overlay_info:nth-of-type(n) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.overlay_info a {
  display: block;
  background: var(--color-primary);
  text-decoration: none;
  color: #fff;
  padding: 12px 36px 12px 14px;
  font-size: 14px;
  border-radius: 6px 6px 0 0;
}
.overlay_info .desc {
  padding: 14px;
  position: relative;
  min-width: 200px;
  height: 56px;
}
.overlay_info img {
  vertical-align: top;
}
.overlay_info .address {
  font-size: 12px;
  color: #333;
  position: absolute;
  left: 80px;
  right: 14px;
  top: 20px;
  white-space: normal;
}
.overlay_info .close {
  position: absolute;
  top: 14px;
  right: 10px;
  color: white;
  width: 17px;
  height: 17px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/overlay_close.png");
}
.overlay_info .close:hover {
  cursor: pointer;
}

.btn-circle {
  width: 40px;
  height: 40px;
  text-align: center;
  padding: 6px 0px;
  font-size: 12px;
  line-height: 1.428571429;
  border-radius: 20px;
}
.btn-circle.btn-sm {
  width: 30px;
  height: 30px;
  text-align: center;
  padding: 0px 0px;
  font-size: 12px;
  line-height: 1.428571429;
  border-radius: 15px;
}
.btn-circle.btn-lg {
  width: 50px;
  height: 50px;
  padding: 10px 16px;
  font-size: 18px;
  line-height: 1.33;
  border-radius: 25px;
}
.btn-circle.btn-xl {
  width: 70px;
  height: 70px;
  padding: 10px 16px;
  font-size: 24px;
  line-height: 1.33;
  border-radius: 35px;
}
</style>
