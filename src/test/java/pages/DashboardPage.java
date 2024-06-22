package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    private SelenideElement headerPage = $("[data-test-id='dashboard']");

    public DashboardPage() {
        headerPage.shouldHave(Condition.text("Личный кабинет")).shouldBe(Condition.visible);
    }
}
