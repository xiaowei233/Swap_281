<template>
  <div class="container">
    <div class="cat">
          <vs-select
        placeholder="Select your categories"
        multiple
        label="Categories"
        v-model="dropdown"
      >
        <vs-select-item
          :key="index"
          :value="item.id"
          :text="item.category"
          v-for="(item,index) in dropdown"
          @input="executeLoader($event)"
          @click="executeLoader($event)"
        />
      </vs-select>
</div>
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
import Vue from 'vue'

import { vsSelect } from 'vuesax'
import 'vuesax/dist/vuesax.css'

import ItemDataService from "./ItemDataService";
Vue.use(vsSelect)

export default {
  name: "ItemList",
  data() {
    return {
      items: [],
      dropdown: []
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
    executeLoader(selectedItems) {
      console.log(selectedItems);
      let idList = [];
      for (let i = 0; i < selectedItems.length; i++)
        idList.push(selectedItems[i].id);
      ItemDataService.getItemByCategoryIdList(idList).then(res => {
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
.cat {
  margin: 20px;
}
</style>