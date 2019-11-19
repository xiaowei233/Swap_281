<template>

<div id="add-blog">
    
    <link href="https://cdn.jsdelivr.net/npm/vuesax/dist/vuesax.css" rel="stylesheet">

    <h2>Update Your Information Below</h2>
    <vs-input class="vsinput username-input" label-placeholder="Username" v-model.lazy="userProfileEdit.username" :danger="usernameCheck" danger-text="Username is already taken"
         :success="usernameSafe" success-text="The username is valid" required />
    <vs-input class="vsinput2" label-placeholder="First Name" v-model.lazy="userProfileEdit.first_name" required />
    <vs-input class="vsinput2" label-placeholder="Last Name" v-model.lazy="userProfileEdit.last_name" required />
    <button class="btn btn-outline-secondary confirm-btn" v-on:click.prevent="post">Confirm</button>
    <button class="btn btn-outline-secondary confirm-btn" @click="cancel">Cancel</button>
     <button class="btn btn-outline-secondary confirm-btn" @click="test">Test</button>
</div>
</template>
<script>
import VueRouter from "vue"
import UserDataService from "./UserDataService"
export default {

    name: "UserProfileEdit",
    data() {
        return {
            
            userProfileEdit: {
                id: 0,
                username: '',
                first_name: '',
                last_name: '',
            },
            errors : [],
            router: new VueRouter(),
            usernameCheck: false,
            usernameSafe: false,
        }
    },
    methods: {
        checkUsername: function () {
            if (!this.userProfileEdit.username) return false;
            UserDataService.compareUsername(this.userProfileEdit.username).then((res) => {
                this.usernameCheck = res.data;
                this.usernameSafe = !res.data;
            });
            return this.usernameCheck;
        },
        // checkEmailEnding: function () {
        //     if (!this.userProfileEdit.email) return false;
        //     return this.userProfileEdit.email.slice(this.userProfileEdit.email.length - 11, this.userProfileEdit.email.length) != "@lehigh.edu";
        // },
        post: function () {
            
            this.errors = [];
            if (!this.userProfileEdit.username) this.errors.push("Username is requried");
            if (!this.userProfileEdit.first_name) this.errors.push("First name is requried");
            if (!this.userProfileEdit.last_name) this.errors.push("Last name is requried");
            if (this.errors.length != 0) return;
            this.userProfileEdit.id = this.$route.params.userId;
            UserDataService.edit(this.userProfileEdit).then(res => {
                console.log(res.data)
                this.$router.push(`/user/profile-edit-confirm/${this.userProfileEdit.id}`);
            });
        },
        cancel: function () {
            this.$router.push(`/user/profile/${this.$route.params.userId}`);
        },
        test: function(){
            console.log(this.userProfileEdit.id)
        },
        
    }
}
</script>