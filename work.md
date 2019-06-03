 # Git笔记
 ## git 配置
 #### 1.配置用户名(提交时会引用)
 ###### git config --global user.name "用户名"
 ##### 2.配置邮箱
 ###### git config --global user.email "邮箱"
 ##### 3.编码配置
 ###### 避免git gui中的中文乱码
 ##### git config --global gui.encoding utf-8
 ###### 避免git status显示的中文文件名乱码
 ##### git config --global core.quotepath off
 ##### 4.其他
 ##### git config --global core.ignorecase false
 ## git ssh key pair 配置
 ##### 1.在git bash命令行窗口中输入：
 ###### ssh-keygen -t rsa -C "邮箱"
 ##### 2.然后一路回车，不要输入任何密码之类，生成
 ###### ssh key pair 
 ##### 3,在用户目录下生成.ssh文件夹，找到公钥和私钥
 ###### id_rsa id_rsa.pub
 ##### 4.将公钥的内容复制
 ##### 5.进入github网站，将公钥添加进去
 ## git验证
 ##### 6.执行git --version，出现版本信息，安装成功。
 ## git工作原理
 ##### 工作区 git add--->暂存区 git commit--->本地仓库
 ##### 可以将多个文件多次提交到暂存区，在暂存区通过git commit（清空暂存区）一次性提交到本地仓库,然后再提交到远程仓库GitHub
 ##### git init:先在本地初始化一个仓库
 ##### git add README.md：将我的工作区里的文件提交到暂存区
 ##### git commit -m "本次提交的一git个描述" :将暂存区的内容提交到本地仓库
 ##### git remote add origin git@github.com:lll865457981/learnshopping.git:将本地仓库和远程仓库做一个关联
 ##### git push -u origin master：将本地仓库里的内容提交到远程仓库
 
 ## git常用命令
 ##### git init：创建本地仓库；在工作区
 ###### 文件前缀如果是点是隐藏文件
 ##### git add：添加到暂存区
 ##### git commit -m（必须要加这个参数） "本次提交的一个描述" ：提交到本地仓库
 ###### 注意：不能从工作区直接git commit 到本地仓库
 ##### git status:查看工作区的状态
 ##### On branch master：在master分支上进行管理；默认在master主分支上进行管理
 ###### 在创建远程仓库之前先进行ssh的配置
 ##### git push -u origin master:提交到远程仓库
 ##### git log：查看提交committed
 ##### git reset --hard committid：日记的唯一标识，提交版本回退
 ##### git branch：查看分支（前面待着*，表示当前指向的分支）
 ##### git branch 新分支名：创建分支
 ##### git checkout -b dev：创建并切换到dev分支
 ##### 切换分支：git checkout 分支名
 ##### git pull：拉取代码
 ##### git push -u(修改的时候可以省略) origin master:向master分支提交，默认提交master分支
 ##### git merge 分支名：分支合并（切换到master分支后对其他分支进行合并）
 ##### git remote add origin "远程仓库地址"：关联远程仓库
 ##### git push -u origin master:第一次向远程仓库推送
 ##### git push origin master:以后提交到远程仓库
 ###### 出现上传不了的时候，是因为远程仓库的东西没有拉下来，可在提交命令的后面加上“-f”,进行强制提交
 #### 远程分支合并dev
 ##### git checkout dev   切换到要合并的分支
 ##### git pull origin dev  拉取分支的数据
 ##### git checkout master  切换到master分支
 ##### git merge dev        将dev分支合并到master分支
 ##### git push origin master   再进行文件的远程提交
 ## 企业项目开发模式
 ##### 项目采用：
   ##### 分支开发，主干发布
   ##### 将本地分支合并到远程分支：git push origin HEAD -u
 
 ### .gitignore文件
 ##### 作用：告诉git哪些文件不需要添加到版本管理中
 #### 忽略规则
 ###### #此为注释 - 将被git忽略
 ##### *.a ：忽略所有.a结尾的文件
 ##### !lib.a ： 但 lib.a 除外
 ##### /TODO ：仅仅忽略项目根目录下的 TODO 文件，不包括 subdir/TODO
 ##### build/ ： 忽略 build/ 目录下的所有文件
 ##### doc/*.txt ： 会忽略 doc/notes.txt 但不包括 doc/server/arch.txt
 
 ### 删除远程仓库的文件
 ##### 1.git pull origin master
 ##### 2.git rm -r --cached "文件名"
 ##### 3.git comment -m "信息"
 ##### 4.git push -u origin master
