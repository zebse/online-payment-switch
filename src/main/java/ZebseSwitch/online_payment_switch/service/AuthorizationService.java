package ZebseSwitch.online_payment_switch.service;

import ZebseSwitch.online_payment_switch.model.Transaction;
import ZebseSwitch.online_payment_switch.repository.TransactionRepository;
import ZebseSwitch.online_payment_switch.dto.AuthorizationRequest;
import ZebseSwitch.online_payment_switch.dto.AuthorizationResponse;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

/*
 * Never copy and paste code without understanding it.
 * Always ask: what does this line do and why does it exist?
 */

@Service
public class AuthorizationService {

    private final TransactionRepository transactionRepository;

    // Constructor Injection (BEST PRACTICE in Spring)
    public AuthorizationService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public AuthorizationResponse authorize(AuthorizationRequest request) {

        // STEP 1: Create Transaction entity
        Transaction txn = new Transaction();

        // Generate internal transaction ID
        String transactionId = "TXN-" + UUID.randomUUID();

        txn.setTransactionId(transactionId);
        txn.setPan(request.getPan());
        txn.setProcessingCode(request.getProcessingCode());
        txn.setAmount(request.getAmount());
        txn.setStan(request.getStan());
        txn.setRrn(request.getRrn());
        txn.setTransactionTime(LocalDateTime.now());

        // Initial state BEFORE decision
        txn.setTransactionStatus("PENDING");

        // STEP 2: SAVE FIRST (IMPORTANT - Switch behavior)
        transactionRepository.save(txn);

        // STEP 3: Business logic (simple demo rule)
        //AuthorizationResponse response = new AuthorizationResponse();

        if (request.getAmount().doubleValue() <= 1000) {
            txn.setResponseCode("00");
            txn.setTransactionStatus("APPROVED");
        } else {
            txn.setResponseCode("51");
            txn.setTransactionStatus("DECLINED");
        }
        System.out.println(
                "[DB] Saving Transaction");
        transactionRepository.save(txn);

        return new AuthorizationResponse(
                txn.getResponseCode(),
                txn.getTransactionStatus().equals("APPROVED")
                        ? "Approved"
                        : "Insufficient Funds (Simulated)");
    }
}