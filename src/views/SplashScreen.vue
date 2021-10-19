<template>
    <div class='splash_container'>
        
        <div class='logo_img_section'>
            logo img
        </div>
        <div class='button_section'>
        
            
            <div class='start_button' v-on:click="goHome">
                시작하기
            </div>
           
        </div>

    </div>
</template>

<script>
export default {
    data(){

	},
	methods: {
		goHome(){
			location.href='#/home'
		}
	}
}
</script>

<style>
div {
  margin: 0;
  padding: 0;
}
.splash_container{
    display: flex;
    flex-direction: column;
    height: 100%;
    width: 100%;
    background-color: aqua;
}
.logo_img_section{
    flex: 7 1 0;
    background-color:coral;
}
.button_section{
    display: flex;
    flex: 1 1 0;
    background-color:brown;
    align-items: center;
    justify-content: center;
}
.start_button{
    display: flex;
    align-items: center;
    justify-content: center;
  
    height: 40%;
    width: 80%;
    
    border-radius: 8px;
    background-color:blue;
    color: white;
}
</style>