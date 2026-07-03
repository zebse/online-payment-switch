package ZebseSwitch.online_payment_switch.iso8583.fieldreader;

import ZebseSwitch.online_payment_switch.iso8583.FieldDefinition;
import ZebseSwitch.online_payment_switch.iso8583.ReadResult;
import org.springframework.stereotype.Component;

/*
 * ============================================================
 * Never copy and paste code without understanding it.
 *
 * This class knows ONLY how to read LLVAR fields.
 * ============================================================
 */

@Component
public class LlvarFieldHandler implements FieldTypeHandler {

    @Override
    public ReadResult read(
            String message,
            int position,
            FieldDefinition definition) {

        int length =
                Integer.parseInt(
                        message.substring(
                                position,
                                position + 2));

        String value =
                message.substring(
                        position + 2,
                        position + 2 + length);

        return new ReadResult(
                value,
                length + 2);

    }

}