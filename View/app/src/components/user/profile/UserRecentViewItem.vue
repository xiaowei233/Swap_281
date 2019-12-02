<template>
  <div class="container">
    <h1>My Recently Viewed Items</h1>
    <div class="row">
      <div class="col-lg-4 col-md-6 mb-4" v-for="item in items" v-bind:key="item.item.id">
        <div class="card h-100">
          <a href="#">
            <img
              class="card-img-top customclass"
              @click="toItemDetail(item.item.id)"
              v-bind:src="'data:image/png;base64,'+ item.item.thumbnail"
              alt
            />
          </a>
          <div class="card-body">
            <h4 class="card-title itemTitle" @click="toItemDetail(item.item.id)">
              <a>{{item.item.title}}</a>
            </h4>
            <h5>${{item.item.price}}</h5>
            <p class="card-text">{{item.item.description}}</p>
            <p class="card-text">Condition: {{item.condition}}</p>
            <p class="card-text">
              by
              <span
                class="user_name"
                v-bind:id="item.item.user_id"
                @click="toUserProfile(item.username)"
              >{{item.username}}</span>
            </p>
          </div>
          <div class="card-footer">
            <small class="text-muted">{{item.item.createDate.slice(0,10)}}</small>
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
  name: "UserRecentlyViewedItem",
  data() {
    return {
      items: []
    };
  },
  methods: {
    refresh() {
      UserDataService.getRecentlyViewedItem(UserAccount.userId).then(res => {
        this.items = res.data;
      });
    },
    toItemDetail(id) {
      window.location.href = "/itemDetail?id=" + id;
    },
    toUserProfile(event) {
      // console.log(event);
      this.$router.push("/user/profile/" + event);
    }
  },
  created() {
    this.refresh();
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.customclass:hover {
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
  object-fit: cover;
}
.itemTitle:hover {
  -webkit-text-fill-color: blue;
  cursor: pointer;
}
.user_name {
  -webkit-text-fill-color: blue;
}
.user_name:hover {
  text-decoration: underline;
  cursor: pointer;
}
</style>