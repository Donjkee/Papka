import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main
{
    public static void main(String []args) throws InterruptedException
    {
        System.setProperty("webdriver.chrome.driver","C:\\tools\\chromedrivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://google.com");
        Thread.sleep(500);

        WebElement input = driver.findElement(By.xpath("//input[@name='q']"));
        input.click();
        input.sendKeys("weareqa", Keys.ENTER);
        Thread.sleep(500);

        clickButton(driver ,"//a[@href='https://weareqa.com/']");
        clickButton(driver ,"(//a[@href='#about'])[2]");
        clickButton(driver ,"(//a[@href='#our-services'])[2]");
        clickButton(driver ,"(//a[@href='#tech-stack'])[2]");
        clickButton(driver ,"(//a[@href='#case-studies'])[2]");
        clickButton(driver, "//a[@class='wp-block-button__link']");
        printButton(driver, "//input[@id='wpforms-122-field_0']", "Max");
        printButton(driver, "//input[@id='wpforms-122-field_3']", "Apple");
        printButton(driver, "//input[@id='wpforms-122-field_1']", "111@gmail.com");
        printButton(driver, "//textarea[@id='wpforms-122-field_2']", "Hello!");
        clickButton(driver, "//input[@id='wpforms-122-field_4_1']");
        clickButton(driver, "//button[@id='wpforms-submit-122']");

        Thread.sleep(10000);
        driver.quit();
    }

    public static void printButton(@NotNull WebDriver driver, String xPath, String text) throws InterruptedException
    {
        WebElement temp = driver.findElement(By.xpath(xPath));
        temp.click();
        temp.sendKeys(text);
        Thread.sleep(500);
    }

    public static void clickButton(@NotNull WebDriver driver, String xPath) throws InterruptedException
    {
        WebElement temp = driver.findElement(By.xpath(xPath));
        temp.click();
        Thread.sleep(500);
    }
}
