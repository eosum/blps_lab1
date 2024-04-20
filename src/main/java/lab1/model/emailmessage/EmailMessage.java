package lab1.model.emailmessage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.mail.SimpleMailMessage;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailMessage {
    private String recipientEmail;
    private String topic;
    private String message;
}
