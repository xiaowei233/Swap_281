<template>
  <div id="add-blog">
    <link href="https://cdn.jsdelivr.net/npm/vuesax/dist/vuesax.css" rel="stylesheet" />

    <h2>Item Posted</h2>
    <p>Your item has now been posted. You will be redirected to your posting shortly.</p>
  </div>
</template>

<script>
export default {
  name: "ItemPostConfirmation",
  data() {
    return {};
  },
  methods: {},
  created: function() {
    var itemId = this.$route.query.id;
    setTimeout(() => {
      this.$router.push("/itemDetail?id=" + itemId);
    }, 2000);
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
