<template>
<body>
  <!-- Page Content -->
  <div class="container">
    <div v-if="carousel" class="carousel slide my-4">
      <div class="carousel-inner">
        <div class="carousel-item active" v-bind="carousel" @click="toItemDetail(carousel.item.id)">
          <img
            class="d-block img-fluid carouselImage"
            v-bind:src="'data:image/png;base64,'+ carousel.item.thumbnail"
          />
        </div>
      </div>
      <a class="carousel-control-prev controller" @click="onPrev()">
        <i class="material-icons">arrow_left</i>
      </a>
      <a class="carousel-control-next right-controller" @click="onNext()">
        <i class="material-icons">arrow_right</i>
      </a>
    </div>

    <div class="row">
      <div class="col-lg-3">
        <h1 class="my-4">Swap</h1>
        <div class="list-group">
          <a href="#" class="list-group-item">Category 1</a>
          <a href="#" class="list-group-item">Category 2</a>
          <a href="#" class="list-group-item">Category 3</a>
        </div>
      </div>
      <!-- /.col-lg-3 -->

      <div class="col-lg-9">
        <div class="row">
          <div class="col-lg-4 col-md-6 mb-4" v-for="item in items" v-bind:key="item.item.id">
            <div class="card h-100">
              <a href="#" @click="toItemDetail(item.item.id)">
                <img
                  class="card-img-top cardImages"
                  v-bind:src="'data:image/png;base64,'+ item.item.thumbnail"
                  alt
                />
              </a>
              <div class="card-body">
                <h4 class="card-title">
                  <a href="#" @click="toItemDetail(item.item.id)">{{item.item.title}}</a>
                </h4>
                <h5>${{item.item.price}}</h5>
                <p class="card-text">{{item.item.description}}</p>
                <p class="card-text">
                  by
                  <span class="user_name">{{item.full_name}}</span>
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

export default {
  name: "HomePage",
  data() {
    return {
      items: [],
      carousalpics: [],
      carousel: undefined,
      carouselId: 0
    };
  },
  methods: {
    async refresh() {
      ItemDataService.getAllItems().then(res => {
        this.items = res.data;
        var length = this.items.length;
        if (this.items[length - 3] != null) {
          let lastThree = [
            this.items[length - 1],
            this.items[length - 2],
            this.items[length - 3]
          ];
          this.carousel = lastThree[0];
          this.carousalpics = lastThree;
        }
      });
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
  background: linear-gradient(90deg, rgba(0, 0, 0, 1), rgba(0, 0, 0, 0));
}
.right-controller {
  height: 100%;
  background: linear-gradient(-90deg, rgba(0, 0, 0, 1), rgba(0, 0, 0, 0));
}
.user_name {
  -webkit-text-fill-color: blue;
  text-decoration: underline;
}
.user_name:hover {
  cursor: pointer;
}
.post-date {
  text-align: center;
}
</style>

