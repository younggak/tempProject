<template>
    <div class="container">
        <div id="sms_1">
            <div id="sms_title">
                <b>문자</b>
            </div>
            <div id="search_container">
                <input type="search" id="search_space" placeholder="검색하세요"/>
                <input type="button" id="search_btn" @click="success('helloWorld')" value="검색"/>
            </div>
        </div>
        <div id="sms_2">
            <div id="blank">
                asdfasdfasdfasdf
            </div>
        </div>
    </div>
</template>

<script>
export default {
  methods: {
    // getData () {
    //   cordova.exec(success, null,"CordovaCustomPlugin", "coolMethod", [111,222]);
    // },
    success (result) {
      alert(result)
    }
  }
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

    #sms_1 {
        flex: 1 1 0;
        background-color: #454d7f;
    }
    #sms_2 {
        flex: 4 1 0;
        background-color: darksalmon;
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
