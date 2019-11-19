<template>
  <div class="container">
    <div class="row">
      <div class="col-lg-4 col-md-6 mb-4" v-for="item in items" v-bind:key="item.id">
        <div class="card h-100 customclass" @click="toItemDetail(item.id)">
          <a href="#">
            <img class="card-img-top" v-bind:src="'data:image/png;base64,'+ item.thumbnail" alt />
          </a>
          <div class="card-body">
            <h4 class="card-title">
              <a>{{item.title}}</a>
            </h4>
            <h5>${{item.price}}</h5>
            <p class="card-text">{{item.description}}</p>
          </div>
          <div class="card-footer">
            <small class="text-muted">{{item.createDate}}</small>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ItemDataService from "./ItemDataService";

export default {
  name: "ItemList",
  data() {
    return {
      items: []
    };
  },
  methods: {
    refresh() {
      ItemDataService.getAllItems().then(res => {
        this.items = res.data;
      });
    },
    getItemById(item) {
      this.$router.push(`/item/detail/${item.itemDetailId}`);
    },
    lowToHigh(){
        ItemDataService.lowToHigh().then(res => {
        this.items = res.data;
      });
    //   this.$router.push(`/item/list/lowToHigh`);
    },
    highToLow(){
        ItemDataService.highToLow().then(res => {
        this.items = res.data;});
        // this.$router.push(`/item/istl/highToLow`);
    },
    earlyToLate(){
        ItemDataService.earlyToLate().then(res => {
        this.items = res.data;});
    },
    lateToEarly(){
        ItemDataService.lateToEarly().then(res => {
        this.items = res.data;});
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
.customclass:hover {
  cursor: pointer;
}
img {
    position: relative;
    margin-top: 10px;
    width:  300px;
    height: 300px;
    background-position: 50% 50%;
    background-repeat:   no-repeat;
    background-size:     cover;
}

</style>
