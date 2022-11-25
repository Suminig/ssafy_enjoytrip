<template>
  <section id="listSchedule" class="pb-0" data-aos="zoom-in-up">
    <div class="container" style="min-height: 750px">
      <div class="section-header">
        <h2>여행정보 공유 게시판</h2>
      </div>

      <router-link to="/plan" class="btn-default float-right mb-3 scrollto" v-if="userInfo">여행계획 짜기</router-link>
      <table class="table table-hover">
        <tr style="background: #0ea2bd">
          <th class="ps-3" style="width: 10%">순번</th>
          <th class="ps-3" style="width: 50%">제목</th>
          <th class="ps-3" style="width: 15%">작성자</th>
          <th class="ps-3" style="width: 15%">작성일</th>
          <th class="ps-3" style="width: 10%">조회수</th>
        </tr>
        <tbody id="board-table-body">
          <board-list-item v-for="board in boards" :key="board.boardid" :board="board"></board-list-item>
        </tbody>
      </table>
    </div>
  </section>
</template>

<script>
import { mapState, mapMutations, mapActions } from "vuex";
import BoardListItem from "@/components/board/BoardListItem";

export default {
  name: "BoardList",
  components: {
    BoardListItem,
  },
  computed: {
    ...mapState("boardStore", ["boards"]),
    ...mapState("userStore", ["userInfo"]),
  },
  methods: {
    ...mapMutations("boardStore", ["CLEAR_BOARD_LIST"]),
    ...mapActions("boardStore", ["getBoardList"]),
  },
  mounted() {
    this.CLEAR_BOARD_LIST();
    this.getBoardList();
  },
};
</script>

<style scoped>
.btn-getStarted {
  font-size: 16px;
  color: var(--color-white);
  background: var(--color-primary);
  padding: 8px 23px;
  border-radius: 4px;
  transition: 0.3s;
  font-family: var(--font-secondary);
}
</style>
