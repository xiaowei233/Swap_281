import axios from 'axios'

const URL = 'http://localhost:23333'

class ItemDataService {

    // eslint-disable-next-line
    getAllItems() {
        return axios.get(`${URL}/item/list/all`);
    }

    getItemById(id) {
        return axios.get(`${URL}/item/detail/${id}`);
    }

    postNewItem(newItem) {
        return axios.post(`${URL}/item/detail/post`, newItem);
    }

    getItemCategoryFilter() {
        return axios.get(`${URL}/item/detail/category-drop-down`);
    }

    // eslint-disable-next-line
    getCategoryList() {
        return axios.get(`${URL}/item/list/dropdown`);
    }
    
    getItemConditionFilter() {
        return axios.get(`${URL}/item/detail/condition-drop-down`);
    }

    lowToHigh() {
        return axios.get(`${URL}/item/list/lowToHigh`);
    }
    highToLow() {
        return axios.get(`${URL}/item/list/highToLow`);
    }
    earlyToLate() {
        return axios.get(`${URL}/item/list/earlyToLate`);
    }
    lateToEarly() {
        return axios.get(`${URL}/item/list/lateToEarly`);
    }

    getItemByCategory(id) {
        return axios.get(`${URL}/item/list/category/${id}`);
    }

    // item/list/filter?category=1,2,3,4,5
    getItemByCategoryIdList(idList) {
        if (idList.length !== 0)
            return axios.get(`${URL}/item/list/filter?category=${idList}`)
        return axios.get(`${URL}/item/list/all`);
    }
}

export default new ItemDataService()