import Vue from 'vue';
import Router from 'vue-router';

import HomePage from './src/components/HomePage.vue';

Vue.use(Router);

const routes = 
{
    routes: [
      {
        path: '/',
        name: 'HomePage',
        component: HomePage
      },
    ]
}


export default router;