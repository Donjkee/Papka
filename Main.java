import java.util.Scanner;

public class Main
{
    public static void main(String []args) throws InterruptedException
    {
        System.setProperty("webdriver.chrome.driver","C:\\tools\\chromedrivers\\chromedriver.exe");
        DriverChrome test = new DriverChrome("https://cosmocode.io/automation-practice-webtable/");
        Scanner scanner = new Scanner(System.in);

        String country;
        System.out.println("Print 1 to stop");
        while (true)
        {
            System.out.print("Print country: ");
            country = scanner.nextLine();
            if(country.equals("1"))
            {
                break;
            }
            System.out.println("Capital is: " + test.getCapital(country));
        }

        test.quit();
    }
}
