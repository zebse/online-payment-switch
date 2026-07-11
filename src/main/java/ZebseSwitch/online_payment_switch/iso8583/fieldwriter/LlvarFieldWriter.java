package ZebseSwitch.online_payment_switch.iso8583.fieldwriter;

import ZebseSwitch.online_payment_switch.iso8583.FieldDefinition;
import ZebseSwitch.online_payment_switch.iso8583.FieldType;
import org.springframework.stereotype.Component;

@Component
public class LlvarFieldWriter
        implements FieldWriter {

    @Override
    public FieldType supports() {

        return FieldType.LLVAR;

    }

    @Override
    public String write(
            String value,
            FieldDefinition definition) {

        return String.format(
                "%02d%s",
                value.length(),
                value);

    }

}