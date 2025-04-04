package qa.guru.allureTests;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import qa.guru.allureClasses.WebSteps;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.By.linkText;

public class StepsTest extends BaseTest {
    private static final String REPOSITORY = "eroshenkoam/allure-testops-utils";
    private static final int ISSUE = 48;

    @Test
    @Tag("remote")
    @DisplayName("Проверяем наличие Issue с номером {ISSUE} с помощью step")
    public void testLambdaStep() {
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
    @Tag("remote")
    @DisplayName("Проверяем наличие Issue с номером {ISSUE} с помощью @Step")
    public void testAnnotatedStep() {
        WebSteps steps = new WebSteps();

        steps.openGitHub()
                .searchForRepository(REPOSITORY)
                .clickOnRepositoryLink(REPOSITORY)
                .openIssueTab()
                .shouldSeeIssueWithNumber(ISSUE);
    }

    @Test
    @Tag("simple")
    @DisplayName("Позитивный тест")
    public void testTrue() {
        assertTrue(true);
    }

    @Test
    @Tag("simple")
    @DisplayName("Негативный тест")
    public void testFalse() {
        assertFalse(true);
    }
}
