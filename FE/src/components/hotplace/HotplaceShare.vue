<template>
  <section id="writeSchedule" class="pb-0" data-aos="zoom-in-up">
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
              v-model="title"
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
              v-model="desc"
            ></textarea>
          </div>
          <div class="mb-5">
            <label for="content" class="form-label">핫 플레이스 사진 : </label>
            <input
              type="file"
              accept="image/*"
              class="form-control"
              style="height: 45px"
              placeholder="핫 플레이스..."
              @change="handleFileChange"
            />
          </div>
          <div class="col-auto text-center">
            <button
              type="button"
              id="btn-share"
              class="btn btn-default mb-3 mr-3"
              style="background: #0ea2bd; border: none"
              @click="share"
            >
              핫 플레이스 공유하기
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
  name: "HotplaceShare",
  data() {
    return {
      title: null,
      desc: null,
      file: null,
    };
  },
  computed: {
    ...mapState("userStore", ["userInfo"]),
  },
  methods: {
    ...mapMutations("hotplaceStore", ["CLEAR_HOTPLACE_LIST"]),
    ...mapActions("hotplaceStore", ["writeHotplace", "getHotplaceList"]),

    handleFileChange(e) {
      this.file = e.target.files[0];
    },
    async share() {
      if (!this.title) {
        alert("제목을 입력해 주세요!!!");
        return;
      } else if (!this.desc) {
        alert("설명을 입력해 주세요!!!");
        return;
      } else if (!this.file) {
        alert("핫 플레이스 사진을 추가해주세요!!!");
        return;
      } else {
        let data = new FormData();
        data.append("uid", this.userInfo.uid);
        data.append("title", this.title);
        data.append("desc", this.desc);
        data.append("upfile", this.file);

        await this.writeHotplace(data);
        this.CLEAR_HOTPLACE_LIST();
        this.getHotplaceList();
        this.$router.push({ name: "hotplaceList" });
      }
    },
  },
};
</script>

<style></style>
