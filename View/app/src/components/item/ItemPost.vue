<template>
<div id="add-blog">
    <link href="https://cdn.jsdelivr.net/npm/vuesax/dist/vuesax.css" rel="stylesheet">

    <h2>Post an item</h2>
    <form v-if="!submitted">
        <label>Item Title:</label>
        <input class="form-control" type="text" v-model.lazy="itemPost.title" required />

        <label>Item Description:</label>
        <textarea class="form-control" v-model.lazy.trim="itemPost.description" required></textarea>

        <label>Item Price:</label>
        <!-- <input type="number" v-model.lazy="itemPost.price" required /> -->
        <vue-numeric class="form-control" currency="$" v-bind:precision="2" v-model.lazy="itemPost.price" required></vue-numeric>

        <label>Item Category</label>
        <b-form-select v-model="itemPost.categoryId" :options="categories"></b-form-select>

        <label>Item Photo</label>

        <vs-upload text="Upload an image" @change="onFileSelect" limit=1 accept="image/*" v-bind:show-upload-button="false" />

        <hr />
        <button class="btn btn-outline-secondary" v-on:click.prevent="post">Confirm</button>
    </form>
</div>
</template>

<script>
// Imports
import ItemDataService from './ItemDataService';
import 'material-icons/iconfont/material-icons.css';
import VueRouter from "vue"

import Vue from 'vue'
import VueNumeric from 'vue-numeric'
import UserAccount from '../user/UserAccount';

Vue.use(VueNumeric)

export default {
    name: "ItemPost",
    data() {
        return {
            categories: [],
            itemPost: {
                title: '',
                description: '',
                price: 0,
                categoryId: 0,
                thumbnail: null,
                user_id: 0,
            },
            router: new VueRouter(),
        }
    },
    methods: {
        post: function () {
            this.itemPost.user_id = UserAccount.userId;
            console.log(this.itemPost)
            ItemDataService.postNewItem(this.itemPost).then((res)=>{
                this.$router.push("/item/post-confirm");
                console.log(res.data);
            });
        },
        onFileSelect(e, f) {
            var reader = new FileReader();
            var fileByteArray = [];
            reader.readAsArrayBuffer(f[0]);
            reader.onloadend = function (evt) {
                if (evt.target.readyState == FileReader.DONE) {
                    var arrayBuffer = evt.target.result,
                        array = new Uint8Array(arrayBuffer);
                    for (var i = 0; i < array.length; i++) {
                        fileByteArray.push(array[i]);
                    }
                }
            }

            this.itemPost.thumbnail = fileByteArray;
        }
    },
    created: function () {
        ItemDataService.getItemCategoryFilter().then((res) => {
            this.categories = res.data;
            this.categories.forEach((e) => {
                e.text = e.category;
                e.value = e.id;
            })
        });
    }
}
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
</style>
