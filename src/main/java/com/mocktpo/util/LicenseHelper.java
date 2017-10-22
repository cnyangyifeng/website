package com.mocktpo.util;

import com.verhas.licensor.License;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ResourceBundle;

public class LicenseHelper {

    private static final Logger logger = LogManager.getLogger();
    private static final ResourceBundle msgs = ResourceBundle.getBundle("license");

    private LicenseHelper() {
    }

    public static String encode(String plain) {
        String path = LicenseHelper.class.getResource(msgs.getString("keyrings_dir")).getPath();
        try {
            License lic = new License();
            lic.setLicense(plain);
            return lic.loadKey(path + msgs.getString("gpg_secring_file"), msgs.getString("gpg_key")).encodeLicense(msgs.getString("gpg_password"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void encode(String plainFile, String encodedFile) {
        String path = LicenseHelper.class.getResource(msgs.getString("keyrings_dir")).getPath();
        try {
            File f = new File(path + encodedFile);
            boolean created = f.createNewFile();
            OutputStream os = new FileOutputStream(f);
            os.write((new License().setLicense(new File(path + plainFile)).loadKey(path + msgs.getString("gpg_secring_file"), msgs.getString("gpg_key")).encodeLicense(msgs.getString("gpg_password"))).getBytes("utf-8"));
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void decode(String encodedFile, String plainFile) {
        String path = LicenseHelper.class.getResource(msgs.getString("keyrings_dir")).getPath();
        try {
            final License license;
            if ((license = new License()).loadKeyRing(path + msgs.getString("gpg_pubring_file"), null).setLicenseEncodedFromFile(path + encodedFile).isVerified()) {
                OutputStream os = System.out;
                if (null != plainFile) {
                    File f = new File(path + plainFile);
                    boolean created = f.createNewFile();
                    logger.debug("A new plain license file created:" + created);
                    os = new FileOutputStream(f);
                }
                Writer w = new OutputStreamWriter(os, "utf-8");
                w.write("-----BEGIN PGP MESSAGE-----\n");
                w.flush();
                license.dumpLicense(os);
                os.flush();
                w.write("-----END PGP MESSAGE-----\n\n");
                w.write("Encoding license key id=" + license.getDecodeKeyId() + "L\n\n");
                w.write("-----BEGIN KEYRING DIGEST-----\n");
                w.write(license.dumpPublicKeyRingDigest());
                w.write("-----END KEYRING DIGEST-----\n");
                w.close();
                os.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
