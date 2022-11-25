<template>
  <section id="modifySchedule" style="pb-0" data-aos="zoom-in-out">
    <div class="container" style="min-height: 750px">
      <div class="section-header">
        <h2>여행정보 공유 게시판</h2>
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
              placeholder="제목..."
              v-model="board.title"
            />
          </div>
          <div class="mb-0">
            <label for="content" class="form-label">내용 : </label>
            <textarea
              class="form-control"
              id="content"
              name="content"
              rows="7"
              style="resize: none"
              v-model="board.content"
            ></textarea>
          </div>
          <div class="mb-0">
            <div class="container p-0">
              <!-- Section: Timeline -->
              <section class="py-5 pb-5">
                <ul class="timeline-with-icons ml-3" id="planInner">
                  <board-modify-spot
                    v-for="(spot, index) in boardSpots"
                    :key="spot.ssid"
                    :index="index"
                  ></board-modify-spot>
                </ul>
              </section>
              <!-- Section: Timeline -->
            </div>
          </div>
          <div class="col-auto text-center">
            <button type="button" id="btn-modify" class="btn btn-default mb-3 mr-3" @click="modify">
              여행계획 수정하기
            </button>
            <button type="button" id="btn-cancel" class="btn btn-danger mb-3" @click="cancelModify">취소하기</button>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
import BoardModifySpot from "@/components/board/BoardModifySpot";

export default {
  name: "BoardModify",
  components: {
    BoardModifySpot,
  },
  computed: {
    ...mapState("userStore", ["userInfo"]),
    ...mapState("boardStore", ["board", "boardSpots"]),
  },
  methods: {
    ...mapActions("boardStore", ["modifyBoard", "getBoard", "getBoardspotList"]),
    ...mapMutations("boardStore", ["CLEAR_BOARD", "CLEAR_BOARDSPOT_LIST"]),

    async modify() {
      if (confirm("게시글을 수정하시겠습니까??")) {
        let boardid = this.board.boardid;
        for (var i = 0; i < this.boardSpots.length; i++) {
          await this.modifyBoard({ ...this.board, ssid: this.boardSpots[i].ssid, memo: this.boardSpots[i].memo });
        }

        this.CLEAR_BOARD();
        this.CLEAR_BOARDSPOT_LIST();
        this.getBoard(boardid);
        this.getBoardspotList(boardid);
        this.$router.push({ name: "boardDetail" });
      }
    },
    cancelModify() {
      this.$router.push({ name: "boardDetail" });
    },
  },
  mounted() {
    if (!this.userInfo || this.userInfo.uid != this.board.uid) {
      alert("게시글 수정 권한이 없습니다!!!");
      this.$router.push({ name: "board" });
    }
  },
};
</script>

<style></style>
