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

package ZebseSwitch.online_payment_switch.controller;

import ZebseSwitch.online_payment_switch.dto.AuthorizationRequest;
import ZebseSwitch.online_payment_switch.dto.AuthorizationResponse;
import ZebseSwitch.online_payment_switch.service.AuthorizationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class TransactionController {

    private final AuthorizationService authorizationService;

    public TransactionController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @PostMapping("/authorize")
    public AuthorizationResponse authorize(@RequestBody AuthorizationRequest request) {

        return authorizationService.authorize(request);
    }
}