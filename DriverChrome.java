import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverChrome
{
        private WebDriver driver;
        private int factHeight = 0;

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
            WebElement table = driver.findElement(By.xpath("//table"));
            int rowCount = Integer.parseInt(table.getAttribute("aria-rowcount"));
            int colCount = Integer.parseInt(table.getAttribute("aria-colcount"));

            WebElement scrollBar = driver.findElement(By.xpath("(//smart-scroll-bar)[1]"));
            WebElement scrollDown = driver.findElement(By.xpath("//div[@class='smart-scroll-button smart-arrow-down']"));
            int scrollPerClick = Integer.parseInt(scrollBar.getAttribute("thumbSize"));
            int scrollHeight = Integer.parseInt(driver.findElement(By.xpath("//tbody/tr[@aria-rowindex='1']"))
                    .getAttribute("scrollHeight"));

            WebElement[] elements = new WebElement[colCount];

            for(int i = 0; i < colCount; i++)
            {
                elements[i] = driver.findElement(By.xpath("(//tr/th)[" + (i + 1) + "]"));
            }

            WebElement rowElement, colElement;
            for(int i = 1; i <= rowCount; i++)
            {
                rowElement = driver.findElement(By.xpath("//tbody/tr[@aria-rowindex=" + i + "]"));
                for(int j = 0; j < colCount; j++)
                {
                    try
                    {
                        colElement = rowElement.findElement(By.xpath("(//td)["+ (j + 1) +"]"));
                    }
                    catch (NoSuchElementException e)
                    {
                        return i;
                    }

                    if(!colElement.getAttribute("ariaColIndex").equals(elements[j].getAttribute("ariaColIndex")))
                    {
                        return i;
                    }
                }
                scroll(scrollBar, scrollDown, scrollPerClick, scrollHeight);
                System.out.println(i);
            }
            return 0;
        }

        void scroll(@NotNull WebElement scrollBar, WebElement scrollButton, int scrollPerClick, int px)
        {
            int i = Integer.parseInt(scrollBar.getAttribute("aria-valuenow"));
            int end = factHeight + px;
            for(; i < end; i += scrollPerClick)
            {
                scrollButton.click();
            }
            factHeight += px;
        }

        public void quit()
        {
            driver.quit();
        }
}
