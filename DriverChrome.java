import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

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

        public boolean check(Map<String, String> map)
        {
            WebElement element;
            String text;
            for (String key : map.keySet())
            {
                if(key.equals("Country"))
                {
                    try
                    {
                        text = map.get(key);
                        element = driver.findElement(By.xpath("//td/strong[text()='" + text + "']"));
                    }
                    catch (NoSuchElementException e)
                    {
                        return false;
                    }
                }
                else if(key.equals("Capitals"))
                {
                    try
                    {
                        text = map.get(key);
                        element = driver.findElement(By.xpath("//td[text()='"+
                                text +"']/preceding-sibling::td[2]"));
                    }
                    catch (NoSuchElementException e)
                    {
                        return false;
                    }
                }
                else
                {
                    return false;
                }

            }

            return true;
        }


        public void quit()
        {
            driver.quit();
        }
}
