<template>
  <body>
    <!-- Page Content -->
    <div class="container">
      <div class="row">
        <div class="col-lg-3">
          <h1 class="my-4">Your Lists</h1>
          <div class="list-group">
            <a href="#" class="list-group-item">Wish List</a>
            <a href="#" class="list-group-item">Shopping List</a>
            <a href="#" class="list-group-item">Birthday Gifts</a>
            <a href="#" class="list-group-item">Room Essentials</a>
          </div>
        </div>
        <div class="col">
          <Cards />
          <Cards />
          <Cards />
        </div>
        <!-- row -->
      </div>
      <!-- /card deck-->
    </div>

    <!-- /.row -->

    <!-- /.container -->
  </body>
</template>

<script>
import Cards from "../../shared/Cards.vue";

export default {
  name: "UserList",
  components: {
    Cards
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
myCardClass {
  margin-bottom: 100px;
}
</style>
