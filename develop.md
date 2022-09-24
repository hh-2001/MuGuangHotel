# 开发文档



## 1数据库设计与分析



config：文件路径上传配置

lodger：客户住房记录

lodginginfo：客人的个人信息存储

menu：不同人员的权限

menuitem：不同权限调用的操作路径

notify：公告记录

role：角色类别

roleitem：不同角色的权限路径调配

room：客房的入住状态

roomtype：客房类别

storey：客房的楼层

user：用户存储表





管理员：

```TEX
可以操作:
user:增删改

config:增删改

menu，menuitem:增删改

role，roleitem(该表与menu两表需要关联):增删改
```



员工：

```tex
可以操作自身的user:修改password,sex,nickName

lodger(登记客人信息):只能增加(新客人就增加)
lodginginfo:更新(离开时间)，若是新的登记就增加一条记录(同时在lodger查找，若无在lodger增加)

room(有人入住就改变房间状态):只能更新status(viewList一直挂着的页面)需要作联表roomtype并且更新bill表

roomtype:获取每个房间的价格
```



经理:

```tex
可以操作自身的user:修改password,sex,nickName

notify:只能新增

billDay:只能查看
```





```text
工作人员:
	营业数据
	入住管理
	结账管理
	房态管理
	客人业务处理
	
酒店经理:
	营收管理
	日常记录
	房间管理
	公告管理
管理员(locked，在menuitem中设置visible):
	用户管理
	角色权限管理
	系统与菜单管理
```



```tex
adminDao=com.hhz.dao.Impl.AdminDaoImpl
roomDao=com.hhz.dao.Impl.RoomDaoImpl
lodgerDao=com.hhz.dao.Impl.LodgerDaoImpl
```

