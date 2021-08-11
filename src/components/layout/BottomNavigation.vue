<template>
        <nav class="nav">
            <a href="#/" class="nav__link">
                <div>
                    <b-icon icon="house-fill" class="nav__icons"></b-icon>
                </div>
                <span class="nav__text">홈</span>
            </a>

            <a href="#/sms" class="nav__link">
                <div>
                    <b-icon icon="chat-left-dots-fill" class="nav__icons"></b-icon>
                </div>
                <span class="nav__text">문자열람</span>
            </a>

            <a href="#slide-3" class="nav__link">
                <div>
                    <b-icon icon="graph-up" class="nav__icons"></b-icon>
                </div>
                <span class="nav__text">상세분석</span>
            </a>

            <a href="#slide-4" class="nav__link">
                <div>
                    <b-icon icon="lightning-fill" class="nav__icons"></b-icon>
                </div>
                <span class="nav__text">예방팁</span></a>

            <a href="#slide-5" class="nav__link">
                <div>
                    <b-icon icon="list" class="nav__icons"></b-icon>
                </div>
                <span class="nav__text">메뉴</span></a>
        </nav>
</template>

<script>
export default {
}
</script>

<style>
.nav {
    /* position: fixed; */
    bottom: 0;
    width: 100%;
    height: 55px;
    box-shadow: 0 0 3px rgba(0, 0, 0, 0.2);
    background-color: #ffffff;
    display: flex;
    overflow-x: auto;
}

.nav__link {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    flex-grow: 1;
    min-width: 50px;
    overflow: hidden;
    white-space: nowrap;
    font-family: sans-serif;
    font-size: 13px;
    color: #444444;
    text-decoration: none;
    -webkit-tap-highlight-color: transparent;
    transition: background-color 0.1s ease-in-out;
}

.nav__link:hover {
    background-color: #eeeeee;
}

.nav__link--active {
    color: #009578;
}

.nav__icon {
    font-size: 18px;
}

</style>
