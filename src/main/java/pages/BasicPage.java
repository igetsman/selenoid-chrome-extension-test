package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class BasicPage {

    public void clickButton(String text) {
        $(By.xpath("//div[text()='" + text + "']")).click();
    }

    public void clickButtonSpan(String text) {
        $(By.xpath("//span[text()='" + text + "']")).click();
    }

    public void contentIsVisible(String text) {
        $(By.xpath("//*[text()='" + text + "']")).shouldBe(Condition.visible);
    }

    public void openPage(String url) {
        open(url);
    }
}
