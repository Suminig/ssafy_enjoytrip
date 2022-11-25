<template>
  <section id="writeSchedule" class="pb-0" data-aos="zoom-in-up">
    <div class="container" style="min-height: 750px">
      <div class="section-header">
        <h2>여행 계획 작성</h2>
      </div>

      <div class="row justify-content-center">
        <div class="col-lg-8 col-md-10 col-sm-12">
          <div class="mb-3">
            <label for="subject" class="form-label">제목 : </label>
            <input type="text" class="form-control" id="subject" name="subject" placeholder="제목..." v-model="title" />
          </div>
          <div class="mb-0">
            <label for="content" class="form-label">내용 : </label>
            <textarea
              class="form-control"
              id="content"
              name="content"
              rows="7"
              style="resize: none"
              placeholder="내용..."
              v-model="content"
            ></textarea>
          </div>
          <div class="mb-0">
            <div class="container p-0">
              <section class="py-5 pb-5">
                <ul class="timeline-with-icons ms-3" id="planInner">
                  <plan-write-detail-item
                    v-for="(schedule, index) in schedules"
                    :key="schedule.spotid"
                    :schedule="schedule"
                    :num="index + 1"
                  ></plan-write-detail-item>
                </ul>
              </section>
            </div>
          </div>
          <div class="col-auto text-center">
            <button
              type="button"
              id="btn-share"
              class="btn btn-default mb-3 mr-3"
              style="background: #0ea2bd; border: none"
              @click="share"
            >
              여행계획 공유하기
            </button>
            <button type="button" id="btn-cancel" class="btn btn-danger mb-3" @click="cancel">다시 계획하기</button>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
import PlanWriteDetailItem from "@/components/plan/PlanWriteDetailItem";

export default {
  name: "PlanWriteDetail",
  data() {
    return {
      title: "",
      content: "",
    };
  },
  components: {
    PlanWriteDetailItem,
  },
  computed: {
    ...mapState("planStore", ["schedules", "boardid"]),
    ...mapState("userStore", ["userInfo"]),
  },
  methods: {
    ...mapMutations("planStore", ["CLEAR_BOARD_ID"]),
    ...mapActions("planStore", ["writeSchedule", "getBoardid", "writeScheduleSpot"]),

    async share() {
      if (!this.title) {
        alert("제목을 입력해주세요!!!");
        return;
      } else if (!this.content) {
        alert("내용을 입력해주세요!!!");
        return;
      } else {
        let board = {
          uid: this.userInfo.uid,
          title: this.title,
          content: this.content,
        };
        await this.writeSchedule(board);

        this.CLEAR_BOARD_ID();
        await this.getBoardid(this.userInfo.uid);

        for (var i = 0; i < this.schedules.length; i++) {
          var memo = document.querySelector(`#memo${i + 1}`).value;
          this.schedules[i].memo = memo;
          let scheduleSpot = {
            boardid: this.boardid,
            spotid: this.schedules[i].spotid,
            memo: this.schedules[i].memo,
          };
          this.writeScheduleSpot(scheduleSpot);
        }

        this.$router.push({ name: "board" });
      }
    },
    cancel() {
      this.$router.push({ name: "plan" }).catch(() => {});
    },
  },
  mounted() {
    window.scrollTo(0, 0);
  },
};
</script>

<style scoped>
ul {
  margin-left: 1rem;
}
</style>
