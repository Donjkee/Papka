
public class Main
{
    public static void main(String []args) throws InterruptedException
    {
        System.setProperty("webdriver.chrome.driver","C:\\tools\\chromedrivers\\chromedriver.exe");
        DriverChrome test = new DriverChrome("https://www.htmlelements.com/demos/table/overview/index.htm");

        test.checkTable();
        Thread.sleep(10000);
        test.quit();
    }
}
