## 电商项目-需求分析
### 核心-购买
#### 一、用户模块
##### 登录
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
    `role`      int(4)      not null  comment '用户角色',
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
 ```
    1.先会封装高复用的对象，例如：状态码，返回接口的数据，以及提示信息
    创建private无参的构造方法，和有参构造方法(重载)
    2.对外提供static访问的方法，返回status状态，将status设置为常量
    
    登录：
    1.UserController根据需求文档的要求，添加路径、创建登录方法、向下调用IUserService进行
    业务的处理
    2.IUserService接口创建登录方法，并将username，password传入
    IUserServiceImpl实现login方法进行业务上的处理，
        step1:参数非空校验
        step2:检查username是否存在
        step3:根据用户名和密码查询
        step4:处理结果并返回
    IUserService向下调用dao接口进行数据的查询，
    3.dao接口定义查找username的方法，和根据用户名密码查询方法传入username，password参数(参数多个时，参数的类型为map)
    4.在mapper文件中进行对数据库的查询，然后将数据查到的数据向上返回
 ```
    