<template>
<div id="add-blog">
    <link href="https://cdn.jsdelivr.net/npm/vuesax/dist/vuesax.css" rel="stylesheet">

    <h2>Register</h2>
    <form>
        <vs-input class="vsinput username-input" label-placeholder="Username" v-model.lazy="userRegister.username" :danger="usernameCheck" danger-text="Username is already taken"
         :success="usernameSafe" success-text="The username is valid" required />
        <button :class="{ username_danger: usernameCheck || usernameSafe }" class="btn btn-outline-secondary confirm-btn username-btn" v-on:click.prevent="checkUsername">Check Username</button>

        <vs-input class="vsinput" type="password" label-placeholder="Password" v-model.lazy="userRegister.password" required />
        <vs-input class="vsinput" type="password" label-placeholder="Confirm Password" v-model.lazy="userRegister.passwordConfirm" :danger="userRegister.password != userRegister.passwordConfirm" danger-text="Passwords do not match" required />
        <hr />

        <vs-input class="vsinput2" label-placeholder="First Name" v-model.lazy="userRegister.first_name" required />
        <vs-input class="vsinput2" label-placeholder="Last Name" v-model.lazy="userRegister.last_name" required />

        <vs-input class="vsinput" label-placeholder="Email (xxx000@lehigh.edu)" icon-after="true" icon="email" v-model.lazy="userRegister.email" :danger="checkEmailEnding()" danger-text="Enter a Lehigh email (@lehigh.edu)" required />
        <button class="btn btn-outline-secondary confirm-btn" v-on:click.prevent="post">Confirm</button>
    </form>
    
    <vs-alert title="Please correct the following error(s):" v-if="errors.length" color="danger">
        <ul>
            <li v-for="error in errors" :key="error">{{ error }}</li>
        </ul>
    </vs-alert>

    <!-- <vs-popup class="holamundo" title="Notification" :active.sync="popup">
        <p>
            Note: This site currently only allows Lehigh users to register.
        </p>
    </vs-popup> -->
</div>
</template>

<script>
// Imports
import Vue from 'vue'
import 'material-icons/iconfont/material-icons.css';
import VueRouter from "vue"
import UserDataService from './UserDataService'
import vsPopup from 'vuesax'
Vue.use(vsPopup)

export default {
    name: "UserRegister",
    data() {
        return {
            userRegister: {
                username: '',
                password: '',
                passwordConfirm: '',
                first_name: '',
                last_name: '',
                email: '',
            },
            popup: true,
            errors: [],
            router: new VueRouter(),
            usernameCheck: false,
            usernameSafe: false,
        }
    },
    methods: {
        checkUsername: function () {
            if (!this.userRegister.username) return false;
            UserDataService.compareUsername(this.userRegister.username).then((res) => {
                this.usernameCheck = res.data;
                this.usernameSafe = !res.data;
            });
            return this.usernameCheck;
        },
        checkEmailEnding: function () {
            if (!this.userRegister.email) return false;
            return this.userRegister.email.slice(this.userRegister.email.length - 11, this.userRegister.email.length) != "@lehigh.edu";
        },
        post: function () {
            this.errors = [];
            if (!this.userRegister.username) this.errors.push("Username is requried");
            if (this.checkUsername()) this.errors.push("Username is already taken");
            if (!this.userRegister.password) this.errors.push("Password is requried");
            if (!this.userRegister.first_name) this.errors.push("First name is requried");
            if (!this.userRegister.last_name) this.errors.push("Last name is requried");
            if (!this.userRegister.email) this.errors.push("Email is requried");
            if (this.checkEmailEnding()) this.errors.push("Enter a valid Lehigh email");
            if (this.errors.length != 0) return;

            UserDataService.registerNewUser(this.userRegister).then(() => {
                this.$router.push("/user/register-confirm");
            });
        },

    },
    created: function () {}
}
</script>

<style scoped>
#add-blog * {
    box-sizing: border-box;
}

#add-blog {
    margin: 20px auto;
    max-width: 500px;
}

.vsinput {
    width: 100%;
    margin-top: 20px !important;
}

.vsinput2 {
    width: 40%;
        margin-top: 20px !important;

}

.confirm-btn {
    margin-top: 20px;
}

.username-input {
    width: 70%;
    float: left;
    margin-right: 20px;
}

.username-btn {
    font-size: 0.7rem;
    margin-bottom: 7px;
}

.username_danger {
    margin-bottom: 25px;
}
</style>
