<template>
<div class="analysis_container">
    
    <div class="analysis_1">
        <div class="top_title_main"><b>분석리포트</b></div>
        
        <div class="top_title_blank"></div>

        <div class="top_title_sub">
            <div id="top_title_sub_1"><b>전체</b></div>
            <div id="top_title_sub_2"><b>유형별</b></div>
        </div>
    </div>

    <div class="analysis_2">
        <div class="middle_main">
            <div class="middle_top">
                <div class = "main_title"><b>월간보안</b></div>
            </div>

            <div class="middle_center">
                <div class = "graph_section">
                    <vc-donut :sections="sections" :size="270">Basic donut</vc-donut>
                </div>

                <div class = "text_section">
                    <div class="text_section_1">
                        <div><b>안전문자: 50건</b></div>
                        <div><b>위험문자: 20건</b></div>
                    </div>
                    <div class="text_section_2">
                        <div><b>연락처문자: 45건</b></div>
                        <div><b>비연락처문자: 45건</b></div>
                    </div>
                </div>
            </div>
          
        </div>
    </div>   
</div>
</template>

<script>
export default {
     data: function() {
      return {
        sections: [
            { value: 25 }, { value: 25 }
        ],
      };
    }
}
</script>

<style>
div {
    margin: 0;
    padding: 0;
}
.analysis_container {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;

    height: calc(100% - 55px);
    width: 100%;
    
    background-color: #ffd28c;
}
.analysis_1{
    flex: 1 1 0;
    display: flex;
    align-items: center;
    /* justify-content: center;  */
    flex-direction: row;

    height: 20%;
    width: 95%;
    /* background-color: aqua; */
}
.top_title_main{
    flex: 1 1 0;
    display: flex;
    align-items: center;
    /* justify-content: center;  */

    /* background-color:crimson; */

    font-size: 180%;
}
.top_title_blank{
    flex: 1 1 0;
    background-color: bisque;
}
.top_title_sub{
    flex: 1 1 0;
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: row;

    /* background-color: blueviolet; */
}
#top_title_sub_1{
    flex: 1 1 0;
    display: flex;
    align-items: center;
    justify-content: center;

    /* background-color:bisque; */
}
#top_title_sub_2{
    flex: 1 1 0;
    display: flex;
    align-items: center;
    justify-content: center;
    /* background-color: brown; */
}

.analysis_2{
    flex: 5 1 0;
    display: flex;
    align-items: center;
    justify-content: center;

    width: 95%;
    /* background-color: crimson; */
}
.middle_main{
    /* flex: 4 1 0; */
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;

    height: 95%;
    width: 100%;
    background-color: white;
    border-radius: 15px;
    /* margin: 5% auto 0; */
}
.middle_top{
    flex: 1 1 0;
    display: flex;
    flex-direction: column;
    width: 100%;
    font-size: 150%;
}
.main_title{
    padding-left: 5%;
    padding-top: 5%;
}
.middle_center{
    flex: 8 1 0;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;

    font-size: 150%;
    width: 100%;
    /* background-color:darkmagenta; */
}

.graph_section{
    flex: 3 1 0;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    width: 100%;


    background-color: cyan;
}

.text_section{
    flex: 1 1 0;
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;
    width: 80%;

    /* background-color: blueviolet; */
}
.text_section_1{
    flex: 1 1 0;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;

    font-size: 70%;
    background-color: darkgrey;
}
.text_section_2{
    flex: 1 1 0;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;

    font-size: 70%;
    background-color: green;
}

</style>