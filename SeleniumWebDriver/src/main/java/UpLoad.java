import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UpLoad extends TestJCIA{

    public UpLoad() {
        super("http://localhost:9000/login");
    }

    @DataProvider
    public Object[][] Authentication() throws Exception {
        webDriver.findElement(By.id("username")).sendKeys("NXPGaming");
        webDriver.findElement(By.id("password")).sendKeys("phong2002");
        webDriver.findElement(By.xpath("//*[@id=\"login-btn\"]")).click();
        Object[][] testObjArray = ExcelUtils.getTableArray("C:\\Users\\asus\\OneDrive\\Máy tính\\Testing\\btl\\Test.xlsx",4,"Sheet3");
        return (testObjArray);
    }
    @Test(dataProvider="Authentication")
    public void upLoad(String projectName, String uploadYourProject, String expectedOutput) {
        System.out.println(webDriver.getCurrentUrl());
        webDriver.get("http://localhost:9000/upload");
//        webDriver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div/form/div/span[2]/span")).clear();
        webDriver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div/form/div/span[2]/span")).sendKeys(uploadYourProject);
        webDriver.findElements(By.id("frompc-proj")).clear();
        webDriver.findElement(By.id("frompc-proj")).sendKeys(projectName);
        webDriver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div/form/button[1]")).click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(webDriver.getCurrentUrl(), expectedOutput);
    }




}