import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "../views/HomeView";
import store from "@/store";

Vue.use(VueRouter);

// https://v3.router.vuejs.org/kr/guide/advanced/navigation-guards.html
const onlyAuthUser = async (to, from, next) => {
  const checkUserInfo = store.getters["userStore/checkUserInfo"];
  const checkToken = store.getters["userStore/checkToken"];
  let token = sessionStorage.getItem("access-token");

  if (checkUserInfo != null && token) {
    await store.dispatch("userStore/getUserInfo", token);
  }
  if (!checkToken || checkUserInfo === null) {
    alert("로그인 후 이용해주세요!!");
    router.push({ name: "login" });
  } else {
    next();
  }
};

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/user",
    name: "user",
    component: () => import("@/views/UserView"),
    children: [
      {
        path: "login",
        name: "login",
        component: () => import("@/components/user/UserLogin"),
      },
      {
        path: "join",
        name: "join",
        component: () => import("@/components/user/UserJoin"),
      },
      {
        path: "profile",
        name: "profile",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/user/UserProfile"),
      },
      {
        path: "modify",
        name: "modify",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/user/UserModify"),
      },
      {
        path: "findId",
        name: "findId",
        component: () => import("@/components/user/UserFindId"),
      },
      {
        path: "findPw",
        name: "findPw",
        component: () => import("@/components/user/UserFindPw"),
      },
    ],
  },
  {
    path: "/spot",
    name: "spot",
    component: () => import("@/views/SpotView"),
  },
  {
    path: "/spotDetail",
    name: "spotDetail",
    component: () => import("@/views/SpotDetailView"),
  },
  {
    path: "/board",
    name: "board",
    component: () => import("@/views/BoardView"),
  },
  {
    path: "/boardDetail",
    name: "boardDetail",
    component: () => import("@/views/BoardDetailView"),
  },
  {
    path: "/boardModify",
    name: "boardModify",
    component: () => import("@/views/BoardModifyView"),
  },
  {
    path: "/plan",
    name: "plan",
    component: () => import("@/views/PlanView"),
  },
  {
    path: "/planDetail",
    name: "planDetail",
    beforeEnter: onlyAuthUser,
    component: () => import("@/views/PlanDetailView"),
  },
  {
    path: "/hotplace",
    name: "hotplace",
    component: () => import("@/views/HotplaceView"),
    children: [
      {
        path: "list",
        name: "hotplaceList",
        component: () => import("@/components/hotplace/HotplaceList"),
      },
      {
        path: "share",
        name: "hotplaceShare",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/hotplace/HotplaceShare"),
      },
      {
        path: "modify",
        name: "hotplaceModify",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/hotplace/HotplaceModify"),
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
