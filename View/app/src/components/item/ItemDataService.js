import axios from 'axios'

const URL = 'http://localhost:23333'

class ItemDataService {

    // eslint-disable-next-line
    getAllItems() {
        return axios.get(`${URL}/item/list/all`);
    }

    // eslint-disable-next-line
    getItemById(id) {
        return axios.get(`${URL}/item/detail/${id}`);
    }

    postNewItem(newItem) {
        return axios.post(`${URL}/item/detail/post`, newItem);
    }

    getItemCategoryFilter(){
        return axios.get(`${URL}/item/detail/category-drop-down`);
    }

    lowToHigh(){
        return axios.get(`${URL}/item/list/lowToHigh`);
    }
    highToLow(){
        return axios.get(`${URL}/item/list/highToLow`);
    }
    earlyToLate(){
        return axios.get(`${URL}/item/list/earlyToLate`);
    }
    lateToEarly(){
        return axios.get(`${URL}/item/list/lateToEarly`);

    getItemById(itemId) {
        return axios.get(`${URL}/item/detail/${itemId}`);

    }
}

export default new ItemDataService()