 <template>
    <div class="container">
        <div id="sms_1">
            <div id="sms_title">
                <b>문자</b>
            </div>
            <div id="search_container">
                <input type="search" id="search_space" placeholder="검색하세요"/>
                <input type="button" id="search_btn" @click="clearSharedPreference()" value="검색"/>
            </div>
        </div>

        <div id="sms_2">
            <div v-for="smsComponent of SMS" v-bind:key="smsComponent" class="recycler">
                <div id="ava_zone">
                    <b-avatar class="ava"></b-avatar>
                </div>

                <div id="number_and_text_zone">
                    <div id="number_zone">{{ smsComponent.number }}</div>
                    <div id="text_zone">{{ smsComponent.text }}</div>
                </div>  
            </div>
        </div> 
        <div>

        </div>
    </div>
</template>

<script>

var list;
var i = 0;
var listSize = 0;
var rawString = "raw now";
var string2 = 'yesyes';
var numberList = [];
var textList = [];
export default {
    data: function() {
        return {
            temp: 'hello',
            SMS: [
                {number : 0,text : 0}
            ]
        }
    },
    methods: {
        async getData() {
            await this.totalData();
            this.temp = string2; 
            while(i < listSize+1){
                this.SMS.push({number: numberList[i], text: textList[i]});
                i++;
            }
            
        },
        async totalData() {
            return new Promise(function(resolve, reject){
                cordova.exec(setListSize, null,"CordovaCustomPlugin", "getListSize", []);
                cordova.exec(getSMSCordova, null,"CordovaCustomPlugin", "getSMS", []);
                resolve();
            });  
        },
        hello(){
            finalAsync();
        },

        async one(){
            var that=this;
            return new Promise(function(resolve,reject){
                setTimeout(function(){
                    that.temp = 'eeee';
                    resolve();
                },1000);
            });
        },
        async final(){
            await this.one();
            alert(this.temp); 
        },
        clearSharedPreference(){
            cordova.exec(sharedPreferenceDone, null,"CordovaCustomPlugin", "sharedPreferenceClear", []);
        }
  },
  mounted() {
      i=1;
      this.SMS = [];
      this.getData();
  }
}
//hello world

function success(result){
    rawString = result;
    list=rawString.split('@');
    // alert("연락처 내용 중에서 3번째 : " + list[3]);
}
function setListSize(result){
    listSize = result;
    // alert("문자 리스트 사이즈 : " + listSize);
}
function getSMSCordova (result) {
    var tempList = [];
    rawString = result;
    tempList = rawString.split('#');
    numberList = tempList[0].split('@');
    textList = tempList[1].split('@');
}
function sharedPreferenceDone(result){
    alert("done!");
}
</script>

<style scoped>
    div {
        margin: 0;
        padding: 0;
    }
    .container {
        display: flex;
        flex-direction: column;
        height: calc(100% - 55px);
    }

    .slide {
        display: inline-block;
        position: relative;
        width: 100%;
        height: 100%;
        overflow: hidden;
    }

    .default_bar {
        font-size: 10px;
        height: 90%;
        width: 80%;

        display: flex;
        flex-direction: column;

        border-radius: 20px;
        background-color: rgb(33, 194, 33);
    }

    .recycler{
        background-color: #FFFFFF;
        border-radius: 10px;
        border: 1px solid #778899;
        margin-top: 10px;
        width: 90%;
        height: 80px;    

        display: flex;
        flex-direction: row;
        
    }
    .ava{
        background-color: #778899;
        color:#FFFFFF;
    }
    #ava_zone{
        background-color: aqua;
        width: 20%;
        display: flex;
        align-items: center;
        justify-content: center;
        border-radius: 15px 0 0 15px;
    }
    #number_zone{
        width: 100%;
        height: 100%;
        background-color: chartreuse;
        border-radius: 0 15px 0 0;
    }
    #text_zone{
        height: 100%;
        width: 100%;
        background-color:crimson;
        border-radius: 0 0 15px 0;
    }

    #number_and_text_zone{
        height: 100%;
        width: 100%;
        display: flex;
        flex-direction: column;
    }
    #sms_1 {
        flex: 1 1 0;
        /* width: 95%; */
        background-color: #454d7f;
    }
    #sms_2 {
        flex: 4 1 0;
        background-color: darksalmon;
        flex-direction: column;
        overflow: auto;
       
       display: flex;
       align-items: center;
    }

    #sms_title {
        margin-top: 3%;
        margin-left: 7%;
        font-size: 30px;
        color: #FFFFFF;
    }

    #blank {
        font-size: 20px;
    }

    #search_container {
        height: 30%;

        margin-top: 3%;

        display: flex;
        flex-direction: row;
        align-items: center;
        justify-content: center;
    }

    #search_space {
        height: 100%;
        width: 80%;
        border-radius: 20px 0 0 20px;
    }

    #search_btn {
        height: 95%;
        border-radius: 0 20px 20px 0;
    }
    #slide-1 {
        background-color: antiquewhite;
    }

    #slide-2 {
        background-color: #45959b;

    }

    #slide-3 {
        background-color: #778899;

    }

    #slide-4 {
        color: #FFFFFF;
        background-color: #291F37;

    }

    @keyframes stack {
        0% {
            width: 0;
        }
        100% {
            width: 75%;
        }

    }

    @keyframes stack_bottomUp {
        0% {
            height: 0;
        }
        100% {
            height: 50%;
        }

    }
</style>
