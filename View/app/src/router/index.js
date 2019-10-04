import Vue from 'vue';
import Router from 'vue-router';
import HomePage from '@/components/HomePage';
import ItemList from '@/components/item/ItemList'
import Profile from '@/components/Profile'
Vue.use(Router);
 
export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'HomePage',
      component: HomePage
    },
    {
      path: '/item/list/all',
      name: 'ItemList',
      component: ItemList
    },
    {
      path: '/Profile',
      name: 'Profile',
      component: Profile
    },

  ]
});