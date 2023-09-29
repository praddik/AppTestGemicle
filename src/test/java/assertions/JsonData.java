package assertions;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Row;

import java.util.List;
import java.util.stream.Collectors;

public class JsonData {
    String jsonHtml = "//div[@class='json-row']";
    ObjectMapper mapper = new ObjectMapper();

    public List<Row> getRowsFromJsons() {
        ElementsCollection elementsCollection = Selenide.$$x(jsonHtml);
        Selenide.sleep(2000);

        return elementsCollection.stream()
                .map(SelenideElement::getText)
                .collect(Collectors.toList())
                .stream()
                .map(this::getRows)
                .collect(Collectors.toList());
    }

    public Row getRows(String json) {

        JsonNode jsonElement = null;
        try {
            jsonElement = mapper.readTree(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return new Row(Integer.parseInt(jsonElement.get("id").asText())
                , jsonElement.get("color").asText()
                , Integer.parseInt(jsonElement.get("value1").asText()),
                Integer.parseInt(jsonElement.get("value2").asText()));
    }
}

