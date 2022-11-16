import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class SignUp extends TestJCIA{
    @DataProvider
    public Object[][] Authentication() throws Exception {
        Object[][] testObjArray = ExcelUtils.getTableArray("C:\\Users\\asus\\OneDrive\\Máy tính\\Testing\\btl\\Test.xlsx",5,"SignUp");
        return (testObjArray);
    }
    @Test(dataProvider="Authentication")
    public void signUp(String userName, String Email, String passWord, String expectedOutput) {
        if (webDriver.getCurrentUrl().equals("http://localhost:9000/login")) {
            webDriver.findElement(By.xpath("//*[@id=\"signup-alt\"]")).click();
        }
        webDriver.findElement(By.id("username")).clear();
        webDriver.findElement(By.id("username")).sendKeys(userName);
        System.out.println(webDriver.findElement(By.id("email")));
        webDriver.findElement(By.id("email")).clear();
        webDriver.findElement(By.id("email")).sendKeys(Email);
        webDriver.findElement(By.id("password")).clear();
        webDriver.findElement(By.id("password")).sendKeys(passWord);
        webDriver.findElement(By.xpath("//*[@id=\"signup-btn\"]")).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(webDriver.getCurrentUrl(), expectedOutput);
    }
}