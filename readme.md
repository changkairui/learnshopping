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
    primary key(`id`),
    unique key `user_name_index`(`username`) using btree
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
### 订单明细表
### 支付表
### 地址表