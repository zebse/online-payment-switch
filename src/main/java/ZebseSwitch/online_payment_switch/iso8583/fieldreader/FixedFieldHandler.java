package ZebseSwitch.online_payment_switch.iso8583.fieldreader;

import ZebseSwitch.online_payment_switch.iso8583.FieldDefinition;
import ZebseSwitch.online_payment_switch.iso8583.ReadResult;
import org.springframework.stereotype.Component;

/*
 * ============================================================
 * Never copy and paste code without understanding it.
 *
 * This class knows ONLY how to read FIXED fields.
 * ============================================================
 */

@Component
public class FixedFieldHandler implements FieldTypeHandler {

    @Override
    public ReadResult read(
            String message,
            int position,
            FieldDefinition definition) {

        int length = definition.getMaxLength();

        String value =
                message.substring(
                        position,
                        position + length);

        return new ReadResult(value, length);

    }

}