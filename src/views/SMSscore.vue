<template>
<div id = 'sms_score_home_container'>
    <div id='sms_score_appbar'>
        <div id='app_bar_back_icon'> 
            <v-icon v-on:click='goBack'>close</v-icon>
        </div>
        
        <div id='app_bar_title'> title </div>
        <div id='app_bar_blank'> blank </div>
    </div>
    <div id='sms_score_title1'> 
        피싱 분석
    </div>
    <div id='sms_score_title2'> 
        문자 안심점수
    </div>
    <div id='sms_score_score'>
        69/100
    </div>
    <div id='sms_score_ment'> 
        홍길동 님의 보안지수는 Lv.2 입니다.
        낮은 Lv 유지 시 피싱에 취약해져 범죄에 쉽게 노출 될 수 있습니다. 
    </div>
</div>
</template>

<script>
export default {
     data: function() {
         return{

         }
     },
     methods:{
        goBack(){
             this.$router.go(-1);
        }
     }
}
</script>

<style scoped>
#sms_score_home_container{
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    height: 100%;
    width: 100vw;
    padding: 0px;
    margin: 0px;
}
#sms_score_appbar{
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;

    flex: 1 1 0;
    width: 100vw;
    background-color:darkorange;
}
#app_bar_back_icon{
    flex: 1 1 0;
    background-color: darkkhaki;
     height: 100%;
}
#app_bar_title{
    flex: 1 1 0;
    background-color: green;
    height: 100%;
}
#app_bar_title{
    flex: 1 1 0;
    background-color: lightskyblue;
    height: 100%;
}
#app_bar_blank{
    flex: 1 1 0;
    background-color: magenta;
    height: 100%;
}


#sms_score_title1{
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;

    flex: 1 1 0;
    width: 100vw;
    background-color:aqua;
}
#sms_score_title2{
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;

    flex: 1 1 0;
    width: 100vw;
    background-color:aquamarine;
}
#sms_score_score{
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;

    flex: 1 1 0;
    width: 100vw;
    background-color:blueviolet;
}
#sms_score_ment{
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;

    flex: 1 1 0;
    width: 100vw;
    background-color:darkgoldenrod;
}
</style>