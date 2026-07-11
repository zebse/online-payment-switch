package ZebseSwitch.online_payment_switch.iso8583;

import ZebseSwitch.online_payment_switch.controller.IsoController;
import ZebseSwitch.online_payment_switch.iso8583.exception.IsoParsingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.TreeMap;


@Component
public class IsoPacker {

    private final IsoSpecification isoSpecification;
    private final FieldWriterFactory fieldWriterFactory;
    private final BitmapUtil bitmapUtil;

    public IsoPacker(IsoSpecification isoSpecification,
                     FieldWriterFactory fieldWriterFactory,
                     BitmapUtil bitmapUtil) {
        this.isoSpecification = isoSpecification;
        this.fieldWriterFactory = fieldWriterFactory;
        this.bitmapUtil = bitmapUtil;
    }
    private static final Logger logger =
            LoggerFactory.getLogger(
                    IsoPacker.class);

    public String pack(IsoMessage message) {

        StringBuilder builder = new StringBuilder();
        builder.append(message.getMti());

        TreeMap<Integer, String> ordered = new TreeMap<>(message.getFields());

        // Bitmap is derived from whichever fields are actually populated
        builder.append(bitmapUtil.build(ordered.keySet()));

        for(Map.Entry<Integer,String> entry :
                message.getFields().entrySet()){

            int fieldNumber = entry.getKey();
            String value = entry.getValue();

            FieldDefinition definition = isoSpecification.getDefinition(fieldNumber);

            if (definition == null) {
                throw new IsoParsingException(
                        String.format("Unsupported Data Element DE%d during packing", fieldNumber));
            }

            try {
                builder.append(fieldWriterFactory.write(value, definition));
            } catch (Exception ex) {
                throw new IsoParsingException(
                        String.format("Failed to pack DE%d", fieldNumber),
                        message.getMti(), fieldNumber, builder.length(), ex);
            }

            logger.debug(
                    "Packing DE{} [{}]",
                    fieldNumber,
                    definition.getFieldName());
        }


        return builder.toString();
    }
}