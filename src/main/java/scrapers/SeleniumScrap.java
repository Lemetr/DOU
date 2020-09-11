package scrapers;

import constants.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class SeleniumScrap extends BasePage {

    public SeleniumScrap() {super(Constants.GET_URL);}

    public String getScrap(){

        WebElement element = driver.findElement(By.cssSelector(Constants.MORE_BUTTON));

        while (element.isDisplayed()){
            element.click();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        List<WebElement> elements = driver.findElements(By.cssSelector(Constants.VACANCIES));

        StringBuilder sb = new StringBuilder();

        for (WebElement e : elements) {

            String position = e.findElement(By.cssSelector(Constants.POSITION)).getText();
            String company = e.findElement(By.cssSelector(Constants.COMPANY)).getText();
            String cities = e.findElement(By.cssSelector(Constants.CITIES)).getText();
            String description = e.findElement(By.cssSelector(Constants.DESCRIPTION)).getText();

            sb.append(position).append(";")
                    .append(company).append(";")
                    .append(cities).append(";")
                    .append(description).append("\n");
        }

        close();
        quit();

        return sb.toString();
    }
}
