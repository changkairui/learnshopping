### Git是一个免费、开源的分布式系统，可以帮助我们高效的开发

### Git基础的配置如下：
### 配置用户名：
#### git config --global user.name "" 
### 配值邮箱：
#### git config --global user.email"" 
### 避免中文乱码：
#### git config --global gui.encoding utf-8 
### 避免显示状态中文乱码：
#### git config --global core.quotepath off 
### 避免其他乱码：
#### git config --global core.ignorecase false 

#### 生成密钥
#### ssh-keygen -t rsa -C ""
### 然后去C盘用户里面.ssh里面指定id_rsa.pub把它复制到github网页settings里面

### git运行原理，在工作区里的文件要先添加到暂存区里，然后commit添加到本地仓库，本地仓库和远程仓库的连接通过git remote add origin "远程仓库地址"

### git常用的命令
### 创建本地仓库：
#### git init 
### 添加到暂存区：
#### git add ""
### 提交到本地仓库里：
#### git commit -m ""
### 本地仓库和远程仓库关联：
#### git remote add origin git 
### 第一向远程仓库推送：
#### git push -u origin master 
### 第二次向远程仓库推送：
#### git push origin master 
### 查看给git文件目前的状态：
#### git status 
### 查看提交的commit：
#### git log 
### 版本的回退：
#### git reset --hard committed 
### 查看分支：
#### git branch 
### 创建dev分支并切换：
#### git checkout -b dev 
### 切换分支到master：
#### git checkout master 
### 拉取：
#### git pull 
### 把dev分支的文件合并到master：
#### git merge dev 
### 将分支推送到远程：
#### git push origin HEAD -u 
### 提交所有：
#### git add . 

###git的忽略规则：
###忽略所以以.a结尾的文件
####*.a 
###但lib.c除外
####!lib.c 
###忽略根目录下的src文件，不包括src
####/src 
###忽略src下所有文件
####src/ 
