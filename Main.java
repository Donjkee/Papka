
public class Main
{
    public static void main(String []args) throws InterruptedException
    {
        System.setProperty("webdriver.chrome.driver","C:\\tools\\chromedrivers\\chromedriver.exe");
        DriverChrome test = new DriverChrome("https://www.htmlelements.com/demos/table/overview/index.htm");

        int number = test.checkTable();
        if(number != 0)
        {
            System.out.println("Wrong table number: " + number + "!");
        }
        else
        {
            System.out.println("Everything is ok!");
        }

        test.quit();
    }
}
