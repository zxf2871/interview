# 1 android UI适配屏幕 px
[适配]: [https://www.jianshu.com/p/55e0fca23b4f](适配)
## 1 dpi 计算
- 普通手机:1920x1080??: (1920^2 + 1080^2)^(1/2) / 5英寸 =  2203 / 5 = 440dpi    分辨率   1080/(440/160) = 372.7dp 实际:411
- 小米10 2340x1080 (2340^2 + 1080^2)^(1/2) / 6.67英寸 = 2577 / 6.67 = 386dpi    分辨率 1080/(386/160) = 447.7dp  实际:392
- ????density??, ?????dp???. ???view??dp???????????????; 

- 怎样获取android dpi = Context.getResource().getDisplayMetrics()

# 2 AB两个Activity生命周期
start A -> start B -> backpressed
- | A: onCreate
- | A: onStart
- | A: onResume
- |
- | A: onPause
- | B: onCreate
- | B: onStart
- | B: onResume
- | A: onStop
- |
- | B: onPause
- | A: onRestart
- | A: onStart
- | A: onResume
- | B: onStop
- | B: onDestroy

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
    -> msg new Message()/Handler.obtainMessage()/Handler.obtain(); 创建一个消息msg
    -> Handler.sendMessage(msg)/sendMessageAtTime()/post(Runnable)/postDelayed(Runnable,time)/Message.sendToTarget(); //handler发送消息
    -> Handler.mQueue = Looper.mQueue
    -> mQueue.enqueueMessage(); //发送给 MessageQueue 
    -> Looper.loop(); //Looper的循环 取MessageQueue中的消息
	-> MessageQueue.next() 取msg;  nativePollOnce(ptr, time);//native层的MessageQueue阻塞time时长
    -> msg.target.dispatchMessage(msg); //循环中的for(;;){}  msg.target就是Handler
    -> 先判断mCallback != null; 执行 mCallback.handleMessage(); //也就是post(Runnable)中的Runnable
    -> Handler.handleMessage(msg); // 执行自定义子类中的@Override handleMessage()
    
```
## 阻塞UI是怎么回事? post和sendMessage()有什么区别? 有哪些需要注意的问题? 闲时加载?
[Android 消息机制] http://www.heqiangfly.com/2016/10/10/android-knowledge-message-system-source-code/
1. UI阻塞 "无法响应" 
>
2. post 是将执行的Runnable放到message中的mCallBack里面. 然后再通过sendmessage发给队列. 
   在handler执行dispatchMessage时如果message有mCallBack也就是这个Runnable则执行Runnable; 否则执行handleMessage()
3. 闲时加载
```
    Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() {
        @Override
        public boolean queueIdle() {
            return false;
        }
    });
```
4. 一个线程只能持有一个Looper; new Handler时可以指定Looper, 也可以默认, 默认的话使用当前线程的Looper; Looper.loop()会创建一个MessageQueue, 
   Handler通过sendMessage调用到enqueueMessage()向MessageQueue里发送消息, Looper.loop()循环中调用Message.next()取消息   
5. 同步屏障 msg.targe = null;  
> View绘制的TranversalRunnable
6. postDelay(Runnable, time); 是怎样做到延时的?  
> when = SystemClcok.uptimeMillis() + time;
> enqueueMessage(）将when赋值给 msg.when并根据when插入到队列相对应时间的位置，时间越长越向后放。
> MessageQueue.next()中如果队列第一个header存在延时的 msg 则计算nextPollTimeoutMillis 并调用nativePollOnce(str, nextPollTimeoutMillis)进行精准睡眠，时间到后会唤醒并取走该msg去执行。
> 如果nativePollOnce()方法正在睡眠，这是来了新的消息，首先是通过equeueMessage()将
7. Android为什么只能主线程更新UI? 
> android View线程不安全，多线程访问出现意想不到的问题；加锁会让访问逻辑更复杂，且锁机制太重，影响UI访问效率
8. ThreadLocal 隔离线程变量，Looper是怎样达到自己线程只能有一个且是自己创建的那个Looper的。
> Looper中 sThreadLocal = new ThreadLocal<Looper>(); set get方法限制在各自线程内部，可以在多个线程中互不干扰的存储和读取数据。当Looper.prepare()时，将新创建一个Looper放到sThreadLocal中只和创建的线程相关，也就是说只有创建Looper的线程能拿到自己创建的那个，其他线程是取不到别人的Looper的；
9. Looper的死循环不会阻塞主线程 
>   Looper.loop()虽然是个死循环，但是并非一直轮询。它只有在MessageQueue中通过next()取到msg时才去执行msg对应的操作。而MessageQueue.next()如果没有消息则会处于休眠状态， 这是通过nativePollOnce()这个native方法实现的。只有当下个消息到达或有事务时才被唤醒。
>   nativePollOnce（）是由c层实现的实创资源的等待状态。
MessageQueue.nativePollOnce() native方法弹出一个msg 如果当前没有则阻塞直到有. 
   其中阻塞的nativePollOnce解释和 c++代码: [参考链接](https://www.cnblogs.com/jiy-for-you/p/11707356.html)

# 3 React-Native 原生开发-android

# 4 RxJava

# 5 Glide加载图片

# 6 ANR Application Not responding
* Service 前台20s 后台200s
* BroadcastQueue 前台10s 后台60s 
* ContentProvider 10s
* InputDispatching 5s

# 7 Activity启动过程
* Launcher -> startActivitySafely() addFlag(FLAG_ACTIVITY_NEW_TASK)
* Instumentation -> execStartActivity()
* ActivityTaskManager.getService() //AMS 
> .startActivity() -> startActivityAsUser() -> 一串链式调用 

# 8 Binder机制
# 9 怎样计算一个view在屏幕中的百分比
1. getLocalVisibleRect(rec) //获取view 
* 在底部未露出：(0, [x]值是相对于可滑动父布局, wdith, [x]值是相对于可滑动父布局）
* 底部刚露出一部分时(0, 0, wdith, [x]<height); 
* 中间显示全部时(0, 0, wdith, height)
* 滑到上部，有一部分划出去时(0, 0<[x]<height, wdith, [x]=height)
* 完全向上划出屏幕 (0, [x]<-height, wdith, [x]<0) 
> **可见时:** 竖直方向：以自身左上角为(0,0)显示；  wdith是view宽；height是view的高 
``` 
    //可见条件
    return rect.top >=0 && rect.bottom <= view.getHeith();
    
    //显示百分比：只有在可见条件下
    return rect.height() * 100 / getHeight();
    
    //显示上下
    1. rect.top = 0 //在下方露出一部分
    2. rect.bottom = view.getHeight()  //在上面露出一部分
    3. 两个都成立则全部露出。
```

> **不可见时:**
```
    rect.height == view.getHeight();
    return rect.top <0 || rect.bottom > view.getHeith();
    百分比公式 rect.height() * 100 / getHeight() == 100
```
2. getGlobalVisibleRect(rec) //获取view相对滑动父布局左顶点偏移量， 不可见时和getLocalVisibleRect(rec)一致 
3. getDrawingRect(rec) //获取view的绘制范围 （0，0，view.width, view.height）用在和滑动容器上比较合适
4. getHitRect(rec) view相对于父布局的偏移