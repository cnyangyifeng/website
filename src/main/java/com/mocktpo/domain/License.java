package com.mocktpo.domain;

import com.mocktpo.util.constants.GlobalConstants;

import java.io.Serializable;
import java.util.Date;

public class License implements Serializable {

    private static final long serialVersionUID = 1L;

    private String appName = GlobalConstants.APP_NAME;
    private String edition = GlobalConstants.DEFAULT_EDITION;
    private String version = GlobalConstants.DEFAULT_VERSION;
    private String activationCode;
    private String email;
    private String hardware;
    private String validThrough = GlobalConstants.DEFAULT_VALID_THROUGH;
    private Date dateCreated;
    private Date dateUpdated;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
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

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }
}
