import Vue from "vue";
import Router from "vue-router";
import HomePage from "@/components/HomePage";

//Item Components
import ItemList from "@/components/item/ItemList";
import ItemDetail from "@/components/item/ItemDetail";
import ItemPost from "@/components/item/ItemPost";
import ItemPostConfirmation from "@/components/item/ItemPostConfirmation";

//User Account Components
import UserRegister from "@/components/user/account/UserRegister";
import UserRegisterConfirmation from "@/components/user/account/UserRegisterConfirmation";
import UserEmailVerification from "@/components/user/account/UserEmailVerification";
import UserSignIn from "@/components/user/account/UserSignIn";

//User Profile Components
import UserItem from "@/components/user/profile/UserItem";
import UserList from "@/components/user/profile/UserList";
import UserProfile from "@/components/user/profile/UserProfile";
import UserProfileEdit from "@/components/user/profile/UserProfileEdit";
import UserProfileEditConfirmation from "@/components/user/profile/UserProfileEditConfirmation";
import UserRecentlyViewedItem from "../components/user/profile/UserRecentViewItem";
import UserFavoritedItem from "../components/user/profile/UserFavoritedItem";

Vue.use(Router);

export default new Router({
  mode: "history",
  routes: [
    {
      path: "/",
      name: "HomePage",
      component: HomePage
    },
    {
      path: "/item/list/all",
      name: "ItemList",
      component: ItemList
    },
    {
      path: "/itemDetail",
      name: "ItemDetail",
      component: ItemDetail
    },
    {
      path: "/item/post",
      name: "ItemPost",
      component: ItemPost
    },
    {
      path: "/user/register",
      name: "UserRegister",
      component: UserRegister
    },
    {
      path: "/user/register-confirm",
      name: "UserRegisterConfirmation",
      component: UserRegisterConfirmation
    },
    {
      path: "/user/email-verify/:token",
      name: "UserEmailVerification",
      component: UserEmailVerification
    },
    {
      path: "/user/sign-in",
      name: "UserSignIn",
      component: UserSignIn
    },
    {
      path: "/user/list",
      name: "UserList",
      component: UserList
    },
    {
      path: "/user/items",
      name: "UserItem",
      component: UserItem
    },
    {
      path: "/item/post-confirm",
      name: "ItemPostConfirmation",
      component: ItemPostConfirmation
    },
    {
      path: "/user/profile/:id",
      name: "UserProfile",
      component: UserProfile
    },
    {
      path: "/user/profile-edit/:userId",
      name: "UserProfileEdit",
      component: UserProfileEdit
    },
    {
      path: "/user/profile-edit-confirm/:userId",
      name: "UserProfileEditConfirmation",
      component: UserProfileEditConfirmation
    },
    {
      path: "/user/recently-viewed",
      name: "UserRecentlyViewedItem",
      component: UserRecentlyViewedItem
    },
    {
      path: "user/favorited",
      name: "UserFavoritedItem",
      component: UserFavoritedItem
    }
  ]
});
