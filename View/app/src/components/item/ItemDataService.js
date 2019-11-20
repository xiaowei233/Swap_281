import axios from "axios";

const URL = "http://localhost:23333";

class ItemDataService {
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

    getItemByCategoryIdList(idList) {
        if (idList.length !== 0)
            return axios.get(`${URL}/item/list/filter?category=${idList}`);
        return axios.get(`${URL}/item/list/all`);
    }

    favoriteOneItem(userId, itemId) {
        return axios.get(
            `${URL}/item/detail/new-favorite?userId=${userId}&itemId=${itemId}`
        );
    }

    checkItemFavorited(userId, itemId) {
        return axios.get(
            `${URL}/item/detail/check-favorite?userId=${userId}&itemId=${itemId}`
        );
    }

    getSimilarItems(itemId) {
        return axios.get(`${URL}/item/detail/similar_item/${itemId}`);
    }

    addToRecentlyViewed(userId, itemId) {
        return axios.get(
            `${URL}/user/item/add_recently_viewed/${userId}/${itemId}`
        );
    }

    search(keyword) {
        return axios.get(`${URL}/item/list/search/${keyword}`
        )
    }
}

export default new ItemDataService();
