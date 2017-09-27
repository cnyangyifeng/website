package com.mocktpo.util;

import com.mocktpo.orm.domain.License;
import com.mocktpo.util.constants.MT;
import org.apache.commons.lang3.StringUtils;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.util.regex.Pattern;

public class EmailUtils {

    private static final Pattern r = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private EmailUtils() {
    }

    public static boolean validate(String email) {
        return r.matcher(email).find();
    }

    public static void sendActivationCode(License lic) {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(MT.SMTP_HOST);
        sender.setUsername(MT.SMTP_SENDER_EMAIL);
        sender.setPassword(MT.SMTP_SENDER_PASSWORD);
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setFrom(MT.SMTP_SENDER_EMAIL);
            helper.setBcc(MT.SMTP_SENDER_EMAIL);
            helper.setTo(lic.getEmail());
            helper.setSubject(MT.LICENSE_EMAIL_SUBJECT);
            helper.setText(getActivationCode(lic));
        } catch (Exception e) {
            e.printStackTrace();
        }
        sender.send(message);
    }

    private static String getActivationCode(License lic) {
        String plain = prepareActivationCode(lic);
        String encoded = LicenseUtils.encode(plain);
        encoded = StringUtils.replace(encoded, "-----BEGIN PGP MESSAGE-----\nVersion: BCPG v1.52", "-----BEGIN MOCKTPO ACTIVATION CODE-----");
        encoded = StringUtils.replace(encoded, "-----END PGP MESSAGE-----", "\n-----END MOCKTPO ACTIVATION CODE-----");
        return encoded;
    }

    private static String prepareActivationCode(License lic) {
        StringBuilder sb = new StringBuilder();
        sb.append("app_name=");
        sb.append(lic.getProduct());
        sb.append("\nedition=");
        sb.append(lic.getEdition());
        sb.append("\nversion=");
        sb.append(lic.getVersion());
        sb.append("\nactivation_code=");
        sb.append(lic.getActivationCode());
        sb.append("\nemail=");
        sb.append(lic.getEmail());
        sb.append("\nhardware=");
        sb.append(lic.getHardware());
        sb.append("\nvalid_through=");
        sb.append(lic.getValidThrough());
        return sb.toString();
    }
}
