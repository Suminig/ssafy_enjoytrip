<template>
  <div class="tab-pane" id="\${tabId}" v-if="themaInfo">
    <div class="row gy-4">
      <div class="col-lg-8 order-2 order-lg-1" data-aos="fade-up" data-aos-delay="100">
        <h3 class="mb-3">
          {{ themaInfo.title }}
        </h3>
        <p class="fst-italic" @click="check()">
          {{ themaInfo.desc ? themaInfo.desc.substring(0, 500) + "..." : "" }}
        </p>
        <ul>
          <li>
            <b-icon icon="house-fill" class="icon"></b-icon>
            {{ themaInfo.address }}
          </li>
          <li>
            <div v-if="themaInfo.tel">
              <b-icon icon="telephone-fill" class="icon"></b-icon>
              {{ themaInfo.tel }}
            </div>
          </li>
        </ul>
      </div>
      <div class="col-lg-4 order-1 order-lg-2 text-center img" data-aos="fade-up" data-aos-delay="200">
        <img style="border-radius: 7px" :src="themaInfo.image" alt="" class="img-fluid" @error="errorImage" />
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "MainThemaItem",
  data() {
    return {};
  },
  computed: {
    ...mapState("mainStore", ["themaInfo"]),
  },
  methods: {
    errorImage(e) {
      e.target.src = require("@/assets/img/logo_color.png");
    },
  },
};
</script>

<style>
.features .tab-content {
  margin-top: 30px;
}
.features .tab-pane {
  -webkit-animation: fadeIn 0.5s ease-out;
  animation: fadeIn 0.5s ease-out;
}
.features .tab-pane h3 {
  font-weight: 600;
  font-size: 36px;
  color: var(--color-secondary);
}
.features .tab-pane ul {
  list-style: none;
  padding: 0;
}
.features .tab-pane ul li {
  padding-bottom: 10px;
}
.features .tab-pane ul .icon {
  font-size: 24px;
  margin-right: 4px;
  color: var(--color-primary);
}
.features .tab-pane p:last-child {
  margin-bottom: 0;
}
</style>
