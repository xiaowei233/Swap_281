<template>
<body>
  <!-- Page Content -->
  <div class="container">
    <!-- <p>Most Recently Posted Items</p> -->
    <!-- <h3 id="recentlyPostedId">Recently Posted</h3> -->
    <div v-if="carousel" class="carousel slide my-4">
      <div class="carousel-inner">
        <div class="carousel-item active" v-bind="carousel" @click="toItemDetail(carousel.item.id)">
          <img
            class="d-block img-fluid carouselImage"
            v-bind:src="'data:image/png;base64,'+ carousel.item.thumbnail"
          />
          <h5 id="carouselTitle">{{carousel.item.title}}</h5>
          <h5 id="carouselDescription">{{carousel.item.description}}</h5>
   

        </div>
      </div>
      <a class="carousel-control-prev controller" @click="onPrev()">
        <i class="material-icons">arrow_left</i>
      </a>
      <a class="carousel-control-next right-controller" @click="onNext()">
        <i class="material-icons">arrow_right</i>
      </a>
    </div>

    <hr class="hrBelowC" />
    <div class="d-flex flex-row-reverse">
      <p class="itemListLink" @click="goToItemList()">All Items</p>
    </div>

    <div class="row">
      <div class="col-lg-12">
        <div class="row">
          <div
            class="col-lg-3 col-md-6 mb-4"
            v-for="item in items.slice(0, 12)"
            v-bind:key="item.item.id"
          >
            <div class="card h-100">
              <a href="#" @click="toItemDetail(item.item.id)">
                <img
                  class="card-img-top cardImages"
                  v-bind:src="'data:image/png;base64,'+ item.item.thumbnail"
                  alt
                />
              </a>
              <div class="card-body">
                <h5 class="card-title itemTitle">
                  <h5 @click="toItemDetail(item.item.id)">{{item.item.title}}</h5>
                </h5>
                <h5>${{item.item.price}}</h5>
                <!-- <p class="card-text">{{item.item.description}}</p> -->
                <p class="card-text">Condition: {{item.condition}}</p>
                <p class="card-text">
                  by
                  <span
                    class="user_name"
                    v-bind:id="item.user_id"
                    @click="toUserProfile(item.username)"
                  >{{item.username}}</span>
                </p>
              </div>
              <div class="card-footer post-date">
                <small class="text-muted">{{item.item.createDate.slice(0, 10)}}</small>
              </div>
            </div>
          </div>
        </div>

        <!-- /.row -->
      </div>
      <!-- /.col-lg-9 -->
    </div>
    <!-- /.row -->
  </div>
  <!-- /.container -->
</body>
</template>

<script>
import ItemDataService from "./item/ItemDataService";
import "vuesax/dist/vuesax.css";

export default {
  name: "HomePage",
  data() {
    return {
      items: [],
      carousalpics: [],
      carousel: undefined,
      carouselId: 0,
      dropdown: [],
      selectedItems: []
    };
  },
  methods: {
    async refresh() {
      ItemDataService.getAllItems().then(res => {
        this.items = res.data;
        if (this.items[2] != null) {
          let lastThree = [this.items[0], this.items[1], this.items[2]];
          this.carousel = lastThree[0];
          this.carousalpics = lastThree;
        }
      });

      ItemDataService.getCategoryList().then(res => {
        this.dropdown = res.data;
      });
    },
    goToItemList() {
      this.$router.push("item/list");
    },
    toUserProfile(event) {
      // console.log(event);
      this.$router.push("/user/profile/" + event);
    },
    toItemDetail(id) {
      window.location.href = "/itemDetail?id=" + id;
    },
    onPrev() {
      this.carouselId = this.carouselId - 1 < 0 ? 2 : this.carouselId - 1;
      this.carousel = this.carousalpics[this.carouselId];
    },
    onNext() {
      this.carouselId = this.carouselId + 1 >= 3 ? 0 : this.carouselId + 1;
      this.carousel = this.carousalpics[this.carouselId];
    },
    executeLoader(selectedItems) {
      let idList = [];
      for (let i = 0; i < selectedItems.length; i++)
        idList.push(selectedItems[i]);
      ItemDataService.getItemByCategoryIdList(idList).then(res => {
        this.items = res.data;
        console.log(this.items[0].item.user_id);
      });
    }
  },
  created() {
    this.refresh();
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.cardImages {
  position: relative;
  margin-top: 10px;
  width: 100%;
  height: 200px;
  object-fit: cover;
  background-position: 50% 50%;
  background-repeat: no-repeat;
  background-size: cover;
}
.carousel-item {
  width: 80vw;
  height: 50vh;
}
.carouselImage {
  position: relative;
  margin-top: 10px;
  width: 30vw;
  object-fit: cover;
  margin-left: calc(50% - 17.5vw);
  background-position: 50% 50%;
  background-repeat: no-repeat;
  background-size: cover;
}
.active:hover {
  cursor: pointer;
}
.material-icons {
  color: white;
  -webkit-text-fill-color: white;
  font-size: 54px !important;
}
.controller {
  height: 100%;
  width: 8%;
  background: linear-gradient(90deg, rgba(0, 0, 0, 1), rgba(0, 0, 0, 0));
}
.right-controller {
  height: 100%;
  width: 8%;
  background: linear-gradient(-90deg, rgba(0, 0, 0, 1), rgba(0, 0, 0, 0));
}
.user_name {
  -webkit-text-fill-color: blue;
}
.user_name:hover {
  text-decoration: underline;
  cursor: pointer;
}
.post-date {
  text-align: center;
}
#SwapHeader {
  text-align: center;
}
.select {
  width: 100%;
  margin-bottom: 10px;
}
.vs-select--item {
  padding-left: 2em;
}
button > span {
  padding-left: 2em;
}

.vs-select--item.con-icon.activex {
  padding-left: 2em !important;
}
.itemListLink:hover {
  cursor: pointer;
  text-decoration: underline;
}
.itemTitle:hover {
  -webkit-text-fill-color: blue;
  cursor: pointer;
}
.itemListLink {
  -webkit-text-fill-color: blue;
}
.hrBelowC {
  border: 5px solid rgb(184, 177, 177);
}
</style>

