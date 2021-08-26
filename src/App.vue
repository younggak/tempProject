<template>
  <div id="app">
    <router-view />
    <BottomNavigation />
    <LoadingScreen :isLoading="isLoading" />
    <!-- <div v-if="!isLoading">
    </div> -->
  </div>
</template>

<script>
import BottomNavigation from "./components/layout/BottomNavigation.vue";
import LoadingScreen from "./components/layout/LoadingScreen.vue";
//dd
export default {
  name: "App",
  components: {
    BottomNavigation,
    LoadingScreen,
  },
};
</script>

<style>
html,
body {
  margin: 0;
  padding: 0;
  height: 100%;
}
div {
  margin: 0;
  padding: 0;
}
#app {
  background-color: blueviolet;
  height: 100%;
  margin: 0;
}
</style>
