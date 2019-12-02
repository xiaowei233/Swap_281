<template>
  <div class="container">
    <h1>My Posted Items</h1>
    <div class="row">
      <div class="col-lg-4 col-md-6 mb-4" v-for="item in items" v-bind:key="item.id">
        <div class="card h-100 customclass">
          <a href="#">
            <img
              @click="toItemDetail(item.item.id)"
              class="card-img-top"
              v-bind:src="'data:image/png;base64,' + item.item.thumbnail"
              alt
            />
          </a>
          <div class="card-body">
            <h4 class="card-title">
              <a @click="toItemDetail(item.item.id)">{{ item.item.title }}</a>
            </h4>
            <h5>${{ item.item.price }}</h5>
            <p class="card-text">{{ item.item.description }}</p>
            <!-- <p class="card-text">Condition: {{ item.condition }}</p> -->
            <p
              class="user_name"
              v-if="item.num != 0"
              @click="toItemDetail(item.item.id)"
            >This item is favorited by {{ item.num }} people!</p>
          </div>
          <div class="card-footer">
            <small class="text-muted">{{ item.item.createDate.slice(0, 10) }}</small>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import UserDataService from "../UserDataService";
import UserAccount from "../account/UserAccount";

export default {
  name: "UserItem",
  data() {
    return {
      items: []
    };
  },
  methods: {
    refresh() {
      UserDataService.getItemsByUser(UserAccount.userId).then(res => {
        this.items = res.data;
      });
    },

    toItemDetail(id) {
      window.location.href = "/itemDetail?id=" + id;
    }
  },
  created() {
    this.refresh();
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.customclass image:hover,
.customclass .card-title:hover {
  -webkit-text-fill-color: blue;
  cursor: pointer;
}
img {
  position: relative;
  margin-top: 10px;
  width: 300px;
  height: 300px;
  background-position: 50% 50%;
  background-repeat: no-repeat;
  background-size: cover;
}
.user_name {
  margin-bottom: 0;
  -webkit-text-fill-color: blue;
}
.user_name:hover {
  text-decoration: underline;
  cursor: pointer;
}
</style>
