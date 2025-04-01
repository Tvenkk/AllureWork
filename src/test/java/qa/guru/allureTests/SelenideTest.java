package qa.guru.allureTests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class SelenideTest {

    @Test
    public void testIssueSearch() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com/");

        $(".header-search-button").click();
        $("[name='query-builder-test']").sendKeys("eroshenkoam/allure-testops-utils");
        $("[name='query-builder-test']").submit();

        $(linkText("eroshenkoam/allure-testops-utils")).click();
        $("#issues-tab").click();
        $(".issue-item-module__defaultNumberDescription--GXzri")
                .shouldHave(Condition.text("#48"));
    }
}
