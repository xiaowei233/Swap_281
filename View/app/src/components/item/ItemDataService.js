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
}

export default new ItemDataService()