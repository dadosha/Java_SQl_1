package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement usernameField = $("[data-test-id='login'] input");
    private SelenideElement passwordField = $("[data-test-id='password'] input");
    private SelenideElement buttonLogin = $("[data-test-id='action-login']");
    private SelenideElement invalidNotificationText = $("[data-test-id='error-notification'] .notification__content");

    private void makeLogin(String username, String password) {
        usernameField.setValue(username);
        passwordField.setValue(password);
        buttonLogin.click();
    }

    public VerificationCodePage validLogin(String username, String password) {
        makeLogin(username, password);

        return new VerificationCodePage();
    }

    public void invalidLogin(String username, String password) {
        makeLogin(username, password);

        invalidNotificationText.shouldHave(Condition.text("Ошибка! Неверно указан логин или пароль")).shouldBe(Condition.visible);
    }

    public void invalidLoginThreeTimes(String username, String password) {
        makeLogin(username, password);

        invalidNotificationText.shouldHave(Condition.text("Ошибка! Неверно указан логин или пароль")).shouldBe(Condition.visible);

        buttonLogin.click();
        invalidNotificationText.shouldHave(Condition.text("Ошибка! Неверно указан логин или пароль")).shouldBe(Condition.visible);

        buttonLogin.click();
        invalidNotificationText.shouldHave(Condition.text("Ошибка! Неверно указан логин или пароль")).shouldBe(Condition.visible);
    }
}
