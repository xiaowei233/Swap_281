<template>
  <div id="add-blog">
    <link href="https://cdn.jsdelivr.net/npm/vuesax/dist/vuesax.css" rel="stylesheet" />

    <div v-if="emailVerified">
      <h2>Account Email Verified</h2>
      <p>You completed the account registratoin process, you will be redirected to homepage shortly.</p>
    </div>

    <div v-if="error">
      <h2>Account Email Verification Failed</h2>
      <p>Something went wrong. Check later.</p>
    </div>
  </div>
</template>

<script>
import UserDataService from "../UserDataService";

export default {
  name: "UserEmailVerification",
  data() {
    return {
      token: null,
      emailVerified: false,
      error: false
    };
  },
  methods: {},
  created: function() {
    this.token = this.$route.params.token;
    UserDataService.verifyEmail(this.token).then(res => {
      this.emailVerified = res.data;
      if (this.emailVerified) {
        setTimeout(() => {
          this.$router.push("/");
          window.location.reload();
        }, 5000);
      } else {
        this.error = true;
      }
    });
  }
};
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
