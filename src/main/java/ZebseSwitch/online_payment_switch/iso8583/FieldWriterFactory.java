package ZebseSwitch.online_payment_switch.iso8583;

import ZebseSwitch.online_payment_switch.iso8583.fieldwriter.FieldWriter;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Component
public class FieldWriterFactory {

    private final Map<FieldType, FieldWriter> writers =
            new EnumMap<>(FieldType.class);

    public FieldWriterFactory(
            List<FieldWriter> writerList) {

        for(FieldWriter writer : writerList){

            writers.put(
                    writer.supports(),
                    writer);

        }

    }

    public String write(
            String value,
            FieldDefinition definition){

        FieldWriter writer =
                writers.get(
                        definition.getFieldType());

        if(writer == null){

            throw new IllegalArgumentException(
                    "Unsupported field type "
                            + definition.getFieldType());

        }

        return writer.write(
                value,
                definition);

    }

}