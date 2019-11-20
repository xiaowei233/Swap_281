import Vue from 'vue';
import Router from 'vue-router';
import HelloWorld from '@/components/HelloWorld'
import ItemList from '@/components/item/ItemList'

import ItemPost from '@/components/item/ItemPost'
import UserRegister from '@/components/user/UserRegister'
import UserRegisterConfirmation from '@/components/user/UserRegisterConfirmation'
import UserEmailVerification from '@/components/user/UserEmailVerification'
import UserSignIn from '@/components/user/UserSignIn'
import ItemPostConfirmation from '@/components/item/ItemPostConfirmation'
import ItemDetail from '@/components/item/ItemDetail'
import UserProfile from '@/components/user/UserProfile'
import UserProfileEdit from '@/components/user/UserProfileEdit'
import UserProfileEditConfirmation from '@/components/user/UserProfileEditConfirmation'
Vue.use(Router);
const originalPush = Router.prototype.push;
Router.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err);
};
export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/item/list/all',
      name: 'ItemList',
      component: ItemList
    },
    {
      path: '/item/detail/:id',
      name: 'ItemDetail',
      component: ItemDetail
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
    },
    {
      path: '/user/profile/:id',
      name: 'UserProfile',
      component: UserProfile
    },
    {
      path: '/user/profile-edit/:userId',
      name: 'UserProfileEdit',
      component: UserProfileEdit
    },
    {
      path: '/user/profile-edit-confirm/:userId',
      name: 'UserProfileEditConfirmation',
      component: UserProfileEditConfirmation
    },


  ]
});