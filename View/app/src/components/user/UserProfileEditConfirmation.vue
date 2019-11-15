<template>
<div id="add-blog">
    <link href="https://cdn.jsdelivr.net/npm/vuesax/dist/vuesax.css" rel="stylesheet">

    
    <p>Your profile is updated. You will be redirectly to the homepage shortly..</p>

</div>
</template>

<script>
import VueRouter from "vue"

export default {
    name: "UserProfileEditConfirmation",
    data() {
        return {
            userId: 0,
            router: new VueRouter(),
        }
    },
    methods: {

    },
    created: function () {
        setTimeout(() => {
            
            this.$router.go(-2)
            //this.$router.push(`/user/profile/${this.userId}`);
        }, 1500);
    }
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
</style>
