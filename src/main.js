import $ from 'jquery';

import Vue from 'vue'
import App from './App.vue'
import router from './router'
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

const ENV = { isNative: false };
const loadNativeScripts = async () => new Promise((resolve, reject) => $.getScript('cordova.js', resolve).fail((xhr, s, e) => { reject(e); }));

function onServiceReady() {
  // Make BootstrapVue available throughout your project
  Vue.use(BootstrapVue)
  // Optionally install the BootstrapVue icon components plugin
  Vue.use(IconsPlugin)

  Vue.config.productionTip = false
  /* eslint-disable no-new */
  new Vue({
    el: '#app',
    router,
    components: { App },
    template: '<App/>'
  });
}

const onDeviceReady = () => {
  console.log('Running Cordova' + cordova.platformId + '@' + cordova.version);
  onServiceReady();
}

const onDocumentReady = () => {
  loadNativeScripts().then(
    () => { // cordova.js file found
      ENV.isNative = true;
      document.addEventListener('deviceready', onDeviceReady, false);
    },
    (e) => {
      ENV.isNative = false;
      console.log('Running Browser');
      onServiceReady();
    }
  );
}

document.addEventListener('DOMContentLoaded', onDocumentReady, false);