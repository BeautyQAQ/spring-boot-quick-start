## MongoDB

配置MongoDB时, 需要赋予用户权限, 执行以下命令:

```shell
db.createUser({ 'user' : 'root', 'pwd' : 'password', 'roles':[ {role:'readWrite',db:'test'}  ]})
```
