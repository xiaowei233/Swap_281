<template>
<div>
    <b-navbar toggleable="lg" type="dark" variant="info">
        <b-navbar-brand :to="{ name: 'HomePage' }">Home</b-navbar-brand>
        <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
        <b-collapse id="nav-collapse" is-nav>
            <b-navbar-nav>
                <b-nav-item :to="{ name: 'ItemList' }">All Items</b-nav-item>
                <b-nav-item :to="{ name: 'ItemPost' }">Post Item</b-nav-item>

            </b-navbar-nav>

            <!-- Right aligned nav items -->
            <b-navbar-nav class="ml-auto">
                <b-nav-form>
                    <b-form-input size="sm" class="mr-sm-2" placeholder="Search"></b-form-input>
                    <b-button size="sm" class="my-2 my-sm-0" type="submit">Search</b-button>
                </b-nav-form>

                <b-nav-form v-if="!isLoggedIn">
                    <b-nav-item :to="{ name: 'UserSignIn' }">Sign In</b-nav-item>
                    <b-nav-item :to="{ name: 'UserRegister' }">Sign Up</b-nav-item>
                </b-nav-form>

                <b-nav-form v-if="isLoggedIn">
                    <b-nav-item-dropdown right>
                        <!-- Using 'button-content' slot -->
                        <template v-slot:button-content>
                            <em > {{username}}</em>
                        </template>
                        <!--<b-dropdown-item :to="{ name: 'Profile' }">Profile</b-dropdown-item> -->
                        <b-dropdown-item @click="SignOut">Sign Out</b-dropdown-item>
                    </b-nav-item-dropdown>
                </b-nav-form>

            </b-navbar-nav>
        </b-collapse>
    </b-navbar>
</div>
</template>

<script>
import UserAccount from './user/UserAccount'

export default {
    name: "NavBar",
    data() {
        return {
            isLoggedIn: UserAccount.isLoggedIn,
            username: UserAccount.username,
            userId: UserAccount.userId,
            userSession: UserAccount.userSession,
        }
    },
    methods: {
        refresh() {
          UserAccount.UserAccount();
          this.isLoggedIn = UserAccount.isLoggedIn;
          this.username =UserAccount.username;
          this.userId = UserAccount.userId;
          this.userSession = UserAccount.userSession;
        },
        SignOut(){
          UserAccount.SignOut();
          window.location.reload();
          this.refresh();
        }
    },
    created() {
        this.refresh();
    }
}
</script>

<style scoped>
button {
  border: none;
}
</style>
