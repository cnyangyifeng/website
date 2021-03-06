package com.mocktpo.util;

import com.verhas.licensor.License;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ResourceBundle;

public class LicenseUtils {

    private static final Logger logger = LogManager.getLogger();
    private static final ResourceBundle msgs = ResourceBundle.getBundle("license");

    private LicenseUtils() {
    }

    public static String encode(String plainText) {
        String path = LicenseUtils.class.getResource(msgs.getString("keyrings_dir")).getPath();
        try {
            License lic = new License();
            lic.setLicense(plainText);
            return lic.loadKey(path + msgs.getString("gpg_secring_file"), msgs.getString("gpg_key")).encodeLicense(msgs.getString("gpg_password"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void encode(String plainFileName, String encodedFileName) {
        String path = LicenseUtils.class.getResource(msgs.getString("keyrings_dir")).getPath();
        try {
            File f = new File(path + encodedFileName);
            logger.debug("A new encoded license file has been created: {}.", f.createNewFile());
            OutputStream os = new FileOutputStream(f);
            os.write((new License().setLicense(new File(path + plainFileName)).loadKey(path + msgs.getString("gpg_secring_file"), msgs.getString("gpg_key")).encodeLicense(msgs.getString("gpg_password"))).getBytes("utf-8"));
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void decode(String encodedFileName, String plainFileName) {
        String path = LicenseUtils.class.getResource(msgs.getString("keyrings_dir")).getPath();
        try {
            final License lic;
            if ((lic = new License()).loadKeyRing(path + msgs.getString("gpg_pubring_file"), null).setLicenseEncodedFromFile(path + encodedFileName).isVerified()) {
                OutputStream os = System.out;
                if (plainFileName != null) {
                    File f = new File(path + plainFileName);
                    logger.debug("A new plain license file has been created:" + f.createNewFile());
                    os = new FileOutputStream(f);
                }
                Writer w = new OutputStreamWriter(os, "utf-8");
                w.write("-----BEGIN PGP MESSAGE-----\n");
                w.flush();
                lic.dumpLicense(os);
                os.flush();
                w.write("-----END PGP MESSAGE-----\n\n");
                w.write("Encoding license key id=" + lic.getDecodeKeyId() + "L\n\n");
                w.write("-----BEGIN KEYRING DIGEST-----\n");
                w.write(lic.dumpPublicKeyRingDigest());
                w.write("-----END KEYRING DIGEST-----\n");
                w.close();
                os.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
