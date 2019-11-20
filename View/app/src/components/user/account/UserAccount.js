class UserAccount {
  UserAccount() {
    this.userId = window.localStorage.getItem("userId");
    this.username = window.localStorage.getItem("username");
    this.isLoggedIn = window.localStorage.getItem("isLoggedIn") ? true : false;
    this.session;
  }

  UpdateUserInfo(username, userId) {
    this.userId = userId;
    this.username = username;
    this.isLoggedIn = true;
    window.localStorage.setItem("userId", this.userId);
    window.localStorage.setItem("username", this.username);
    window.localStorage.setItem("isLoggedIn", this.isLoggedIn);
  }

  SignOut() {
    window.localStorage.clear();
  }
}

export default new UserAccount();
