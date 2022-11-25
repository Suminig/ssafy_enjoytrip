import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";

// module import
import mainStore from "@/store/modules/mainStore";
import spotStore from "@/store/modules/spotStore";
import planStore from "@/store/modules/planStore";
import boardStore from "@/store/modules/boardStore";
import hotplaceStore from "@/store/modules/hotplaceStore";
import userStore from "@/store/modules/userStore";

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    mainStore,
    spotStore,
    planStore,
    boardStore,
    hotplaceStore,
    userStore,
  },
  plugins: [
    createPersistedState({
      // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
      storage: sessionStorage,
    }),
  ],
});
