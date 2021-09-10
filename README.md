# Okat_Main

## 주의사항
### cordova build android 시에 local properties가 없다고 에러가 뜰 경우, 안드로이드 스튜디오로 platform폴더 밑에 있는 android폴더를 열어줍시다. 
### 안드로이드 스튜디오에서 자동으로 빌드가 된 이후에 다시 cordova build android를 해줍시다.

### 또한 자꾸 cordova 관련된 method의 에러가 발생할 경우, 파일 탭을 누르고 Invalidate Caches / Restart 한번 해줍시다

## 파이썬 그래들 설정
1. 일단 파이썬을 컴퓨터에 깔아줍니다.
2. 안드로이드 스튜디오를 켜줍니다.
3. build.gradle (Module: android.app)에 들어가서 ctrl+F python을 해줍니다.
4.  python {
       buildPython "C:/Users/u210813/AppData/Local/Programs/Python/Python39/python.exe"
    }
    본 경로를 본인 컴퓨터에 깔린 파이썬 실행파일 위치로 바꿔줍니다.
## 안드로이드 그래들 추천
그래들 버전 6.5 이상에서 잘 돌아갑니다. 그러나 7.0.0에서는 잘 돌아가지 않습니다.
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
