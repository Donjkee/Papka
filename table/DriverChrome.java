import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.Map;

public class DriverChrome
{
        private WebDriver driver;


        public DriverChrome(String url) throws InterruptedException
        {
            driver = new ChromeDriver();
            driver.get(url);
            Thread.sleep(500);
        }

        public WebDriver getDriver()
        {
            return driver;
        }

        public void setDriver(WebDriver driver)
        {
            this.driver = driver;
        }

        public int checkTable()
        {
            WebElement grid = driver.findElement(By.xpath("//table"));

            Table table = new Table(grid);

            table.setScrollBar(driver.findElement(By.xpath("(//smart-scroll-bar)[1]")));
            table.setScrollButton(driver.findElement(By.xpath("//div[@class='smart-scroll-button smart-arrow-down']")));

            int scrollHeight = Integer.parseInt(driver.findElement(By.xpath("//table/thead"))
                    .getAttribute("clientHeight"));

            table.setScrollHeight(scrollHeight);

            return table.checkTable();
        }

        public void quit()
        {
            driver.quit();
        }
}