<template>
  <section id="features" class="features">
    <div class="section-header">
      <h2>테마별 인기 여행지</h2>
      <p>관광지 뿐만 아니라 먹거리, 놀거리도 확인하시고 여행 계획에 추가해보세요!</p>
    </div>

    <div class="container" data-aos="fade-up">
      <ul class="nav nav-tabs row gy-2 d-flex mb-5">
        <li class="nav-item col-6 col-md-4 col-lg-3 mb-3">
          <div class="nav-link active" id="nav-link-1" v-on:click="toggle(1)">
            <font-awesome-icon class="fontawesom" icon="fa-solid fa-location-dot color-cyan mt-3 mb-3" />
            <h4>관광지</h4>
          </div>
        </li>
        <!-- End Tab 1 Nav -->

        <li class="nav-item col-6 col-md-4 col-lg-3 mb-3">
          <div class="nav-link" id="nav-link-2" v-on:click="toggle(2)">
            <font-awesome-icon class="fontawesom" icon="fa-solid fa-landmark color-indigo mt-3 mb-3" />
            <h4>문화시설</h4>
          </div>
        </li>
        <!-- End Tab 2 Nav -->

        <li class="nav-item col-6 col-md-4 col-lg-3 mb-3">
          <div class="nav-link" id="nav-link-3" v-on:click="toggle(3)">
            <font-awesome-icon class="fontawesom" icon="fa-solid fa-calendar color-teal mt-3 mb-3" />
            <h4>행사 / 공연 / 축제</h4>
          </div>
        </li>
        <!-- End Tab 3 Nav -->

        <li class="nav-item col-6 col-md-4 col-lg-3 mb-3">
          <div class="nav-link" id="nav-link-4" v-on:click="toggle(4)">
            <font-awesome-icon class="fontawesom" icon="fa-solid fa-person-running color-blue mt-3 mb-3" />
            <h4>레포츠</h4>
          </div>
        </li>
        <!-- End Tab 4 Nav -->

        <li class="nav-item col-6 col-md-4 col-lg-3 mb-3">
          <div class="nav-link" id="nav-link-5" v-on:click="toggle(5)">
            <font-awesome-icon class="fontawesom" icon="fa-solid fa-bed color-orange mt-3 mb-3" />
            <h4>숙박</h4>
          </div>
        </li>
        <!-- End Tab 5 Nav -->

        <li class="nav-item col-6 col-md-4 col-lg-3 mb-3">
          <div class="nav-link" id="nav-link-6" v-on:click="toggle(6)">
            <font-awesome-icon class="fontawesom" icon="fa-solid fa-cart-shopping color-yellow mt-3 mb-3" />
            <h4>쇼핑</h4>
          </div>
        </li>
        <!-- End Tab 6 Nav -->

        <li class="nav-item col-6 col-md-4 col-lg-3 mb-3">
          <div class="nav-link" id="nav-link-7" v-on:click="toggle(7)">
            <font-awesome-icon class="fontawesom" icon="fa-solid fa-utensils color-pink mt-3 mb-3" />
            <h4>음식점</h4>
          </div>
        </li>
        <!-- End Tab 7 Nav -->
      </ul>

      <div class="tab-content">
        <div v-if="tab">
          <main-thema-item></main-thema-item>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import MainThemaItem from "./MainThemaItem";
import { mapState, mapActions, mapMutations } from "vuex";

export default {
  name: "MainThema",
  components: {
    MainThemaItem,
  },
  data() {
    return {
      tab: "1",
    };
  },
  computed: {
    ...mapState("mainStore", ["themaInfo"]),
  },
  methods: {
    ...mapMutations("mainStore", ["CLEAR_MAINTHEME_INFO"]),
    ...mapActions("mainStore", ["getThemaInfo"]),

    toggle(num) {
      let theme = ["", "12", "14", "15", "28", "32", "38", "39"];
      this.tab = num;

      document.querySelector(".active").classList.remove("active");
      document.querySelector(`#nav-link-${num}`).classList.add("active");

      this.CLEAR_MAINTHEME_INFO();
      this.getThemaInfo(theme[num]);
    },
  },
  mounted() {
    this.getThemaInfo("12");
  },
};
</script>

<style>
.features .nav-tabs {
  border: 0;
}
.features .nav-link {
  border: 0;
  padding: 25px 20px;
  color: var(--color-secondary);
  box-shadow: 5px 5px 25px rgba(var(--color-secondary-rgb), 0.15);
  border-radius: 0;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  transition: 0s;
  cursor: pointer;
  height: 100%;
}

.features .nav-link .fontawesom {
  font-size: 32px;
  line-height: 0;
}
.features .nav-link h4 {
  font-size: 20px;
  font-weight: 600;
  margin: 10px 0 0 0;
  color: var(--color-secondary);
}
.features .nav-link:hover {
  color: var(--color-primary);
}
.features .nav-link.active {
  transition: 0.3s;
  background: var(--color-secondary)
    linear-gradient(rgba(var(--color-primary-rgb), 0.95), rgba(var(--color-primary-rgb), 0.6));
  border-color: var(--color-primary);
}
.features .nav-link.active h4 {
  color: var(--color-white);
}
.features .nav-link.active .fontawesom {
  color: var(--color-white) !important;
}
@keyframes fadeIn {
  0% {
    opacity: 0;
  }
  100% {
    opacity: 1;
  }
}
</style>
