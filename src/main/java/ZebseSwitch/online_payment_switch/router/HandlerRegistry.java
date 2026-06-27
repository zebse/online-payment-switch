package ZebseSwitch.online_payment_switch.router;

/*
 * Never copy and paste code without understanding it.
 * Always ask: what does this line do and why does it exist?
 */

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class HandlerRegistry {

    private final Map<String, IsoMessageHandler> handlers =
            new HashMap<>();

    public HandlerRegistry(
            AuthorizationHandler authorizationHandler) {

        handlers.put("0200:000000", authorizationHandler);

    }

    public IsoMessageHandler getHandler(
            String mti,
            String processingCode) {

        String routeKey =
                mti + ":" + processingCode;

        return handlers.get(routeKey);
    }

}