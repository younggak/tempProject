<template>
  <div class="home_container">
    <div id="home_appBar">
      <div id="logo">
        <img src="assets/we_ka_t.png" id="logo_img" />
      </div>
      <div id="empty_box"></div>
      <div id="dots">
          <b-icon-three-dots-vertical style="height: 100%;"></b-icon-three-dots-vertical>
      </div>
    </div>

    <div id="home_cat">
      <div id="background_fish">
		  <div id='background_head'>
			  <img src="assets/catFace.svg" id="catFace_img" />
		  </div>
      </div>
    </div>


    <div id="home_score_column">
        <div id="home_score_row">
            <div id="score_current">{{score_number}}</div>
            <div id="score_total">/100</div>
        </div>
        <div id='home_score_ment'>
            완벽한 방어중입니다.
        </div>
    </div>

	<div id='detail_button'> 
    <div id='detail_button_btn' v-on:click='smsDetailPageToggle=!smsDetailPageToggle'>피싱 분석</div>
	</div>

	<div id='detail_text'>
        <div id='detail_text_text'> 
            <div>
                홍길동님의 보안지수는<span id='home_level'>Lv.4</span>입니다.
            </div> 
            <div>
                지금처럼 보안습관을 유지해주세요.
            </div>
        </div>
	</div>

	<div id='detail_score'> 
		<div class='detail_score_section'>
            <img src='assets/sms.svg'>
            <div id='sms_number'>{{susinSMS}}</div>
            <div id='detail_score_text'>수신문자</div>
        </div>
		<div class='detail_score_section'>
            <img src='assets/question.svg'>
            <div id='question_number'>{{nowaySMS}}</div>
            <div id='detail_score_text'>알 수 없는 문자</div>
        </div>
		<div class='detail_score_section'>
            <img src='assets/exclamation.svg'>
            <div id='exclamation_number'>{{uisimSMS}}</div>
            <div id='detail_score_text'>피싱 의심 문자</div>
        </div>
	</div>

    <div id='bottom_sheet'>
		
	</div>

    <div v-if='smsDetailPageToggle' v-bind:style="bottomSheet_style1">
        
        <div v-bind:style="detailTopMenuStyle"> 
            <div v-bind:style="backButtonStyle" v-on:click="smsDetailPageToggle=!smsDetailPageToggle">back</div>
            <div v-bind:style='detailTopMenuStyle_component1' v-on:click='getFullSMS'>전체 문자</div>
            <div v-bind:style='detailTopMenuStyle_component2' v-on:click='getPhoneBook'>연락처 정보</div>
            <div v-bind:style='detailTopMenuStyle_component3' v-on:click='getNotBookedSMS'>등록X 문자</div>
            <div v-bind:style='detailTopMenuStyle_component4' v-on:click='getBookedSMS'>등록O 문자</div>
            <div v-bind:style='detailTopMenuStyle_component5' v-on:click='statistics'>통계</div>
        </div>
        <div v-for="smsComponent of vforList" v-bind:key="smsComponent">
            {{smsComponent.number}}
            <br>
            <br>
        </div>

        <!-- <div v-for="smsComponent of phoneList" v-bind:key="smsComponent">
            {{smsComponent.number}}
        </div> -->
    </div>

    <transition name='upDown'>
        <div v-bind:style='bottomSheet_style' v-on:click='toggle = !toggle'> 
            
        </div>
    </transition>
  </div>
</template>

<script>
var tempList = [];
var numberList = [];
var phoneBookList = [];
var listSize = 0;
var i = 0;
export default {
    data: function(){
        return{
            smsDetailPageToggle:false,
            score_number:0,
            toggle: false,
            tempSMSNumber: 0,
            listSize: numberList.length,

            susinSMS: 0,
            nowaySMS: 0,
            uisimSMS: 0,
            
            safeSMS:0,

            vforList: [
                {  }
            ],

            phoneList:[
                { number: 0, }
            ],
            detailTopMenuStyle:{
                display: 'flex',
                flexDirection: 'row',       
                alignItems: 'center',
                justifyContent: 'center', 
            },
            detailTopMenuStyle_component1: {
                flex: '1 1 0',
                borderWidth: '2px',
                fontSize: '10px',
                backgroundColor: 'yellow',
            },
             detailTopMenuStyle_component2: {
                flex: '1 1 0',
                borderWidth: '2px',
                fontSize: '10px',
                backgroundColor: 'tomato',
            },
             detailTopMenuStyle_component3: {
                flex: '1 1 0',
                borderWidth: '2px',
                fontSize: '10px',
                backgroundColor: 'darksalmon',
            },
             detailTopMenuStyle_component4: {
                flex: '1 1 0',
                borderWidth: '2px',
                fontSize: '10px',
                backgroundColor:'khaki',
            },
             detailTopMenuStyle_component5: {
                flex: '1 1 0',
                borderWidth: '2px',
                fontSize: '10px',
                backgroundColor: 'hotpink',
            },
            bottomSheet_style:{
                backgroundColor: 'aquamarine',
                position: 'absolute',
                top:'90%',
                width: '80%',
                height: '50%',
                fontSize: '20px',
            },
            bottomSheet_style1:{
                backgroundColor: 'deepskyblue',
                position: 'absolute',
                width: '80%',
                height: '50%',
                fontSize: '10px',
                overflow: 'scroll',
            },
            backButtonStyle:{
                flex: '1 1 0',
                backgroundColor:'red',
                borderRadius: '15px',
            }
        }
    },
    methods:{
        toggling(){
            this.score_number++;
            this.toggle = !this.toggle;
            console.log(this.score_number+'\n'+this.toggle);
        },
        bottomSheetAnimate(){
           
        },

        statistics(){
            this.vforList.splice(1);
            this.vforList.push({number:'전체 문자' + this.susinSMS});
            this.vforList.push({number:'연락처에 있는 문자' + this.safeSMS});
            this.vforList.push({number:'연락처에 없는 문자' + this.nowaySMS})
        },
        async getFullSMS(){
            this.vforList.splice(1);
            await this.cordovaGetFullSMS();
            i = 0;
            alert("전체 문자 갯수 : "+tempList.length)
            this.susinSMS = tempList.length;
            while(i<tempList.length){
                this.vforList.push({number:tempList[i]});
                i++;
            }
        },

        async getPhoneBook(){
            this.vforList.splice(1);
            await this.cordovaGetPhoneBook();
            i = 0;
            alert("연락처 갯수 : "+ tempList.length);
            while(i < tempList.length){
                this.vforList.push({number: tempList[i]});
                i++;
            }
        },
        async getNotBookedSMS(){
            this.vforList.splice(1);
            
            await this.cordovaGetNotBookedSMS();
            i=0;
            this.nowaySMS = tempList.length;
            alert(""+tempList.length);
            while(i < tempList.length){
                this.vforList.push({number: tempList[i]});
                i++;
            }
        },

        async getBookedSMS(){
            this.vforList.splice(1);
            await this.cordovaGetBookedSMS();
            i=0;
            while(i < tempList.length){
                this.vforList.push({number: tempList[i]});
                i++;
            }
            alert("연락처에 있는 문자"+tempList.length );
            this.safeSMS = tempList.length;
        },

        async cordovaGetFullSMS(){
            return new Promise(function(resolve, reject){
                cordova.exec(refreshSMSDataBase, null,"CordovaCustomPlugin", "refreshSMSDataBase", []);
                resolve();
            });
        },
        async cordovaGetPhoneBook(){
            return new Promise(function (resolve, reject){
                cordova.exec(refreshPhoneBookDataBase, null,"CordovaCustomPlugin", "refreshPhoneBookDataBase", []);
                resolve();
            });
        },

        async cordovaGetNotBookedSMS(){
            return new Promise(function (resolve, reject){
                cordova.exec(function_getNotBookedSMS, null,"CordovaCustomPlugin", "getNotBookedSMS", []);
                resolve();
            });
        },

         async cordovaGetBookedSMS(){
            return new Promise(function (resolve, reject){
                cordova.exec(function_getBookedSMS, null,"CordovaCustomPlugin", "getBookedSMS", []);
                resolve();
            });
        },
        
    }
}

function refreshSMSDataBase(result){
    tempList = result.split('^&');
}
function refreshPhoneBookDataBase(result){
    tempList = result.split('@');
    // alert('result');
}
function function_getNotBookedSMS(result){
    tempList = result.split('^&');
}
function function_getBookedSMS(result){
    tempList = result.split('^&')
}
</script>

<style scoped>

#score_current{
	display: flex;
  flex-direction: row;
  justify-content: flex-end;
	align-items:flex-end;
	
	height: 100%;
	width:50%;
	
	line-height:90%;
	font-size: 70px;
	color:#009944;
	/* background-color: aquamarine; */
}
#score_total{
	display: flex;
	height: 100%;
	width:50%;
	align-items: flex-end;
	font-size: 40px;
    
    line-height: 100%;
	color:#9fa0a0;
	/* background-color: burlywood; */
}
#detail_score_text{
    color:#9fa0a0;
}
#logo_img{
    width: 80%;
}
.home_container {
  display: flex;
  flex-direction: column;

  align-items: center;
  justify-content: center;
  height: 100%;
  width: 100vw;
  padding: 0px;
  margin: 0px;

  position: sticky;
  overflow: hidden;
}
.detail_score_section{
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
	flex: 1 1 0;
}
#sms_number{
    color:#009944;
}
#question_number{
    color:#ffcc00;
}
#exclamation_number{
    color: #fd0000;
}

#home_appBar {
  max-height: 100px;
  display: flex;
  flex-direction: row;
  flex: 1.5 1 0;
  width: 100%;
  /* background-color: burlywood; */
}

#home_cat {
  flex: 4 1 0;
  width: 90%;
}

#home_score_column{
  width: 100%;
  flex: 4 1 0;

  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

#home_score_row{
  width: 100%;
  flex: 1.5 1 0;

  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  /* background-color:blanchedalmond; */
}
#home_score_ment{
	width: 100%;
	flex: 1 1 0;

	display: flex;
	flex-direction: column;
  	align-items: center;
  	justify-content: center;
	font-size:120%;
	color:#009944;
}

#detail_button{
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;

    width: 100%;
	flex: 3 1 0;
	/* background-color: cyan; */
}

#detail_text{	
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;

	width: 100%;
	flex: 3 1 0;
	/* background-color:gold; */
}

#detail_text_text{
    color:#9fa0a0;
}
#home_level{
    font-weight: bold;
    color:#009944;
}

#detail_score{
	width: 100%;
	flex: 4 1 0;

	display: flex;
	flex-direction: row;
	/* background-color:mistyrose; */
}

#bottom_sheet{
	width: 100%;
	flex: 1.5 1 0;
	background-color: tomato;
    position: relative;
}
/***********************************************************/


#logo {
  display: flex;
  align-items: center;
  justify-content: center;
  flex: 2 1 0;
  /* background-color: aqua; */
}
#empty_box {
  flex: 7 1 0;
  /* background-color: darkblue; */
}
#dots {
  display: flex;
  align-items: center;
  justify-content: center;
  flex: 1 1 0;
  /* background-color: darkkhaki; */
}

#background_fish {
    display: flex;
    align-items: center;
    justify-content: center;

    height: 100%;
    background-image: url('assets/background.svg');
    background-repeat: repeat;
	background-position-y: -10%;
    /* background-position-y: 10%;
    background-position-x: 50%; */
}

#background_head{
	background-image: url('assets/catHead.svg');
    height: 123px;
    width: 184px;
    
	display: flex;
    align-items: center;
    justify-content: center;
    
	padding-top: 50px;
}


#detail_button_btn{
  display: flex;
  align-items: center;
  justify-content: center;

  height: 50%;
  width: 40%;
  border-radius: 25px;
  background-color: #0473e1;

  font-weight: bold;
  color:white;
}


</style>
