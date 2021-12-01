# 1 NativeCrash 特性
1. 访问非法地址
2. 地址对齐出错
3. 主动abort
# 2.  合格的异常捕获组件: 
1 支持crash时进行更多扩展操作; 
2 打印logcat和日志; 
3 上报crash次数; 
4 对不同的crash做不同的恢复措施; 
5 针对业务不断改进适应
# 3. 方案
1. Google Breakpad; 
2. Logcat;
3. coffeecatch;
# 4. Native崩溃捕获
1. 编译端 编译文件保留
2. 客户端 收集尽可能的信息写入日志.择时上传
3. 服务端 寻找合适的符号文件, 生成c/c++调用栈
# 5 Native崩溃难点
1. 文件句柄泄漏, 导出日志失败
2. 栈溢出导致日志生成失败 使用额外空间signalstack; 系统会在危险的情况下把栈指针指向这里,  特殊请求需要替换当前栈, 应在堆中预留部分空间
3. 堆内存耗尽导致日志生产失败
4. 堆破坏或二次崩溃导致日志生成失败
5. 遵循Android文本格式添加更多重要信息; 改造Breakpad, 增加Logcat信息, java调用栈, 其他信息
# 6 信号量代表含义:
``` 
    #define SIGHUP 1  // 终端连接结束时发出(不管正常或非正常)
    #define SIGINT 2  // 程序终止(例如Ctrl-C)
    #define SIGQUIT 3 // 程序退出(Ctrl-\)
    #define SIGILL 4 // 执行了非法指令，或者试图执行数据段，堆栈溢出
    #define SIGTRAP 5 // 断点时产生，由debugger使用
    #define SIGABRT 6 // 调用abort函数生成的信号，表示程序异常
    #define SIGIOT 6 // 同上，更全，IO异常也会发出
    #define SIGBUS 7 // 非法地址，包括内存地址对齐出错，比如访问一个4字节的整数, 但其地址不是4的倍数
    #define SIGFPE 8 // 计算错误，比如除0、溢出
    #define SIGKILL 9 // 强制结束程序，具有最高优先级，本信号不能被阻塞、处理和忽略
    #define SIGUSR1 10 // 未使用，保留
    #define SIGSEGV 11 // 非法内存操作，与 SIGBUS不同，他是对合法地址的非法访问，    比如访问没有读权限的内存，向没有写权限的地址写数据
    #define SIGUSR2 12 // 未使用，保留
    #define SIGPIPE 13 // 管道破裂，通常在进程间通信产生
    #define SIGALRM 14 // 定时信号,
    #define SIGTERM 15 // 结束程序，类似温和的 SIGKILL，可被阻塞和处理。通常程序如    果终止不了，才会尝试SIGKILL
    #define SIGSTKFLT 16  // 协处理器堆栈错误
    #define SIGCHLD 17 // 子进程结束时, 父进程会收到这个信号。
    #define SIGCONT 18 // 让一个停止的进程继续执行
    #define SIGSTOP 19 // 停止进程,本信号不能被阻塞,处理或忽略
    #define SIGTSTP 20 // 停止进程,但该信号可以被处理和忽略
    #define SIGTTIN 21 // 当后台作业要从用户终端读数据时, 该作业中的所有进程会收到SIGTTIN信号
    #define SIGTTOU 22 // 类似于SIGTTIN, 但在写终端时收到
    #define SIGURG 23 // 有紧急数据或out-of-band数据到达socket时产生
    #define SIGXCPU 24 // 超过CPU时间资源限制时发出
    #define SIGXFSZ 25 // 当进程企图扩大文件以至于超过文件大小资源限制
    #define SIGVTALRM 26 // 虚拟时钟信号. 类似于SIGALRM, 但是计算的是该进程占用的CPU时间.
    #define SIGPROF 27 // 类似于SIGALRM/SIGVTALRM, 但包括该进程用的CPU时间以及系统调用的时间
    #define SIGWINCH 28 // 窗口大小改变时发出
    #define SIGIO 29 // 文件描述符准备就绪, 可以开始进行输入/输出操作
    #define SIGPOLL SIGIO // 同上，别称
    #define SIGPWR 30 // 电源异常
    #define SIGSYS 31 // 非法的系统调用
```
# 7 崩溃分析
1. 崩溃信息: 进程名; 线程名; 崩溃栈堆和类型; 主线程调用栈
2. 系统信息: 系统日志; 机型,系统,厂商,CPU, ABI, Linux版本  共同属性: 设备状态; root; 模拟器
3. 内存信息
```
    //内存剩余
    /proc/meminfo
```
- 虚拟内存: 
```
    /proc/self/status 大小
    /proc/self/maps 具体分布
    64位虚拟内存不成问题
```
4. 资源信息
```
    /proc/self/limits 获取fd的限制
    单个进程允许打开的句柄个数为1024  超过800则需要将所有的fd和文件名输出日志进行排查
    /proc/self/status  线程数
```