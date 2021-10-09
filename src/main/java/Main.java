import javafx.util.Pair;
import parser.AttributeTypeValidator;
import storage.StorageService;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        AttributeTypeValidator attributeTypeValidator = new AttributeTypeValidator();

        StorageService storageService = new StorageService(attributeTypeValidator);
        storageService.put("student", List.of(new Pair<>("name", "Ishrat"), new Pair<>("age", "10")));
        storageService.put("student1", List.of(new Pair<>("name", "Ishrat"), new Pair<>("age", "10.0")));

        System.out.println(storageService.get("student"));

    }

}
