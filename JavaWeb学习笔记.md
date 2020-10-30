# 什么是JavaWeb

JavaWeb是指，所有通过Java语言编写可以通过浏览器访问的程序的总称



JavaWeb是基于请求和响应来开发的。

请求时指客户端给服务器发送数据，叫请求Request。

响应是指服务器给客户端回传数据，叫响应Response。

请求和响应是成对出现的



# 常用的Web服务器

+ Tomcat：由Apache组织提供的一种web服务器，提供对jsp和servlet的支持。它是一种轻量级的javaWeb容器（服务器），也是当前应用最广的JavaWeb服务器（免费）。
+ Jbos：是一个遵从JavaEE规范的、开放源代码的、纯Java的EJB服务器，它支持所有的JavaEE规范（免费〉。
    GlassFish： 由oracle公司开发的一款JavaWeb服务器，是一款强健的商业服务器，达到产品级质量（应用很少)。
+ Resin：是CAUCHO公司的产品，是一个非常流行的服务器，对servlet和JSP提供了良好的支持，性能也比较优良，resin自身采用JAVA语言开发（收费，应用比较多〉。
+ WebLogic：是oracle公司的产品，是目前应用最广泛的Web服务器，支持JavaEE规范，而且不断的完善以适应新的开发要求，适合大型项目（收费，用的不多，适合大公司〉。





## 启动Tomcat服务器

Tomcat目录下的bin目录下的startup.bat文件，双击即可启动。

或者catalina run

可以通过shutdown.bat关闭Tomcat服务器



#### 更改Tomcat的端口号

找到comf/Server.xml中的Connector中的port属性，来更改端口号

修改过后一定要重启Tomcat



## 部署Web工程到Tomcat中

方法一：

+ 将web工程的目录拷贝到Tomcat的webapps目录下即可
    + 输入url即可访问

方法二：

+ 找到Tomcat下的conf/Catalina目录。在目录下创建一个 XXX.xml文件

    + 在文件中

    + ```xml
        <!-- Context表示一个工程上下文
        	 path表示工程访问路径:/abc
        	 docBase表示工程目录在哪里
        -->
        <Context path="/abc" docBase="E:\project" />
        ```

    + http://localhost:8080/abc/indexl.html



# Servlet

## servlet的生命周期

+ 执行Servlet构造器方法
+ 执行init初始化方法
+ 执行service方法
    + 只有该方法会多次执行，其他的都只执行一次
+ 执行destroy销毁方法



## ServletConfig类

除了方法中给定，还可以自己获取、

`ServletConfig ServletConfig = getServletConfig();`

+ 可以获取Servlet程序的别名servlet-name
+ 可以获取初始化参数init-param

+ 可以获取Servlet类的上下文ServletContext
    + servletConfig.getServletContext()



Servlet程序和ServletConfig对象都是由Tomcat创建，我们使用

Servlet程序默认是第一次访问时创建，ServletConfig是每个Servlet程序创建时，就创建一个对应的ServletConfig对象

一定要注意，ServletConfig是在init()方法内进行初始化的（从这里面获取到的）



## ServletContext类

+ 是一个接口，表示Servlet上下文对象

+ 一个Web工程，只有一个ServletContext对象实例

+ ServletContext对象是一个域对象

    + 像Map一样存取数据，这里的域指的是存取数据的操作范围

        + |        | 存             | 取             | 删                |
            | ------ | -------------- | -------------- | ----------------- |
            | Map    | put()          | get()          | remove()          |
            | 域对象 | setAttribute() | getAttribute() | removeAttribute() |

+ ServletContext是在Web工程部署启动时创建，在工程停止时销毁

+ 作用

    + 获取web.xml中配置的上下文参数context-param
    + 获取当前的工程路径，格式：/工程路径
    + 获取工程部署后在服务器硬盘上的绝对路径
    + 像map一样存取数据



## HttpServletRequest类

每次有请求进入Tomcat服务器，Tomcat服务器就会把请求过来的HTTP协议信息解析好封装到Request对象中。

然后传递到service方法（doGet和doPost）中给我们用，可以通过HTTPServletRequest对象，获取到所有请求的信息





## 请求转发

特点：

+ 浏览器地址栏没变化
+ 仍是一次请求
+ 共享Request域中的数据
+ 可以转发到WEB-INF目录下
+ 不可以转发到工程以外的资源上



#### base标签的作用

+ 设置页面相对路径工作时参照的地址

`<base href="http://localhost:8080/web_servlet/a/b/(c.html)"`

+ 其中最后的资源名可以省略，但是`/`不可以省略

+ 当a标签是`<a href="../../index.html"></a>`时，不会参考当前浏览器的地址，而是参考base标签内的地址来跳转



#### web中 / 斜杠的不同意义

+ `/`斜杠被浏览器解析，得到的地址是：http://ip:port/
    + `<a href="/">斜杠</a>`

+ `/`斜杠被服务器解析，得到的地址是：http://ip:port/工程路径
    + `<url-pattern>/servlet1</url-pattern>`
    + servletContext.getRealPath("/");
    + request.getRequestDispatcher("/")

+ 特殊情况：response.sendRediect("/");
    + 把`/`斜杠发送给浏览器解析，得到http://ip:port/



#### HttpServletResponse类的作用

HttpServletResponse类和HttpServletRequest类一样，每次请求进来，Tomcat服务器都会创建一个Response对象传递给Servlet程序去使用。

HttpServletRequest表示请求过来的信息，HTTPServletResponse表示所有响应的信息

如果需要设置返回给客户端的信息，都可以通过HttpServletResponse对象来进行设置



### 两个输出流

字节流	getOutputStream	常用于下载（传递二进制数据）

字符流	getWriter				  常用于回传字符串（常用）

两个流同时只能用一个，否则抛异常



### 给客户端回传字符串数据

```java
@Override
protected void deGet(HttpServletRequest request, HttpServletResponse response){
    PrintWriter writer = response.getWriter();
    writer.write("response's content!")
}
```



#### 解决响应中文乱码

```java
        // 设置服务器字符集为UTF-8
//        response.setCharacterEncoding("UTF-8");

        // 通过响应头，设置浏览器也使用UTF-8字符集
//        response.setHeader("Content-Type", "text/html; chartSet=UTF-8");

        // 或者这样，这样会同时设置服务器和客户端都使用UTF-8字符集，还设置了响应头
        response.setContentType("text/html; charset=UTF-8");
```



## 请求重定向

特点

+ 浏览器地址栏会发生变化
+ 两次请求
+ 不共享Request域中的数据
+ 不能访问WEB-INF下的资源
+ 可以访问工程外的资源

方案一：

```java
// 状态码改为302，表示重定向
response.setStatus(302);
// 设置响应头，说明 新的地址在哪里
response.setHeader("Location", "http://localhost:8080/web_servlet/response2");
```

方案二：

```java
// 或者直接这样
response.sendRedirect("http://localhost:8080/web_servlet/response2");
```



#### JavaEE三层架构

![image-20201029195651493](C:\Users\Jarvis\AppData\Roaming\Typora\typora-user-images\image-20201029195651493.png)

![image-20201029195828824](C:\Users\Jarvis\AppData\Roaming\Typora\typora-user-images\image-20201029195828824.png)



#### 代码流程

+ 第一步：创建数据库和需要的表

+ 第二步：构建数据库表对应的JavaBean对象

+ 第三步：写数据库的工具类（JdbcUtils等）

    + 可以用Druid
    + 获取数据库连接池中的连接
    + 关闭连接，放回数据库连接池

+ 第四步：编写BaseDao

    + 可以用dbutils
    + 增删改查

+ 第五步：编写Service和测试 

    



# Listener监听器

+ Listener是JavaEE的规范，就是接口
+ 监听器的作用是，监听某种事物的变化，然后通过回调函数，反馈给程序去做一些相应的处理



## ServletContextListener监听器







# 文件的上传和下载

+ 要有个form标签，method=post请求
+ form标签的encType属性值必须为multipart/form-data值
    + 表示提交的数据，以多段（每个表单项一个数据段）的形式进行拼接，然后以二进制流的形式发送给服务器
    + 客户端怎么发送的，服务器就怎么接收，服务器需要使用zi
+ 在form标签中使用`<input type=file>`添加上传的文件
+ 编写服务器代码接收，处理上传的数据