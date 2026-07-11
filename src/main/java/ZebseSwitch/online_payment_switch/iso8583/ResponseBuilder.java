package ZebseSwitch.online_payment_switch.iso8583;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class ResponseBuilder {

    private final Random random = new Random();

    /**
     * Fields that should normally be echoed back
     * to the requester.
     */
    private static final List<Integer> ECHO_FIELDS = List.of(
            2,   // PAN
            3,   // Processing Code
            4,   // Amount
            7,   // Transmission Date Time
            11,  // STAN
            12,  // Local Time
            13,  // Local Date
            14,  // Expiry Date
            22,  // POS Entry Mode
            25,  // POS Condition Code
            32,  // Acquiring Institution ID
            35,  // Track 2
            37,  // RRN
            41,  // Terminal ID
            42,  // Merchant ID
            48   //Private data

    );

    public IsoMessage buildResponse(
            IsoMessage request,
            String responseMti,
            String responseCode) {

        IsoMessage response = new IsoMessage();

        response.setMti(responseMti);

        // Copy all echo fields
        for (Integer field : ECHO_FIELDS) {

            if (request.hasField(field)) {
                response.setField(field, request.getField(field));
            }
        }

        // DE39 always exists
        response.setField(39, responseCode);

        // DE38 only for approved authorization response
        if ("0210".equals(responseMti)
                && "00".equals(responseCode)) {

            response.setField(38, generateAuthorizationCode());

        }

        return response;
    }

    private String generateAuthorizationCode() {

        return String.format("%06d",
                random.nextInt(1_000_000));
    }

}