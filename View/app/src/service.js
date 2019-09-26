import axios from 'axios'

// const INSTRUCTOR = 'in28minutes'
const API_URL = 'http://localhost:8080'
// const INSTRUCTOR_API_URL = `${COURSE_API_URL}/instructors/${INSTRUCTOR}`

class ItemDataService {

    // eslint-disable-next-line
    getAllItems() {
        //console.log('executed service')
        return axios.get(`${API_URL}/items`);
    }

    // eslint-disable-next-line
    getItemById(id) {
        //console.log('executed service')
        return axios.get(`${API_URL}/items/${id}`);
    }

    // // eslint-disable-next-line
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