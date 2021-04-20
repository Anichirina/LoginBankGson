import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import lombok.Value;

@Value
@ToString
@Data
@AllArgsConstructor
public class Registration {
    private String login;
    private String password;
    private String status;
}
