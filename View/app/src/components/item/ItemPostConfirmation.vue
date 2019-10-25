<template>
<div id="add-blog">
    <link href="https://cdn.jsdelivr.net/npm/vuesax/dist/vuesax.css" rel="stylesheet">

    <h2>Item Posted</h2>
    <p>Your item is uploaded. You will be redirectly to the homepage shortly..</p>

</div>
</template>

<script>
import VueRouter from "vue"

export default {
    name: "ItemPostConfirmation",
    data() {
        return {
            router: new VueRouter(),
        }
    },
    methods: {

    },
    created: function () {
        setTimeout(() => {
            this.$router.push("/");
        }, 5000);
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
