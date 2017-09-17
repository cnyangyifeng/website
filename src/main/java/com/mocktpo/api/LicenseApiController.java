package com.mocktpo.api;

import com.mocktpo.domain.License;
import com.mocktpo.service.LicenseService;
import com.mocktpo.vo.ActivationVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class LicenseApiController {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private LicenseService service;

    @RequestMapping(value = "/api/license/activate", method = RequestMethod.POST)
    public ResponseEntity<Void> activate(@RequestBody ActivationVo vo) {
        logger.debug("{}.{}() accessed.", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
        String activationCode = vo.getActivationCode();
        String hardware = vo.getHardware();
        List<License> lz = service.findByActivationCode(activationCode);
        if (lz != null && lz.size() > 0) {
            License lic = lz.get(0);
            String licensedHardware = lic.getHardware();
            if (StringUtils.isEmpty(licensedHardware)) { // hardware remains blank
                lic.setHardware(hardware);
                service.update(lic);
                return ResponseEntity.ok().build();
            } else {
                if (licensedHardware.equals(hardware)) { // same hardware registered
                    return ResponseEntity.ok().build();
                } else {
                    return ResponseEntity.status(HttpStatus.CONFLICT).build(); // different hardware registered
                }
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // activation code never exists
        }
    }
}
