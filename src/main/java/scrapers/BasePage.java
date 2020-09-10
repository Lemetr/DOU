package scrapers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BasePage {
    WebDriver driver;

    public BasePage(String url) {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.navigate().to(url);
    }

    protected void close(){
        driver.close();
    }

    protected void quit(){
        driver.quit();
    }
}
