import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";

Vue.config.productionTip = false;

// API
import "@/api/BootstrapVue.js";
import "@/api/fontawesomeVue.js";
import "@/api/aos.js";
import "@/api/glightbox.js";

require("@/assets/styles/main.css");
require("@/assets/styles/variables.css");

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
