import axios from "axios";

const URL = "http://localhost:23333";

class UserDataService {
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
    return axios.post(`${URL}/user/profile/edit/`, updatedProfile);
  }
  getItemsByUser(userId) {
    return axios.get(`${URL}/user/item/${userId}`);
  }

  getFavoritedItem(userId) {
    return axios.get(`${URL}/user/item/fav/${userId}`);
  }

  getRecentlyViewedItem(userId) {
    return axios.get(`${URL}/user/item/recently_viewed/${userId}`);
  }
}

export default new UserDataService();
