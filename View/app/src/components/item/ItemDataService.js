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
    }
}

export default new ItemDataService()