package ZebseSwitch.online_payment_switch.iso8583;

/*
 * ============================================================
 * Never copy and paste code without understanding it.
 *
 * This class represents ONE ISO8583 field definition.
 *
 * It does NOT store transaction values.
 *
 * It stores metadata.
 * ============================================================
 */

import org.springframework.stereotype.Component;


public class FieldDefinition {

    private int fieldNumber;

    private String fieldName;

    private FieldType fieldType;

    private int maxLength;

    public FieldDefinition(
            int fieldNumber,
            String fieldName,
            FieldType fieldType,
            int maxLength) {

        this.fieldNumber = fieldNumber;
        this.fieldName = fieldName;
        this.fieldType = fieldType;
        this.maxLength = maxLength;

    }

    public int getFieldNumber() {
        return fieldNumber;
    }

    public String getFieldName() {
        return fieldName;
    }

    public FieldType getFieldType() {
        return fieldType;
    }

    public int getMaxLength() {
        return maxLength;
    }

}