<template>
    <div id="add-blog">
        <h2>Post an item</h2>
        <form v-if="!submitted">
            <label>Item Title:</label>
            <input type="text" v-model.lazy="itemPost.title" required />
            <label>Item Description:</label>
            <textarea v-model.lazy.trim="itemPost.description"></textarea>
            <div id="checkboxes">
                <p>Item Categories:</p>
                <label>Cate1</label>
                <input type="checkbox" value="cate1" v-model="itemPost.categories" />
                <label>Cate2</label>
                <input type="checkbox" value="cate2" v-model="itemPost.categories" />
                <label>Cate3</label>
                <input type="checkbox" value="cate3" v-model="itemPost.categories" />
                <label>Cate4</label>
                <input type="checkbox" value="cate4" v-model="itemPost.categories" />
            </div>
            <label>Trading Method:</label>
            <select v-model="itemPost.tradingMethod">
                // eslint-disable-next-line
                <option v-for="tradingMethod in tradingMethods">{{ tradingMethod }}</option>
            </select>
            <hr />
            <button v-on:click.prevent="post">Confirm</button>
        </form>
        <div v-if="submitted">
            <h3>Thanks for adding your post</h3>
        </div>
        
    </div>
</template>

<script>
// Imports
export default {
    data () {
        return {
            itemPost: {
                title: '',
                description: '',
                categories: [],
                tradingMethod: ''
            },
            tradingMethods: ['Sell', 'Trade', 'Rent'],
            submitted: false
        }
    },
    methods: {
        post: function(){
            this.$http.post('http://jsonplaceholder.typicode.com/posts', {
                title: this.itemPost.title,
                body: this.itemPost.description,
                userId: 1
            }).then(function(data){
                
                this.submitted = true;
            });
        }
    }
}
</script>

<style scoped>
#add-blog *{
    box-sizing: border-box;
}
#add-blog{
    margin: 20px auto;
    max-width: 500px;
}
label{
    display: block;
    margin: 20px 0 10px;
}
input[type="text"], textarea{
    display: block;
    width: 100%;
    padding: 8px;
}

h3{
    margin-top: 10px;
}
#checkboxes input{
    display: inline-block;
    margin-right: 10px;
}
#checkboxes label{
    display: inline-block;
    margin-top: 0;
}
</style>