package ZebseSwitch.online_payment_switch.service;

import ZebseSwitch.online_payment_switch.dto.AuthorizationRequest;
import ZebseSwitch.online_payment_switch.dto.AuthorizationResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AuthorizationService {

    public AuthorizationResponse authorize(AuthorizationRequest request) {

        /*
         * ============================================================
         *  STOP ✋
         *
         *  Never copy and paste code without understanding it.
         *
         *  Ask yourself:
         *  1. Why does this class exist?
         *  2. What problem does it solve?
         *  3. How does it fit into the payment switch?
         *  4. Could I explain this to another developer?
         *
         *  If the answer is NO, don't continue until you understand it.
         * ============================================================
         */

        // Basic validation simulation
        if (request.getPan() == null || request.getPan().length() < 10) {
            return new AuthorizationResponse("12", "Invalid Card Number");
        }

        if (request.getAmount() == null || request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            return new AuthorizationResponse("13", "Invalid Amount");
        }

        // Mock business rule: decline large transactions
        if (request.getAmount().compareTo(new BigDecimal("10000")) > 0) {
            return new AuthorizationResponse("05", "Do Not Honor");
        }

        // Approved flow
        return new AuthorizationResponse(
                "00",
                "Approved"
        );
    }
}