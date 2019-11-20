<template>
<div id="add-blog">
    <link href="https://cdn.jsdelivr.net/npm/vuesax/dist/vuesax.css" rel="stylesheet">

    <h2>Registration Completed</h2>
    <p>Please check your email to confirm your account registration</p>

</div>
</template>

<script>
export default {
    name: "UserRegisterConfirmation",
    data() {
        return {
            emailConfirmed: false,
        }
    },
    methods: {

    },
    created: function () {

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
