package com.mocktpo.modules.email;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private static final Logger logger = LogManager.getLogger();

    public void sendActivationEmail() {
        logger.info("An activation email has been sent to you.");
    }
}
