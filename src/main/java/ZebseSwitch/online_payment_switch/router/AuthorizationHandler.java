package ZebseSwitch.online_payment_switch.router;

import ZebseSwitch.online_payment_switch.dto.AuthorizationRequest;
import ZebseSwitch.online_payment_switch.dto.AuthorizationResponse;
import ZebseSwitch.online_payment_switch.iso8583.IsoMapper;
import ZebseSwitch.online_payment_switch.iso8583.IsoMessage;
import ZebseSwitch.online_payment_switch.iso8583.IsoParser;
import ZebseSwitch.online_payment_switch.iso8583.ResponseBuilder;
import ZebseSwitch.online_payment_switch.service.AuthorizationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/*
 * Never copy and paste code without understanding it.
 * Always ask: what does this line do and why does it exist?
 */

@Component
public class AuthorizationHandler implements IsoMessageHandler {

    private final AuthorizationService authorizationService;
    private final ResponseBuilder responseBuilder;
    public AuthorizationHandler(
            AuthorizationService authorizationService, ResponseBuilder responseBuilder) {

        this.authorizationService = authorizationService;
        this.responseBuilder = responseBuilder;
    }
    private static final Logger logger =
            LoggerFactory.getLogger(
                    AuthorizationHandler.class);

    @Override
    public IsoMessage handle(IsoMessage message) {
        AuthorizationRequest request =
                IsoMapper.toAuthorizationRequest(message);

        AuthorizationResponse authResponse =
                authorizationService.authorize(request);

        IsoMessage response =
                responseBuilder.buildAuthorizationResponse(
                        message,
                        authResponse.getResponseCode());

        logger.info(
                "[ISO0210 built | STAN={} | RC={} | AuthCode={}",
                //authResponse.getTransactionId(),
                response.getField(11),
                response.getField(39),
                response.getField(38)
        );

        return response;
    }

}