<template>
  <div id="add-blog">
    <link
      href="https://cdn.jsdelivr.net/npm/vuesax/dist/vuesax.css"
      rel="stylesheet"
    />

    <h2>Post an item</h2>
    <form>
      <label>Item Title:</label>
      <input
        class="form-control"
        type="text"
        v-model.lazy="itemPost.title"
        required
      />

      <label>Item Description:</label>
      <textarea
        class="form-control"
        v-model.lazy.trim="itemPost.description"
        required
      ></textarea>

      <label>Item Price:</label>
      <!-- <input type="number" v-model.lazy="itemPost.price" required /> -->
      <vue-numeric
        class="form-control"
        currency="$"
        v-bind:precision="2"
        v-model.lazy="itemPost.price"
        required
      ></vue-numeric>

      <label>Item Category</label>
      <b-form-select
        v-model="itemPost.categoryId"
        :options="categories"
        required
      ></b-form-select>

      <label>Item Condition</label>
      <b-form-select
        v-model="itemPost.condition_id"
        :options="conditions"
        required
      ></b-form-select>

      <label>Item Photo</label>

      <!-- <vs-upload text="Upload an image" @change="onFileSelect" limit=1 accept="image/*" v-bind:show-upload-button="false" /> -->
      <croppa
        id="popup-croppa"
        v-model="myCroppa"
        :height="280"
        :width="280"
        :quality="1"
        accept="image/*"
        class="resizable-croppa"
        :prevent-white-space="true"
      ></croppa>
      <hr />
      <button class="btn btn-outline-secondary" v-on:click.prevent="post">
        Confirm
      </button>
    </form>

    <vs-popup class="holamundo" title="Notification" :active.sync="popup">
      <p>
        Yolo
      </p>
    </vs-popup>
  </div>
</template>

<script>
// Imports
import ItemDataService from "./ItemDataService";
import "material-icons/iconfont/material-icons.css";

import Vue from "vue";
import VueNumeric from "vue-numeric";
import UserAccount from "../user/account/UserAccount";

Vue.use(VueNumeric);

export default {
  name: "ItemPost",
  data() {
    return {
      categories: [],
      conditions: [],
      itemPost: {
        title: "",
        description: "",
        price: 0,
        categoryId: 0,
        thumbnail: null,
        user_id: 0,
        condition_id: 0
      },
      myCroppa: {},
      resizing: false,
      resizableW: 200,
      resizableH: 200,
      popup: false,
      item_id: 0
    };
  },
  methods: {
    post: function() {
      this.itemPost.user_id = UserAccount.userId;
      this.itemPost.thumbnail = this.myCroppa.generateDataUrl("image/*", 1);
      this.itemPost.thumbnail = this.itemPost.thumbnail.slice(
        22,
        this.itemPost.thumbnail.length
      );

      ItemDataService.postNewItem(this.itemPost).then(res => {
        this.$router.push("/item/post-confirm?id=" + res.data.id);
        this.popup = true;
      });
    },
    onFileSelect(e, f) {
      var reader = new FileReader();
      var fileByteArray = [];
      this.oipenccrop = true;
      reader.readAsArrayBuffer(f[0]);
      reader.onloadend = function(evt) {
        if (evt.target.readyState == FileReader.DONE) {
          var arrayBuffer = evt.target.result,
            array = new Uint8Array(arrayBuffer);
          for (var i = 0; i < array.length; i++) {
            fileByteArray.push(array[i]);
          }
        }
      };

      this.itemPost.thumbnail = fileByteArray;
    },
    onResizeTouchStart() {
      this.resizing = true;
    },
    onResizeTouchMove(evt) {
      if (!this.resizing) return;
      document.documentElement.style.cursor = "nwse-resize";
      this.croppa = document.querySelector(".resizable-croppa");
      this.resizableW = evt.clientX - this.croppa.getBoundingClientRect().left;
      this.resizableH = evt.clientY - this.croppa.getBoundingClientRect().top;
    },
    onResizeTouchEnd() {
      this.resizing = false;
      document.documentElement.style.cursor = "default";
    }
  },
  mounted() {
    document.documentElement.addEventListener("mousemove", evt => {
      evt.preventDefault();
      this.onResizeTouchMove(evt);
    });
    document.documentElement.addEventListener("mouseup", evt => {
      evt.preventDefault();
      this.onResizeTouchEnd(evt);
    });
  },
  created: function() {
    ItemDataService.getItemCategoryFilter().then(res => {
      this.categories = res.data;
      this.categories.forEach(e => {
        e.text = e.category;
        e.value = e.id;
      });
    });

    ItemDataService.getItemConditionFilter().then(res => {
      this.conditions = res.data;
      this.conditions.forEach(e => {
        e.text = e.condition;
        e.value = e.id;
      });
    });
  }
};
</script>

<style scoped>
#add-blog * {
  box-sizing: border-box;
}

#add-blog {
  margin: 20px auto;
  max-width: 500px;
}

label {
  display: block;
  margin: 20px 0 10px;
}

input[type="text"],
textarea {
  display: block;
  width: 100%;
  padding: 8px;
}

h3 {
  margin-top: 10px;
}

#checkboxes input {
  display: inline-block;
  margin-right: 10px;
}

#checkboxes label {
  display: inline-block;
  margin-top: 0;
}
#popup-croppa {
  border: 2px dashed grey;
  border-radius: 5px;
  width: 300px;
  margin-left: calc(50% - 150px);
}
#popup-croppa:hover {
  cursor: pointer;
}
</style>
