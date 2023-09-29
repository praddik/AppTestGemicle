import assertions.JsonData;
import baseConfig.BaseTest;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pages.DragAndDropPage;

import static com.codeborne.selenide.Selenide.open;
import static model.Colors.GREEN;
import static model.Colors.RED;
import static model.PairAndNonPair.NONPAIR;


@Feature("Test feature")
public class DragAndDropRowsTest extends BaseTest {
    DragAndDropPage dragAndDropPage = new DragAndDropPage();
    JsonData jsonData = new JsonData();
    String url = "https://www.ag-grid.com/examples/drag-and-drop/simple/packages/reactFunctional/index.html";

    @BeforeSuite
    void beforeSuite() {
        open(url);
    }

    @AfterTest
    public void refreshPage() {
        dragAndDropPage.refreshPage();
    }

    @Test
    public void assertGreenRows() {
        ElementsCollection elementsGreenColor = dragAndDropPage.getAllRowsWithColors(GREEN);
        dragAndDropPage.moveElementsToAnotherPart(elementsGreenColor);
        Assert.assertEquals(jsonData.getRowsFromJsons(), dragAndDropPage.getListRowsFromPage(elementsGreenColor));
    }

    @Test
    public void assertRedRows() {
        ElementsCollection elementsRedColor = dragAndDropPage.getAllRowsWithColorOrPairOrNonPairId(RED, NONPAIR);
        dragAndDropPage.moveElementsToAnotherPart(elementsRedColor);
        Assert.assertEquals(jsonData.getRowsFromJsons(), dragAndDropPage.getListRowsFromPage(elementsRedColor));
    }
}
