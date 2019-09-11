<template>
    <div id = "board">
            <div id="category">  
                All:<input type="checkbox" value="all" id="all" >
                School:<input type="checkbox" value="school" id="school" >
                Car:<input type="checkbox" value="car" id="car" >
                Electronics:<input type="checkbox" value="electronics" id="electronics" >
                Furniture:<input type="checkbox" value="furniture" id="furniture" onclick="getFurniture()">        
            </div>

            <input type="text" id="Add-newItem" value="Search...">
            <button id="Add-newMessageBtn">Search</button>


            <div id="price">
                <input type="checkbox" id="high_to_low" value="high_to_low" >price from high to low<br>
                <input type="checkbox" id="low_to_high" value="low_to_high" >price from low to high<br>
            </div>

            <button id="postItemBtn" onclick="redirectToPostItemPage()">Post New Item</button>

            <div class="itemListBody" id="Message"></div>
    </div>
</template>

<script>
export default {
  name: 'HomePage',
  props: {
    msg: String
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
