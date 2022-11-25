<template>
  <div class="container">
    <a
      class="weatherwidget-io mt-3 mb-3"
      href="https://forecast7.com/en/35d91127d77/south-korea/"
      data-label_1="오늘의 날씨"
      data-icons="Climacons Animated"
      data-theme="pure"
      >오늘의 날씨</a
    >
  </div>
</template>

<script>
export default {
  name: "MainWeather",
  data() {
    return {};
  },
  methods: {
    async callWeather() {
      await (function (d, s, id) {
        var js,
          fjs = d.getElementsByTagName(s)[0];
        if (!d.getElementById(id)) {
          js = d.createElement(s);
          js.id = id;
          js.src = "https://weatherwidget.io/js/widget.min.js";
          fjs.parentNode.insertBefore(js, fjs);
        }
      })(document, "script", "weatherwidget-io-js");
    },
  },
  mounted() {
    this.callWeather();
  },
};
</script>

<style></style>
