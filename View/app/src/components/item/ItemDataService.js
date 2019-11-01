import axios from 'axios'

const URL = 'http://localhost:23333'

class ItemDataService {

    // eslint-disable-next-line
    getAllItems() {
        return axios.get(`${URL}/item/list/all`);
    }


    postNewItem(newItem) {
        return axios.post(`${URL}/item/detail/post`, newItem);
    }

    getItemCategoryFilter(){
        return axios.get(`${URL}/item/detail/category-drop-down`);
    }
    // eslint-disable-next-line
    getItemById(itemId) {
        return axios.get(`${URL}/item/detail/${itemId}`);
    }
}

export default new ItemDataService()