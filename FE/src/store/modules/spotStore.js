import http from "@/api/http";

const spotStore = {
  namespaced: true,
  state: {
    sidos: [{ value: null, text: "선택하세요" }],
    guguns: [{ value: null, text: "선택하세요" }],
    spots: [],
    filters: [],
    spot: null,
    spotWid: null,
  },
  getters: {},
  mutations: {
    SET_SIDO_LIST(state, sidos) {
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido.sidoid, text: sido.name });
      });
    },
    SET_GUGUN_LIST(state, guguns) {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun.gugunid, text: gugun.name });
      });
    },
    CLEAR_SIDO_LIST(state) {
      state.sidos = [{ value: null, text: "시도선택" }];
    },
    CLEAR_GUGUN_LIST(state) {
      state.guguns = [{ value: null, text: "구군선택" }];
    },
    CLEAR_SPOT_LIST(state) {
      state.spots = [];
      state.spot = null;
    },
    CLEAR_DETAIL_SPOT(state) {
      state.spot = null;
    },
    CLEAR_SPOT_WID(state) {
      state.spotWid = null;
    },
    CLEAR_FILTER_LIST(state) {
      state.filters = [];
      state.spot = null;
    },
    SET_SPOT_LIST(state, spots) {
      state.spots = spots;
      state.filters = spots;
    },
    SET_DETAIL_SPOT(state, spot) {
      state.spot = spot;
    },
    SET_SPOT_WID(state, spotWid) {
      state.spotWid = spotWid;
    },
    FILTER_SPOTS_THEME(state, theme) {
      state.filters = [];
      if (theme == "0") state.filters = state.spots;
      else {
        state.spots.forEach((spot) => {
          if (spot.theme == theme) {
            state.filters.push(spot);
          }
        });
      }
    },
  },
  actions: {
    getSido({ commit }) {
      http
        .get(`http://localhost/spot/sido`)
        .then(({ data }) => {
          commit("SET_SIDO_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getGugun({ commit }, sidoCode) {
      http
        .get(`http://localhost/spot/${sidoCode}`)
        .then(({ data }) => {
          commit("SET_GUGUN_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getSpotList({ commit }, param) {
      http
        .get(`http://localhost/spot/${param.sidoCode}/${param.gugunCode}`)
        .then(({ data }) => {
          commit("SET_SPOT_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    async getSpotDetail({ commit }, spotid) {
      await http
        .get(`http://localhost/spot/detail/${spotid}`)
        .then(({ data }) => {
          commit("SET_DETAIL_SPOT", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getSpotWid({ commit }, param) {
      http
        .get(
          `https://api.openweathermap.org/data/2.5/weather?lat=${param.lat}&lon=${param.lang}&appid=${param.key}`
        )
        .then(({ data }) => {
          commit("SET_SPOT_WID", data.id);
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};

export default spotStore;
