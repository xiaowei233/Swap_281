
<template>
    <div id="itemList" class="columns">
        <div class="column">
            <itemList-component v-for="itemList in itemLists" :itemList="itemList" />
        </div>
    </div>
</template>
<script>
export default {
    name:'ItemList'
}

const examples = [
  {
    id: 1,
    name: 'James',
    handle: '@jokerjames',
    img: '',
    tweet: "you don't succeed, dust yourself off and try again.",
    likes: 10,
  },
  { 
    id: 2,
    name: 'Fatima',
    handle: '@fantasticfatima',
    img: '',
    tweet: 'Better late than never but never late is better.',
    likes: 12,
  },
  {
    id: 3,
    name: 'Xin',
    handle: '@xeroxin',
    img: '',
    tweet: 'Beauty in the struggle, ugliness in the success.',
    likes: 18,
  }
]


Vue.component('itemList-component', {
  template: `
    <div class="itemList">
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
                <strong>{{itemList.name}}</strong> <small>{{itemList.handle}}</small>
                <br>
                {{itemList.tweet}}
              </p>
            </div>
              <div class="level-left">
                <a class="level-item">
                  <span class="icon is-small"><i class="fas fa-heart"></i></span>
                  <span class="likes">{{itemList.likes}}</span>
                </a>
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

new Vue({
  el: '#itemList',
  data: {
    itemLists
  }
});
</script>