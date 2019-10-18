<template>
  <div class="container">
    <h3>All Items</h3>

    <div class="container">
      <table class="table">
        <thead>
          <tr>
            <th>Id</th>
            <th>Title</th>
            <th>Description</th>
            <th>
              <div>
                <b-dropdown  text="Price" >
                  <b-dropdown-item v-on:click="lowToHigh()">Low-To-High</b-dropdown-item>
                  <b-dropdown-item v-on:click="highToLow()">High-To-Low</b-dropdown-item>
                </b-dropdown>
              </div>
            </th>
            <th>
                <b-dropdown  text="Date" >
                  <b-dropdown-item v-on:click="earlyToLate()">Ascending</b-dropdown-item>
                  <b-dropdown-item v-on:click="lateToEarly()">Descending</b-dropdown-item>
                </b-dropdown>

            </th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in items" v-bind:key="item.id" v-on:click="getItemById(item)">
            <td>{{item.id}}</td>
            <td>{{item.title}}</td>
            <td>{{item.description}}</td>
            <td>$ {{item.price}}</td>
            <td>{{item.createDate}}</td>
          </tr>
        </tbody>
      </table>
      <div class="row">
        <button class="btn btn-success" v-on:click="postItem()">Post New Item</button>
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
    }
  },
  created() {
    this.refresh();
  }
};
</script>