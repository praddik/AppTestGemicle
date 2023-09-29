package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import model.Colors;
import model.PairAndNonPair;
import model.Row;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$$x;


public class DragAndDropPage {
    String id = ".//div[contains(@col-id,'id')]";
    String color = ".//div[contains(@col-id,'color')]";
    String val1 = ".//div[contains(@col-id,'value1')]";
    String val2 = ".//div[contains(@col-id,'value2')]";
    String allRowsPath = "//div[contains(@class,'%s-row')]";
    String jsonRow = "json-row";
    String jsonDisplayPath = "json-display";
    String checkPairRow = "//div[contains(@class,'%s')]//div[@col-id='id'][text() mod 2=%d]/..";

    public ElementsCollection getAllRowsWithColors(Colors colors) {
        return $$x(String.format(allRowsPath, colors.getText()));
    }

    public ElementsCollection getAllRowsWithColorOrPairOrNonPairId(Colors colors, PairAndNonPair paiOrNotValue) {
        Selenide.sleep(2000);
        return $$x(String.format(checkPairRow, colors.getText(), Integer.parseInt(paiOrNotValue.getText())));
    }

    public void moveElementsToAnotherPart(ElementsCollection collection) {
        Selenide.sleep(2000);
        collection.forEach(el -> hoverAndMove(createJsonModel(el.shouldBe(Condition.visible))));
    }

    public void hoverAndMove(String json) {
        Selenide.executeJavaScript(
                "var newDiv = document.createElement('div');" +
                        "newDiv.className = '" + jsonRow + "';" +
                        "newDiv.innerHTML = '" + json + "';" +
                        "document.querySelector('." + jsonDisplayPath + "').appendChild(newDiv);");
        Selenide.sleep(1000);
    }

    public String createJsonModel(SelenideElement element) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode parent = mapper.createObjectNode();

        parent.put("id", Long.parseLong(element.$x(id).shouldBe(Condition.visible).getText()));
        parent.put("color", element.$x(color).shouldBe(Condition.visible).getText());
        parent.put("value1", Long.parseLong(element.$x(val1).shouldBe(Condition.visible).getText()));
        parent.put("value2", Long.parseLong(element.$x(val2).shouldBe(Condition.visible).getText()));

        String json = null;
        try {
            json = mapper.writeValueAsString(parent);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Selenide.sleep(1000);
        return json;
    }

    public List<Row> getListRowsFromPage(ElementsCollection elementsCollection) {
        return elementsCollection.stream()
                .map(row -> new Row(
                        Integer.parseInt(row.$x(id).getText().trim()),
                        row.$x(color).getText().trim(),
                        Integer.parseInt(row.$x(val1).getText().trim()),
                        Integer.parseInt(row.$x(val2).getText().trim())
                ))
                .collect(Collectors.toList());

    }

    public void refreshPage() {
        Selenide.executeJavaScript("window.location.reload(true);");
    }
}
