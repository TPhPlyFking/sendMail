package org.example.util;

import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.*;

public class MailUtil {
    public static void sendMail(String to, String from, String subject, String body, boolean bodyIsHTML)
            throws MessagingException {

        // 1. Cấu hình Port 587 và bảo mật STARTTLS theo yêu cầu Koyeb
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // Bắt buộc phải có dòng này cho port 587

        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);

        // 2. Xây dựng tin nhắn và ép font tiếng Việt (UTF-8)
        MimeMessage message = new MimeMessage(session);

        // Ép UTF-8 cho Tiêu đề
        message.setSubject(subject, "UTF-8");

        if (bodyIsHTML) {
            message.setContent(body, "text/html; charset=UTF-8");
        } else {
            // Ép UTF-8 cho Nội dung chữ thường
            message.setText(body, "UTF-8");
        }

        Address fromAddress = new InternetAddress(from);
        Address toAddress = new InternetAddress(to);
        message.setFrom(fromAddress);
        message.setRecipient(Message.RecipientType.TO, toAddress);

        // 3. Gửi email
        Transport transport = session.getTransport();
        transport.connect("phatttran956@gmail.com", "iwiguxksekuueakx");
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
}