1. sdk依赖了两个非常常见的开源库
    api 'com.squareup.okhttp3:okhttp:3.1.2'  // Okhttp库
    api 'com.squareup.retrofit2:retrofit:2.0.2' // Retrofit库

   直接用的话，则要求宿主app也依赖了这两个库
   如果要做到传递依赖的话，得把aar包上传到maven仓库上，比较麻烦而且我也不会。。

2、接口请求都时mock的数据，没有实际调通，不过鉴权、解析相关逻辑都是按之前的文档写的，都打印了日志可以检查；

3、SDK只对外暴露了一个类 就是 com.adbase.sdk.IAdBaseSDK ，使用方式参考demo

4、代码打包没有做混淆处理