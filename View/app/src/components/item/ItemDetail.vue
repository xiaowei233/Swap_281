<template>
  <div>
    <div class="row" v-if="itemDetail != undefined">
      <div class="col-sm">
        <div class="card mt-4">
          <div class="card-body">
            <img
              class="card-img-top"
              v-bind:src="'data:image/png;base64,' + itemDetail.thumbnail"
              alt
            />
            <div class="itemInfo">
              <h3 class="card-title">{{ itemDetail.title }}</h3>
              <h4>${{ itemDetail.price }}</h4>
              <p class="card-text">{{ itemDetail.description }}</p>
              <small class="text-muted">{{ itemDetail.createDate.slice(0, 10) }}</small>
              <div id="fav-btn" v-if="user.isLoggedIn" @click="favItem">
                <i class="material-icons" v-if="favorited">favorite</i>
                <i class="material-icons" v-if="!favorited">favorite_border</i>
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
      similarItem: undefined
    };
  },
  methods: {
    refresh() {
      this.itemId = this.$route.query.id;
      ItemDataService.getItemById(this.itemId).then(res => {
        this.itemDetail = res.data;
      });
    },
    favItem() {
      ItemDataService.favoriteOneItem(this.user.userId, this.itemId).then(
        res => {
          this.favorited = res.data;
        }
      );
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
    ItemDataService.addToRecentlyViewed(UserAccount.userId, this.itemId).then(
      res => {
        console.log("Added");
      }
    );
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
</style>
