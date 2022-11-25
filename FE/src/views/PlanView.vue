<template>
  <div data-aos="zoom-in-up">
    <plan-search ref="search"></plan-search>
    <plan-write
      v-on:upSchedule="upSchedule"
      v-on:downSchedule="downSchedule"
      v-on:deleteSchedule="deleteSchedule"
    ></plan-write>
  </div>
</template>

<script>
import PlanSearch from "@/components/plan/PlanSearch";
import PlanWrite from "@/components/plan/PlanWrite";

export default {
  name: "PlanView",
  components: {
    PlanSearch,
    PlanWrite,
  },
  methods: {
    upSchedule(spotid) {
      this.$refs.search.upSchedule(spotid);
    },
    downSchedule(spotid) {
      this.$refs.search.downSchedule(spotid);
    },
    deleteSchedule(spotid) {
      this.$refs.search.deleteSchedule(spotid);
    },
  },
};
</script>

<style></style>
