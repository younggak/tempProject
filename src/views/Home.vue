<template>
<div class="container">
        <div id="home_1">
            <div id="home_title">
                <b>홈</b>
            </div>
            <div id="home_dailyGraph">
                <div id="inner_home_dailyGraph">
                    <div id="upper_Graph">
                        <div id="today_comment">
                            Today
                        </div>

                        <div id="right_yatsu">
                            <div id="danger">
                                위험
                            </div>
                            <div id="safe">
                                안전
                            </div>
                        </div>
                    </div>

                    <div id="main_daily_graph">
                        <span>75%</span>
                    </div>

                    <div id="safe_comment">
                        안전점수
                    </div>
                    <div id="safe_score">
                        <b>25점</b>
                    </div>
                </div>
            </div>
        </div>

        <div id="home_2">
            <div id="home_article">
                <div id="news_text">
                    <b>뉴스</b>
                </div>

            </div>
        </div>

        <div id="home_3">
            <div id="home_weeklyGraph">
                <div id="weekly_report_text">
                    <b>주간 리포트</b>
                </div>

                <div id="main_weekly_graph">

                    <div class="graph_bundle">
                        <div class="default_bar">
                            <span id="weekGraph1"></span>
                        </div>

                        <div class="date">
                            <b>7/23</b>
                        </div>
                    </div>

                    <div class="graph_bundle">
                        <div class="default_bar">
                            <span id="weekGraph2"></span>
                        </div>

                        <div class="date">
                            <b>7/24</b>
                        </div>
                    </div>

                    <div class="graph_bundle">
                        <div class="default_bar">
                            <span id="weekGraph3"></span>
                        </div>

                        <div class="date">
                            <b>7/25</b>
                        </div>
                    </div>

                    <div class="graph_bundle">
                        <div class="default_bar">
                            <span id="weekGraph4"></span>
                        </div>

                        <div class="date">
                            <b>7/26</b>
                        </div>
                    </div>

                    <div class="graph_bundle">
                        <div class="default_bar">
                            <span id="weekGraph5"></span>
                        </div>

                        <div class="date">
                            <b>7/27</b>
                        </div>
                    </div>

                    <div class="graph_bundle">
                        <div class="default_bar">
                            <span id="weekGraph6"></span>
                        </div>

                        <div class="date">
                            <b>7/28</b>
                        </div>
                    </div>

                    <div class="graph_bundle">
                        <div class="default_bar">
                            <span id="weekGraph7"></span>
                        </div>

                        <div class="date">
                            <b>7/29</b>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {}
</script>

<style>
    div{
        margin: 0;
        padding: 0;
    }
    .container {
        display: flex;
        flex-direction: column;
        height: calc(100% - 55px);
    }
     #home_1 {
        background-color: #42bfeb;
        flex: 1 1 0;
    }

    #home_2 {
        flex: 1 1 0;
        display: flex;
        height: 100%;
        flex-direction: column;
        align-items: center;
        justify-content: center;
    }

    #home_3 {
        flex: 1 1 0;
        display: flex;
        height: 100%;
        flex-direction: column;
        align-items: center;
        justify-content: center;
    }

    #home_dailyGraph {
        height: 60%;
        width: 80%;

        margin: 5% auto 0;

        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;

        border-radius: 20px;
        background-color: #FFFFFF;
    }

    #inner_home_dailyGraph {
        height: 85%;
        width: 80%;
    }

    #today_comment {
        /* margin-left: 5%; */
        font-size: 20px;
    }

    #home_title {
        margin-top: 10px;
        font-size: 30px;
        margin-left: 10%;
        color: #FFFFFF;
    }

    #upper_Graph {
        font-size: 20px;
        display: flex;
        flex-direction: row;
    }

    #right_yatsu {
        display: flex;
        flex-direction: row;
        margin-left: auto;
    }

    #main_daily_graph {
        font-size: 15%;
        margin: 0 auto;
        height: 15%;
        width: 100%;
        border-radius: 20px;
        background-color: #778899;
    }

    #main_daily_graph span {
        display: block;
        height: 100%;
        line-height: 100%;
        width: 75%;
        text-align: right;

        animation: stack 2s 1;
        box-sizing: border-box;
        border-radius: 20px;
        background-color: crimson;
    }

    #danger {
        color: crimson;
    }
    #safe {
        color: #42bfeb;
    }

    #safe_comment {
        /* margin-left: 5%; */
        font-size: 15px;
    }
    #safe_score {
        /* margin-left: 5%; */
        font-size: 30px;
    }

    #home_article {
        height: 100%;
        width: 100%;
        background-color: #FFFFFF;
    }

    #home_weeklyGraph {
        height: 90%;
        width: 100%;

        display: flex;
        flex-direction: column;
        background-color: #FFFFFF;
    }

    #news_text {
        font-size: 20px;
        margin-left: 10%;
        margin-top: 5%;
    }

    #weekly_report_text {
        font-size: 20px;
        margin-left: 10%;
        margin-top: 5%;

    }

    #main_weekly_graph {
        display: flex;
        flex-direction: row;
        justify-content: center;
        margin-top: 10px;

        width: 100%;
        height: 60%;
        /* background-color: aquamarine; */
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

    #main_weekly_graph div span {
        height: 50%;
        width: 100%;

        animation: stack_bottomUp 2s 1;
        margin-top: auto;
        display: block;
        box-sizing: border-box;
        border-radius: 20px;
        background-color: crimson;
    }

    .graph_bundle {
        height: 100%;
        width: 5%;
        margin-left: 2.5%;
        margin-right: 2.5%;

        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        /* background-color: #778899; */
    }

    .date {
        font-size: 10px;
        height: 10%;
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
