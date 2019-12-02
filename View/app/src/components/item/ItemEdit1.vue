<template>
  <div id="add-blog">
    <link
      href="https://cdn.jsdelivr.net/npm/vuesax/dist/vuesax.css"
      rel="stylesheet"
    />

    <h2>Update your item</h2>
    <form>
      <label>Item Title:</label>
      <input
        class="form-control"
        type="text"
        v-model.lazy="itemDetail.item.title"
        required
      />

      <label>Item Description:</label>
      <textarea
        class="form-control"
        v-model.lazy.trim="itemDetail.item.description"
        required
      ></textarea>

      <label>Item Price:</label>
      <!-- <input type="number" v-model.lazy="itemPost.price" required /> -->
      <vue-numeric
        class="form-control"
        currency="$"
        v-bind:precision="2"
        v-model.lazy="itemDetail.item.price"
        required
      ></vue-numeric>

      <label>Item Category</label>
      <b-form-select
        v-model="itemDetail.item.categoryId"
        :options="categories"
        required
      ></b-form-select>

      <label>Item Condition</label>
      <b-form-select
        v-model="itemDetail.item.condition_id"
        :options="conditions"
        required
      ></b-form-select>

      <label>Item Photo</label>

      <!-- <vs-upload text="Upload an image" @change="onFileSelect" limit=1 accept="image/*" v-bind:show-upload-button="false" /> -->
      

      <croppa
        id="popup-croppa"
        v-model="myCroppa"
        :initial-image="initialImage"
        :height="280"
        :width="280"
        
        :quality="1"
        accept="image/*"
        class="resizable-croppa"
        :prevent-white-space="true"
      >
      <img
      v-bind:src="'data:image/png;base64,' + itemDetail.item.thumbnail"
      slot="initial"
      />
      </croppa>
      <hr />
      <button class="btn btn-outline-secondary" v-on:click.prevent="save">
        Update
      </button>
      <button class="btn btn-outline-secondary" v-on:click.prevent="deleteItem">
        Delete
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
import Vue from 'vue'
import ItemDataService from "./ItemDataService";
import UserAccount from "../user/account/UserAccount";
import vsPopup from 'vuesax'
Vue.use(vsPopup);
export default {
  name: "ItemEdit1",
  data() {
    return {
      popupActivo:false,
      popupActivo2: false,
      isHidden: true,
      myCroppa: {},
      itemPost: {
        title: "",
        description: "",
        price: 0,
        categoryId: 0,
        thumbnail: null,
        user_id: 0,
        condition_id: 0
      },
      isEditing: true,
      itemDetail: undefined,
      itemId: 0,
      user: UserAccount,
      favorited: false,
      similarItem: undefined,
      userList: [],
      categories:[],
      conditions:[],
      initialImage: null,
    };
  },
  methods: {
    
    refresh() {
      this.itemId = this.$route.query.id;
      ItemDataService.getItemById(this.itemId).then(res => {
        this.itemDetail = res.data;
        console.log("This is the itemDetail:" + this.itemDetail);
      });
    },
    favItem() {
      ItemDataService.favoriteOneItem(this.user.userId, this.itemId).then(
        res => {
          this.favorited = res.data;
        }
      );
    },
    viewAllUser() {
      ItemDataService.getAllUsersFavorited(this.itemId).then(res => {
        this.userList = res.data;
      });
    },
    toUserProfile(event) {
      // console.log(event);
      this.$router.push("/user/profile/" + event);
    },
    change() {
      this.isEditing = !this.isEditing;

      console.log(this.isEditing);
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
    },
    save(){
        if (this.isEditing == true) {
        this.itemPost.title = this.itemDetail.item.title;
        this.itemPost.description = this.itemDetail.item.description;
        this.itemPost.price = this.itemDetail.item.price;
        this.itemPost.categoryId = this.itemDetail.item.categoryId;
        this.itemPost.user_id = UserAccount.userId;
        this.itemPost.condition_id = this.itemDetail.item.condition_id;
        this.itemPost.thumbnail = this.myCroppa.generateDataUrl("image/*", 1);
        this.itemPost.thumbnail = this.itemPost.thumbnail.slice(
          22,
          this.itemPost.thumbnail.length
        );

        ItemDataService.updateItem(this.itemId,this.itemPost).then(res => {
            this.$router.push("/user/items");
            window.location.reload();
          //this.refresh();
          console.log(res);
          //this.$router.push("/item/post-confirm?id=" + res.data.id);
          //this.popup = true;
        });
      }
    },
    deleteItem(){
        ItemDataService.deleteItem(this.itemId).then(()=>{
            this.$router.push("/user/items");
            window.location.reload();
        });
        
    },
    dltBtn(){
        this.popupActivo=true;
        console.log(this.popupActivo)
    }
  },
  created() {
    this.refresh();
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
  
    ItemDataService.checkItemFavorited(this.user.userId, this.itemId).then(
      res => {
        this.favorited = res.data;
      }
    );
    ItemDataService.getSimilarItems().then(res => {
      this.similarItem = res.data;
    });
    ItemDataService.addToRecentlyViewed(UserAccount.userId, this.itemId)
      .then
      // res => {
      //   console.log("Added");
      // }
      ();
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
    var image = new Image()
    // Notice: it's necessary to set "crossorigin" attribute before "src" attribute.
    image.setAttribute('crossorigin', 'anonymous')
    image.src = 'data:image/png;base64,' +itemDetail.item.thumbnail;
    this.initialImage = image
    this.croppa.refresh()
  },
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
