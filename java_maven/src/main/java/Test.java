import com.aop.CalImpl;
import com.sample.BeanConfiguration;
import com.sample.Car;
import com.sample.Phone;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        /* IoC */

        /*ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        User user = context.getBean("user", User.class);
        System.out.println(user);*/

        /*AnnotationConfigApplicationContext context2 = new AnnotationConfigApplicationContext();
        // 视频中类都放在com的包下，然后传参'com'，就不需要下面的注册和刷新
        context2.register(User.class);
        context2.register(Address.class);
        context2.refresh();
        User user1 = (User) context2.getBean("user");
        System.out.println(user1);*/

        /*ApplicationContext context3 = new AnnotationConfigApplicationContext("com.sample");
        Car car = context3.getBean("car", Car.class);
        System.out.println(car);*/

        /*ApplicationContext context4 = new AnnotationConfigApplicationContext(BeanConfiguration.class);
        // 如果传了配置类，配置类可以省略@Configuration， 会扫描@Bean创建对象
        // 如果像context3只传父包，就需要@Configuration进行声明
        Phone phone = context4.getBean("phone", Phone.class);
        System.out.println(phone);*/

        /* AOP */

        /*
        AOP 基于 IoC，将业务对象和切面对象分别注入到 IoC 中，同时业务对象只写业务代码，切面对象只写非业务代码，
        从而在代码层面实现业务代码和非业务代码的解耦合，通过 IoC 再将业务代码和非业务代码整合到一起即可，开发时解耦，运行时合并。
        如何整合？通过代理对象，将业务对象注入到 IoC 中，将切面对象也注入到 IoC 中，由 IoC 负责根据业务对象和切面对象创建出一个动态代理对象，
        分别去完成业务代码和非业务代码的执行。
        1、业务对象和切面对象必须注入到 IoC 中
        2、切面对象需要添加切面注解以及动态代理注解
        */

        ApplicationContext aopContext = new AnnotationConfigApplicationContext("com.aop");
        CalImpl cal = aopContext.getBean("calImpl", CalImpl.class);
        System.out.println(cal.add(1, 2));
        cal.subtract(5, 2);
        cal.multiply(1, 3);
        cal.divide(9, 3);
    }
}
