<template>
  <div class="container">
    <div class="row">
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

      <div class="col-lg-12">
        <div class="row">
          <div class="col-lg-3 col-md-6 mb-4" v-for="item in items" v-bind:key="item.item.id">
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
    toUserProfile(event) {
      // console.log(event);
      this.$router.push("/user/profile/" + event);
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
        // this.items = res.data;
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
.itemTitle:hover {
  -webkit-text-fill-color: blue;
  cursor: pointer;
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

.user_name {
  -webkit-text-fill-color: blue;
}
.user_name:hover {
  text-decoration: underline;
  cursor: pointer;
}
</style>
