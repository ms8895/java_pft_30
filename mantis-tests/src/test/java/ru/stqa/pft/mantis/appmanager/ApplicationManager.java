package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    private final Properties properties;
    WebDriver wd;

    private JavascriptExecutor js;
    private String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));


        if (browser.equals(BrowserType.CHROME)) {
            System.setProperty("webdriver.chrome.driver", "C:\\webdriver\\chromedriver\\chromedriver.exe");
            wd = new ChromeDriver();
        } else if (browser.equals(BrowserType.FIREFOX)) {
            System.setProperty("webdriver.gecko.driver", "C:\\webdriver\\firefoxdriver\\geckodriver.exe");
            wd = new FirefoxDriver();
        } else if (browser.equals(BrowserType.OPERA)) {
            System.setProperty("webdriver.opera.driver", "C:\\webdriver\\operadriver\\operadriver.exe");

            wd = new OperaDriver();
        }

        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        js = (JavascriptExecutor) wd;
        wd.get(properties.getProperty("web.baseUrl"));
    }

    public void stop() {
        wd.quit();
    }

    public HttpSession newSession() {
        return new HttpSession(this);
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
