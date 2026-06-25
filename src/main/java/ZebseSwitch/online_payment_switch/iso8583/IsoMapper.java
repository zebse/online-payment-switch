package ZebseSwitch.online_payment_switch.iso8583;

import ZebseSwitch.online_payment_switch.dto.AuthorizationRequest;
import ZebseSwitch.online_payment_switch.dto.AuthorizationResponse;

public class IsoMapper {

    public static AuthorizationRequest toAuthorizationRequest(IsoMessage iso) {

        AuthorizationRequest request = new AuthorizationRequest();

        request.setPan(iso.getField(2));
        request.setProcessingCode(iso.getField(3));

        request.setAmount(
                new java.math.BigDecimal(iso.getField(4))
        );

        request.setStan(iso.getField(11));

        request.setRrn(iso.getField(37));

        return request;
    }

    public static IsoMessage toAuthorizationResponse(
            AuthorizationResponse response) {

        IsoMessage iso = new IsoMessage();

        iso.setMti("0210");

        iso.setField(39, response.getResponseCode());

        return iso;
    }

}