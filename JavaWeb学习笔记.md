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



### 附件中文名乱码

对于IE和Chrome和Edge浏览器，使用URLEncoder类的encode(filename,charset)方法来解决

+ 新版本的火狐也支持了

对于旧版本的火狐浏览器，使用BASE64编解码来解决 





# Cookie

+ 饼干
+ 由服务器通知客户端保存键值对的一种技术
+ 客户端有了Cookie后，每次请求都发送给服务器
+ 每个Cookie的大小不能超过4kb



#### Cookie的创建

+ 客户端（浏览器）：没有Cookie

+ 服务器（Tomcat）：
    + 创建Cookie对象
        + Cookie cookie = new Cookie("key1", "value1");
    + 通知客户端保存Cookie
        + response.addCookie( cookie );
        + 通过响应头Set-Cookie通知客户端保存Cookie
            + Set-Cookie: key1=value1
+ 客户端收到响应后，发现有set-cookie响应头，就去确认一下有没有这个Cookie
    + 没有就创建
    + 有就修改



#### Cookie的获取

+ 客户端（浏览器）：
    + 有Cookie，key1=value1
+ 通过请求头：Cookie: xx=xx 把Cookie信息发送给服务器
+ 服务器（Tomcat）：
    + 获取客户端发送过来的Cookie只需要一行代码
    + request.getCookies()，返回Cookie[]数组



#### Cookie值的修改

方案一：

+ 先创建一个要修改的Cookie对象
+ 在构造器，同时赋予新的Cookie值
+ 调用response.addCookie(Cookie);

方案二：

+ 先查找到需要修改的Cookie对象

+ 调用setValue()方法赋予新的Cookie值

+ 调用response.addCookie()通知客户端保存修改
    + ```java
        Cookie cookie = CookieUtils.findCookie("key2", request.getCookies());
        if(cookie != null){
            cookie.setValue("newValue2");
            response.addCookie(cookie);
        }
        ```

    + **Cookie值不能是中文，一些符号，但是可以将这些内容通过BASE64编码后使用**



#### Cookie生命控制

+ setMaxAge()：
    + 正数表示在指定的秒数后过期
    + 负数表示关闭浏览器后，删除Cookie。（默认负数）
    + 0表示立即删除
    + 时间都是按格林时间为标准



#### Cookie有效路径Path的设置

+ Cookie的path属性是通过请求的地址来进行有效地过滤

+ ```java
    Cookie cookie = new Cookie("path1", "path1");
    // getContextPath() ===>>> 得到工程路径
    cookie.setPath(request.getContextPath() + "/abc"); // ===>>> /工程路径/abc
    response.addCookie(cookie)
    ```

#### 使用Cookie做到免用户名登录

+ 在第一次登录后，把用户名记录在Cookie中发送给客户端
+ 第二次访问登录页面时，把记录用户名的Cookie发送给服务器，服务器将其中的用户名同登录页面一同返回给客户端



# Session会话

+ Session是Java中的一个接口（HttpSession）
+ Session就是会话，用来维护一个客户端和服务器之间关联的一种技术
+ 每个客户端都有自己的一个Session会话
+ Session会话中，经常用来保存用户登录之后的信息
+ Session会话中，保存的信息可以是中文的



#### Session的创建和获取

+ ```java
    // 创建和获取Session会话对象（API一样）
    HttpSession session = request.getSession();
    // 判断当前Session会话是否是新创建的
    boolean isNew = session.isNew();
    // 获取Session会话的唯一ID
    String id = session.getId();
    ```



#### Session域中数据的存取

```java
// 存
request.getSession().setAttribute("key1", "value1");
// 取
Object key1 = request.getSession().getAttribute("key1");
// 删
request.removeAttribute("key1");
```



#### Session生命周期控制

+ setMaxInactiveInterval(int inteval)设置Session的超时时间（单位为秒），超过指定时长，Session就会被销毁

    + 正数，设定Session的超时时长
    + 负数，永不超时（谨慎使用）
    + 没有0，0不是马上超时
        + session.invalidate()，通过该方法让Session马上超时

+ getMaxInactiveInterval()获取Session的超时时间

+ Session默认的超时时长为30分钟

    + 这是因为在Tomcat服务器的配置文件web.xml中默认有以下配置

    + ```xml
        <session-config>
        	<session-time>30</session-timeout>
        </session-config>
        ```

        + 如果需要修改指定Session的超时时长，就需要使用setMaxInactiveInterval

+ Session的超时指的是，客户端两次请求的最大间隔时长

+ 当重新部署程序的时候，再次访问，会覆盖掉原有的Session



## 浏览器和Session之间关联的技术内幕

+ 客户端（浏览器）刚开始没有Cookie信息，这时向服务器（Tomcat）发送请求

+ 服务器，request.getSession();创建会话对象

    + 服务器每次创建Session会话的时候，都会创建一个**Cookie对象**
        + 这个Cookie对象的**key永远是：JSESSIONID**
        + **值是**新创建出来的**Session的id值**（可以通过session.getId()查看）
    + 通过响应把新创建出来的Session的id值返回给客户端
        + Set-Cookie: JSESSIONID=Session的id值；

+ 浏览器解析收到的数据，就马上创建出一个Cookie对象

+ 后续有了Cookie后，每次请求，都会把Session的id以Cookie的形式发送给服务器

    + Cookie：JSESSIONID=Session的id值

+ 服务器对于后续请求，request.getSession();通过Cookie中的id值找到之前创建好与之对应的Session对象，并返回

+ 如果在浏览器中，将该Session对象的Cookie被删除了，服务器会重新创建Session会话，将上述流程重新走一遍

    

## 表单重复提交的问题

三种常见情况：

+ 提交完表单，服务器使用**请求转发**来进行页面的跳转。这个时候，当用户按下F5，就会发起最后一次的请求。造成表单重复提交的问题。
    + 解决方法：使用**重定向**来进行跳转

+ 用户正常提交服务器，但是用于网络延迟等原因，迟迟未收到服务器的响应，这时用户以为提交失败，就多点了几次提交操作，也会造成表单重复提交
+ 用户正常提交服务器，服务器也没有延迟，但是提交完成后，用户回退浏览器，重新提交，也会造成表单重复提交



#### 解决方案：验证码

+ 当用户第一次访问表单时，就要给表单生成一个随机的验证码字符串
    + 要把验证码保存到Session域中
    + 要把验证码生成为验证码图片显示在表单中

+ 当用户将填好的表单发送给服务器时，对应的Servlet程序要进行相应的操作
    + 提取Session中的验证码，并删除Session中的验证码
    + 获取表单中的表单项信息
    + 比较Session中的验证码和表单项中的验证码是否相等



### Google验证码kaptcha

+ 导入谷歌验证码的jar包
+ 在web.xml中配置用于生成验证码的Servlet程序
+ 在表单中使用img标签去显示验证码图片并使用它



注意：当请求验证码图片的地址不变时，由于浏览器会将该地址的资源缓存下来，再发请求时就无法获取新的验证码图片了。因此可以在请求验证码图片的地址后面加个参数，比如

```javascript
$("#kaptcha").click(function () {
    this.src = "kaptcha.jpg?d=" + new Date();
});
```



# Filter过滤器

+ 三大组件之一，JavaEE的规范，接口
+ 作用：**拦截请求，过滤响应**

```xml
    <!-- filter标签用于配置一个Filter过滤器 -->
    <filter>
        <!-- 给filter起一个别名 -->
        <filter-name>FilterServlet</filter-name>
        <!-- 配置filter的全类名 -->
        <filter-class>com.example.filter.TestFilter</filter-class>
    </filter>
    <!-- filter-mapping配置Filter过滤器的拦截路径 -->
    <filter-mapping>
        <!-- filter-name表示当前的拦截路径给哪个filter使用 -->
        <filter-name>FilterServlet</filter-name>
        <!-- url-pattern配置拦截路径
            / 斜杠 表示请求地址为：http://ip:port/工程路径/ 映射到IDEA的web目录
            /imgs/* 表示请求地址为： http://ip:port/工程路径/imgs/*
        -->
        <url-pattern>/imgs/*</url-pattern>
    </filter-mapping>
```

```java
/**
     * doFilter方法是专门用于拦截请求，过滤响应的。 可以做权限检查
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 需要获取强转成子类对象
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        // 获取会话
        HttpSession session = httpServletRequest.getSession();
        // 提取 user 字段
        Object user = session.getAttribute("user");
        if (user == null){
            System.out.println("尚未登录");
            StringBuffer requestURL = httpServletRequest.getRequestURL();
            System.out.println(requestURL.toString());
            session.setAttribute("Referer", requestURL.toString());
            servletRequest.getRequestDispatcher("/login.html").forward(servletRequest, servletResponse);
            return;
        }else{
            System.out.println("已登录继续！");
            // 让程序继续往下访问用户的目标资源
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
```

当浏览器有缓存的页面或者资源时，就不会去请求服务器

这时需要清掉浏览器的缓存或者在请求的url中添加能够唯一识别的参数，使得请求的url不是相同的即可



#### Filter过滤器的使用步骤

+ 编写一个类去实现Filter接口
+ 实现过滤方法doFilter()
+ 到web.xml中去配置Filter的拦截路径



#### Filter生命周期

Filter的生命周期包含几个方法：

+ 构造器方法
+ init初始化方法
    + 在Web工程启动的时候，1、2步都启动了
+ doFilter过滤方法
    + 每次拦截到请求就会执行
+ destroy销毁方法
    + 停止Web工程时就会执行



## FilterConfig类

Filter过滤器的配置文件

Tomcat每次创建Filter的时候，也会同时创建一个FilterConfig类，这里包含了Filter配置信息

FilterConfig类的作用是获取filter过滤器的配置内容

+ 获取Filter名称filter-name的内容
+ 获取Filter中配置的init-param初始化参数
+ 获取ServletContext对象



### FilterChain过滤器链

FilterChain.doFilter()方法的作用

+ 执行下一个Filter过滤器（如果有的话）
+ 执行目标资源（没有Filter）



如果有两个Filter（Filter1在前，Filter2在后）的情况下：

+ 执行Filter1的前置代码
    + 执行Filter1中的FilterChain.doFilter();
+ 执行Filter2的前置代码
    + 执行Filter2中的FilterChain.doFilter()；
        + 若没有执行FilterChain.doFilter()；就直接跳到Filter1的后置代码了
    + 结果没有别的Filter了
    + 就执行目标资源（html、jsp页面、servlet程序、图片、文本、视频等），返回给浏览器
+ 执行Filter2的后置代码
+ 执行Filter1的后置代码



在多个Filter过滤器执行的时候，它们执行的先后顺序是他们在web.xml中从上到下的顺序决定的，而且应该是由filter-mapping来决定的



多个Filter过滤器执行的特点：

+ 所有filter和目标资源**默认都执行在同一个线程中**
+ 多个Filter共同执行的时候，它们都使用同一个Request对象，数据共享



#### Filter拦截路径的三种方式

+ 精确匹配
+ 目录匹配
    + `<url-pattern>/imgs/*</url-pattern>`
+ 后缀名匹配
    + `<url-pattern>*.html</url-pattern>`



Filter过滤器只关心请求的地址是否匹配，不关心请求的资源是否存在

同时一个Filter可以拦截多个路径，即一个filter-mapping内可以有多个url-pattern



### ThreadLocal的使用

**用于解决线程安全的工具类**

每个线程都保持对线程局部变量副本的隐式引用，只要线程是活动的并且ThreadLocal实例是可访问的；在线程消失之后，其线程局部实例的所有副本都会被垃圾回收（除非存在对这些副本的其他引用）。

+ ThreadLocal可以给当前线程关联一个数据（可以是普通变量，可以是对象，也可以是数组，集合）
+ ThreadLocal的特点：
    + ThreadLocal可以为当前线程关联一个数据（它可以像Map一样存取数据，key为当前线程）
    + 每一个ThreadLocal对象，只能为当前线程关联一个数据，如果要为当前线程关联多个数据，就需要使用多个ThreadLocal对象实例
    + 每个ThreadLocal对象实例定义的时候，一般都是static类型
    + ThreadLocal中保存数据，在线程销毁后，会由JVM虚拟机自动释放

```java
public static ThreadLocal<Object> threadLocal = new ThreadLocal<Object>();
threadLocal.set("abc"); // 被后续的覆盖
threadLocal.set("def");
threadLocal.get(); // def
```



#### JDBC的数据库事务管理

```java
Connection conn = JdbcUtils.getConnection();
try{
    conn.setAutoCommit(false); // 设置为手动管理事务
    // 执行一系列的jdbc操作
    conn.commit(); // 手动提交事务
}catch(Exception e){
    conn.rollback(); // 回滚事务
}
```

+ 要确保所有操作要么都成功，要么都失败，就必须要使用数据库的事务
+ 要确保所有操作都在一个事务内，就必须要确保，所有操作都使用同一个Connection连接对象
+ 如何确保所有操作都使用同一个Connection连接对象？
    + 可以使用ThreadLocal对象，来确保所有操作都使用同一个Connection对象
    + ThreadLocal要确保所有操作都使用同一个Connection连接对象
    + 那么操作的前提条件是**所有操作都必须在同一个线程中完成**
    + 并且在这时可以在dao层捕获异常，但是一定要往上抛，
        + 好让服务层执行查询、插入、更新等等操作时，能捕获到异常
            + 这时如果没有异常就commit
            + 有异常就rollback
            + 最后在提交和回滚的finally中执行关闭连接close（如果是用的连接池，就执行连接池中的remove操作）

 

#### 使用Filter统一给Service方法加上try-catch来管理事务

![image-20201104160545036](C:\Users\Jarvis\AppData\Roaming\Typora\typora-user-images\image-20201104160545036.png)

#### 使用Tomcat统一管理异常，友好展示错误页面

+ 需要有异常页面(html/jsp等)

+ 需要配置web.xml文件

    + ```xml
        <!-- error-page标签配置，服务器出错之后，自动跳转的页面 -->
        <error-page>
        	<!-- error-code是错误类型 -->
            <error-code>404</error-code>
            <!-- location标签表示，要跳转去的页面路径 -->
            <location>/pages/error/error404.html</location>
        </error-page>
        ```



# JSON

json是一种轻量级的数据交换格式

+ 轻量级指的是跟xml做比较
+ 数据交换指的是客户端和服务器之间业务数据的传递格式



## JSON在javaScript中的使用

### json的定义

+ var json = {"a":"123","b":321,"c":true};

### json的访问

+ json["a"]或json.a



### json的两个常用方法

json存在的两种形式

+ 对象的形式，称为json对象
+ 字符串的形式存在，称为json字符串

一般要操作json中的数据时，需要json对象的格式

一般要在客户端和服务器之间进行数据交换的时候，使用json字符串



#### JSON.stringify()

把JSON对象转换称为JSON字符串

var jsonObjString = JSON.stringify(jsonObj);



#### JSON.parse()

把JSON字符串转换成JSON对象

var jsonObj = JSON.parse(jsonObjString);



## JSON在Java中的使用

需要gson的jar包

#### JSON与JavaBean对象互转

+ ```java
    Person person = new Person(1, "hahah");
    // 创建Gson对象
    Gson gson = new Gson();
    // 将JavaBean对象转换成json对象
    String personJsonString = gson.toJson(person);
    
    // fromJson把json字符串转换回Java对象
    // 参数1，json字符串
    // 参数2，转换成为的Java对象
    gson.fromJson(personJsonString, Person.class);
    ```





#### JSON与Java List互转

```java
List<Person> personList = new ArrayList<>();
personList.add(new Person(1, "哈哈"))；
personList.add(new Person(2, "嘿嘿"))；
Gson gson = new Gson();

// 把List转换为json字符串
String personListJsonString = gson.toJson(personList);

// 将json字符串转换成List，需要一个继承TypeToken<List<Person>>，才能让fromJson明白该怎么转换成List，否则就转换成了List中嵌套着Map，而不是List中是Person对象
List<Person> list = gson.fromJson(personListJsonString, new PersonListType().getType());
// 这是list就是上面的personList了
```

```java
public class PersonListType extends TypeToken<ArrayList<Person>>{
    // 这里面什么都不用写
}
```



#### JSON与Java Map互转

```java
Map<Integer, Person> personMap = new HashMap<>();

personMap.put(1, new Person(1, "哈哈"));
personMap.put(2, new Person(2, "嘿嘿"));

Gson gson = new Gson();

// 把map集合转换成为json字符串
String personMapJsonString = gson.toJson(personMap);

// 把json字符串转换成为map集合
Map<Integer, Person> map = gson.fromJson(personMapJsonString, new PersonMapType().getType());
// 或者使用匿名内部类
Map<Integer, Person> map = gson.fromJson(personMapJsonString, new TypeToken<HashMap<Integer, Person>>(){}.getType());
```

```java
public class PersonMapType extends TypeToken<HashMap<Integer, Person>>{
    // 这里面什么都不用写
}
```



# Ajax请求

AJAX即“Asynchronous Javascript And XML”（异步 JavaScript 和 XML），是一种创建交互式网页应用的网页开发技术

**ajax是一种浏览器通过js异步发起请求，局部更新页面的技术 **



#### 原生Ajax请求

XMLHttpRequest对象的三个重要的属性

+ onreadystatechange：存储函数（或函数名），每当readyState属性改变时，就会调用该函数
+ readyState：存有XMLHttpRequest的状态，从0到4发生变化
    + 0：请求未初始化
    + 1：服务器连接已建立
    + 2：请求已接收
    + 3：请求处理中
    + 4：请求已完成，且响应已就绪
+ statues：
    + 200：OK
    + 404：未找到页面