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
                                <th>Price</th>
                                <th>Date</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="item in items" v-bind:key="item.id" v-on:click="getItemById(item.id)">
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
import ItemDataService from './ItemDataService';

export default {
    name: "ItemList",
    data() {
        return {
            items: [],
            
        }
    },
    methods: {
        refresh() {
            ItemDataService.getAllItems()
            .then((res) => {
                this.items = res.data;
            });
        },
        getItemById(id){
            this.$router.push(`/item/${id}`)
        }

    },
    created() {
        this.refresh();
    }
}

</script>