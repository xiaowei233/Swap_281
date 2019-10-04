<template>
  <div id="app">
    <img alt="Vue logo" src="./assets/logo.png">
    <Navbar 
    
    />
    <router-view></router-view>
  </div>
</template>

<script>
import Navbar from './components/Navbar.vue';  

export default {
  name: 'app',
  components:{
    Navbar,
  }
}
</script>

<style>
@import url(https://unpkg.com/bootstrap@4.1.0/dist/css/bootstrap.min.css);

</style>
