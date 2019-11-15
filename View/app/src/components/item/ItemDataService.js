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

    getItemCategoryFilter() {
        return axios.get(`${URL}/item/detail/category-drop-down`);
    }
    // eslint-disable-next-line
    getItemById(itemId) {
        return axios.get(`${URL}/item/detail/${itemId}`);
    }

    // item/list/filter?category=1,2,3,4,5
    getItemByCategoryIdList(idList) {
        //console.log(idList)
        if (idList.length !== 0 && idList !== undefined)
            return axios.get(`${URL}/item/list/filter?category=${idList}`)
        return axios.get(`${URL}/item/list/all`);
    }

    // eslint-disable-next-line
    getCategoryList() {
        return axios.get(`${URL}/item/list/dropdown`);
    }
}

export default new ItemDataService()