<template>
  <li class="item">
    <div>
      <div class="info">
        <span style="display: table-cell; vertical-align: middle">
          <button
            v-if="spot.theme == 12"
            type="button"
            class="btn btn-danger btn-circle btn-sm mr-2"
            data-bs-toggle="tooltip"
            title="관광지"
          >
            <font-awesome-icon class="fontawesom" icon="fa-solid fa-location-dot fa-lg" /></button
          ><button
            v-else-if="spot.theme == 14"
            type="button"
            class="btn btn-warning btn-circle btn-sm mr-2"
            data-bs-toggle="tooltip"
            title="문화시설"
          >
            <font-awesome-icon class="fontawesom" icon="fa-solid fa-landmark fa-lg" /></button
          ><button
            v-else-if="spot.theme == 15"
            type="button"
            class="btn btn-success btn-circle btn-sm mr-2"
            data-bs-toggle="tooltip"
            title="행사/공연/축제"
          >
            <font-awesome-icon class="fontawesom" icon="fa-solid fa-calendar fa-lg" /></button
          ><button
            v-else-if="spot.theme == 28"
            type="button"
            class="btn btn-info btn-circle btn-sm mr-2"
            data-bs-toggle="tooltip"
            title="레포츠"
          >
            <font-awesome-icon class="fontawesom" icon="fa-solid fa-person-running fa-lg" />
          </button>
          <button
            v-else-if="spot.theme == 32"
            type="button"
            class="btn btn-primary btn-circle btn-sm mr-2"
            data-bs-toggle="tooltip"
            title="숙박"
          >
            <font-awesome-icon class="fontawesom" icon="fa-solid fa-bed fa-lg" />
          </button>
          <button
            v-else-if="spot.theme == 38"
            type="button"
            class="btn btn-dark btn-circle btn-sm mr-2"
            data-bs-toggle="tooltip"
            title="쇼핑"
          >
            <font-awesome-icon class="fontawesom" icon="fa-solid fa-cart-shopping fa-lg" />
          </button>
          <button
            v-else
            type="button"
            class="btn btn-secondary btn-circle btn-sm mr-2"
            data-bs-toggle="tooltip"
            title="음식점"
          >
            <font-awesome-icon class="fontawesom" icon="fa-solid fa-utensils fa-lg" />
          </button>
        </span>
        <span>
          <h5>{{ spot.title }}</h5>
          <span>{{ spot.address }}</span>
        </span>
      </div>
    </div>
  </li>
</template>

<script>
export default {
  name: "PlanSearchItem",
  props: {
    spot: Object,
  },
};
</script>

<style scoped>
.btn-circle {
  width: 40px;
  height: 40px;
  text-align: center;
  padding: 6px 0px;
  font-size: 12px;
  line-height: 1.428571429;
  border-radius: 20px;
}
.btn-circle.btn-sm {
  width: 30px;
  height: 30px;
  text-align: center;
  padding: 0px 0px;
  font-size: 12px;
  line-height: 1.428571429;
  border-radius: 15px;
}
.btn-circle.btn-lg {
  width: 50px;
  height: 50px;
  padding: 10px 16px;
  font-size: 18px;
  line-height: 1.33;
  border-radius: 25px;
}
.btn-circle.btn-xl {
  width: 70px;
  height: 70px;
  padding: 10px 16px;
  font-size: 24px;
  line-height: 1.33;
  border-radius: 35px;
}
li {
  list-style: none;
}
.item {
  position: relative;
  border-bottom: 1px solid #888;
  overflow: hidden;
  cursor: pointer;
  min-height: 65px;
}
.item span {
  display: block;
  margin-top: 4px;
}
.item h5 {
  font-weight: 500;
  line-height: 1.2;
  display: block;
  font-size: 1em;
  margin-bottom: 0;
}
.item h5,
.item .info {
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
}
.item .info {
  padding: 10px 0 10px 10px;
  display: inline-flex;
}
.info .gray {
  color: #8a8a8a;
}
</style>
