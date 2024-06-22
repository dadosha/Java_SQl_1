package data;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.Locale;

public class DataHelper {
    private static final Faker faker = new Faker(new Locale("en"));

    private DataHelper() {
    }

    public static String generateName() {
        return faker.name().firstName();
    }

    public static String generatePassword() {
        return faker.internet().password();
    }

    public static String generateId() {
        return faker.internet().password();
    }

    public static UserInfo generateUser() {
        return new UserInfo(generateId(), generateName(), generatePassword());
    }

    public static UserInfo generatePasswordForUser() {
        return new UserInfo("b101c113-875c-4b03-a988-8b554de4a642", "petya", generatePassword());
    }

    public static UserInfo getCorrectUserLogInInfo() {
        return new UserInfo("c690a106-a570-4aa6-a19d-bde280489511", "vasya", "qwerty123");
    }

    public static String generateCode() {
        return faker.numerify("######");
    }

    @Value
    public static class UserInfo {
        String id;
        String login;
        String password;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VerificationCode {
        String code;
    }
}
