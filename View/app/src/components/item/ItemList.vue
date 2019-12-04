<template>
  <div class="container">

    <div class="row">
      <vs-select
        class="select"
        placeholder="Choose categories"
        multiple
        v-model="selectedItems"
        @input="executeLoader($event)"
      >
        <vs-select-item
          :value="item.id"
          :text="item.category"
          v-for="item in dropdown"
          v-bind:key="item.id"
        />
      </vs-select>

      <vs-select
        class="sort"
        placeholder="Sort By ..."
        single
        v-model="option"
        @input="toSort($event)"
      >
        <vs-select-item
          :value="obj.value"
          :text="obj.text"
          v-for="obj in options"
          v-bind:key="obj.id"
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
  // import CubeSpin from 'vue-loading-spinner/components/Cube'
    
import "vuesax/dist/vuesax.css";
export default {
  name: "ItemList",
  data() {
    return {
      dropdown: [],
      items: [],
      selectedItems: [],
      option: 2,
      keyword: "",
      sort: "createDate-DESC",
      options: [
        { text: "Price: Low to High", value: 0 },
        { text: "Price: High to Low", value: 1 },
        { text: "Most Recent to Least Recent", value: 2 },
        { text: "Least Recent to Most Recent", value: 3 }
      ],
      loader: null, 
    };
  },
  methods: {
    refresh() {
      ItemDataService.getAllItems().then(res => {
        this.items = res.data;
      });
    },
    toUserProfile(event) {
      // console.log(event);
      this.$router.push("/user/profile/" + event);
    },
    toItemDetail(id) {
      window.location.href = "/itemDetail?id=" + id;
    },
    executeLoader(selectedItems) {
      this.selectedItems = selectedItems;
      ItemDataService.search(this.keyword, selectedItems, this.sort).then(
        res => {
          this.items = res.data;
        }
      );
    },
    toSort(option) {
      console.log("from option");
      this.option = option;
      if (option === 0) this.sort = "price-ASC";
      else if (option === 1) this.sort = "price-DESC";
      else if (option === 2) this.sort = "createDate-DESC";
      else if (option === 3) this.sort = "createDate-ASC";

      this.loader = this.$loading.show({
                  // Optional parameters
                  container: this.fullPage ? null : this.$refs.formContainer,
                  canCancel: true,
                  onCancel: this.onCancel,
                });

      ItemDataService.search(this.keyword, this.selectedItems, this.sort).then(res => {
        this.items = res.data;
        this.loader.hide();
      });
    }
  },
  created() {
    this.loader = this.$loading.show({
                  // Optional parameters
                  container: this.fullPage ? null : this.$refs.formContainer,
                  canCancel: true,
                  onCancel: this.onCancel,
                });

    this.keyword =
      this.$route.query.search == undefined ? "" : this.$route.query.search;
    ItemDataService.getCategoryList().then(res => {
      this.dropdown = res.data;
    });
    ItemDataService.search(this.keyword, this.selectedItems, this.sort).then(res => {
      // console.log(res.data);
      this.loader.hide()
      this.items = res.data;
    });
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
  margin-top: 10px;
  margin-left: 15px;
}
.sort {
  margin-top: 10px;
  margin-left: 10px;
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
.selectL {
  margin-top: 30px !important;
  position: absolute;
}
</style>
