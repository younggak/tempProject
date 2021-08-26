<template>
    <div class="container">
        <div id="sms_1">
            <div id="sms_title">
                <b>문자</b>
            </div>
            <div id="search_container">
                <input type="search" id="search_space" placeholder="검색하세요"/>
                <input type="button" id="search_btn" @click="[getData(),  updateData()]" value="검색"/>
            </div>
        </div>


        <div id="sms_2">
            <div v-for="person of people" v-bind:key="person" class="recycler">
                {{ person.name }} {{ person.age }}
            </div>
             <div>{{ temp }}</div>
        </div> 
       
    </div>
</template>

<script>
var list;
var i = 0;
var listSize = 0;
var rawString = "raw now"
var string2 = 'yesyes'
export default {
    data() {
        return {
            temp: 'hello',
            people: [
                {name: 0, age: 0},
                {name: 0, age: 0},
                {name: 0, age: 0},
                {name: 0, age: 0}
            ]
        }
    },
  methods: {
    getData () {
      cordova.exec(success, null,"CordovaCustomPlugin", "coolMethod", []);
      cordova.exec(setListSize, null,"CordovaCustomPlugin", "getListSize", []);
      cordova.exec(getSMSCordova, null,"CordovaCustomPlugin", "getSMS", []);
    },
     updateData () {
     this.temp = string2;
     alert("updateData로부터, listSize=" + listSize);
     while(i < listSize){
         this.people.push({name: list[i], age: i});
         i++;
     }
    }
  }
}

function success(result){
    alert("연락처 내용 : "+result)
    rawString = result;
    list=rawString.split('@');
    alert("연락처 내용 중에서 3번째 : " + list[3]);
}
function setListSize(result){
    listSize = result;
    alert("연락처 사이즈 : " + listSize);
}
function getSMSCordova (result) {
    alert("최근 받은 문자 내용 : " + result);
}

</script>

<style>
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
        margin-top: 10px;
        width: 90%;    
    }

    #sms_1 {
        flex: 1 1 0;
        background-color: #454d7f;
    }
    #sms_2 {
        flex: 4 1 0;
        background-color: darksalmon;
        flex-direction: column;
        overflow: auto;
       
       display: flex;
       align-items: center;
       /* justify-content: center; */
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
        /* background-color: brown; */
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
