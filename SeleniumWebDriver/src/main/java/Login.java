import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Login extends TestJCIA{

    public Login() {
        super("http://localhost:9000/login");
    }
    @DataProvider
    public Object[][] Authentication() throws Exception {

        Object[][] testObjArray = ExcelUtils.getTableArray("C:\\Users\\asus\\OneDrive\\Máy tính\\Testing\\btl\\Test.xlsx",4,"Sheet2");
        return (testObjArray);

    }
    @Test(dataProvider="Authentication")
    public void login(String userName, String passWord, String expectedOutput) {
        if (webDriver.getCurrentUrl().equals("http://localhost:9000/home")) {
            webDriver.findElement(By.xpath("/html/body/div[1]/div[6]")).click();
        }
        webDriver.findElement(By.id("username")).clear();
        webDriver.findElement(By.id("username")).sendKeys(userName);
        webDriver.findElement(By.id("password")).clear();
        webDriver.findElement(By.id("password")).sendKeys(passWord);
        webDriver.findElement(By.xpath("//*[@id=\"login-btn\"]")).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(webDriver.getCurrentUrl(), expectedOutput);
    }
}