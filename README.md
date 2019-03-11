# SwaggerAPIManagement-HUAWEI

*API management system based on Swagger2* 
`USTC工程实践项目`
<br/>
<br/>

1. 导入项目到本地
[参考教程](https://blog.csdn.net/hry2015/article/details/77984399)

<br/>

2. 在本地的项目目录下建立git管理
预备工作，依次输入：（**以下操作均要在项目文件夹下完成，其中${} 部分为需要你修改代入的地方**）
~~~
$ git config --global user.name "${你的名称}"    #设置你的信息
$ git config --global user.email ${你的邮箱}
$ git init                                      #对这个项目初始化git管理
$ git checkout -b ${你的分支名}                  #在本地创建属于你的分支
$ git remote add origin https://github.com/MatthewHuangs/SwaggerAPIManagement-HUAWEI.git    #设置远程仓库，名叫origin
$ git fetch origin ${GitHub上你的分支名}         #将GitHub上你的分支fetch到本地
$ git branch --set-upstream-to=origin/${GitHub上你的分支名} ${你的分支名}    #将GitHub你的分支与你本地的分支匹配，确保一定要成功才能做代码提交操作，不然会篡改GitHub上的master分支
~~~

<br/>

3. 代码上传
依次输入：
~~~
$ git add .                       #将你的代码提交都缓存区
$ git commit -m "${描述信息}"      #提交你的代码到本地仓库
$ git push origin ${你的分支名}    #第一次上传代码时，在最后加上-f，强行上传避免merge你的分支这一麻烦操作
~~~
<br/>
<br/>

[comment]: 注释部分
 
[项目的github地址](https://github.com/MatthewHuangs/SwaggerAPIManagement-HUAWEI)
