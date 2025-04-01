package qa.guru.allureClasses;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class WebSteps {

    @Step("Открываем главную страницу")
    public WebSteps openGitHub() {
        open("https://github.com/");

        return this;
    }

    @Step("Ищем репозиторий {repo}")
    public WebSteps searchForRepository(String repo) {
        $(".header-search-button").click();
        $("[name='query-builder-test']").sendKeys(repo);
        $("[name='query-builder-test']").submit();

        return this;
    }

    @Step("Кликаем по ссылке репозитория {repo}")
    public WebSteps clickOnRepositoryLink(String repo) {
        $(linkText(repo)).click();

        return this;
    }

    @Step("Открываем таб Issues")
    public WebSteps openIssueTab() {
        $("#issues-tab").click();

        return this;
    }

    @Step("Проверяем наличие Issue с номером {issue}")
    public WebSteps shouldSeeIssueWithNumber(int issue) {
        $(".issue-item-module__defaultNumberDescription--GXzri")
                .shouldHave(Condition.text("#" + issue));

        return this;
    }

    // Скриншот
    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
