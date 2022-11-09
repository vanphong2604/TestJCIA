import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public abstract class TestJCIA {
    private String baseUrl;
    private static final String DRIVERPATH = "C:\\Users\\asus\\Documents\\Testing\\TestJCIA\\Brower\\chromedriver.exe";;
    protected WebDriver webDriver;

    public TestJCIA(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @BeforeTest
    public void launchBrowser() {
        System.out.println("launching chrome browser");
        System.setProperty("webdriver.chrome.driver", DRIVERPATH);
        webDriver = new ChromeDriver();
        webDriver.get(baseUrl);
    }

    @AfterTest
    public void terminateBrowser(){
        webDriver.close();
    }
}
