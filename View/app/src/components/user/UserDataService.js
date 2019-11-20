import axios from 'axios'

const URL = 'http://localhost:23333'

class UserDataService {

    // eslint-disable-next-line
    registerNewUser(data) {
        return axios.post(`${URL}/user/account/register`, data);
    }

    compareUsername(username) {
        return axios.get(`${URL}/user/account/username-check/${username}`);
    }

    verifyEmail(token) {
        return axios.put(`${URL}/user/account/verify-email/${token}`);
    }

    auth(username, password) {
        return axios.put(`${URL}/user/account/auth/${username}/${password}`);
    }
    getUserInfo(userId) {
        return axios.get(`${URL}/user/profile/user-profile-info/${userId}`);
    }
    getUserPostedItems(userId) {
        return axios.get(`${URL}/user/profile/user-posted-items/${userId}`);
    }
    edit(updatedProfile) {
        console.log(updatedProfile)
        return axios.post(`${URL}/user/profile/edit/`, updatedProfile);
    }
    getItemsByUser(userId)
    {
        return axios.get(`${URL}/user/items/${userId}`)
    }
}

export default new UserDataService()