import com.sample.BeanConfiguration;
import com.sample.Car;
import com.sample.Phone;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
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

        ApplicationContext context4 = new AnnotationConfigApplicationContext(BeanConfiguration.class);
        // 如果传了配置类，配置类可以省略@Configuration， 会扫描@Bean创建对象
        // 如果像context3只传父包，就需要@Configuration进行声明
        Phone phone = context4.getBean("phone", Phone.class);
        System.out.println(phone);
    }
}
