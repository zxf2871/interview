# 1 android UI适配屏幕 px
[适配]: https://www.jianshu.com/p/55e0fca23b4f
## 1dpi 计算
- 普通手机:1920x1080??: (1920^2 + 1080^2)^(1/2) / 5英寸 =  2203 / 5 = 440dpi    分辨率   1080/(440/160) = 372.7dp 实际:411
- 小米10 2340x1080 (2340^2 + 1080^2)^(1/2) / 6.67英寸 = 2577 / 6.67 = 386dpi    分辨率 1080/(386/160) = 447.7dp  实际:392
- ????density??, ?????dp???. ???view??dp???????????????; 

??????dpi ??????????android????getDisplayMetrics

# 2 AB两个Activity生命周期
start A -> start B -> backpressed
| A: onCreate
| A: onStart
| A: onResume

| A: onPause
| B: onCreate
| B: onStart
| B: onResume
| A: onStop

| B: onPause
| A: onRestart
| A: onStart
| A: onResume
| B: onStop
| B: onDestroy

A 是窗体的话 打开B时不会stop,  注意Activit的四个lunchMode: stander; singleTop; singleTask; singleInstance

# 3 startActivity 子线程是否有问题?
| 没有问题. 

# 4 Handler 机制
```
    //经常见到有这样的失败
    void checkThread() throw new CalledFromWrongThreadException(
                        "Only the original thread that created a view hierarchy can touch its views");
```

```
    Handler.sendMessage(msg); //handler发送消息
    -> sendMessageAtTime(msg, uptimeMillis); //调用中间方法
    -> enqueueMessage(); //发送给 MessageQueue 
    -> Looper.loop(); //Looper的死循环 取MessageQueue中的消息
    -> msg.target.dispatchMessage(msg); //死循环中的for(;;){}  msg.target就是Handler
    -> Handler.dispatchMessage(msg); //调用了handler
    -> handleMessage(msg); //
    
```
## 阻塞UI是怎么回事? post和sendMessage()有什么区别? 有哪些需要注意的问题? 闲时加载?
1. 
2. post 是将执行的Runnable放到message中的mCallBack里面. 然后再通过sendmessage发给队列. 
   在handler执行dispatchMessage时如果message有mCallBack也就是这个Runnable则执行Runnable; 否则执行handleMessage()
3. 闲时加载
```aidl
            Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() {
                @Override
                public boolean queueIdle() {
                    return false;
                }
            });
```