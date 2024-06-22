package test;

import data.DataHelper;
import data.SQLHelper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import pages.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static data.SQLHelper.*;

public class LoginTest {

    LoginPage loginPage;

    @BeforeEach
    void setup() {
        loginPage = open("http://localhost:9999", LoginPage.class);
    }

    @AfterAll
    static void delete() {
        deleteAll();
    }

    @Test
    @DisplayName("Successful login")
    void shouldSuccessfulLogin () {
        var userInfo = DataHelper.getCorrectUserLogInInfo();
        var verificationPage = loginPage.validLogin(userInfo.getLogin(), userInfo.getPassword());
        verificationPage.validCodeEnter(getCode());
    }

    @Test
    @DisplayName("Unsuccessful login")
    void shouldUnsuccessfulLogin () {
        var userInfo = DataHelper.generateUser();
        loginPage.invalidLogin(userInfo.getLogin(), userInfo.getPassword());
    }

    @Test
    @DisplayName("Incorrect code")
    void shouldUnsuccessfulCode () {
        var userInfo = DataHelper.getCorrectUserLogInInfo();
        var verificationPage = loginPage.validLogin(userInfo.getLogin(), userInfo.getPassword());
        verificationPage.invalidCodeEnter(DataHelper.generateCode());
    }

    @Test
    @DisplayName("If the password is entered incorrectly three times, the system is locked out")
    void shouldBlockedUser () {
        var userInfo = DataHelper.generatePasswordForUser();
        loginPage.invalidLoginThreeTimes(userInfo.getLogin(), userInfo.getPassword());

        String actualStatus = getUserStatus(userInfo.getId());

        Assertions.assertNotEquals("active", actualStatus);
    }
}
