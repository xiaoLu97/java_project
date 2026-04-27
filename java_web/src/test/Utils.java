import Dto.Person;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class Utils {
    public static void main(String[] args) throws  Exception {
        String url = "jdbc:mysql://localhost:3306/jdbc_test";
        String user = "root";
        String pwd =  "yonghwa"; // "lzj1997LZJ";
        Connection connection = DriverManager.getConnection(url, user, pwd);

        QueryRunner queryRunner = new QueryRunner();
        /*String sql = "select * from person where id = ?";
        Person person = queryRunner.query(connection, sql, new BeanHandler<>(Person.class), 2);
        System.out.println(person);*/
        // jdbc的任务：拿到resultSet
        // BeanHandler的任务：转成Person对象： rsh.handle(rs); 通过泛型决定返回值
        // BeanProcessor： 1. 构建对象this.newInstance(type) 反射 2. 给属性赋值，匹配不上则不处理

        // 查询集合
        String sql1 = "select * from person";
        List<Person> list = queryRunner.query(connection, sql1, new BeanListHandler<>(Person.class));
        for (Person person1 : list) {
            System.out.println(person1);
        }
    }
}
