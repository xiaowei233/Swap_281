import Vue from 'vue'
import Router from 'vue-router'

import HelloWorld from '../components/HelloWorld.vue';
import HomePage from '../components/HomePage.vue';
import ItemList from '../components/ItemList.vue';
//import Items from '../components/Items.vue';
import ItemDetail from '../components/ItemDetail.vue';

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
        path: '/test',
        name: 'testPage',
        component: HomePage
    },
    {
      path: '/item',
      name: 'Items',
      component: ItemList
    },
    {
      path: '/itemDetail',
      name: 'itemDetail',
      component: ItemDetail
    },
  ],
  mode: 'history'
})