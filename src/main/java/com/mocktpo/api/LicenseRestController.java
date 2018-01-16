package com.mocktpo.api;

import com.mocktpo.modules.order.OrderService;
import com.mocktpo.orm.domain.Order;
import com.mocktpo.util.LicenseHelper;
import com.mocktpo.util.LicenseUtils;
import com.mocktpo.web.vo.ActivationVo;
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

@RestController
public class LicenseRestController {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/api/v1/licenses/activate", method = RequestMethod.POST)
    public ResponseEntity<String> activate(@RequestBody ActivationVo activationVo) {
        logger.info("activation code: {}, hardware: {}", activationVo.getActivationCode(), activationVo.getHardware());
        Order order = orderService.findByActivationCode(activationVo.getActivationCode());
        if (order == null) {
            return ResponseEntity.notFound().build(); // activation code doesn't exist
        } else {
            String activatedHardware = order.getHardware();
            if (StringUtils.isEmpty(activatedHardware)) {
                order.setHardware(activationVo.getHardware());
                orderService.update(order);
                String licenseCode = LicenseHelper.prepareEncodedText(LicenseUtils.encode(LicenseHelper.preparePlainText(order)));
                return ResponseEntity.ok().body(licenseCode); // register a new hardware
            } else {
                if (activatedHardware.equals(activationVo.getHardware())) {
                    String licenseCode = LicenseHelper.prepareEncodedText(LicenseUtils.encode(LicenseHelper.preparePlainText(order)));
                    return ResponseEntity.accepted().body(licenseCode); // same hardware has been registered
                } else {
                    return ResponseEntity.status(HttpStatus.CONFLICT).build(); // different hardwares have been registered
                }
            }
        }
    }
}
