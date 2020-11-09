<h1 align="center"><a href="https://github.com/728119427/MyBlog" target="_blank">MyBlog</a></h1>

<br/>
<br/>
## MyBlog个人博客(小巧简洁，适合新手)
**声明：项目是学习B站视频所做，并非个人原创！后端代码做了较大的改动，原项目使用JPA,我个人使用的是Mybatis。**<br/>
<br/>
[视频地址](https://www.bilibili.com/video/BV1KJ411D7bW)

<br>

## 在线演示地址
[在线演示](http://117.50.63.69:8090)

<br>

## 功能列表
**前台**(游客可查看)：

- **主页**
    - 显示博客列表
    - 热门标签
    - 热门分类
    - 推荐博客
    - 搜索功能
- **分类**
    - 列出所有分类，并按分类显示博客列表
- **标签**
    - 显示所有标签，并按标签显示博客列表
- **归档**
    - 按年份显示所有博客
- **关于我**
    - 展示个人信息

<br/>

**后台**(博主管理员):

 - **登录**
    - 需要登录后才能进行管理操作
    - **注意并没有注册功能，需要自己再数据库添加user信息！**
 - **博客管理**
    - 对博客进行编辑，删除，新增
 - **分类管理**
    - 对分类进行增删改
 - **标签管理**
    - 对标签进行增删改

<br>
    
## 开发环境及工具
> - IntelliJ IDEA
> - JDK8
> - SpringBoot2.x
> - Maven
> - Git

<br>

## 技术栈 
**前端**：
> - Semantic UI框架

**后端**
> - SpringBoot
> - MyBatis,Mybatis-generator,pageHelper
> - thymeleaf模板

**数据库**
> - Mysql
> - flyway 

<br>

## 集成插件    
|   插件名称      |           链接地址                 |
| :------:| :--------------------------:|
| Markdown编辑器| https://pandao.github.io/editor.md/
|内容排版|https://github.com/sofish/typo.css|
|代码高亮|https://github.com/PrismJS/prism|
|目录生成|https://tscanlin.github.io/tocbot/
|动画插件|https://animate.style/|
|生成博客阅读二维码|https://davidshimjs.github.io/qrcodejs/
|平滑滚动插件|https://github.com/flesler/jquery.scrollTo|

<br>

## 数据库表展示
**`项目使用了数据库管理工具flyway，运行项目后会自动生成表，但是需要先创建blog数据库`**

数据库表关系
>  一对多: type-blog, user-blog, blog-comment <br/>
>  多对多： blog-tag

**user表**
````sql
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255),
  `create_time` datetime,
  `email` varchar(255),
  `nickname` varchar(255),
  `password` varchar(255),
  ` type` int(11),
  `update_time` datetime,
  `username` varchar(255),
  PRIMARY KEY (`id`)
) ENGINE = InnoDB CHARACTER SET = utf8;
````
**type表**
````sql
CREATE TABLE `type`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255)  NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB  CHARACTER SET = utf8 ;
````
**tag表**
````sql
CREATE TABLE `tag`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255)  NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB  CHARACTER SET = utf8 ;
````
**blog表**
````sql
CREATE TABLE `blog`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `appreciation` bit(1) NOT NULL DEFAULT false,
  `commentabled` bit(1) NOT NULL DEFAULT false,
  `content` longtext NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `description` varchar(255)  DEFAULT NULL,
  `first_picture` varchar(255)  DEFAULT NULL,
  `flag` varchar(255)  DEFAULT NULL,
  `published` bit(1) NOT NULL DEFAULT false,
  `recommend` bit(1) NOT NULL DEFAULT false,
  `share_statement` bit(1) NOT NULL DEFAULT false,
  `title` varchar(255)  DEFAULT NULL,
  `update_time` datetime  DEFAULT NULL,
  `views` int(11)  DEFAULT NULL,
  `type_id` bigint(20)  DEFAULT NULL,
  `user_id` bigint(20)  DEFAULT NULL,
  `comment_count` int(255)  DEFAULT NULL,
  PRIMARY KEY (`id`) ,
  CONSTRAINT `FK292449gwg5yf7ocdlmswv9w4j` FOREIGN KEY (`type_id`) REFERENCES `type` (`id`) ,
  CONSTRAINT `FK8ky5rrsxh01nkhctmo7d48p82` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE = InnoDB  CHARACTER SET = utf8 ;
````
**comment表**
````sql
CREATE TABLE `comment`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(255)  DEFAULT NULL,
  `email` varchar(255)  DEFAULT NULL,
  `content` varchar(255)  DEFAULT NULL,
  `avatar` varchar(255)  DEFAULT NULL,
  `create_time` datetime  DEFAULT NULL,
  `blog_id` bigint(20)  DEFAULT NULL,
  `parent_comment_id` bigint(20)  DEFAULT NULL,
  `admin_comment` bit(1) NOT NULL DEFAULT false,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB  CHARACTER SET = utf8 ;
````

**blog_tag表**
````sql
CREATE TABLE `blog_tag`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `blog_id` bigint(20) NOT NULL ,
  `tag_id` bigint(20) NOT NULL ,
  PRIMARY KEY (`id`),
)ENGINE = InnoDB  CHARACTER SET = utf8 ;
````

<br>

## 页面展示

`给大家看看一位哥们自己修改的前端页面，原本博客的前端页面还是挺普通的，这里给大家提供这位哥们的美化版,感谢他的无私奉献！`

[前端地址](https://github.com/oneStarLR/myblog-page)

![博客展示](http://seven.cn-bj.ufileos.com/%E4%BB%96%E4%BA%BA%E5%8D%9A%E5%AE%A2%E9%A1%B5%E9%9D%A2%E5%B1%95%E7%A4%BA.png?UCloudPublicKey=CEnvo7uzX5eCtplVo47O2X4VievOYUd30fyy5QXO3&Signature=yhuCqZ0489%2FHCtLvbL9JvdmO0eQ%3D&Expires=1604932711)

<br/>

## 结语
后续可能还会对该博客进行其他功能的开发,类似后台图片上传，个人资料修改等敬请期待！

