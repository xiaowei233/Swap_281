<template>
    <div class="container">
                <h3>Single Items</h3>
                
                <div class="container">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Id</th>
                                
                                <th>Description</th>
                                <th>Photo</th>
                                <th>ItemId</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-if="itemDetail">
                                <td>{{itemDetail.id}}</td>
                                
                                <td>{{itemDetail.description}}</td>
                                <td>{{itemDetail.photo}}</td>
                                <td>{{itemDetail.itemId}}</td>
                            </tr>
                        </tbody>
                    </table>
                    
                </div>
            </div>
</template>
<script>
import ItemDataService from './ItemDataService';

export default {
    name: "ItemDetail",
    data() {
        return {
            itemDetail: {},
        }
    },
    methods: {
        refresh() {
            
            ItemDataService.getItemById(this.$router.currentRoute.params.id)
            .then((res) => {
              console.log(res.data)
              this.itemDetail = res.data;
              console.log(this.itemDetail)

            });
        },
    },
    created() {
        this.refresh();
    }
}
</script>