package com.mocktpo.util;

import com.mocktpo.orm.domain.Order;
import org.apache.commons.lang3.StringUtils;

public class LicenseHelper {

    private LicenseHelper() {
    }

    public static String preparePlainText(Order order) {
        return "order_number=" + order.getOrderNumber() + "\npid=" + order.getPid() + "\nemail=" + order.getEmail() + "\npayment_type=" + order.getPaymentType() + "\nprice=" + order.getPrice() + "\nstatus=" + order.getStatus() + "\nactivation_code=" + order.getActivationCode() + "\nhardware=" + order.getHardware();
    }

    public static String prepareEncodedText(String encodedText) {
        encodedText = StringUtils.remove(encodedText, "-----BEGIN PGP MESSAGE-----\nVersion: BCPG v1.52\n\n");
        encodedText = StringUtils.remove(encodedText, "\n-----END PGP MESSAGE-----\n");
        return encodedText;
    }
}
