## Mybatis

- 建议 1 ：对于绝大多数查询，我们是返回统一字段，所以可以使用 <sql /> 标签，定义 SQL 段。对于性能或者查询字段比较大的查询，按需要的字段查询。
- 建议 2 ：对于数据库的关键字，使用大写。例如说，SELECT、WHERE 等等。
- 建议 3 ：基本是每“块”数据库关键字占用一行，尽量要排版干净
