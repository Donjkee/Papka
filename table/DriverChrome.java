import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

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

        public void checkTable()
        {
            WebElement grid = driver.findElement(By.xpath("//table"));
            Table table = new Table(grid);

            table.setScrollBar(driver.findElement(By.xpath("(//smart-scroll-bar)[1]")));
            table.setScrollDownButton(driver.findElement(By.xpath("//div[@class='smart-scroll-button smart-arrow-down']")));
            table.setScrollUpButton(driver.findElement(By.xpath("//div[@class='smart-scroll-button smart-arrow-up']")));
            int scrollHeight = Integer.parseInt(driver.findElement(By.xpath("//table/thead"))
                    .getAttribute("clientHeight"));
            table.setScrollHeight(scrollHeight);

            Actions actions = new Actions(driver);
            table.setActions(actions);

            Map<String, String> map = new HashMap<>();
            map.put("Product Name", "Ultrean 6 Quart Air Fryer");
            map.put("Date", "27.12.2022");

            table.verifyRow(map);
        }

        public void quit()
        {
            driver.quit();
        }
        
        public WebDriver getDriver()
        {
            return driver;
        }

        public void setDriver(WebDriver driver)
        {
            this.driver = driver;
        }
}
