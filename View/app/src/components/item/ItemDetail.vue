<template>
  <div>
    <div class="row">
      <div class="col-sm">
        <div class="card mt-4">
          <div class="card-body">
            <img class="card-img-top" v-bind:src="'data:image/png;base64,'+ itemDetail.thumbnail" alt />
            <h3 class="card-title">{{itemDetail.title}}</h3>
            <h4>${{itemDetail.price}}</h4>
            <p class="card-text">{{itemDetail.description}}</p>
            <small class="text-muted">{{itemDetail.createDate.slice(0, 10)}}</small>

          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ItemDataService from "./ItemDataService";

export default {
  name: "ItemDetail",
  data() {
    return {
      itemDetail: {},
      itemId: 0
    };
  },
  methods: {
    refresh() {
      this.itemId = this.$route.query.id;
      
      ItemDataService.getItemById(this.itemId).then(res => {
        this.itemDetail = res.data;
      });
    }
  },
  created() {
    this.refresh();
  }
};
</script>


<style scoped>
.card-img-top {
    width: 50% !important;
    min-width: 500px;
}
img {
  width: 500px;
  height: 500px;
}
</style>