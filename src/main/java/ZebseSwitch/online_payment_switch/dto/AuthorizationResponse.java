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

package ZebseSwitch.online_payment_switch.dto;

public class AuthorizationResponse {

    private String responseCode;
    private String responseMessage;

    public AuthorizationResponse(String responseCode, String responseMessage) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }
}