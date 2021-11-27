<template>
    <div class='splash_container'>
        <div class='main_space_one'></div>
        <div class='main_titleMent'><b>나와 내 이웃을 지키는</b></div>
        <div class='main_titleImg'><b>WEKAT</b></div>
        <div class='main_btnSection'>
            <div class='main_btnSection_btn' v-on:click="goHome">
                <b>시작하기</b>
            </div>
        </div>

        <div class='main_space_two'></div>
        <div class='main_permission'>시작하기를 누르면 이용약관 및 정책 동의로 간주합니다</div>




        <!-- <div class='logo_img_section'>
            <div class='button_section'>
                <div class='start_button' v-on:click="goHome">
                    시작하기
                </div> 
            </div>
            
        </div>
       -->

    </div>
</template>

<script>
export default {
    data: function(){
        return{

        }
	},
	methods: {
		goHome(){
            this.$router.push('/home')
		}
	}
}
</script>

<style scoped>
div {
  margin: 0;
  padding: 0;
}
.splash_container{
    display: flex;
    flex-direction: column;
    height: 100%;
    width: 100vw;
    background-color: #0473e1;
}
.main_space_one{
    flex: 2 1 0;
    background-color: #0473e1;
}


.main_titleMent{
    flex: 0.7 1 0;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;

    font-size: 130%;

    color: white;

    background-color:#0473e1;
}

.main_titleImg{
    flex: 1 1 0;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;

    font-size: 400%;

    color: white;

    background-color:#0473e1;
}

.main_btnSection{
    flex: 1 1 0;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;

    background-color:#0473e1;
}
.main_btnSection_btn{
    /* flex: 1 1 0; */
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;

    height: 60%;
    width:50%;
    border-radius: 30px;
    color: #0473e1;
    background-color:white;
}



.main_space_two{
    flex: 3 1 0;
    background-color: #0473e1;
}
.main_permission{
    flex: 1 1 0;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;

    color: white;

    font-size: 70%;

    background-color:#0473e1;
}

/* .logo_img_section{
    flex: 7 1 0;
    background-color:#0473e1;
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
} */
</style>