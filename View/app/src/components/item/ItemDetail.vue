<script>
export default {
    name:'ItemDetail'
}
const itemDetails = [
  {
    itemId: 1,
    itemTitle: 'Title',
    itemDescription: 'Description',
    itemSeller: 'Seller',
    itemPrice: 'Price',
  }
]
Vue.component('itemDetail-component', {
  template: `
    <div class="itemDetail">
      <div class="box">
        <article class="media">
          <div class="media-left">
            <figure class="image is-64x64">
              <img :src="itemList.img" alt="Image">
            </figure>
          </div>
          <div class="media-content">
            <div class="content">
              <p>
                <strong>{{itemDetail.itemTitle}}</strong> 
                <br>
                {{itemDetail.itemDescription}}
                
              </p>
              <p>
                Seller: {{itemDetail.itemSeller}}
                Price: {{itemDetail.itemPrice}}
              </p>
            </div>
          </div>
        </article>
      </div>
    </div> 
  `,
  props: {
    itemList: Object
  }
});
</script>