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

import java.math.BigDecimal;

public class AuthorizationRequest {

    private String pan;
    private String processingCode;
    private BigDecimal amount;
    private String terminalId;
    private String merchantId;
    private String pinBlock;
    private String currency;
    private String stan;
    private String rrn;

    public AuthorizationRequest() {
    }

    public AuthorizationRequest(String pan,
                                String processingCode,
                                BigDecimal amount,
                                String terminalId,
                                String merchantId,
                                String pinBlock,
                                String currency,
                                String stan,
                                String rrn) {
        this.pan = pan;
        this.processingCode = processingCode;
        this.amount = amount;
        this.terminalId = terminalId;
        this.merchantId = merchantId;
        this.pinBlock = pinBlock;
        this.currency = currency;
        this.stan = stan;
        this.rrn = rrn;
    }

    // getters only (we keep DTO clean)
    public String getPan() {
        return pan;
    }

    public String getProcessingCode() {
        return processingCode;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public String getPinBlock() {
        return pinBlock;
    }

    public String getCurrency() {
        return currency;
    }

    public String getStan() {
        return stan;
    }

    public String getRrn() {
        return rrn;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public void setProcessingCode(String processingCode) {
        this.processingCode = processingCode;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setStan(String stan) {
        this.stan = stan;
    }

    public void setRrn(String rrn) {
        this.rrn = rrn;
    }
}