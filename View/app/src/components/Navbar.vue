<template>
  <div id="navbarDiv">
    <b-navbar toggleable="lg" type="dark" variant="info">
      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav class="navbar-item mr-auto">
          <b-nav-item @click="toHome()">Swap</b-nav-item>
          <!-- <b-nav-item :to="{ name: 'ItemList' }">All Items</b-nav-item> -->
        </b-navbar-nav>

        <b-nav-form class="mx-auto search-bar" @submit.prevent="search()">
          <b-form-input size="sm" class="mr-sm-2 length" v-model="keyword"></b-form-input>
          <b-button size="sm" class="my-2 my-sm-0" @click="search()">Search</b-button>
        </b-nav-form>

        <!-- Right aligned nav items -->
        <b-navbar-nav class="ml-auto navbar-item">
          <b-nav-form v-if="!isLoggedIn">
            <b-nav-item @click="saveLastUrl">Sign In / Sign Up</b-nav-item>
          </b-nav-form>

          <b-nav-form v-if="isLoggedIn">
            <b-nav-item-dropdown right>
              <!-- Using 'button-content' slot -->
              <template v-slot:button-content>
                <em>{{ username }}</em>
              </template>
              <b-dropdown-item @click="Profile">Profile</b-dropdown-item>
              <b-dropdown-item :to="{ name: 'ItemPost' }">Post Item</b-dropdown-item>
              <b-dropdown-item :to="{ name: 'UserItem' }">My Items</b-dropdown-item>
              <!-- <b-dropdown-item :to="{ name: 'UserList' }">
                My Lists
              </b-dropdown-item>-->
              <b-dropdown-item :to="{ name: 'UserFavoritedItem' }">Favorited Items</b-dropdown-item>
              <b-dropdown-item :to="{ name: 'UserRecentlyViewedItem' }">Recently Viewed</b-dropdown-item>
              <b-dropdown-item @click="SignOut">Sign Out</b-dropdown-item>
            </b-nav-item-dropdown>
          </b-nav-form>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
import UserAccount from "./user/account/UserAccount";

export default {
  name: "NavBar",
  data() {
    return {
      isLoggedIn: UserAccount.isLoggedIn,
      username: UserAccount.username,
      userId: UserAccount.userId,
      userSession: UserAccount.userSession,
      keyword: ""
    };
  },
  methods: {
    toHome() {
      this.$router.push("/");
      this.$router.go();
    },
    refresh() {
      UserAccount.UserAccount();
      this.isLoggedIn = UserAccount.isLoggedIn;
      this.username = UserAccount.username;
      this.userId = UserAccount.userId;
      this.userSession = UserAccount.userSession;
    },
    Profile() {
      this.$router.push({ path: `/user/profile/${this.username}` });
      this.$router.go();
    },

    SignOut() {
      UserAccount.SignOut();
      this.$router.push("/");
      window.location.reload();
      this.refresh();
    },
    saveLastUrl() {
      window.localStorage.setItem("previousRoute", window.location.href);
      this.$router.push("/user/sign-in");
      this.$router.go();
    },
    search() {
      console.log(this.keyword);
      window.localStorage.setItem("search", this.keyword);

      this.$router.push({
        path: "/item/list",
        query: { search: this.keyword }
      });
      this.$router.go();
    }
  },
  created() {
    this.keyword = window.localStorage.getItem("search");
    this.refresh();
  }
};
</script>

<style scoped>
button {
  border: none;
}

.length {
  width: 50vw !important;
}

.searchButton {
  background-color: grey;
  border: 1px;
  border-radius: 8px;
  padding: 5px;
  text-align: center;
  text-decoration: none;
}

.searchButton:hover {
  cursor: pointer;
}

#searchTextId {
  margin-top: 2px;
  color: white;
  font-size: 16px;
  margin-bottom: 0px;
}
</style>
