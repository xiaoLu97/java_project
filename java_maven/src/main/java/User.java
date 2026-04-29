import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class User {
    @Value("1")
    private Integer id;
    @Value("张三")
    private String name;
    @Autowired
    private Address address;
}
