<template>
  <section id="writeSchedule" class="pb-0" data-aos="zoom-in-up" v-if="hotplace">
    <div class="container" style="min-height: 750px">
      <div class="section-header">
        <h2>핫 플레이스 공유</h2>
      </div>

      <div class="row justify-content-center">
        <div class="col-lg-8 col-md-10 col-sm-12">
          <div class="mb-3">
            <label for="subject" class="form-label">제목 : </label>
            <input
              type="text"
              class="form-control"
              id="subject"
              name="subject"
              placeholder="여행지..."
              v-model="hotplace.title"
            />
          </div>
          <div class="mb-3">
            <label for="content" class="form-label">설명 : </label>
            <textarea
              class="form-control"
              id="content"
              name="content"
              rows="5"
              style="resize: none"
              placeholder="설명..."
              v-model="hotplace.desc"
            ></textarea>
          </div>
          <div class="col-auto text-center">
            <button
              type="button"
              id="btn-share"
              class="btn btn-default mb-3 mr-3"
              style="background: #0ea2bd; border: none"
              @click="modify"
            >
              핫 플레이스 수정하기
            </button>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import { mapState, mapMutations, mapActions } from "vuex";

export default {
  name: "HotplaceModify",
  computed: {
    ...mapState("hotplaceStore", ["hotplace"]),
  },
  methods: {
    ...mapMutations("hotplaceStore", ["CLEAR_HOTPLACE_LIST"]),
    ...mapActions("hotplaceStore", ["modifyHotplace", "getHotplaceList"]),

    async modify() {
      await this.modifyHotplace(this.hotplace);

      alert("핫 플레이스 정보가 수정되었습니다!!");
      this.CLEAR_HOTPLACE_LIST();
      this.getHotplaceList();
      this.$router.push({ name: "hotplaceList" });
    },
  },
};
</script>

<style></style>
