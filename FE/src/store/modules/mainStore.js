import http from "@/api/http";

const mainStore = {
  namespaced: true,
  state: {
    monthInfo: null,
    themaInfo: null,
    popularInfo: [],
    popularDetailInfo: [],
  },
  getters: {},
  mutations: {
    CLEAR_MAINTHEME_INFO(state) {
      state.themaInfo = null;
    },
    CLEAR_POPULAR_DETAIL_INFO(state) {
      state.popularDetailInfo = [];
    },
    SET_MAINMONTH_INFO(state, monthInfo) {
      state.monthInfo = monthInfo;
    },
    SET_MAINTHEME_INFO(state, themaInfo) {
      state.themaInfo = themaInfo;
    },
    SET_POPULAR_INFO(state, popularInfo) {
      state.popularInfo = popularInfo;
    },
    SET_POPULAR_DETAIL_INFO(state, popularDetailInfo) {
      state.popularDetailInfo = popularDetailInfo;
    },
  },
  actions: {
    getMonthInfo({ commit }) {
      http
        .get(`http://localhost/spot/detail/25372`)
        .then(({ data }) => {
          commit("SET_MAINMONTH_INFO", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getThemaInfo({ commit }, theme) {
      http
        .get(`http://localhost/spot/top/${theme}`)
        .then(({ data }) => {
          commit("SET_MAINTHEME_INFO", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getPopularInfo({ commit }) {
      http
        .get("http://localhost/board/top")
        .then(({ data }) => {
          commit("SET_POPULAR_INFO", data);
        })
        .catch((e) => {
          console.log(e);
        });
    },
    async getPopularDetailInfo({ commit }, id) {
      await http
        .get(`http://localhost/board/top/${id}`)
        .then(({ data }) => {
          commit("SET_POPULAR_DETAIL_INFO", data);
        })
        .catch((e) => {
          console.log(e);
        });
    },
  },
};

export default mainStore;
