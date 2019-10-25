<template>
<div id="add-blog">
    <link href="https://cdn.jsdelivr.net/npm/vuesax/dist/vuesax.css" rel="stylesheet">

    <h2 class="auth-title">Sign In</h2>
    <form>
        <vs-input class="vsinput auth-input" label-placeholder="Username" v-model.lazy="userLogin.username" required />

        <vs-input class="vsinput auth-input" type="password" label-placeholder="Password" v-model.lazy="userLogin.password" :danger="authError" danger-text="Invalid login crediential: Wrong username and password pair" :success="authSuccess" success-text="Login Successed" required />

        <button class="btn btn-outline-secondary confirm-btn" v-on:click.prevent="post">Confirm</button>
    </form>
</div>
</template>

<script>
import UserDataService from './UserDataService'
import UserAccount from './UserAccount'
import VueRouter from "vue"

// import Vue from 'vue'
// import GAuth from 'vue-google-oauth2'
// const gauthOption = {
//     clientId: 'CLIENT_ID.apps.googleusercontent.com',
//     scope: 'profile email',
//     prompt: 'select_account'
// }

// Vue.use(GAuth, gauthOption)

export default {
    name: "UserSignIn",
    data() {
        return {
            authError: false,
            authSuccess: false,
            userLogin: {
                username: '',
                password: '',
            },
            router: new VueRouter(),
        }
    },
    methods: {
        post: function () {
            UserDataService.auth(this.userLogin.username, this.userLogin.password).then((res)=>{
                if (res.data) {
                    this.authSuccess = true;
                    this.authError = false;
                    UserAccount.UpdateUserInfo(res.data.username, res.data.id);
                    setTimeout(() => {
                        this.$router.push("/");
                        window.location.reload();
                    }, 2000);
                } else {
                    this.authError = true;
                }
            })
        }
    },
    created: function () {

    }
}
</script>

<style scoped>

#add-blog * {
    box-sizing: border-box;
}

.auth-title{
    position: relative;
    left: calc(50% - 2em);
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

</style>
