import com.codeborne.selenide.Condition;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestLogin {


    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }


    @Test
    @DisplayName("Should successfully login with active registered user")
    void shouldSuccessfulLoginIfRegisteredActiveUser() {
        val registeredUser = DataGenerator.Registration.getRegisteredUser("active");
        // TODO: добавить логику теста, в рамках которого будет выполнена попытка входа в личный кабинет с учётными
        //  данными зарегистрированного активного пользователя, для заполнения полей формы используйте
        //  пользователя registeredUser

        $("[data-test-id='login'] input").setValue(DataGenerator.getRandomLogin());
        $("[data-test-id='password'] input").setValue(DataGenerator.getRandomPassword());
        $(withText("Продолжить")).click();
        $(withText("Личный кабинет")).shouldBe(Condition.visible);
    }


    @Test
    @DisplayName("Should get error message if login with not registered user")
    void shouldGetErrorIfNotRegisteredUser() {
        val notRegisteredUser = DataGenerator.Registration.getUser("active");

        // TODO: добавить логику теста в рамках которого будет выполнена попытка входа в личный кабинет
        //  незарегистрированного пользователя, для заполнения полей формы используйте пользователя notRegisteredUser
        $("[data-test-id='login'] input").setValue(DataGenerator.getRandomLogin());
        $("[data-test-id='password'] input").setValue(DataGenerator.getRandomPassword());
        $(withText("Продолжить")).click();
        $(withText("Пользователь заблокирован")).shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Should get error message if login with blocked registered user")
    void shouldGetErrorIfBlockedUser() {
        val blockedUser = DataGenerator.Registration.getRegisteredUser("blocked");

        // TODO: добавить логику теста в рамках которого будет выполнена попытка входа в личный кабинет,
        //  заблокированного пользователя, для заполнения полей формы используйте пользователя blockedUser
        $("[data-test-id='login'] input").setValue(DataGenerator.getRandomLogin());
        $("[data-test-id='password'] input").setValue(DataGenerator.getRandomPassword());
        $(withText("Продолжить")).click();
    }

    @Test
    @DisplayName("Should get error message if login with wrong login")
    void shouldGetErrorIfWrongLogin() {
        val registeredUser = DataGenerator.Registration.getRegisteredUser("active");
        val wrongLogin = DataGenerator.getRandomLogin();
        // TODO: добавить логику теста в рамках которого будет выполнена попытка входа в личный кабинет с неверным
        //  логином, для заполнения поля формы "Логин" используйте переменную wrongLogin,
        //  "Пароль" - пользователя registeredUser
        $("[data-test-id='login'] input").setValue(DataGenerator.getRandomLogin());
        $("[data-test-id='password'] input").setValue(DataGenerator.getRandomPassword());
        $(withText("Продолжить")).click();

    }

    @Test
    @DisplayName("Should get error message if login with wrong password")
    void shouldGetErrorIfWrongPassword() {
        val registeredUser = DataGenerator.Registration.getRegisteredUser("active");
        val wrongPassword = DataGenerator.getRandomPassword();
        // TODO: добавить логику теста в рамках которого будет выполнена попытка входа в личный кабинет с неверным
        //  паролем, для заполнения поля формы "Логин" используйте пользователя registeredUser,
        //  "Пароль" - переменную wrongPassword
        $("[data-test-id='login'] input").setValue(DataGenerator.getRandomLogin());
        $("[data-test-id='password'] input").setValue(DataGenerator.getRandomPassword());
        $(withText("Продолжить")).click();

    }
}
