import http from "@/api/http";

const hotplaceStore = {
  namespaced: true,
  state: {
    hotplace: null,
    hotplaces: [],
    recentHotplaces: [],
  },
  getters: {},
  mutations: {
    CLEAR_HOTPLACE(state) {
      state.hotplace = null;
    },
    CLEAR_HOTPLACE_LIST(state) {
      state.hotplaces = [];
    },
    CLEAR_RECENT_HOTPLACE_LIST(state) {
      state.recentHotplaces = [];
    },
    SET_HOTPLACE(state, hotplace) {
      state.hotplace = hotplace;
    },
    SET_HOTPLACE_LIST(state, hotplaces) {
      state.hotplaces = hotplaces;
    },
    SET_RECENT_HOTPLACE_LIST(state, recentHotplaces) {
      state.recentHotplaces = recentHotplaces;
    },
  },
  actions: {
    getHotplace({ commit }, hotplaceid) {
      http
        .get(`hotplace/${hotplaceid}`)
        .then(({ data }) => {
          commit("SET_HOTPLACE", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },

    getHotplaceList({ commit }) {
      http
        .get(`hotplace/getHotplaceList`)
        .then(({ data }) => {
          commit("SET_HOTPLACE_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getRecentHotplaceList({ commit }) {
      http
        .get(`hotplace/getRecentList`)
        .then(({ data }) => {
          commit("SET_RECENT_HOTPLACE_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    async writeHotplace(context, formData) {
      await http
        .post(`hotplace/write`, formData, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        })
        .catch((error) => {
          console.log(error);
        });
    },
    async modifyHotplace(context, param) {
      await http.put(`hotplace/modify`, JSON.stringify(param)).catch((error) => {
        console.log(error);
      });
    },
    async deleteHotplace(context, hotplaceid) {
      await http.delete(`hotplace/${hotplaceid}`).catch((error) => {
        console.log(error);
      });
    },
  },
};

export default hotplaceStore;
