package storage;

import javafx.util.Pair;
import parser.AttributeTypeValidator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StorageService {

    private Map<String, List<Pair<String, String>>> store = new HashMap<>();

    private AttributeTypeValidator attributeTypeValidator;

    public StorageService(AttributeTypeValidator attributeTypeValidator) {
        this.attributeTypeValidator = attributeTypeValidator;
    }

    public List<Pair<String, String>> get(String key) {
        return store.get(key);
    }

    public void put(String key, List<Pair<String, String>> listOfAttributePairs) {
        listOfAttributePairs.forEach(pair -> {
            if (attributeTypeValidator.isAttributeValueValid(pair.getKey(), pair.getValue())) {
                store.put(key, listOfAttributePairs);
            } else {
                throw new RuntimeException();
            }
        });
    }

    public void delete(String key) {
        store.remove(key);
    }

    public List<String> keys() {
        return store.entrySet().stream().map(Map.Entry::getKey).collect(Collectors.toList());
    }

    public List<String> search(String attributeKey, String attributeValue) {
        return store.entrySet()
                .stream()
                .filter(entry -> attributeKey.equals(entry.getKey()) && attributeValue.equals(entry.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

}
