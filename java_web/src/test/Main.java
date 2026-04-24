import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:mysql://localhost:3306/jdbc_test";
        String user = "root";
        String pwd = "lzj1997LZJ";
        Connection connection = DriverManager.getConnection(url, user, pwd);

        // 创建 SQL
        /*String sql = "insert into person(name,money) values(?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString( 1, "小明");
        statement.setInt(2, 500);
        int i = statement.executeUpdate();
        System.out.println(i);*/

        // 修改
        /*String sql = "update person set name = ?,money = ? where id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt( 3,  1);
        statement.setInt( 2,  100);
        statement.setString( 1,  "测试");
        int i = statement.executeUpdate();
        System.out.println(i);*/

        // 删除
        /*String sql = "delete from person where id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, 1);
        int i = statement.executeUpdate();
        System.out.println(i);*/

        // 查询
        String sql = "select * from person"; // where id = ?
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            int money = resultSet.getInt(3);
            System.out.println("id=" + id + ", name=" + name + ", money=" + money);
        }
    }
}
