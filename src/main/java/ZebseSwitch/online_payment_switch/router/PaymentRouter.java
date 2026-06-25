package ZebseSwitch.online_payment_switch.router;

/*
 * Never copy and paste code without understanding it.
 * Always ask: what does this line do and why does it exist?
 */

import ZebseSwitch.online_payment_switch.iso8583.IsoMessage;
import org.springframework.stereotype.Component;

@Component
public class PaymentRouter {

    private final HandlerRegistry handlerRegistry;

    public PaymentRouter(HandlerRegistry handlerRegistry) {
        this.handlerRegistry = handlerRegistry;
    }

    public IsoMessage route(IsoMessage message) {

        String mti = message.getMti();
        String processingCode = message.getField(3);

        IsoMessageHandler handler =
                handlerRegistry.getHandler(
                        mti,
                        processingCode);

        if (handler == null) {

            IsoMessage errorResponse = new IsoMessage();

            errorResponse.setMti("0210");
            errorResponse.setField(39, "30");

            return errorResponse;
        }

        return handler.handle(message);
    }
}