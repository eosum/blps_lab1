package lab1.service;

import lab1.model.emailmessage.EmailMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;

    public void sendEmail(EmailMessage emailMessage) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("humoourist@yandex.ru");
        message.setTo(emailMessage.getRecipientEmail());
        message.setSubject(emailMessage.getTopic());
        message.setText(emailMessage.getMessage());

        mailSender.send(message);
    }
}
