import axios from 'axios'

// const INSTRUCTOR = 'in28minutes'
const URL = 'http://localhost:23333'

// const INSTRUCTOR_API_URL = `${COURSE_API_URL}/instructors/${INSTRUCTOR}`

class ItemDataService {

    // eslint-disable-next-line
    getAllItems() {
        //console.log('executed service')
        return axios.get(`${URL}/item/list/all`);
    }

    // eslint-disable-next-line
    getItemById(id) {
        //console.log('executed service')
        return axios.get(`${URL}/items/${id}`);
    }

    // eslint-disable-next-line
    postItem(item){
        return axios.post(`${URL}/postItem`, item);
    }
    // deleteCourse(name, id) {
    //     //console.log('executed service')
    //     return axios.delete(`${INSTRUCTOR_API_URL}/courses/${id}`);
    // }

    // // eslint-disable-next-line
    // updateCourse(name, id, course) {
    //     //console.log('executed service')
    //     return axios.put(`${INSTRUCTOR_API_URL}/courses/${id}`, course);
    // }

    // // eslint-disable-next-line
    // createCourse(name, course) {
    //     //console.log('executed service')
    //     return axios.post(`${INSTRUCTOR_API_URL}/courses/`, course);
    // }
}

export default new ItemDataService()