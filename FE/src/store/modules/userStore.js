import jwtDecode from "jwt-decode";
import router from "@/router";
import { login, findById, tokenRegeneration, logout } from "@/api/user";
import http from "@/api/http";

const userStore = {
  namespaced: true,
  state: {
    isLogin: false,
    isLoginError: false,
    userInfo: null,
    isValidToken: false,
    idCheck: null,
    userid: null,
    tempUser: null,
    tempPw: null,
  },
  getters: {
    checkUserInfo: function (state) {
      return state.userInfo;
    },
    checkToken: function (state) {
      return state.isValidToken;
    },
    checkIdCheck: function (state) {
      return state.idCheck;
    },
  },
  mutations: {
    CLEAR_TEMP_PW(state) {
      state.tempPw = null;
    },
    SET_IS_LOGIN: (state, isLogin) => {
      state.isLogin = isLogin;
    },
    SET_IS_LOGIN_ERROR: (state, isLoginError) => {
      state.isLoginError = isLoginError;
    },
    SET_IS_VALID_TOKEN: (state, isValidToken) => {
      state.isValidToken = isValidToken;
    },
    SET_USER_INFO: (state, userInfo) => {
      state.isLogin = true;
      state.userInfo = userInfo;
    },
    SET_ID_CHECK: (state, idCheck) => {
      state.idCheck = idCheck;
    },
    SET_USER_ID: (state, id) => {
      state.userid = id;
    },
    SET_TEMP_USER: (state, tempUser) => {
      state.tempUser = tempUser;
    },
    SET_TEMP_PW: (state, tempPw) => {
      state.tempPw = tempPw;
    },
  },
  actions: {
    async userConfirm({ commit }, user) {
      await login(
        user,
        ({ data }) => {
          if (data.message === "success") {
            let accessToken = data["access-token"];
            let refreshToken = data["refresh-token"];
            // console.log("login success token created!!!! >> ", accessToken, refreshToken);
            commit("SET_IS_LOGIN", true);
            commit("SET_IS_LOGIN_ERROR", false);
            commit("SET_IS_VALID_TOKEN", true);
            sessionStorage.setItem("access-token", accessToken);
            sessionStorage.setItem("refresh-token", refreshToken);
          } else {
            commit("SET_IS_LOGIN", false);
            commit("SET_IS_LOGIN_ERROR", true);
            commit("SET_IS_VALID_TOKEN", false);
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    async getUserInfo({ commit, dispatch }, token) {
      let decodeToken = jwtDecode(token);
      // console.log("2. getUserInfo() decodeToken :: ", decodeToken);
      await findById(
        decodeToken.id,
        ({ data }) => {
          if (data.message === "success") {
            commit("SET_USER_INFO", data.userInfo);
            // console.log("3. getUserInfo data >> ", data);
          } else {
            console.log("?????? ?????? ??????!!!!");
          }
        },
        async (error) => {
          console.log(
            "getUserInfo() error code [?????? ???????????? ?????? ?????????.] ::: ",
            error.response.status
          );
          commit("SET_IS_VALID_TOKEN", false);
          await dispatch("tokenRegeneration");
        }
      );
    },
    async tokenRegeneration({ commit, state }) {
      console.log(
        "?????? ????????? >> ?????? ?????? ?????? : {}",
        sessionStorage.getItem("access-token")
      );
      await tokenRegeneration(
        JSON.stringify(state.userInfo),
        ({ data }) => {
          if (data.message === "success") {
            let accessToken = data["access-token"];
            console.log("????????? ?????? >> ????????? ?????? : {}", accessToken);
            sessionStorage.setItem("access-token", accessToken);
            commit("SET_IS_VALID_TOKEN", true);
          }
        },
        async (error) => {
          // HttpStatus.UNAUTHORIZE(401) : RefreshToken ?????? ?????? >> ?????? ?????????!!!!
          if (error.response.status === 401) {
            console.log("?????? ??????");
            // ?????? ????????? ??? DB??? ????????? RefreshToken ??????.
            await logout(
              state.userInfo.userid,
              ({ data }) => {
                if (data.message === "success") {
                  console.log("???????????? ?????? ?????? ??????");
                } else {
                  console.log("???????????? ?????? ?????? ??????");
                }
                alert("RefreshToken ?????? ??????!!! ?????? ???????????? ?????????.");
                commit("SET_IS_LOGIN", false);
                commit("SET_USER_INFO", null);
                commit("SET_IS_VALID_TOKEN", false);
                router.push({ name: "login" });
              },
              (error) => {
                console.log(error);
                commit("SET_IS_LOGIN", false);
                commit("SET_USER_INFO", null);
              }
            );
          }
        }
      );
    },
    async userLogout({ commit }, userid) {
      await logout(
        userid,
        ({ data }) => {
          if (data.message === "success") {
            commit("SET_IS_LOGIN", false);
            commit("SET_USER_INFO", null);
            commit("SET_IS_VALID_TOKEN", false);
          } else {
            console.log("?????? ?????? ??????!!!!");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getIdCheck({ commit }, id) {
      http
        .get(`user/${id}`)
        .then(({ data }) => {
          commit("SET_ID_CHECK", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    userSignup(context, param) {
      http
        .post(`user/join`, JSON.stringify(param))
        .then(() => {
          alert("??????????????? ?????????????????????!!");
          router.push({ name: "login" });
        })
        .catch((error) => {
          console.log(error);
        });
    },
    async userDelete(context, uid) {
      await http.delete(`user/delete/${uid}`).catch((error) => {
        console.log(error);
      });
    },
    async userModify(context, formData) {
      await http
        .put(`user/modify`, formData, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        })
        .catch((error) => {
          console.log(error);
        });
    },
    async getIdByEmail({ commit }, email) {
      await http
        .get(`user/findId/${email}`)
        .then(({ data }) => {
          commit("SET_USER_ID", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    sendFindIdEmail(context, param) {
      http.post(`user/findId`, JSON.stringify(param)).catch((error) => {
        console.log(error);
      });
    },
    async getUidForPw({ commit }, id) {
      await http
        .get(`user/findPw/${id}`)
        .then(({ data }) => {
          commit("SET_TEMP_USER", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    async sendFindPwEmail({ commit }, email) {
      await http
        .post(`user/findPw/${email}`)
        .then(({ data }) => {
          commit("SET_TEMP_PW", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    async changeTempPw(context, param) {
      await http.put(`user/findPw`, JSON.stringify(param)).catch((error) => {
        console.log(error);
      });
    },
  },
};

export default userStore;
