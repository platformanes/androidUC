androidUC
=========

## UC安卓平台的的ANE（基于官方的进行 优化）
优化以下内容：
* 把悬浮写进FLASH，解决横屏项目竖屏切换 主Activiry会切掉的BUG
* 优化屏幕模式：横屏 竖屏 自由转屏
* 减小了ANE体积 大小减小到600K左右；
* 温馨提示：切换屏幕方向的BUG只在开启了GPU加速的横屏项目上会引发。若没开启则直接用官方的ANE即可。

## 资源

* 官方ANE[UC for ANE](http://down0.game.uc.cn/cpss/SDK/UCSDK_Android.rar) 
* java原版 [UC for Android](http://down0.game.uc.cn/cpss/docs/html/sdk_android.html)

## 编译方法
* 修改其中的 `pack_ane.bat ` 中的ADT路径为正确本机的路径
* 运行 `pack_ane.bat`


## 作者

* [platformANEs](https://github.com/platformanes)由 [zrong](http://zengrong.net) 和 [rect](http://www.shadowkong.com/) 共同发起并完成。