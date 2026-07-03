package ZebseSwitch.online_payment_switch.iso8583;

import org.springframework.stereotype.Component;

import java.beans.FeatureDescriptor;
import java.util.HashMap;
import java.util.Map;

/*
 * ============================================================
 * Never copy and paste code without understanding it.
 *
 * This class represents our supported ISO8583 specification.
 *
 * The parser asks this class how each Data Element should
 * be interpreted.
 * ============================================================
 */

@Component
public class IsoSpecification {


    private final Map<Integer, FieldDefinition> definitions =
            new HashMap<>();


    public IsoSpecification() {

        definitions.put(2,
                new FieldDefinition(
                        2,
                        "Primary Account Number",
                        FieldType.LLVAR,
                        19));

        definitions.put(3,
                new FieldDefinition(
                        3,
                        "Processing Code",
                        FieldType.FIXED,
                        6));

        definitions.put(4,
                new FieldDefinition(
                        4,
                        "Transaction Amount",
                        FieldType.FIXED,
                        12));
        definitions.put(7,
                new FieldDefinition(7,
                        "Transmission date & time",
                        FieldType.FIXED,
                        10));

        definitions.put(11,
                new FieldDefinition(
                        11,
                        "STAN",
                        FieldType.FIXED,
                        6));

        definitions.put(37,
                new FieldDefinition(
                        37,
                        "Retrieval Reference Number",
                        FieldType.FIXED,
                        12));
            }

    public FieldDefinition getDefinition(int field) {

        return definitions.get(field);

    }

}