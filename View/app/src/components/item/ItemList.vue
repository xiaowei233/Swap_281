<template>
  <div class="container">
    <div>
      <vs-select
        placeholder="Choose categories"
        multiple
        v-model="selectedItems"
        @input="executeLoader($event)"
        class="select"
      >
        <vs-select-item
          :value="item.id"
          :text="item.category"
          v-for="item in dropdown"
          v-bind:key="item.id"
        />
      </vs-select>
    </div>
    <div class="row">
      <div class="col-lg-4 col-md-6 mb-4" v-for="item in items" v-bind:key="item.id">
        <div class="card h-100 customclass" @click="toItemDetail(item.id)">
          <a href="#">
            <img class="card-img-top" v-bind:src="'data:image/png;base64,' + item.thumbnail" alt />
          </a>
          <div class="card-body">
            <h4 class="card-title">
              <a>{{ item.title }}</a>
            </h4>
            <h5>${{ item.price }}</h5>
            <p class="card-text">{{ item.description }}</p>
          </div>
          <div class="card-footer">
            <small class="text-muted">{{ item.createDate }}</small>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ItemDataService from "./ItemDataService";

import "vuesax/dist/vuesax.css";
export default {
  name: "ItemList",
  data() {
    return {
      dropdown: [],
      items: [],
      selectedItems: []
    };
  },
  methods: {
    refresh() {
      ItemDataService.getAllItems().then(res => {
        this.items = res.data;
      });
      ItemDataService.getCategoryList().then(res => {
        this.dropdown = res.data;
      });
    },
    lowToHigh() {
      ItemDataService.lowToHigh().then(res => {
        this.items = res.data;
      });
    },
    highToLow() {
      ItemDataService.highToLow().then(res => {
        this.items = res.data;
      });
    },
    earlyToLate() {
      ItemDataService.earlyToLate().then(res => {
        this.items = res.data;
      });
    },
    lateToEarly() {
      ItemDataService.lateToEarly().then(res => {
        this.items = res.data;
      });
      ItemDataService.getCategoryList().then(res => {
        this.dropdown = res.data;
      });
    },
    toItemDetail(id) {
      window.location.href = "/itemDetail?id=" + id;
    },
    executeLoader(selectedItems) {
      let idList = [];
      for (let i = 0; i < selectedItems.length; i++)
        idList.push(selectedItems[i]);
      ItemDataService.getItemByCategoryIdList(idList).then(res => {
        this.items = res.data;
      });
    }
  },
  created() {
    console.log(this.$route);
    console.log(this.$route.query.search);
    if (this.$route.query.search)
      ItemDataService.search(this.$route.query.search).then(res => {
        console.log(res.data);
        this.items = res.data;
      });
    else this.refresh();
  },
  destroyed() {
    window.localStorage.setItem("search", " ");
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
}
.select {
  width: 50%;
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
</style>
