package ZebseSwitch.online_payment_switch.iso8583.fieldreader;

import ZebseSwitch.online_payment_switch.iso8583.FieldDefinition;
import ZebseSwitch.online_payment_switch.iso8583.FieldType;
import ZebseSwitch.online_payment_switch.iso8583.ReadResult;
import ZebseSwitch.online_payment_switch.iso8583.exception.IsoParsingException;
import org.springframework.stereotype.Component;

@Component
public class LllvarFieldHandler
        implements FieldTypeHandler {

    @Override
    public FieldType supports() {

        return FieldType.LLLVAR;

    }

    @Override
    public ReadResult read(
            String message,
            int position,
            FieldDefinition definition) {

        // Ensure the 3-digit length indicator exists
        if (position + 3 > message.length()) {

            throw new IsoParsingException(
                    "Unexpected end of message while reading LLLVAR length");

        }

        String lengthText =
                message.substring(position, position + 3);

        int length;

        try {

            length = Integer.parseInt(lengthText);

        } catch (NumberFormatException ex) {

            throw new IsoParsingException(
                    "Invalid LLLVAR length: " + lengthText);

        }

        // Validate against specification
        if (length > definition.getMaxLength()) {

            throw new IsoParsingException(
                    String.format(
                            "LLLVAR length %d exceeds maximum %d",
                            length,
                            definition.getMaxLength()));

        }

        // Ensure enough data exists
        if (position + 3 + length > message.length()) {

            throw new IsoParsingException(
                    "Unexpected end of message while reading LLLVAR value");

        }

        String value =
                message.substring(
                        position + 3,
                        position + 3 + length);

        return new ReadResult(
                value,
                length + 3);
    }
}