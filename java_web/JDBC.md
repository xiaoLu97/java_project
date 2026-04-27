# JDBC API
DriverManager 类：管理各种不同的 JDBC 驱动。驱动是java程序和数据库的桥梁

Connection 接口：连接 MySQL 数据库

Statement 接口：执行 SQL 语句

ResultSet 接口：接收结果集

- https://dev.mysql.com/downloads/
- https://downloads.mysql.com/archives/c-j/
- https://cloud.tencent.com/developer/article/2163414

导入后就看到jar的源码

# C3P0
C3P0 需要两个 jar 包才能正常工作：

- https://www.mchange.com/projects/c3p0/ 官方文档Installation
- https://repo1.maven.org/maven2/com/mchange/mchange-commons-java/

# DBUtils

resultSet解析为对应的javaBean
```
T t = null;
t = type.newInstance();
// ResultSet 的字段
Field[] declaredFields = type.getDeclaredFields();
if (resultSet.next()) { // 处理单条结果
    // 遍历实体类的所有属性
    for (Field declaredField : declaredFields) {
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        // 遍历数据库结果集的所有列
        for (int i = 1; i <= columnCount; i++) {
            // 属性名和列名匹配
            if (declaredField.getName().equals(metaData.getColumnName(i))) {
                // 匹配后进行赋值：rs 的字段赋值给 JavaBean 的属性
                Object value = null;
                // 根据数据库字段类型获取对应的值
                switch (metaData.getColumnTypeName(i)) {
                    case "INT":
                        value = resultSet.getInt(i);
                        break;
                    case "VARCHAR":
                        value = resultSet.getString(i);
                        break;
                    // 可继续扩展其他类型：DATE、DOUBLE 等
                }

                // 拼接 set 方法名：set + 属性名首字母大写
                String methodName = "set" + declaredField.getName().substring(0, 1).toUpperCase()
                        + declaredField.getName().substring(1);

                try {
                    // 获取 set 方法
                    Method declaredMethod = type.getDeclaredMethod(methodName, declaredField.getType());
                    // 反射调用 set 方法赋值
                    declaredMethod.invoke(t, value);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
```