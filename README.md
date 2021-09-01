# Okat_Main

## 파이썬 그래들 설정
1. 일단 파이썬을 컴퓨터에 깔아줍니다.
2. 안드로이드 스튜디오를 켜줍니다.
3. build.gradle (Module: android.app)에 들어가서 ctrl+F python을 해줍니다.
4.  python {
       buildPython "C:/Users/u210813/AppData/Local/Programs/Python/Python39/python.exe"
    }
    본 경로를 본인 컴퓨터에 깔린 파이썬 실행파일 위치로 바꿔줍니다.

## clone
$ git clone https://github.com/mmh0705/Okat_Main.git

## install node_modules
$ npm install

## webpack for www
$ webpack 

## cordova platform build
$ cordova platform add android  
$ cordova platform ls  
$ cordova plugin add  
$ cordova plugin add .\CordovaCustomPlugin\  
$ cordova build android  


> A Vue.js project

## Build Setup

``` bash
# install dependencies
npm install

# serve with hot reload at localhost:8080
npm run dev

# build for production with minification
npm run build

# build for production and view the bundle analyzer report
npm run build --report

# run unit tests
npm run unit

# run e2e tests
npm run e2e

# run all tests
npm test
```

For a detailed explanation on how things work, check out the [guide](http://vuejs-templates.github.io/webpack/) and [docs for vue-loader](http://vuejs.github.io/vue-loader).
