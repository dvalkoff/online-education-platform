package ru.mirea.valkov.education.platform.email;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class EmailSenderImpl implements EmailSender {

    private final JavaMailSender mailSender;

    @Override
    public void sendEmail(String to, String email) throws IllegalStateException {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);

            messageHelper.setText(email, true);
            messageHelper.setTo(to);
            messageHelper.setSubject("Activate your account");
            messageHelper.setFrom("ER_radd@mail.ru"); // TODO: fix hardcoded values

            mailSender.send(mimeMessage);

        } catch (MessagingException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }
}
