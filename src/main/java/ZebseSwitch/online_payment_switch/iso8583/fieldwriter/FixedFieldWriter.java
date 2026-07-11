package ZebseSwitch.online_payment_switch.iso8583.fieldwriter;

import ZebseSwitch.online_payment_switch.iso8583.FieldDefinition;
import ZebseSwitch.online_payment_switch.iso8583.FieldType;
import org.springframework.stereotype.Component;

@Component
public class FixedFieldWriter
        implements FieldWriter {

    @Override
    public FieldType supports() {

        return FieldType.FIXED;

    }

    @Override
    public String write(
            String value,
            FieldDefinition definition) {

        return value;

    }

}