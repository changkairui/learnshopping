## 电商项目-需求分析
### 核心-购买
#### 一、用户模块
#### 登录
##### 注册
##### 忘记密码
##### 获取用户信息
##### 修改密码
##### 退出

#### 二、商品模块
##### 后台
##### 添加商品
##### 修改商品
##### 删除商品
##### 商品上下架
##### 查看商品
##### 前台(门户)
##### 搜索商品
##### 查看商品详情

#### 三、类别模块
##### 添加类型
##### 修改类别
##### 删除类别
##### 查看类别
##### 查看子类
##### 查看后代类别

#### 四、购物车模块
##### 添加到购物车
##### 修改购物车中某个商品的数量
##### 删除购物车商品
##### 全选/取消全选
##### 单选/取消单选
##### 查看购物车中商品的数量

#### 五、地址模块
##### 添加地址
##### 修改地址
##### 删除地址
##### 查看地址

#### 六、订单模块
##### 前台：
##### 下订单
##### 订单详情
##### 取消订单
##### 订单详情
##### 后台：
##### 订单列表
##### 订单详情
##### 发货

#### 七、支付模块
##### 支付宝支付
##### 支付
##### 支付回调
##### 查看支付状态

#### 八、线上部署
##### 阿里云部署

--------------2018/12/4---------------------
#### 远程分支的添加跟合并
##### 创建分支：
       git checkout -b dev
##### 跟远程仓库合并：
       git push origin head -u 
##### 将项目需求提交到dev上
       git push origin dev
##### 切换到dev
       git checkout dev
##### 将dev拉回到本地的仓库
       git pull origin dev
##### 切换到master分支
       git checkout master
##### 将dev分支的文件合并到master
       git merge dev
##### 提交master
       git push origin master
       
------------------------------------------   
       
## 数据库设计
### 数据库设计
 ```
    create database learnshopping;
    use learnshopping;
 ```
### 用户表
 ```
    create table neuedu_user(
    `id`        int(10)     not null auto_increment comment '用户id',
    `username`  varchar(50) not null comment '用户名',
    `password`  varchar(50) not null comment '密码',
    `email`     varchar(50) not null comment '邮箱',
    `phone`     varchar(11) not null comment '联系方式',
    `question`  varchar(100) not null comment '密保问题',
    `answer`    varchar(100) not null comment '答案',
    `role`      int(4)      not null  comment '用户角色 0:普通用户 1:管理员', 
    `create_time`   datetime    comment '创建时间',
    `update_time`  datetime   comment '修改时间',
    PRIMARY KRY(`id`),
    UNIQUE KEY `user_name_index`(`username`) USING BTREE
    )ENGINE=InnoDB DEFAULT CHARSET=UTF8;
 ```
### 类别表
 ```
    create table neuedu_category(
    `id`       int(11)     not null auto_increment  comment '类别id',
    `parent_id` int(11)    not null default 0 comment '父类id',
    `name`      varchar(50) not null comment '类别名称',
    `status`    int(4)      default 1 comment '类别状态 1:正常 0:废弃',
    `create_time`   datetime    comment '创建时间',
    `update_time`  datetime   comment '修改时间',
    PRIMARY KEY(`id`)
    )ENGINE=InnoDB DEFAULT CHARSET=UTF8;
 ```
 ```
                       id     parent_id
     电子产品    1       1         0
     家电        2       2         1 
     手机        2       3         1
     电脑        2       4         1
     相机        2       5         1
     华为手机    3       6         3
     小米手机    3       7         3
     p系列       4       8         6
     mate系列    4       9         6
     
     查询电子产品的商品----> 递归
 ```
### 商品表
 ```
    create table neuedu_product(
    `id`            int(40)     not null    auto_increment  comment '商品id',
    `category_id`   int(11)     not null    comment '商品所属的类别id，值引用类别表的id',
    `name`          varchar(100) not null   comment '商品名称',
    `detail`        text    comment '商品详情',
    `subtitle`      varchar(200)    comment '商品副标题',
    `main_image`    varchar(100)    comment '商品主图',
    `sub_images`    varchar(200)    comment '商品子图',
    `price`         decimal(20,2)   not null    comment '商品价格，总共20位，小数2位，正数18位',
    `stock`         int(11)     comment '商品库存',
    `status`        int(6)  default 1   comment '商品状态: 1:在售 2:下架 3:删除',
    `create_time`   datetime comment '创建时间', 
    `update_time`   datetime comment '修改时间',
    PRIMARY KEY(`id`)
    )ENGINE=InnoDB DEFAULT CHARSET=UTF8;
 ```
### 购物车表
 ```
    create table neuedu_cart(
    `id`            int(11)     not null    auto_increment     comment '购物车id',
    `user_id`       int(11)     not null    comment '用户id',
    `product_id`    int(11)     not null    comment '商品id',
    `quantity`      int(11)     not null    comment '购买数量',
    `checked`       int(4)      default 1 comment '1:选中 0:未选中',
    `create_time`   datetime comment '创建时间', 
    `update_time`   datetime comment '修改时间',
    PRIMARY KEY(`id`),
    key `user_id_index`(`user_id`) USING BTREE 
    )ENGINE=InnoDB DEFAULT CHARSET=UTF8;
 ```
### 订单表
 ```
    create table neuedu_order(
    `id`           int(11)         not null    auto_increment      comment '订单id，主键',
    `order_no`     bigint(20)      not null    comment '订单编号',
    `user_id`      int(11)         not null    comment '用户id',
    `payment`      decimal(20,2)   not null    comment '付款总金额，单位元，保留两位小数',
    `payment_type` int(4)          not null    default 1 comment '支付方式 1:线上支付',
    `status`       int(10)         not null    comment '订单状态 0-已取消  10-未付款 20-已付款 30-已发货 40-已完成  50-已关闭',
    `shopping_id`  int(11)         not null    comment '收货地址id',
    `postage`      int(10)         not null    comment '运费',
    `payment_time` datetime        not null    comment '已付款时间',
    `send_time`    datetime        not null    comment '已发货时间',
    `close_time`   datetime        not null    comment '已关闭时间',
    `end_time`     datetime        not null    comment '已结束时间',
    `create_time`   datetime comment '创建时间', 
    `update_time`   datetime comment '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `order_no_index`(`order_no`) USING BTREE
    )ENGINE=InnoDB DEFAULT CHARSET=UTF8;
 ```
 
### 订单明细表
 ```
    create table neuedu_order_item(
    `id`            int(11)     not null    auto_increment      comment '订单明细id，主键',
    `order_no`      bigint(20)  not null    comment '订单编号',
    `user_id`       int(11)     not null    comment '用户id',
    `product_id`    int(11)     not null    comment '商品id',
    `product_name`  varchar(100) not null   comment '商品名称',
    `product_image` varchar(100) not null   comment '商品主图',
    `current_unit_price` decimal(20,2) not null comment '下单时商品的价格，元为单位，保留两位小数',
    `quantity`     int(10)  not null comment '商品的购买数量',
    `total_price`  decimal(20,2) not null comment '商品的总价格，元为单位，保留两位小数',
    `create_time`    datetime  default null  comment '已创建时间',
    `update_time`    datetime  default null  comment '更新时间',
    PRIMARY KEY(`id`),
    KEY `order_no_index`(`order_no`) USING BTREE,
    KEY `order_no_user_id_index`(`order_no`,`user_id`) USING BTREE
    )ENGINE=InnoDB DEFAULT CHARSET=UTF8;
 ```
 
### 支付表
 ```
    create table neuedu_payinfo(
    `id`            int(11)     not null        auto_increment      comment '主键',
    `order_no`      bigint(20)  not null        comment '订单编号',
    `user_id`       int(11)     not null        comment '用户id',
    `pay_platform`  int(4)      not null   default 1     comment '1:支付宝 2:微信',
    `platform_status`  varchar(50) comment '支付状态', 
    `platform_number`  varchar(100) comment '流水号',
    `create_time`    datetime  default null  comment '已创建时间',
    `update_time`    datetime  default null  comment '更新时间',
    PRIMARY KEY(`id`)
    )ENGINE=InnoDB DEFAULT CHARSET=UTF8;
 ```
 
### 地址表
 ```
   create table neuedu_shipping(
   `id`       int(11)      not null  auto_increment,
   `user_id`       int(11)      not  null  ,
   `receiver_name`       varchar(20)      default   null  COMMENT '收货姓名' ,
   `receiver_phone`       varchar(20)      default   null  COMMENT '收货固定电话' ,
   `receiver_mobile`       varchar(20)      default   null  COMMENT '收货移动电话' ,
   `receiver_province`       varchar(20)      default   null  COMMENT '省份' ,
   `receiver_city`       varchar(20)      default   null  COMMENT '城市' ,
   `receiver_district`       varchar(20)      default   null  COMMENT '区/县' ,
   `receiver_address`       varchar(200)      default   null  COMMENT '详细地址' ,
   `receiver_zip`       varchar(6)      default   null  COMMENT '邮编' ,
   `create_time`       datetime      not null   comment '创建时间',
   `update_time`       datetime      not null   comment '最后一次更新时间',
   PRIMARY KEY(`id`)
   )ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
 ```
### 项目架构--四层架构
 ```
    视图层(倒序开发)向下调用
    控制层controller
    业务逻辑层service
        接口和实现类
    Dao层(mapper映射文件)
 ```
### SSM框架搭建
 ```
    一、引入插件：
    1.配置mysql驱动包
    2.配置实体类
    3.配置sql文件(mapper)
    4.配置dao接口
    5.配置数据表
    会自动生成mapper文件，dao mapper映射器和实体类
    
    二、配置db.properties
    
    三、配置spring、mybatis、springmvc
    spirng：
    1.开启注解，将覆盖到的包交给IOC容器管理
    2.引入db.properties
    3.配置连接池
    4.sqlSessionFactory，引入连接池、引入mybatis全局配置、
    引入mapper sql文件的位置、配置MapperScannerConfigurer会动态代理生成符合dao接口的代理实现类
    
    springmvc：
    1.开启注解，管理controller
    2.配置json格式数据返回
    3.配置视图解析器
 ```    
### 开发用户接口
#### 登录
 ```
    1.先会封装高复用的对象，例如：状态码，返回接口的数据，以及提示信息
    创建private无参的构造方法，和有参构造方法(重载)
    2.对外提供static访问的方法，返回status状态，将status设置为常量
    
    登录：
    1.UserController根据需求文档的要求，添加路径、创建登录方法、向下调用IUserService进行业务的处理
    2.IUserService接口创建登录方法，并将username，password传入，IUserServiceImpl实现login方法进行业务上的处理，
        step1:参数非空校验
        step2:检查username是否存在
            调用check_valid方法，check_valid是判断用户不存在才成功
            如果isSuccess成功用户不存在
        step3:根据用户名和密码查询
            dao中定义的返回值为userInfo，进行密码的加密，如果userInfo==null证明密码错误
        step4:处理结果并返回
            会给我们返回一个用户userInfo给前台
    IUserService向下调用dao接口进行数据的查询，
    3.dao接口定义查找username的方法，和根据用户名密码查询方法传入username，password参数(参数多个时，参数的类型为map)
    4.在mapper文件中进行对数据库的查询，然后将数据查到的数据向上返回
 ```
 #### 注册
 ```
    1.controller：
    访问路径：/portal/user/register.do
    request请求参数：String username, String password,String email,String phone,String question,String answer
    框架可以帮我们返回一个UserInfo对象
    2.service：
    创建register方法实现登录功能，将UserInfo对象传入
    Impl里实现register接口，进行判断：
        step1:输入参数的非空校验
        step2:判断用户名是否存在
            checkUsername(userinfo.getUsername())
            >0 表示用户名邮箱存在，check_valid方法验证用户名不存在
            取反表示用户名已存在
        step3:判断邮箱是否存在
            checkEmail(userinfo.getEmail())
        step4:注册
            setRole(普通用户)
            setPassword(密码)
            >0 表示注册成功
        step4:返回结果
    3.dao:
    在dao接口中定义查询和邮箱username和email的方法，mapper映射文件会对数据库进行查询
 ```
#### 检查用户名是否有效
 ```
     1.controller：
     访问路径：/portal/user/check_valid.do
     request请求参数：String str,String type(用户名) type是usernam或email 对应的 str是用户名或邮箱
     将str和type返回
     2.service：
     创建check_valid检查用户名是否有效方法，将参数str type传入
     Impl里实现register接口，进行判断：
        step1:参数的非空校验
        step2:用户名是否有效
            如果type equals username代表用户名存在
            否则注册成功
        step3:邮箱是否有效
            如果type equals email代表邮箱存在
            否则注册成功
        step4:返回结果
      3.dao:
      将str传入到查询出的用户名和邮箱，进行判断
  ```
#### 获取登录用户信息和详细信息
 ```
     1.controller：
     访问路径：/portal/user/get_user_info.do
     request请求参数：无参数
        逻辑：只有当用户登录后才能拿到用户的信息，就需要我们将登录成功后将登录返回的userInfo date
     保存到session中，通过session绑定，getAttribute到USERNAME，判断如果不为空是userInfo 类型，我们
     就是set到用户的信息，详细信息就直接返回这个对象userInfo
 ```
  
##### 根据用户名获取密保问题
 ```
    1.controller：
    访问路径：/portal/user/forget_get_question.do?username=admin
    request请求参数：username
    2.service：
    创建forget_get_question根据用户名获取密保问题方法
    Impl里实现forget_get_question接口，进行判断：
        step1:参数的非空校验
        step2:用户名是否存在
            调用check_valid判断，如果我传入的username的status不等于3(用户名已存在)
            表示用户名不存在
        step3:查询密保问题
            查询到密保问题后进行参数的非空检验
        step4:返回结果
            将question返回
    3.dao:
        根据用户名查询密保问题，创建selectQuestionByUsername方法将username传入
 ```
#### 提交问题答案
 ```
    1.controller:
    访问路径：/portal/user/forget_check_answer.do
    request请求参数：String username,String question,String answer
    2.service：
    创建forget_check_answer提交问题答案方法
    Impl里实现forget_check_answer接口，进行判断：
        step1:参数的非空校验
        step2:校验答案
            将dao查询结果返回，判断count <=0 表示答案错误
            用UUID随机生成一个字符串
            通过username token 返回用户的唯一标识
            以username为key来获取token
        step3:返回结果
    3.dao:
    创建checkAnswerByUsernameAndQuestion提交问题答案方法将username,password,question传入，因为参数是多个
    需要使用@Param注解，以key-value形式查询
 ```
#### 忘记密码的重置密码
 ```
    1.controller:
    访问路径：/portal/user/forget_reset_password.do
    request请求参数：String username,String passwordNew,String forgetToken
    2.service：
    创建forget_reset_password忘记密码的重置密码方法，
    Impl里实现forget_reset_password接口，进行判断：
        step1:参数的非空校验
        
        step2:校验token
          修改密码的时候要考虑到一个越权问题
          横向越权：权限都是一样的，a用户去修改其他用户  
          纵向越权：低级别用户修改高级别用户的权限
          解决：提交答案成功的时候，服务端会给客户端返回一个值（数据），这个数据在客户端服务端都分别保存，
          当用户去重置密码的时候，用户端必须带上这个数据，只有拿到数据服务端校验合法了才能修改，
          所以服务端要给客户端传一个token,服务端客户端都分别保存，然后两个进行校验
          
          如果当前登录的是张三，修改密码的时候会让回答问题，会返回给一个张三的token，如果
          张三想去改李四的密码，但没有李四token
          
          如果服务端和传入用户名的token不一样，表示不能修改别人的密码
        step3:更新密码
          将dao查询结果返回，判断count <=0 表示密码修改失败
        step3:返回结果
     3.dao:
    创建updatePasswordByUsername更新密码法将username,password传入，因为参数是多个
    需要使用@Param注解，以key-value形式查询   
 ```
 
### 类别模块
#### 学习目标
      如何设计及封装无限层级的树状数据结构
      递归算法的设计思想
      如何处理复杂对象重排
      重写hashcode和equals的注意事项

#### 查询品类子节点
 ```
    首先先判断用户是否登录，判断用户是否有权限
    1.controller:
    访问路径：/manage/category/get_category.do
    request请求参数：Integer categoryId
    2.service：
    创建get_category查询品类子节点方法，
    Impl里实现get_category接口，进行判断：
        step1:参数的非空校验
        
        step2:根据categoryId查询类别
            selectByPrimaryKey查询类别categoryId
            判断是否为空
            
        step3:查询子类别
            findChlidCategory查询子类别categoryId，返回值，
            为List<Category>集合，将dao查到的categoryId返回，
            
            
        step4：返回结果
    3.dao:
    创建findChlidCategory查询品类子节点方法categoryId传入，parent_id=#{categoryId}，
    将categoryId返回
 ```
 
#### 增加节点
 ```
     1.controller:
        访问路径：/manage/category/add_category.do
        request请求参数：parentId(default=0) categoryName
        2.service：
        创建add_category增加节点方法，
        Impl里实现add_category接口，进行判断：
            step1:参数的非空校验
            
            step2:添加节点
               因为insert传入的参数是一个Category对象，
               所以在这里需要new一个Category对象来接受，
               设置新的名字、父类id、状态值，判断是否>0，
               >0则表示添加成功
               
           step3:返回结果
     
        3.dao:
        创insert增加节点方法Category实体类传入，返回值为int，
        >0则表示添加成功
 ```

#### 修改品类名字
 ```
     1.controller:
        访问路径：/manage/category/set_category_name.do
        request请求参数：categoryId categoryName
        2.service：
        创建set_category_name修改品类名字方法，
        Impl里实现set_category_name接口，进行判断：
           step1:参数的非空校验
            
           step2:根据categoryId查询
               先查询categoryId，判断categoryId是否为空，
               不为空进行修改
               
           step3:修改
                因为updateByPrimaryKey传入的参数是一个Category对象，
                修改它的名字，判断是否>0，>0则表示添加成功
           step4:返回结果
     
        3.dao:
        创updateByPrimaryKey修改品类名字方法Category实体类传入，返回值为int，
        >0则表示添加成功
 ```
 
#### 获取当前分类id及递归子节点categoryId
 ```
     1.controller:
        访问路径：/manage/category/get_deep_category.do
        request请求参数：categoryId
        2.service：
        创建get_deep_category获取当前类下的子类(递归)方法，
        Impl里实现get_deep_category接口，进行判断：
           step1:参数的非空校验
            
           step2:查询
               调用findAllChildCategory方法传入categorySet集合，
               创建categorySet集合，
               因为返回时Integer类型需要转换一下，通过迭代器遍历
               set集合，每个遍历完的集合得到的都是泛型，在将integerSet
               添加返回
               
           创建一个方法，查询出本节点，如果查询的结果不为空，
           当category添加到categorySet集合中，查询categoryId下
           的子节点，判断categoryList不为空并且长度>0，遍历这个
           集合，对每个节点调用findAllChildCategory方法
     
        3.dao:
        创updateByPrimaryKey修改品类名字方法Category实体类传入，返回值为int，
        >0则表示添加成功
 ```