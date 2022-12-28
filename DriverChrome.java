import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

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

        public String getCapital(String country)
        {
            WebElement element;
            try
            {
                element = driver.findElement(By.xpath("//td[strong[text()='" + country +
                        "']]/following-sibling::td[@style='width: 132.667px;']"));
            }
            catch (NoSuchElementException e)
            {
                return "Error! No such country!";
            }

            return element.getText();
        }

        public void quit()
        {
            driver.quit();
        }
}