package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class VerificationCodePage {
    private SelenideElement codeField = $("[data-test-id='code'] input");
    private SelenideElement verifyButton = $("[data-test-id='action-verify']");
    private SelenideElement invalidNotificationText = $("[data-test-id='error-notification'] .notification__content");

    public VerificationCodePage() {
        verifyButton.shouldBe(Condition.visible);
    }

    public DashboardPage validCodeEnter(String code) {
        codeField.setValue(code);
        verifyButton.click();

        return new DashboardPage();
    }

    public void invalidCodeEnter(String code) {
        codeField.setValue(code);
        verifyButton.click();

        invalidNotificationText.shouldHave(Condition.text("Ошибка! Неверно указан код! Попробуйте ещё раз.")).shouldBe(Condition.visible);
    }
}
