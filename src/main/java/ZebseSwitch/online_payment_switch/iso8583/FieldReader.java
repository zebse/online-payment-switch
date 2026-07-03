package ZebseSwitch.online_payment_switch.iso8583;

import ZebseSwitch.online_payment_switch.iso8583.fieldreader.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/*
 * ============================================================
 * Never copy and paste code without understanding it.
 *
 * This class delegates reading to the appropriate handler.
 * It does NOT know HOW each field type is read.
 * ============================================================
 */

@Component
public class FieldReader {

    private final Map<FieldType, FieldTypeHandler> handlers =
            new HashMap<>();

    public FieldReader() {

        handlers.put(
                FieldType.FIXED,
                new FixedFieldHandler());

        handlers.put(
                FieldType.LLVAR,
                new LlvarFieldHandler());

    }

    public ReadResult read(
            String message,
            int position,
            FieldDefinition definition) {

        FieldTypeHandler handler =
                handlers.get(definition.getFieldType());

        if (handler == null) {

            throw new IllegalArgumentException(
                    "Unsupported Field Type");

        }

        return handler.read(
                message,
                position,
                definition);

    }

}