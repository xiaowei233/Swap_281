<template>
  <div id="navbarDiv">
    <b-navbar toggleable="lg" type="dark" variant="info">
      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav class="navbar-item mr-auto">
          <b-nav-item to="/">Home</b-nav-item>
        </b-navbar-nav>

        <b-nav-form class="mx-auto search-bar">
          <b-form-input size="sm" class="mr-sm-2 length"></b-form-input>
          <b-button size="sm" class="my-2 my-sm-0" type="submit">Search</b-button>
        </b-nav-form>

        <!-- Right aligned nav items -->
        <b-navbar-nav class="ml-auto navbar-item">
          <b-nav-form v-if="!isLoggedIn">
            <b-nav-item :to="{ name: 'UserSignIn' }">Sign In</b-nav-item>
            <b-nav-item :to="{ name: 'UserRegister' }">Sign Up</b-nav-item>
          </b-nav-form>

          <b-nav-form v-if="isLoggedIn">
            <b-nav-item-dropdown right>
              <!-- Using 'button-content' slot -->
              <template v-slot:button-content>
                <em>{{username}}</em>
              </template>
              <b-dropdown-item @click="Profile">Profile</b-dropdown-item>
              <b-dropdown-item :to="{ name: 'ItemPost' }">Post Item</b-dropdown-item>
              <b-dropdown-item :to="{ name: 'UserItem' }">My Items</b-dropdown-item>
              <b-dropdown-item :to="{ name: 'UserList' }">My Lists</b-dropdown-item>
              <b-dropdown-item @click="SignOut">Sign Out</b-dropdown-item>
            </b-nav-item-dropdown>
          </b-nav-form>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
import UserAccount from "./user/UserAccount";

export default {
  name: "NavBar",
  data() {
    return {
      isLoggedIn: UserAccount.isLoggedIn,
      username: UserAccount.username,
      userId: UserAccount.userId,
      userSession: UserAccount.userSession
    };
  },
  methods: {
    refresh() {
      UserAccount.UserAccount();
      this.isLoggedIn = UserAccount.isLoggedIn;
      this.username = UserAccount.username;
      this.userId = UserAccount.userId;
      this.userSession = UserAccount.userSession;
    },
    Profile() {
      this.$router.push({ path: `/user/profile/${this.userId}` });
    },

    SignOut() {
      UserAccount.SignOut();
      this.$router.push("/");
      window.location.reload();
      this.refresh();
    },
  },
  created() {
      this.refresh();
  },
};
</script>

<style scoped>
button {
  border: none;
}

.length {
  width: 50vw !important;
}

</style>
