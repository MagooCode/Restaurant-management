package az.msrestaurantnotification.email;

import az.msrestaurantnotification.events.OrderConfirmationEvent;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static az.msrestaurantnotification.email.EmailTemplate.ORDER_CONFIRMATION;
import static java.nio.charset.StandardCharsets.UTF_8;


@Component
@Slf4j
public class EmailService {
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Autowired
    public EmailService(JavaMailSender mailSender, SpringTemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    @Async
    public void sentOrderConfirmationEmail(OrderConfirmationEvent orderConfirmationEvent)
            throws MessagingException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper =
                new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, UTF_8.name());
        messageHelper.setFrom("mehreliyevelvin1@gmail.com");

        final String templateName = ORDER_CONFIRMATION.getTemplate();
        Map<String,Object> variables = new HashMap<>();
        variables.put("customerName",orderConfirmationEvent.getCustomerName());
        variables.put("totalAmount",orderConfirmationEvent.getTotalAmount());

        Context context = new Context();
        context.setVariables(variables);
        messageHelper.setSubject(templateName);

        try{
            String htmlTemplate = templateEngine.process(templateName,context);
            messageHelper.setText(htmlTemplate,true);

            messageHelper.setTo(orderConfirmationEvent.getCustomerMail());
            mailSender.send(mimeMessage);

            log.info(String.format("INFO- Email succesfully send: %s ", orderConfirmationEvent.getCustomerMail()));
        }

        catch (MessagingException e){
            log.warn("WARNING- Cannot send email to {}", orderConfirmationEvent.getCustomerMail());
        }


    }
}
