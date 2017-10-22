package com.mocktpo.util;

import com.mocktpo.orm.domain.License;
import org.apache.commons.lang3.StringUtils;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class EmailHelper {

    private static final ResourceBundle msgs = ResourceBundle.getBundle("mail");
    private static final Pattern r = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private EmailHelper() {
    }

    public static boolean validate(String email) {
        return r.matcher(email).find();
    }

    public static void sendActivationCode(License lic) {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(msgs.getString("smtp_host"));
        sender.setUsername(msgs.getString("smtp_sender_username"));
        sender.setPassword(msgs.getString("smtp_sender_password"));
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setFrom(msgs.getString("smtp_sender_username"));
            helper.setBcc(msgs.getString("smtp_sender_username"));
            helper.setTo(lic.getEmail());
            helper.setSubject("MockTPO Activation Code");
            helper.setText(getActivationCode(lic));
        } catch (Exception e) {
            e.printStackTrace();
        }
        sender.send(message);
    }

    private static String getActivationCode(License lic) {
        String plain = prepareActivationCode(lic);
        String encoded = LicenseHelper.encode(plain);
        encoded = StringUtils.replace(encoded, "-----BEGIN PGP MESSAGE-----\nVersion: BCPG v1.52", "-----BEGIN MOCKTPO ACTIVATION CODE-----");
        encoded = StringUtils.replace(encoded, "-----END PGP MESSAGE-----", "\n-----END MOCKTPO ACTIVATION CODE-----");
        return encoded;
    }

    private static String prepareActivationCode(License lic) {
        StringBuilder sb = new StringBuilder();
        sb.append("order_number=");
        sb.append(lic.getOrderNumber());
        sb.append("\npid=");
        sb.append(lic.getPid());
        sb.append("\nemail=");
        sb.append(lic.getEmail());
        sb.append("\nactivation_code=");
        sb.append(lic.getActivationCode());
        sb.append("\nhardware=");
        sb.append(lic.getHardware());
        sb.append("\nvalid_through=");
        sb.append(lic.getValidThrough());
        return sb.toString();
    }
}
