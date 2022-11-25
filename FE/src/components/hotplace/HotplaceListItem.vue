<template>
  <div class="col-xl-3 col-lg-4 col-md-6 pl-0 pr-0 portfolio-item filter-app" v-if="hotplace">
    <img :src="'http://localhost/upload/file/' + hotplace.image" class="img-fluid" alt="" />
    <div class="portfolio-info">
      <h4>{{ hotplace.title }}</h4>
      <p>@{{ hotplace.userid }}</p>
      <p>{{ hotplace.desc }}</p>
      <a
        title="수정"
        data-gallery="portfolio-gallery"
        class="preview-link"
        v-if="userInfo && userInfo.uid == hotplace.uid"
        @click="editHotplace"
        ><b-icon icon="pencil-fill"></b-icon
      ></a>
      <a
        title="삭제"
        class="details-link"
        v-if="userInfo && userInfo.uid == hotplace.uid"
        @click="removeHotplace"
      >
        <b-icon icon="trash-fill"></b-icon>
      </a>
    </div>
  </div>
</template>

<script>
import { mapState, mapMutations, mapActions } from "vuex";

export default {
  name: "HotplaceListItem",
  props: {
    hotplace: Object,
  },
  computed: {
    ...mapState("userStore", ["userInfo"]),
  },
  methods: {
    ...mapMutations("hotplaceStore", ["CLEAR_HOTPLACE_LIST"]),
    ...mapActions("hotplaceStore", ["getHotplace", "getHotplaceList", "deleteHotplace"]),

    editHotplace() {
      this.getHotplace(this.hotplace.hotplaceid);
      this.$router.push({ name: "hotplaceModify" });
    },
    async removeHotplace() {
      if (confirm("회원님의 핫 플레이스를 삭제하시겠습니까??")) {
        await this.deleteHotplace(this.hotplace.hotplaceid);

        this.CLEAR_HOTPLACE_LIST();
        this.getHotplaceList();
        alert("핫 플레이스가 삭제되었습니다!!");
        this.$router.push({ name: "hotplaceList" });
      }
    },
  },
};
</script>

<style scoped>
.portfolio .portfolio-item .portfolio-info p {
  font-size: 14px;
  font-weight: 600;
  color: var(--color-white);
  padding-right: 50px;
}
</style>
