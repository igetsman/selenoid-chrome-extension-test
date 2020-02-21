package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class SignInPage {

    private static String baseUrl = "https://security.wildberries.by/login";
    private SelenideElement loginInput = $(By.cssSelector("#Item_Login"));
    private SelenideElement passwordInput = $(By.cssSelector("#Item_Password"));
    private SelenideElement submitButton = $(By.cssSelector("#signIn"));
    private SelenideElement showPassLogin = $(By.cssSelector(".j-s-show-pass-login"));

    public void inputLogin(String text) {
        this.loginInput.val(text);
    }

    public void inputPassword(String text) {
        this.passwordInput.val(text);
    }

    public void clickSubmitButton(){this.submitButton.click();}

    public void showPasswordLogin(){this.showPassLogin.click();}

    public static String getBaseUrl(){return baseUrl;};
}
