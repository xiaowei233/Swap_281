<template>
  <div id="profile-body">
    <link
      href="https://unpkg.com/vue-croppa/dist/vue-croppa.min.css"
      rel="stylesheet"
      type="text/css"
    />

    <link
      href="https://cdn.jsdelivr.net/npm/vuesax/dist/vuesax.css"
      rel="stylesheet"
    />

    <div class="container">
      <div class=" emp-profile">
        <form id="formId">
          <div class="row">
            <div class="col-md-4">
              <div class="profile-img">
                <img
                  v-if="HasImage"
                  :height="280"
                  :width="280"
                  v-bind:src="dataUrl"
                  alt="No Image"
                />
                <img
                  v-else
                  :height="280"
                  :width="280"
                  src="../../../assets/no_img.jpeg"
                  alt="No Image"
                />
                <vs-popup
                  class="holamundo popup"
                  title="Update Your Avatar"
                  :active.sync="popupActivo2"
                >
                  <croppa
                    id="popup-croppa"
                    v-model="myCroppa"
                    :height="280"
                    :width="280"
                    :quality="1"
                    accept="image/*"
                    class="resizable-croppa"
                    :prevent-white-space="true"
                  ></croppa>
                  <div id="fakeBtnConfirm" @click="confirmAvatar">Confirm</div>
                </vs-popup>
                <div id="fakeBtnChange" @click="changeAvatar">
                  Change Avatar
                </div>
              </div>
            </div>
            <div class="col-md-6">
              <div class="profile-head">
                <h3>Profile</h3>
                <br />
                <br />
                <br />
                <ul class="nav nav-tabs" id="myTab" role="tablist">
                  <li class="nav-item">
                    <a
                      class="nav-link active"
                      id="home-tab"
                      data-toggle="tab"
                      href="#home"
                      role="tab"
                      aria-controls="home"
                      aria-selected="true"
                      >Personal Information</a
                    >
                  </li>
                  <li class="nav-item">
                    <a
                      class="nav-link"
                      id="profile-tab"
                      data-toggle="tab"
                      href="#profile"
                      role="tab"
                      aria-controls="profile"
                      aria-selected="false"
                      >Visited Items</a
                    >
                  </li>
                </ul>
              </div>
            </div>
            <div class="col-md-2">
              <input
                type="submit"
                class="profile-edit-btn"
                name="btnAddMore"
                value="Edit Profile"
                @click="edit"
              />
            </div>
          </div>
          <div class="row">
            <div class="col-md-4">
              <div class="profile-work">
                <p>Posted Items</p>

                <a href>item1</a>
                <br />
                <a href>item2</a>
                <br />
              </div>
            </div>
            <div class="col-md-8">
              <div class="tab-content profile-tab" id="myTabContent">
                <div
                  class="tab-pane fade show active"
                  id="home"
                  role="tabpanel"
                  aria-labelledby="home-tab"
                >
                  <div class="row">
                    <div class="col-md-3">
                      <label>User Name</label>
                    </div>
                    <div class="col-md-3">
                      <p>{{ userProfile.username }}</p>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-3">
                      <label>Email</label>
                    </div>
                    <div class="col-md-3">
                      <p>{{ userProfile.email }}</p>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-3">
                      <label>Name</label>
                    </div>
                    <div class="col-md-3">
                      <p>
                        {{ userProfile.first_name }} {{ userProfile.last_name }}
                      </p>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
// Imports
// eslint-disable-next-line
import UserDataService from "../UserDataService";
import VueRouter from "vue";
import Vue from "vue";
import vsPopup from "vuesax";
Vue.use(vsPopup);
export default {
  name: "UserProfile",
  data() {
    return {
      popupActivo2: false,
      HasImage: true,
      isHidden: true,
      myCroppa: {},
      userAvatar: {},
      resizing: false,
      resizableW: 200,
      resizableH: 200,
      userProfile: [],
      userPostedItems: [],
      userId: "",
      router: new VueRouter(),
      dataUrl:
        "https://i1.wp.com/metro.co.uk/wp-content/uploads/2019/02/sei_54328243-2cab.jpg?quality=90&strip=all&zoom=1&resize=644%2C378&ssl=1"
    };
  },
  mounted() {
    document.documentElement.addEventListener("mousemove", evt => {
      evt.preventDefault();
      this.onResizeTouchMove(evt);
    });
    document.documentElement.addEventListener("mouseup", evt => {
      evt.preventDefault();
      this.onResizeTouchEnd(evt);
    });
  },
  methods: {
    changeAvatar() {
      this.popupActivo2 = true;
    },
    confirmAvatar() {
      this.HasImage = true;
      this.dataUrl = this.myCroppa.generateDataUrl("image/*", 1);
      this.popupActivo2 = false;
      if (this.dataUrl == "") {
        this.HasImage = false;
        this.popupActivo2 = false;
      }
    },
    onResizeTouchStart() {
      this.resizing = true;
    },
    onResizeTouchMove(evt) {
      if (!this.resizing) return;
      document.documentElement.style.cursor = "nwse-resize";
      this.croppa = document.querySelector(".resizable-croppa");
      this.resizableW = evt.clientX - this.croppa.getBoundingClientRect().left;
      this.resizableH = evt.clientY - this.croppa.getBoundingClientRect().top;
    },
    onResizeTouchEnd() {
      this.resizing = false;
      document.documentElement.style.cursor = "default";
    },
    edit: function() {
      this.$router.push({ path: `/user/profile-edit/${this.userId}` });
    }
  },
  created: function() {
    this.userId = this.$route.params.id;
    UserDataService.getUserInfo(this.userId).then(res => {
      this.userProfile = res.data;
    });
  }
};
</script>

<style>
#popup-croppa {
  margin-left: calc(50% - 140px);
}
#fakeBtnChange {
  font-size: 70%;
  border: 1px solid;
  width: 100px;
  margin-left: calc(50% - 50px);
  border-radius: 5px;
}
#fakeBtnChange:hover {
  cursor: pointer;
}
#fakeBtnConfirm {
  text-align: center;
  font-size: 70%;
  border: 1px solid;
  width: 100px;
  margin-left: 236px;
  border-radius: 5px;
}
.wrapper {
  position: relative;
  display: inline-block;
  font-size: 0;
}
.icon-resize {
  position: absolute;
  right: 4px;
  bottom: 4px;
  font-size: 16px;
  width: 2em;
  cursor: nwse-resize;
}
#profile-body {
  height: calc(100vh - 56px);
  background: -webkit-linear-gradient(top, #0b889c, white);
}
.emp-profile {
  padding: 3%;
  border-radius: 0.5rem;
  background: #fff;
  position: absolute;
  width: 70vw;
  top: 70px;
  left: 15vw;
}
.profile-img {
  text-align: center;
}
.profile-img img {
  width: 150px;
  height: 150px;
  object-fit: cover;
  margin-bottom: 5px;
}
.profile-img .file {
  position: relative;
  overflow: hidden;
  margin-top: -20%;
  width: 70%;
  border: none;
  border-radius: 0;
  font-size: 15px;
  background: #212529b8;
}
.profile-img .file input {
  position: absolute;
  opacity: 0;
  right: 0;
  top: 0;
}
.profile-head h5 {
  color: #333;
}
.profile-head h6 {
  color: #0062cc;
}
.profile-edit-btn {
  border: none;
  border-radius: 1.5rem;
  width: 70%;
  padding: 2%;
  font-weight: 600;
  color: #6c757d;
  cursor: pointer;
}
.proile-rating {
  font-size: 12px;
  color: #818182;
  margin-top: 5%;
}
.proile-rating span {
  color: #495057;
  font-size: 15px;
  font-weight: 600;
}
.profile-head .nav-tabs {
  margin-bottom: 5%;
}
.profile-head .nav-tabs .nav-link {
  font-weight: 600;
  border: none;
}
.profile-head .nav-tabs .nav-link.active {
  border: none;
  border-bottom: 2px solid #0062cc;
}
.profile-work {
  padding: 14%;
  /* margin-top: -15%; */
}
.profile-work p {
  font-size: 12px;
  color: #818182;
  font-weight: 600;
  margin-top: 10%;
}
.profile-work a {
  text-decoration: none;
  color: #495057;
  font-weight: 600;
  font-size: 14px;
}
.profile-work ul {
  list-style: none;
}
.profile-tab label {
  font-weight: 600;
}
.profile-tab p {
  font-weight: 600;
  color: #0062cc;
}
</style>
