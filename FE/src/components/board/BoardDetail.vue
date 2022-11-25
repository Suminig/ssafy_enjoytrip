<template>
  <section id="viewSchedule" style="pb-0" data-aos="zoom-in-up" v-if="board">
    <div class="container" style="min-height: 750px">
      <div class="section-header">
        <h2>여행정보 공유 게시판</h2>
      </div>

      <div class="d-flex justify-content-end">
        <router-link to="/board" class="btn btn-primary mb-3 mr-2" style="background: #0ea2bd; border: none"
          >목록</router-link
        >
        <button
          id="btn-edit"
          class="btn btn-primary mb-3 mr-2"
          v-if="userInfo && userInfo.uid == board.uid"
          @click="boardModify"
        >
          수정
        </button>
        <button
          id="btn-delete"
          class="btn btn-danger mb-3"
          v-if="userInfo && userInfo.uid == board.uid"
          @click="boardDelete"
        >
          삭제
        </button>
      </div>
      <div class="divider mt-3 mb-3"></div>

      <div class="col-sm-12 pl-5 pr-5" id="scheduleContent">
        <div class="row my-2">
          <h2 class="fw-bold pl-3">{{ board.title }}</h2>
        </div>
        <div class="row">
          <div class="col-md-8 mb-3">
            <div class="clearfix align-content-center">
              <p>
                <span class="fw-bold">
                  <img
                    v-if="board.profileImg"
                    :src="'http://localhost/upload/file/' + board.profileImg"
                    class="rounded-circle border shadow-4 mb-2"
                    style="width: 30px"
                    alt="Avatar"
                  />
                  <img
                    v-if="!board.profileImg"
                    src="@/assets/img/noavatar.png"
                    class="rounded-circle border shadow-4 mb-2"
                    style="width: 30px"
                    alt="Avatar"
                  />
                  {{ board.author }}</span
                >
                <br />
                <span class="text-secondary fw-light">
                  <span class="mr-3">{{ board.date }}</span> 조회 : {{ board.hit }}
                </span>
              </p>
            </div>
          </div>
          <div class="divider"></div>
          <div class="col-md-6 text-secondary">
            {{ board.content }}
          </div>
          <div class="col-md-6">
            <div class="container">
              <section class="pt-0 pb-0">
                <ul class="timeline-with-icons ml-3" id="planInner" v-if="boardSpots">
                  <board-detail-spot
                    v-for="(spot, index) in boardSpots"
                    :key="spot.ssid"
                    :spot="spot"
                    :index="index + 1"
                  ></board-detail-spot>
                </ul>
                <div v-else>등록된 여행계획이 없습니다.</div>
              </section>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import { mapState, mapMutations, mapActions } from "vuex";
import BoardDetailSpot from "@/components/board/BoardDetailSpot";

export default {
  name: "BoardDetail",
  components: {
    BoardDetailSpot,
  },
  computed: {
    ...mapState("userStore", ["userInfo"]),
    ...mapState("boardStore", ["board", "boardSpots"]),
  },
  methods: {
    ...mapActions("boardStore", ["deleteBoard"]),
    ...mapMutations("boardStore", ["CLEAR_BOARD", "CLEAR_BOARD_LIST", "CLEAR_BOARDSPOT_LIST"]),

    boardModify() {
      this.$router.push({ name: "boardModify" });
    },
    async boardDelete() {
      if (confirm("게시글을 삭제하시겠습니까? \n삭제된 게시글은 다시 불러올 수 없습니다")) {
        await this.deleteBoard(this.board.boardid);
        this.CLEAR_BOARD();
        this.CLEAR_BOARDSPOT_LIST();
        this.$router.push({ name: "board" });
      }
    },
  },
};
</script>

<style scoped>
.my-2 {
  margin-top: 0.5rem !important;
  margin-bottom: 0.5rem !important;
}
.fw-bold {
  font-weight: 700 !important;
}
.text-secondary {
  --bs-text-opacity: 1;
  color: rgba(var(--bs-secondary-rgb), var(--bs-text-opacity)) !important;
}
</style>
