package parser;

import models.AttributeTypes;

import java.util.HashMap;
import java.util.Map;

public class AttributeTypeValidator {
    private Map<String, AttributeTypes> attributeTypeMap = new HashMap<>();

    public boolean isAttributeValueValid(String attributeKey, String attributeValue) {
        AttributeTypes attributeType = getTypeFromAttributeValue(attributeValue);
        if(attributeTypeMap.containsKey(attributeKey)) {
            AttributeTypes type = attributeTypeMap.get(attributeKey);
            if(!type.equals(attributeType)) {
                return false;
            }
        } else {
            attributeTypeMap.put(attributeKey, attributeType);
        }
        return true;
    }

    private AttributeTypes getTypeFromAttributeValue(String attributeValue) {
        if(attributeValue.matches("^\\d+$")) {
            return AttributeTypes.INTEGER;
        }
        if(attributeValue.matches("[0-9]{1,13}(\\.[0-9]*)?")) {
            return AttributeTypes.DOUBLE;
        }
        if("TRUE".equals(attributeValue) || "FALSE".equals(attributeValue)) {
            return AttributeTypes.BOOLEAN;
        }
        return AttributeTypes.STRING;
    }
}
