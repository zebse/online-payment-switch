package ZebseSwitch.online_payment_switch.repository;

import ZebseSwitch.online_payment_switch.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * Never copy and paste code without understanding it.
 * It will always remind you to think before using it.
 */

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {

}