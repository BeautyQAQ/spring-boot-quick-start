### 代码片段记录

1. 获取request请求对象的三种方式
2. Collectors.toMap时value为空导致报错的解决方案
3. gradle打包情况下, 如果lombok失效, 需要添加 `annotationProcessor("org.projectlombok:lombok:x.xx.xx")`


### 对象类型

1. DO（Data Object）：与数据库表结构一一对应，通过DAO层向上传输数据源对象。
2. DTO（Data Transfer Object）：数据传输对象，Service或Manager向外传输的对象。
3. BO（Business Object）：业务对象。由Service层输出的封装业务逻辑的对象。
4. VO（View Object）：视图对象。Controller向前端传输的对象。
