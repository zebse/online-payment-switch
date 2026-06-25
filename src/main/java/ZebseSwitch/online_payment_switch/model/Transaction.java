package ZebseSwitch.online_payment_switch.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    private String transactionId;

    private String pan;

    private String processingCode;

    private BigDecimal amount;

    private String stan;

    private String rrn;

    private String responseCode;

    private LocalDateTime transactionTime;

    private String transactionStatus;

    public Transaction() {
    }

    // Getters and Setters

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getProcessingCode() {
        return processingCode;
    }

    public void setProcessingCode(String processingCode) {
        this.processingCode = processingCode;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getStan() {
        return stan;
    }

    public void setStan(String stan) {
        this.stan = stan;
    }

    public String getRrn() {
        return rrn;
    }

    public void setRrn(String rrn) {
        this.rrn = rrn;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(LocalDateTime transactionTime) {
        this.transactionTime = transactionTime;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }
}