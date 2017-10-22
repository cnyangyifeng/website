package com.mocktpo.util;

import com.mocktpo.orm.domain.Order;
import org.apache.commons.lang3.StringUtils;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class EmailUtils {

    private static final ResourceBundle msgs = ResourceBundle.getBundle("mail");
    private static final Pattern r = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private EmailUtils() {
    }

    public static boolean validate(String email) {
        return r.matcher(email).find();
    }

    public static void sendActivationCode(Order order) {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(msgs.getString("smtp_host"));
        sender.setUsername(msgs.getString("smtp_sender_username"));
        sender.setPassword(msgs.getString("smtp_sender_password"));
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setFrom(msgs.getString("smtp_sender_username"));
            helper.setBcc(msgs.getString("smtp_sender_username"));
            helper.setTo(order.getEmail());
            helper.setSubject("MockTPO Activation Code");
            helper.setText(getActivationCode(order));
        } catch (Exception e) {
            e.printStackTrace();
        }
        sender.send(message);
    }

    private static String getActivationCode(Order order) {
        String plainText = prepareActivationCode(order);
        String encodedText = LicenseUtils.encode(plainText);
        encodedText = StringUtils.replace(encodedText, "-----BEGIN PGP MESSAGE-----\nVersion: BCPG v1.52", "-----BEGIN MOCKTPO ACTIVATION CODE-----");
        encodedText = StringUtils.replace(encodedText, "-----END PGP MESSAGE-----", "\n-----END MOCKTPO ACTIVATION CODE-----");
        return encodedText;
    }

    private static String prepareActivationCode(Order order) {
        StringBuilder sb = new StringBuilder();
        sb.append("order_number=");
        sb.append(order.getOrderNumber());
        sb.append("\npid=");
        sb.append(order.getPid());
        sb.append("\nemail=");
        sb.append(order.getEmail());
        sb.append("\npayment_type=");
        sb.append(order.getPaymentType());
        sb.append("\nprice=");
        sb.append(order.getPrice());
        sb.append("\nstatus=");
        sb.append(order.getStatus());
        sb.append("\nactivation_code=");
        sb.append(order.getActivationCode());
        sb.append("\nhardware=");
        sb.append(order.getHardware());
        return sb.toString();
    }
}
