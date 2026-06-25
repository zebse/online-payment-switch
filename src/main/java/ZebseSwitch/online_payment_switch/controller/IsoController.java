package ZebseSwitch.online_payment_switch.controller;

/*
 * Never copy and paste code without understanding it.
 * Always ask: what does this line do and why does it exist?
 */

import ZebseSwitch.online_payment_switch.iso8583.IsoMessage;
import ZebseSwitch.online_payment_switch.iso8583.IsoParser;
import ZebseSwitch.online_payment_switch.iso8583.IsoPacker;
import ZebseSwitch.online_payment_switch.router.PaymentRouter;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/iso")
public class IsoController {

    private final PaymentRouter paymentRouter;

    public IsoController(PaymentRouter paymentRouter) {
        this.paymentRouter = paymentRouter;
    }

    @PostMapping
    public String processIsoMessage(
            @RequestBody String rawMessage) {

        IsoParser parser = new IsoParser();

        IsoMessage request =
                parser.parse(rawMessage);

        IsoMessage response =
                paymentRouter.route(request);

        IsoPacker packer = new IsoPacker();

        return packer.pack(response);
    }
}