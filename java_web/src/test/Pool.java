import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Pool {
    public static void main(String[] args) throws Exception {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/jdbc_test");
        dataSource.setUser("root");
        dataSource.setPassword("yonghwa"); // "lzj1997LZJ";
        dataSource.setInitialPoolSize(5);
        dataSource.setMaxPoolSize(15);
        dataSource.setMinPoolSize(2); // 剩余2个空闲则补充
        dataSource.setAcquireIncrement(5);

//        Connection connection = dataSource.getConnection();
        int threadCount = 20;
        System.out.println("启动 " + threadCount + " 个线程模拟并发查询...\n");

        Thread[] threads = new Thread[threadCount];

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i + 1;
            final ComboPooledDataSource ds = dataSource;
            threads[i] = new Thread(() -> {
                try {
                    queryPerson(ds, threadNum);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, "Thread-" + threadNum);
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("\n所有线程执行完毕！");
    }
    private static void queryPerson(ComboPooledDataSource dataSource, int threadNum) throws Exception {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            System.out.println("[线程-" + threadNum + "] 获取连接");

            String sql = "select * from person";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            System.out.println("[线程-" + threadNum + "] 查询结果:" + resultSet);
            /*while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                int money = resultSet.getInt(3);
                System.out.println("  id=" + id + ", name=" + name + ", money=" + money);
            }

            resultSet.close();*/
            statement.close();
        } finally {
            if (connection != null) {
                connection.close();
                System.out.println("[线程-" + threadNum + "] 释放连接");
            }
        }
    }
}
