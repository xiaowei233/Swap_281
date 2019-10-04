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
                            <tr v-for="item in items" v-bind:key="item.id">
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
import Vue from 'vue';

import ItemDataService from '../../service';

export default {
    name: "Items",
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
        postItem() {
            this.$router.push(`/ItemPost`);
        },
        // updateCourse(id) {
        //     this.$router.push(`/items/${id}`);
        // },
        // deleteCourse(id) {
        //     CourseDataService.deleteCourse(this.INSTRUCTOR, id)
        //     .then(() => {
        //         this.refreshCourses();
        //     });
        // },
    },
    created() {
        this.refresh();
    }
}

</script>