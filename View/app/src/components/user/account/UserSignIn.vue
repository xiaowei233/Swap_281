<template>
  <div id="add-blog">
    <link href="https://cdn.jsdelivr.net/npm/vuesax/dist/vuesax.css" rel="stylesheet" />
    <!-- <g-signin-button @done="onUserLoggedIn"/> -->
    <div v-if="!isLoggedIn">
      <h2 class="auth-title">Sign In</h2>
      <form>
        <vs-input
          class="vsinput auth-input"
          label-placeholder="Username"
          v-model.lazy="userLogin.username"
          required
        />

        <vs-input
          class="vsinput auth-input"
          type="password"
          label-placeholder="Password"
          v-model.lazy="userLogin.password"
          :danger="authError"
          danger-text="Invalid login crediential: Wrong username and password pair"
          :success="authSuccess"
          success-text="Login Successed"
          required
        />

        <button class="btn btn-outline-secondary confirm-btn" v-on:click.prevent="post">Confirm</button>
      </form>

      <div @click="googleSignIn" class="div-sign-in">
        <img height="45px" src="../../../assets/gauth_btn.png" />
        or <a href="/user/register">Sign Up</a>
      </div>

    </div>
    <div v-if="isLoggedIn">
      <p>
        You are now logged in. Returning to home page.
      </p>
    </div>
  </div>
</template>

<script>
import UserDataService from "../UserDataService";
import UserAccount from "./UserAccount";
import Vue from "vue";

import GAuth from "vue-google-oauth2";
import Axios from "axios";
const gauthOption = {
  clientId:
    "835929543153-3s9nek7keah0cd56ruk9neqs1rsnlhal.apps.googleusercontent.com",
  client_id:
    "835929543153-3s9nek7keah0cd56ruk9neqs1rsnlhal.apps.googleusercontent.com",
  scope: "profile email",
  prompt: "select_account",
  hd: "lehigh.edu",
  response_type: "token"
  // redirect_uri: '/',
};
Vue.use(GAuth, gauthOption);

export default {
  name: "UserSignIn",
  data() {
    return {
      authError: false,
      authSuccess: false,
      userLogin: {
        username: "",
        password: ""
      },
      isLoggedIn: UserAccount.isLoggedIn,
      // previous: window.localStorage.getItem("previousRoute"),
      GoogleAuth: undefined
    };
  },
  methods: {
    googleSignIn() {
      console.log(this.$gAuth);
      this.$gAuth
        .signIn()
        .then(GoogleUser => {
          // On success do something, refer to https://developers.google.com/api-client-library/javascript/reference/referencedocs#googleusergetid
          console.log("user", GoogleUser);

          Axios.get(GoogleUser.w3.Paa).then(oReq => {
            GoogleUser.avatar = oReq.response; // not responseText

            var user = {
              username: GoogleUser.w3.U3.slice(0, 6),
              email: GoogleUser.w3.U3,
              password: GoogleUser.Zi.id_token.slice(0, 256),
              first_name: GoogleUser.w3.ofa,
              last_name: GoogleUser.w3.wea,
              // token: GoogleUser.Zi.id_token,
              create_date: new Date(),
              is_verified: true,
              avatar: btoa(
                String.fromCharCode.apply(
                  null,
                  new Uint8Array(GoogleUser.avatar)
                )
              ),
              full_name: GoogleUser.w3.ig
            };
            UserDataService.addNewOrUpdate(user).then(res => {
              UserAccount.UpdateUserInfo(res.data.username, res.data.id);
              setTimeout(() => {
                this.$router.push("/");
                this.$router.go();
              }, 2000);
              this.isLoggedIn = true;
            });
          });
        })
        .catch(error => {
          console.log("error: ");
          console.log(error);
          //on fail do something
        });
    },
    post: function() {
      UserDataService.auth(
        this.userLogin.username,
        this.userLogin.password
      ).then(res => {
        if (res.data) {
          this.authSuccess = true;
          this.authError = false;
          UserAccount.UpdateUserInfo(res.data.username, res.data.id);
          this.isLoggedIn = true;
          setTimeout(() => {
            this.$router.push("/");
            this.$router.go();
          }, 2000);
        } else {
          this.authError = true;
        }
      });
    }
  },
  created: function() {}
};
</script>

<style scoped>
#add-blog * {
  box-sizing: border-box;
}

.auth-title {
  position: relative;
  /* left: calc(50% - 2em); */
}

#add-blog {
  margin: 20px auto;
  max-width: 500px;
}

.confirm-btn {
  margin-top: 20px;
  width: 100%;
}
.auth-input {
  width: 100%;
  margin-top: 30px !important;
  font-size: 20px;
}
.auth-input label {
  font-size: 40px !important;
}
.div-sign-in {
  margin-top: 20px;
}
.div-sign-in img:hover {
  display: inline-block;
  cursor: pointer;
  filter: brightness(90%);
}
.btn-sign-in {
  display: inline-block;
}
</style>
