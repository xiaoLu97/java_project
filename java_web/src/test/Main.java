import java.sql.*;

public class Main {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:mysql://localhost:3306/jdbc_test";
        String user = "root";
        String pwd =  "yonghwa"; // "lzj1997LZJ";
        Connection connection = DriverManager.getConnection(url, user, pwd);

        // 创建表
       /*String createTableSql = "CREATE TABLE IF NOT EXISTS person (" +
                       "id INT PRIMARY KEY AUTO_INCREMENT, " +
                       "name VARCHAR(255), " +
                       "money INT)";
       Statement statement = connection.createStatement();
       statement.executeUpdate(createTableSql);*/

        // 创建 SQL
        /*String sql = "insert into person(name,money) values(?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString( 1, "小红");
        statement.setInt(2, 1500);
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
        /*String sql = "select * from person"; // where id = ?
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            int money = resultSet.getInt(3);
            System.out.println("id=" + id + ", name=" + name + ", money=" + money);
        }*/

        // 事务
        /*connection.setAutoCommit(false);
        try {
            String sql = "update person set money = ? where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, 1000);
            statement.setInt(2, 1);
            statement.executeUpdate();
            System.out.println(10 / 0);
            PreparedStatement statement1 = connection.prepareStatement(sql);
            statement1.setInt(1, 1000);
            statement1.setInt(2, 2);
            statement1.executeUpdate();
        } catch (Exception e) {
            // 事务回滚
            connection.rollback();
        }
        // 手动提交
        connection.commit();*/

    }
}
