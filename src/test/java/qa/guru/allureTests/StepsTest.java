package qa.guru.allureTests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import qa.guru.allureClasses.WebSteps;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.By.linkText;

public class StepsTest {
    private static final String REPOSITORY = "eroshenkoam/allure-testops-utils";
    private static final int ISSUE = 48;

    @Test
    public void testLambdaStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу", () -> {
            open("https://github.com/");
        });
        step("Ищем репозиторий " + REPOSITORY, () -> {
            $(".header-search-button").click();
            $("[name='query-builder-test']").sendKeys(REPOSITORY);
            $("[name='query-builder-test']").submit();
        });
        step("Кликаем по ссылке репозитория " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });
        step("Открываем таб Issues", () -> {
            $("#issues-tab").click();
        });
        step("Проверяем наличие Issue с номером " + ISSUE, () -> {
            $(".issue-item-module__defaultNumberDescription--GXzri")
                    .shouldHave(Condition.text("#" + ISSUE));
        });
    }

    @Test
    public void testAnnotatedStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps = new WebSteps();

        steps.openGitHub()
                .searchForRepository(REPOSITORY)
                .clickOnRepositoryLink(REPOSITORY)
                .openIssueTab()
                .shouldSeeIssueWithNumber(ISSUE);
    }

    @Test
    @Disabled
    public void testDisabled() {
        assertTrue(true);
    }
}
