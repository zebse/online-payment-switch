package ZebseSwitch.online_payment_switch.iso8583.fieldwriter;

import ZebseSwitch.online_payment_switch.iso8583.FieldDefinition;
import ZebseSwitch.online_payment_switch.iso8583.FieldType;

public interface FieldWriter {

    FieldType supports();

    String write(
            String value,
            FieldDefinition definition);

}