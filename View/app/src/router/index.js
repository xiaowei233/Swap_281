import Vue from 'vue';
import Router from 'vue-router';
import HomePage from '@/components/HomePage';
import ItemList from '@/components/item/ItemList'
import ItemPost from '@/components/item/ItemPost'
import UserRegister from '@/components/user/UserRegister'
import UserRegisterConfirmation from '@/components/user/UserRegisterConfirmation'
import UserEmailVerification from '@/components/user/UserEmailVerification'
import UserSignIn from '@/components/user/UserSignIn'
import ItemPostConfirmation from '@/components/item/ItemPostConfirmation'
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
    {
      path: '/item/post',
      name: 'ItemPost',
      component: ItemPost
    },
    {
      path: '/user/register',
      name: 'UserRegister',
      component: UserRegister
    },
    {
      path: '/user/register-confirm',
      name: 'UserRegisterConfirmation',
      component: UserRegisterConfirmation
    },
    {
      path: '/user/email-verify/:token',
      name: 'UserEmailVerification',
      component: UserEmailVerification
    },
    {
      path: '/user/sign-in',
      name: 'UserSignIn',
      component: UserSignIn
    },
    {
      path: '/item/post-confirm',
      name: 'ItemPostConfirmation',
      component: ItemPostConfirmation
    }
  ]
});