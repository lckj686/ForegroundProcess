# ForegroundProcess
写设备端的常驻进程。设备端对进程的管理 没有手机端这么严格。 使用前台进程是可以常驻的。在sunmi poss 设备 上写了一个demo

## 分成了三个方案进行，论证
- 本地log 均记录了 运行时间

### 方案1. 开启前台服务，闹钟定期弹出一个通知
- 开启前台服务
- 闹钟定期弹出通知

### 方案2.开启前台服务，监听锁屏  
- 开始前台服务
- 锁屏时弹出一个像素的activity，
- 解锁时finish该activity
- 闹钟定时弹出通知

### 方案3.开启前台服务，闹钟检测
- 开启前台服务
- 开闹钟定期检查服务进程是否还在run，没有run的话启动起来

## 进程保活的点有参考
：https://mp.weixin.qq.com/s/6w0tMtDA9kPwo0E9xq_BsA

## 结论
目前在硬终端sunmi设备上的运行，几种方案，都是可以一直保活的。后面会持续测试。
android 系统是 5.1

## 在手机端运行
有三个测试机，老三星android 4.3，华为p7 android 6.0，小米5s android7.0
### 1 开比较多的app，屏幕亮着几种方案在不同的手机上，都是可以一直运行的
### 2 开比较多的app，锁屏，解锁
    - 在华为p7 上方案1 和方案3直接进程就被释放了，方案2可以呆的比较久 具体多久还在跟  
    - 在老三星4.3上表现都比较好  
    -   
    - 小米5s 7.0 要是开启自启动，即使手动关闭 也会自动拉起来，并且置到 可视区  


