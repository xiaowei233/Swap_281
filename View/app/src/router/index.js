import Vue from 'vue';
import Router from 'vue-router';
import HomePage from '@/components/HomePage';
import item from '@/components/item/item'
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
      name: 'item',
      component: item
    },
    {
      path: '/Profile',
      name: 'Profile',
      component: Profile
    },

  ]
});