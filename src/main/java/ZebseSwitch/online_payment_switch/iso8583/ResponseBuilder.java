package ZebseSwitch.online_payment_switch.iso8583;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class ResponseBuilder {

    private final Random random = new Random();

    public IsoMessage buildAuthorizationResponse(
            IsoMessage request,
            String responseCode) {

        IsoMessage response = new IsoMessage();

        // MTI 0200 -> 0210
        response.setMti("0210");

        // Echo fields
        copyIfPresent(request, response, 3);
        copyIfPresent(request, response, 4);
        copyIfPresent(request, response, 11);
        copyIfPresent(request, response, 37);

        // Generated fields
        response.setField(38, generateAuthorizationCode());
        response.setField(39, responseCode);

        return response;
    }

    private void copyIfPresent(IsoMessage request,
                               IsoMessage response,
                               int field) {

        if (request.hasField(field)) {
            response.setField(field, request.getField(field));
        }
    }

    private String generateAuthorizationCode() {

        return String.format("%06d",
                random.nextInt(1_000_000));

    }

}