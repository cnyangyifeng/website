package com.mocktpo.orm.domain;

import com.mocktpo.util.constants.MT;

public class License {

    private long id;
    private String product = MT.DEFAULT_PRODUCT;
    private String edition = MT.DEFAULT_EDITION;
    private String version = MT.DEFAULT_VERSION;
    private String activationCode;
    private String email;
    private String hardware;
    private String validThrough = MT.DEFAULT_VALID_THROUGH;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHardware() {
        return hardware;
    }

    public void setHardware(String hardware) {
        this.hardware = hardware;
    }

    public String getValidThrough() {
        return validThrough;
    }

    public void setValidThrough(String validThrough) {
        this.validThrough = validThrough;
    }
}
