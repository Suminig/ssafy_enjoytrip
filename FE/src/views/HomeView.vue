<template>
  <div>
    <main-welcome></main-welcome>
    <main-weather></main-weather>
    <main-nav></main-nav>
    <main-month></main-month>
    <main-thema></main-thema>
    <main-popular></main-popular>
    <hotplace-view></hotplace-view>
  </div>
</template>

<script>
import MainWeather from "@/components/main/MainWeather";
import MainWelcome from "@/components/main/MainWelcome";
import MainNav from "@/components/main/MainNav";
import MainMonth from "@/components/main/MainMonth";
import MainThema from "@/components/main/MainThema";
import MainPopular from "@/components/main/MainPopular";
import HotplaceView from "@/components/main/MainHotplace";
import ChatbotService from "@/api/chatbotService";

export default {
  name: "HomeView",
  components: {
    MainWeather,
    MainWelcome,
    MainNav,
    MainMonth,
    MainThema,
    MainPopular,
    HotplaceView,
  },
  mounted() {
    ChatbotService.boot({
      pluginKey: process.env.VUE_APP_CHATBOT_API_KEY,
    });
  },
};
</script>
<style scoped></style>
