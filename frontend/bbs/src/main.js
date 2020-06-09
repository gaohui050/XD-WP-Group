import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './assets/css/global.css'
import VueQuillEditor from 'vue-quill-editor'
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'
import axios from 'axios'

Vue.config.productionTip = false;
Vue.use(VueQuillEditor);
axios.defaults.baseURL="http://121.36.8.152/api";
// axios.defaults.baseURL="http://localhost:9999";
axios.defaults.headers.post['Content-Type'] = 'application/json';
axios.interceptors.request.use(config => {
  config.headers.token = sessionStorage.getItem("token");
return config;
});
axios.interceptors.response.use(res => {
  return res.data;
});

Vue.prototype.$http = axios;
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app');
