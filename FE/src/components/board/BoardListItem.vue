<template>
  <tr class="board-item" v-if="board" @click="boardDetail()">
    <td class="ps-3">{{ board.boardid }}</td>
    <td class="ps-3">{{ board.title }}</td>
    <td class="ps-3">{{ board.author }}</td>
    <td class="ps-3">{{ board.date }}</td>
    <td class="ps-3">{{ board.hit }}</td>
  </tr>
</template>

<script>
import { mapMutations, mapActions } from "vuex";

export default {
  name: "BoardListItem",
  props: {
    board: Object,
  },
  methods: {
    ...mapActions("boardStore", [
      "getBoardspotList",
      "countUpBoard",
      "getBoard",
    ]),
    ...mapMutations("boardStore", [
      "CLEAR_BOARD",
      "SET_BOARD",
      "CLEAR_BOARDSPOT_LIST",
    ]),

    async boardDetail() {
      this.CLEAR_BOARD();
      this.CLEAR_BOARDSPOT_LIST();
      await this.countUpBoard(this.board.boardid);

      this.getBoard(this.board.boardid);
      this.getBoardspotList(this.board.boardid);
      this.$router.push({ name: "boardDetail" });
    },
  },
};
</script>

<style></style>
