<template>
  <div>
    <div class="row" v-if="itemDetail != undefined">
      <div class="col-sm">
        <div class="card mt-4">
          <div class="card-body">
            <img
              class="card-img-top"
              v-bind:src="'data:image/png;base64,' + itemDetail.item.thumbnail"
              alt
            />
            <div class="itemInfo">
              <h3 class="card-title">{{ itemDetail.item.title }}</h3>
              <h4>${{ itemDetail.item.price }}</h4>
              <p class="card-text">{{ itemDetail.item.description }}</p>
              <p class="card-text">{{ itemDetail.condition }}</p>
              <p class="card-text">
                by
                <span
                  class="user_name"
                  v-bind:id="itemDetail.item.user_id"
                  @click="toUserProfile(itemDetail.username)"
                >{{itemDetail.username}}</span>
              </p>
              <small class="text-muted">{{ itemDetail.item.createDate.slice(0, 10) }}</small>
              <div id="fav-btn" v-if="user.isLoggedIn" @click="favItem">
                <i class="material-icons" v-if="favorited">favorite</i>
                <i class="material-icons" v-if="!favorited">favorite_border</i>
              </div>
              
              <div id="edit-btn" @click="toItemEdit1">Edit</div>
              <p v-if="itemDetail.num != 0 && user.userId == itemDetail.item.user_id">
                This item is favorited by {{ itemDetail.num }} people!
                <span
                  class="user_name"
                  @click="viewAllUser"
                >View All</span>
              </p>
              <div v-if="userList.length != 0">
                <div v-for="user in userList" v-bind:key="user.id">
                  <span class="user_name" @click="toUserProfile(user.username)">{{user.username}}</span>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="card mt-4" v-if="similarItem">
          <div class="card-body">
            <img
              class="card-img-top"
              v-bind:src="'data:image/png;base64,' + itemDetail.thumbnail"
              alt
            />
            <h3 class="card-title">{{ itemDetail.title }}</h3>
            <h4>${{ itemDetail.price }}</h4>
            <p class="card-text">{{ itemDetail.description }}</p>
            <small class="text-muted">
              {{
              itemDetail.createDate.slice(0, 10)
              }}
            </small>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ItemDataService from "./ItemDataService";
import UserAccount from "../user/account/UserAccount";

export default {
  name: "ItemDetail",
  data() {
    return {
      itemDetail: undefined,
      itemId: 0,
      user: UserAccount,
      favorited: false,
      similarItem: undefined,
      userList: []
    };
  },
  methods: {
    refresh() {
      this.itemId = this.$route.query.id;
      console.log(this.$route.query.id)
      ItemDataService.getItemById(this.itemId).then(res => {
        this.itemDetail = res.data;
        console.log("This is the itemDetail:" + this.itemDetail);
      });
    },
    favItem() {
      ItemDataService.favoriteOneItem(this.user.userId, this.itemId).then(
        res => {
          this.favorited = res.data;
        }
      );
    },
    viewAllUser() {
      ItemDataService.getAllUsersFavorited(this.itemId).then(res => {
        this.userList = res.data;
      });
    },
    toUserProfile(event) {
      // console.log(event);
      this.$router.push("/user/profile/" + event);
    },
    toItemEdit() {
      // console.log(event);
      window.location.href = "/item/edit?id=" + this.itemId;
      //this.$router.push("/item/edit/" + this.itemId);
    },
    toItemEdit1() {
      // console.log(event);
      window.location.href = "/item/edit1?id=" + this.itemId;
      //this.$router.push("/item/edit/" + this.itemId);
    }
  },
  created() {
    this.refresh();
    ItemDataService.checkItemFavorited(this.user.userId, this.itemId).then(
      res => {
        this.favorited = res.data;
      }
    );
    ItemDataService.getSimilarItems().then(res => {
      this.similarItem = res.data;
    });
    ItemDataService.addToRecentlyViewed(UserAccount.userId, this.itemId)
      .then
      // res => {
      //   console.log("Added");
      // }
      ();
  }
};
</script>

<style scoped>
.card-img-top {
  float: left;
  width: 30vw !important;
  min-width: 500px;
}

#fav-btn:hover {
  cursor: pointer;
}

.card {
  width: 70vw;
  left: 15vw;
}
.itemInfo {
  margin-top: 20px;
}
img {
  width: 500px;
  height: 500px;
}
.user_name {
  -webkit-text-fill-color: blue;
}
.user_name:hover {
  text-decoration: underline;
  cursor: pointer;
}
</style>
