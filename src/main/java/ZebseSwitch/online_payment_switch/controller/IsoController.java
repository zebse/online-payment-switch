package ZebseSwitch.online_payment_switch.controller;

/*
 * Never copy and paste code without understanding it.
 * Always ask: what does this line do and why does it exist?
 */

import ZebseSwitch.online_payment_switch.iso8583.*;
import ZebseSwitch.online_payment_switch.router.PaymentRouter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/iso")
public class IsoController {

    private final PaymentRouter paymentRouter;
    private final IsoParser isoParser;
    private final IsoPacker isoPacker;

    public IsoController(PaymentRouter paymentRouter,
                         IsoParser isoParser,
                         IsoPacker isoPacker) {

        this.paymentRouter = paymentRouter;
        this.isoParser = isoParser;
        this.isoPacker = isoPacker;
    }

    private static final Logger logger =
            LoggerFactory.getLogger(
                    IsoController.class);

    @PostMapping
    public String processIsoMessage(
            @RequestBody String rawMessage) {

        logger.info("ISO8583 request received.");

        IsoMessage request =
                isoParser.parse(rawMessage);

        IsoMessage response =
                paymentRouter.route(request);

        String packedResponse =
                isoPacker.pack(response);

        logger.info("ISO8583 response sent. MTI={}", response.getMti());

        return packedResponse;
    }
}