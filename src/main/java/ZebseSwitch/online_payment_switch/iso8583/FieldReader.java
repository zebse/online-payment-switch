package ZebseSwitch.online_payment_switch.iso8583;

import ZebseSwitch.online_payment_switch.iso8583.exception.IsoParsingException;
import ZebseSwitch.online_payment_switch.iso8583.fieldreader.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

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

    public FieldReader(
            List<FieldTypeHandler> handlerList) {

        for (FieldTypeHandler handler : handlerList) {

            handlers.put(
                    handler.supports(),
                    handler);

        }

    }

    public ReadResult read(
            String message,
            int position,
            FieldDefinition definition) {

        FieldTypeHandler handler =
                handlers.get(definition.getFieldType());

        if (handler == null) {

            throw new IllegalArgumentException(
                    "Unsupported Field Type: "
                            + definition.getFieldType());

        }

        return handler.read(
                message,
                position,
                definition);
        }

}