import org.openqa.selenium.*;
import org.testng.Assert;

public class Login extends TestJCIA{

    public Login() {
        super("http://localhost:9000/login");
    }

    public void login(String userName, String passWord) {
        webDriver.findElement(By.id("username")).sendKeys(userName);
        webDriver.findElement(By.id("password")).sendKeys(passWord);
        String actualOutput = webDriver.getTitle();
        Assert.assertEquals(actualOutput, "JCIA");
    }

}