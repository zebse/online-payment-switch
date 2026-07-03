package ZebseSwitch.online_payment_switch.iso8583.fieldreader;

import ZebseSwitch.online_payment_switch.iso8583.FieldDefinition;
import ZebseSwitch.online_payment_switch.iso8583.ReadResult;

/*
 * ============================================================
 * Never copy and paste code without understanding it.
 *
 * Why use an interface?
 *
 * Because every field type should expose
 * the SAME behavior:
 *
 * read(...)
 *
 * ============================================================
 */

public interface FieldTypeHandler {

    ReadResult read(
            String message,
            int position,
            FieldDefinition definition);

}