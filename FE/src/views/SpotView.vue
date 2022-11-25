<template>
  <div>
    <spot-search></spot-search>
    <spot-list></spot-list>
  </div>
</template>

<script>
import SpotSearch from "@/components/spot/SpotSearch";
import SpotList from "@/components/spot/SpotList";

export default {
  name: "SpotView",
  components: {
    SpotSearch,
    SpotList,
  },
};
</script>

<style></style>
