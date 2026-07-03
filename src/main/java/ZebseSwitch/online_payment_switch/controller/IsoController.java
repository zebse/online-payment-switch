package ZebseSwitch.online_payment_switch.controller;

/*
 * Never copy and paste code without understanding it.
 * Always ask: what does this line do and why does it exist?
 */

import ZebseSwitch.online_payment_switch.iso8583.IsoMessage;
import ZebseSwitch.online_payment_switch.iso8583.IsoParser;
import ZebseSwitch.online_payment_switch.iso8583.IsoPacker;
import ZebseSwitch.online_payment_switch.router.PaymentRouter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/iso")
public class IsoController {

    private final PaymentRouter paymentRouter;
    private final IsoParser isoParser;

    public IsoController(PaymentRouter paymentRouter,
                         IsoParser isoParser) {

        this.paymentRouter = paymentRouter;
        this.isoParser = isoParser;
    }
    private static final Logger logger =
            LoggerFactory.getLogger(
                    IsoController.class);

    @PostMapping
    public String processIsoMessage(
            @RequestBody String rawMessage) {

        logger.info(
                "ISO8583 request received.");
        IsoMessage request =
                isoParser.parse(rawMessage);

        IsoMessage response =
                paymentRouter.route(request);

        IsoPacker packer = new IsoPacker();
        logger.info(
                "ISO8583 response sent.");
        return packer.pack(response);

    }
}