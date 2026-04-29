import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class Address {
    @Value("1")
    private Integer id;
    @Value("Beijing")
    private String value;
}
