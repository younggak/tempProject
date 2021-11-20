<template>
 <v-app>
  <div class="home_container">
    <div id="home_appBar">
      <div id="logo">
        <img src="assets/we_ka_t.png" id="logo_img" />
      </div>
      <div id="empty_box"></div>
     
      <div id="dots">
          <v-icon v-on:click="drawer = !drawer">more_vert</v-icon>
      </div>
      <v-navigation-drawer v-model="drawer"  
      absolute
      temporary 
      right>
        <div v-on:click='developerOptionToggle' style="margin-left: 10px; margin-top: 10px; background-color:#9fa0a0;" >개발자 문자 분석</div>
      </v-navigation-drawer>
    </div>
 
   
    

    <div id="home_cat">
      <div id="background_fish">
          <img src="assets/cat.svg" id="catFace_img" />
		  <!-- <div id='background_head'>
			 
		  </div> -->
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
        <div id='detail_button_btn' v-on:click='goSMSscore'>피싱 분석</div>
   <!-- smsDetailPageToggle=!smsDetailPageToggle -->
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
            <img src='assets/type.svg'>
            <!-- <div id='sms_number'>{{susinSMS}}</div>
            <div id='detail_score_text'>수신문자</div> -->
        </div>
		<div class='detail_score_section'>
            <img src='assets/time.svg'>
            <!-- <div id='question_number'>{{nowaySMS}}</div>
            <div id='detail_score_text'>알 수 없는 문자</div> -->
        </div>
		<div class='detail_score_section'>
            <img src='assets/sms_icon.svg'>
            <div id='detail_score_text'>문자</div>
            <div id='exclamation_number'>{{uisimSMS}} / {{uisimSMS}} / {{uisimSMS}}</div>
            
        </div>
        <div class='detail_score_section'>
            <img src='assets/word.svg'>
            <!-- <div id='question_number'>{{nowaySMS}}</div>
            <div id='detail_score_text'>알 수 없는 문자</div> -->
        </div>

	</div>

    <div id='bottom_sheet'>
       
        <div class="text-center">
            <v-bottom-sheet v-model="sheet" inset>
                <template v-slot:activator="{ on, attrs }">
                    <v-btn color="grey" dark v-bind="attrs" v-on="on" style="width:50%">
                        <v-icon>menu</v-icon>
                    </v-btn>
                </template>
                <v-sheet class="text-center" height="200px">
                    <v-btn class="mt-6" text color="error" v-on:click="sheet = !sheet">
                        close
                    </v-btn>
                    <div class="my-3">
                        This is a bottom sheet using the inset prop
                    </div>
                </v-sheet>
            </v-bottom-sheet>


        </div>
       
	</div>

    <div v-if='smsDetailPageToggle' v-bind:style="bottomSheet_style1">

        <div v-bind:style="smsDetailPageRow"> 
            <div v-bind:style="backButtonStyle" v-on:click="smsDetailPageToggle=!smsDetailPageToggle">back</div>

            <div v-bind:style="smsDetailPageColumn"> 
                <div v-bind:style="detailTopMenuStyle"> 
                    <div v-bind:style='detailTopMenuStyle_component1' v-on:click='getFullSMS'>전체 문자</div>
                    <div v-bind:style='detailTopMenuStyle_component2' v-on:click='getPhoneBook'>연락처 정보</div>
                    <div v-bind:style='detailTopMenuStyle_component3' v-on:click='getNotBookedSMS'>연락X 문자</div>
                    <div v-bind:style='detailTopMenuStyle_component4' v-on:click='getBookedSMS'>연락O 문자</div>
                </div>
                <div v-bind:style="detailTopMenuStyle"> 
                    <div v-bind:style='detailTopMenuStyle_component2' v-on:click='getURLSMS'>URL</div>
                    <div v-bind:style='detailTopMenuStyle_component3' v-on:click='getWebSMS'>[Web]</div>
                    <div v-bind:style='detailTopMenuStyle_component4' v-on:click='getZeroSevenSMS'>070</div>
                    <div v-bind:style='detailTopMenuStyle_component5' v-on:click='getAdSMS'>(광고)</div>
                    <div v-bind:style='detailTopMenuStyle_component1' v-on:click='getOverseaSMS'>(국외)</div>
                </div>
            </div>

            <div v-bind:style='statisticsStyle' v-on:click='statistics'>통계</div>    
        </div>
        
        <div v-for="smsComponent of vforList" v-bind:key="smsComponent" v-bind:style="smsComponentStyle">
            {{smsComponent.number}}
            <br>
            <br>
        </div>

    </div>

  </div>
   </v-app>
</template>

<script>
var numberList = [];


var fullSmsList = [];
var phoneBookList = [];
var notInPhoneBookList = [];
var inPhoneBookList = [];

var urlList = [];
var webList = [];
var zeroSevenList = [];
var adList = [];
var overseaList = [];

var i = 0;
export default {
    data: function(){
        return{
            smsDetailPageToggle:false,
            score_number:100,
            toggle: false,
            tempSMSNumber: 0,
            listSize: numberList.length,

            fullSMS: 0,
            notInSMS: 0,    
            inSMS:0,
            urlSMS:0,
            webSMS:0,
            zeroSevenSMS:0,
            adSMS:0,
            overseaSMS:0,

            uisimSMS: 0,

            drawer: false,
            sheet: false,
            vforList: [],

            phoneList:[
                { number: 0, }
            ],

            smsDetailPageRow: {
                display: 'flex',
                flexDirection: 'row', 
                alignItems: 'center',
                justifyContent: 'center', 
                
                width:'100%', 
                height:'10%',
                backgroundColor: 'chartreuse', 
            },

            backButtonStyle:{
                flex: '1 1 0',
                backgroundColor:'red',
                height:'100%',

                display:'flex', 
                flexDirection: 'row',       
                alignItems: 'center',
                justifyContent: 'center', 
            },
            
            smsDetailPageColumn:{
                flex: '5 1 0',
                display: 'flex',
                flexDirection: 'column', 
                alignItems: 'center',
                justifyContent: 'center',  
                height:'100%',
                width:'100%',  
                backgroundColor:'white',   
            },

            detailTopMenuStyle:{
                flex: '1 1 0',
                display: 'flex',
                flexDirection: 'row',       
                alignItems: 'center',
                justifyContent: 'center', 
                
                backgroundColor:'blue',
                height:'100%',
                width:'100%', 
            },

             detailTopMenuStyle_component1: {
                flex: '1 1 0',
                borderWidth: '2px',
                fontSize: '10px',
                backgroundColor: 'yellow',
                height:'100%',

                display:'flex', 
                flexDirection: 'row',       
                alignItems: 'center',
                justifyContent: 'center', 
            },
             detailTopMenuStyle_component2: {
                flex: '1 1 0',
                borderWidth: '2px',
                fontSize: '10px',
                backgroundColor: 'tomato',
                height:'100%',

                display:'flex', 
                flexDirection: 'row',       
                alignItems: 'center',
                justifyContent: 'center', 
            },
             detailTopMenuStyle_component3: {
                flex: '1 1 0',
                borderWidth: '2px',
                fontSize: '10px',
                backgroundColor: 'darksalmon',
                height:'100%',

                display:'flex', 
                flexDirection: 'row',       
                alignItems: 'center',
                justifyContent: 'center', 
            },
             detailTopMenuStyle_component4: {
                flex: '1 1 0',
                
                display:'flex', 
                flexDirection: 'row',       
                alignItems: 'center',
                justifyContent: 'center', 
                
                borderWidth: '2px',
                fontSize: '10px',
                backgroundColor:'khaki',
                height:'100%',
            },
            detailTopMenuStyle_component5: {
                flex: '1 1 0',
                borderWidth: '2px',
                fontSize: '10px',
                backgroundColor: 'hotpink',
                height:'100%',

                display:'flex', 
                flexDirection: 'row',       
                alignItems: 'center',
                justifyContent: 'center', 
            },

            statisticsStyle:{
                flex: '1 1 0',
                borderWidth: '2px',
                fontSize: '10px',
                backgroundColor: 'yellow',
                height:'100%',

                display:'flex', 
                flexDirection: 'row',       
                alignItems: 'center',
                justifyContent: 'center', 
            },


            smsComponentStyle:{
                backgroundColor: 'chartreuse',
                fontSize: '10px',
                margin: '10px',
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
           
        }
    },
    methods:{
        async developerOptionToggle()  {
            this.drawer = !this.drawer;

            fullSmsList = [];
            phoneBookList = [];
            notInPhoneBookList = [];
            inPhoneBookList = [];

            urlList = [];
            webList = [];
            zeroSevenList = [];
            adList = [];
            overseaList = [];

            await this.cordovaGetFullSMS();
            await this.cordovaGetPhoneBook();
            await this.cordovaGetNotBookedSMS();
            await this.cordovaGetBookedSMS();
            await this.cordovaGetURLSmsHashMap();
            await this.cordovaGetWebSmsHashMap();
            await this.cordovaGetZeroSevenSmsHashMap();
            await this.cordovaGetAdSmsHashMap();
            await this.cordovaGetOverseaSmsHashMap();

            this.fullSMS = fullSmsList.length-1;
            this.inSMS = inPhoneBookList.length-1;            
            this.notInSMS = notInPhoneBookList.length-1;

            this.urlSMS = urlList.length-1;
            this.webSMS = webList.length-1;
            this.zeroSevenSMS = zeroSevenList.length-1;
            this.adSMS = adList.length-1;
            this.overseaSMS = overseaList.length-1;
            
            this.smsDetailPageToggle = !this.smsDetailPageToggle;
        },
        goSMSscore(){
            this.$router.push('/smsscore');
        },
        toggling(){
            this.score_number++;
            this.toggle = !this.toggle;
            console.log(this.score_number+'\n'+this.toggle);
        },

        statistics(){
            this.vforList.splice(0);
            this.vforList.push({number:'전체 문자 : ' + this.fullSMS});
            this.vforList.push({number:'연락처에 있는 문자 : ' + this.inSMS});
            this.vforList.push({number:'연락처에 없는 문자 : ' + this.notInSMS});
            this.vforList.push({number:'URL포함 문자 : ' + this.urlSMS});
            this.vforList.push({number:'[Web발신] 문자 : ' + this.webSMS});
            this.vforList.push({number:'070문자 : ' + this.zeroSevenSMS});
            this.vforList.push({number:'[광고] 문자 : ' + this.adSMS});
            this.vforList.push({number:'[국외발신] 문자 : ' + this.overseaSMS});
        },
        getFullSMS(){
            this.vforList.splice(0);
            i = 0;
            
            while(i<this.fullSMS){
                this.vforList.push({number:fullSmsList[i]});
                i++;
            }
        },
        getPhoneBook(){
            this.vforList.splice(0);
            i = 0;
            // alert("연락처 갯수 : "+ phoneBookList.length);
            while(i < phoneBookList.length-1){
                this.vforList.push({number: phoneBookList[i]});
                i++;
            }
        },
        getNotBookedSMS(){
            this.vforList.splice(0);
            i=0;
            while(i < this.notInSMS){
                this.vforList.push({number: notInPhoneBookList[i]});
                i++;
            }
        },
        getBookedSMS(){
            this.vforList.splice(0);
            i=0;
            while(i < this.inSMS){
                this.vforList.push({number: inPhoneBookList[i]});
                i++;
            }
            // alert("연락처에 있는 문자"+inPhoneBookList.length );
        },

        getURLSMS(){
            this.vforList.splice(0);
            i=0;
            while(i < this.urlSMS){
                this.vforList.push({number: urlList[i]});
                i++;
            }
        },
        getWebSMS(){
            this.vforList.splice(0);
            i=0;
            while(i < this.webSMS){
                this.vforList.push({number: webList[i]});
                i++;
            }
        },
        getZeroSevenSMS(){
            this.vforList.splice(0);
            i=0;
            while(i < this.zeroSevenSMS){
                this.vforList.push({number: zeroSevenList[i]});
                i++;
            }
        },
        getAdSMS(){
            this.vforList.splice(0);
            i=0;
            while(i < this.adSMS){
                this.vforList.push({number: adList[i]});
                i++;
            }
        },
        getOverseaSMS(){
            this.vforList.splice(0);
            i=0;
            while(i < this.overseaSMS){
                this.vforList.push({number: overseaList[i]});
                i++;
            }
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
        
        //특수한 문자들 
        async cordovaGetURLSmsHashMap(){
             return new Promise(function (resolve, reject){
                cordova.exec(function_getURLSmsHashMap, null,"CordovaCustomPlugin", "getURLSmsHashMap", []);
                resolve();
            });
        },

        async cordovaGetWebSmsHashMap(){
             return new Promise(function (resolve, reject){
                cordova.exec(function_getWebSmsHashMap, null,"CordovaCustomPlugin", "getWebSmsHashMap", []);
                resolve();
            });
        },

        async cordovaGetZeroSevenSmsHashMap(){
             return new Promise(function (resolve, reject){
                cordova.exec(function_getZeroSevenSmsHashMap, null,"CordovaCustomPlugin", "getZeroSevenSmsHashMap", []);
                resolve();
            });
        },

        async cordovaGetAdSmsHashMap(){
             return new Promise(function (resolve, reject){
                cordova.exec(function_getAdSmsHashMap, null,"CordovaCustomPlugin", "getAdSmsHashMap", []);
                resolve();
            });
        },

        async cordovaGetOverseaSmsHashMap(){
             return new Promise(function (resolve, reject){
                cordova.exec(function_getOverseaSmsHashMap, null,"CordovaCustomPlugin", "getOverseaSmsHashMap", []);
                resolve();
            });
        }

    }
}

function function_getURLSmsHashMap(result){
    urlList = result.split('^&');
}

function function_getWebSmsHashMap(result){
    webList= result.split('^&');
}

function function_getZeroSevenSmsHashMap(result){
    zeroSevenList= result.split('^&');
}

function function_getAdSmsHashMap(result){
    adList= result.split('^&');
}

function function_getOverseaSmsHashMap(result){
    overseaList= result.split('^&');
}



function refreshSMSDataBase(result){
    fullSmsList = result.split('^&');
}
function refreshPhoneBookDataBase(result){
    phoneBookList = result.split('@');
}
function function_getNotBookedSMS(result){
    notInPhoneBookList = result.split('^&');
}
function function_getBookedSMS(result){
    inPhoneBookList = result.split('^&')
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
	background-color: white;
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
