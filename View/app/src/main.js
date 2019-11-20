import Vue from 'vue';
import App from './App.vue';
import router from './router/index.js';
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

import Croppa from 'vue-croppa'

Vue.use(Croppa) 
Vue.use(BootstrapVue);
Vue.config.productionTip = false

// Register routes


new Vue({
  render: h => h(App),
  router: router,
}).$mount('#app')
