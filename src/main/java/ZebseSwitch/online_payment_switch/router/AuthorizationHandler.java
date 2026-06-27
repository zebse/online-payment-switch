package ZebseSwitch.online_payment_switch.router;

import ZebseSwitch.online_payment_switch.dto.AuthorizationRequest;
import ZebseSwitch.online_payment_switch.dto.AuthorizationResponse;
import ZebseSwitch.online_payment_switch.iso8583.IsoMapper;
import ZebseSwitch.online_payment_switch.iso8583.IsoMessage;
import ZebseSwitch.online_payment_switch.service.AuthorizationService;

import org.springframework.stereotype.Component;

/*
 * Never copy and paste code without understanding it.
 * Always ask: what does this line do and why does it exist?
 */

@Component
public class AuthorizationHandler implements IsoMessageHandler {

    private final AuthorizationService authorizationService;

    public AuthorizationHandler(
            AuthorizationService authorizationService) {

        this.authorizationService = authorizationService;
    }

    @Override
    public IsoMessage handle(IsoMessage message) {
        AuthorizationRequest request =
                IsoMapper.toAuthorizationRequest(message);

        AuthorizationResponse response =
                authorizationService.authorize(request);

        return IsoMapper.toAuthorizationResponse(response);
    }
}