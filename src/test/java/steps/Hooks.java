package steps;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.codeborne.selenide.Selenide.*;

public class Hooks {

    @Before
    public void openUrl() throws Exception {
        Configuration.startMaximized=true;
        System.out.println("plugin.chrome.path="+System.getProperty("plugin.chrome.path"));
        System.out.println("selenide.browser="+System.getProperty("selenide.browser"));
        System.out.println("selenide.remote="+System.getProperty("selenide.remote"));
        openBrowser(System.getProperty("selenide.browser","chrome"));
        open("about:blank");
    }


    public void openBrowser(String browser) throws Exception {
        System.out.println("browser="+browser);
        String extensionPath=new File("").getAbsolutePath().toString()+String.format("%1$ssrc%1$stest%1$sresources%1$s",File.separator)+System.getProperty("plugin.chrome.path");
        System.out.println("extension="+extensionPath);
        String hubUrl = System.getProperty("selenide.remote");
        System.out.println("hubUrl="+hubUrl);
        if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            Configuration.browser = "firefox";
            if (hubUrl!=null) {
                WebDriverRunner.setWebDriver(new RemoteWebDriver(new URL(hubUrl), DesiredCapabilities.firefox()));
            }
        } else if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            Configuration.browser = "chrome";
            ChromeOptions options = new ChromeOptions();
            options.addExtensions (new File(extensionPath));
            if (hubUrl==null) {
                WebDriverRunner.setWebDriver(new ChromeDriver(options));
            } else {
                WebDriverRunner.setWebDriver(new RemoteWebDriver(new URL(hubUrl), options));
            }
        }
    }

    @After
    public void closeBrowser(Scenario scenario) throws Exception {
        if (scenario.isFailed()) {
            scenario.embed(((TakesScreenshot)WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES), "image/png");
        }
        close();
    }
}
