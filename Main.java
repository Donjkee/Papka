import java.util.HashMap;
import java.util.Map;

public class Main
{
    public static void main(String []args) throws InterruptedException
    {
        System.setProperty("webdriver.chrome.driver","C:\\tools\\chromedrivers\\chromedriver.exe");
        DriverChrome test = new DriverChrome("https://cosmocode.io/automation-practice-webtable/");
        Map<String, String> map = new HashMap<>();
        map.put("Country", "Algeria");
        map.put("Capitals", "Luanda");

        if(!test.check(map))
        {
            System.out.println("Wrong table!");
        }
        else
        {
            System.out.println("Everything is good!");
        }

        test.quit();
    }
}
