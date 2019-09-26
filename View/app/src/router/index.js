import Vue from 'vue';
import Router from 'vue-router';
import HomePage from '@/components/HomePage';
import item from '@/components/item/item'
import ItemPost from '@/components/item/ItemPost'
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
      path: '/item',
      name: 'item',
      component: item
    },
    {
      path: '/ItemPost',
      name: 'ItemPost',
      component: ItemPost
    },
    {
      path: '/Profile',
      name: 'Profile',
      component: Profile
    },

  ]
});