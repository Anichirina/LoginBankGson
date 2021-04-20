import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AutoTestLogin {
    @BeforeEach
    void Setup() {
        open("http://localhost:9999");
    }
        @Test

        void shouldTestHappyPath() {
            Registration validValidActiveUser = Generator.getValidActiveUser();
            $("[data-test-id='login'] input").setValue(validValidActiveUser.getLogin());
            $("[data-test-id='password'] input").setValue(.getPassword());
            $(withText("Продолжить")).click();
            $(withText("Личный кабинет")).shouldBe(visible);
        }
        @Test
        void shouldTestIncorrectLoginOfValidUser() {
            Registration userWithIncorrectLogin = Generator.getUserWithIncorrectLogin();
            $("[data-test-id='login'] input").setValue(DataGenerator.Autharization.incorrectLogin());
            $("[data-test-id='password'] input").setValue(clientData.getPassword());
            $(withText("Продолжить")).click();
            $("[data-test-id='error-notification'] .notification__content").shouldBe(visible).shouldHave(text("Неверно указан логин или пароль"));
        }
        @Test
        void shouldTestIncorrectPasswordOfValidUser() {
            Registration userWithIncorrectPassword = Generator.getUserWithIncorrectPassword();
            $("[data-test-id='login'] input").setValue(clientData.getLogin());
            $("[data-test-id='password'] input").setValue(DataGenerator.Autharization.incorrectPassword());
            $(withText("Продолжить")).click();
            $("[data-test-id='error-notification'] .notification__content").shouldBe(visible).shouldHave(text("Неверно указан логин или пароль"));
        }
        @Test
        void shouldTestBlockedUser() {
            Registration validValidAcBlockedUser = Generator.getValidBlockedUser();
            $("[data-test-id='login'] input").setValue(clientData.getLogin());
            $("[data-test-id='password'] input").setValue(clientData.getPassword());
            $(withText("Продолжить")).click();
            $("[data-test-id='error-notification'] .notification__content").shouldBe(visible).shouldHave(text("Пользователь заблокирован"));
        }

    }


