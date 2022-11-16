import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DependencyList extends TestJCIA{
    private UpLoad upLoad;
    public DependencyList() {
        upLoad = new UpLoad();
    }
    @DataProvider
    public Object[][] Authentication() throws Exception {
        webDriver.findElement(By.id("username")).sendKeys("NXPGaming");
        webDriver.findElement(By.id("password")).sendKeys("phong2002");
        webDriver.findElement(By.xpath("//*[@id=\"login-btn\"]")).click();
        Object[][] testObjArray = ExcelUtils.getTableArray("C:\\Users\\asus\\OneDrive\\Máy tính\\Testing\\btl\\Test.xlsx", 4, "Sheet4");
        return (testObjArray);
    }
    @Test(dataProvider="Authentication")
    public void dependencyList(String projectName, String uploadYourProject, String expectedOutput) {
        System.out.println(uploadYourProject);
        upLoad.upLoad(projectName, uploadYourProject);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement from = webDriver.findElement(By.xpath("/html/body/div[2]/div[5]"));
        WebElement to = webDriver.findElement(By.xpath("/html/body/div[2]/div[3]"));
        Actions builder = new Actions(webDriver);
        Action dragAndDrop = builder.clickAndHold(from)
                .moveToElement(to).release(to).build();
        dragAndDrop.perform();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String ActualOutput = "";
        if(!webDriver.findElement(By.xpath("//*[@id=\"dependency-list-content\"]")).getText().equals("No dependency to show")) {
            ActualOutput = webDriver.findElement(By.className("accordion")).getText().replace("\n"," ")
                    .replace(" From","\nFrom");
        } else {
            ActualOutput = "No dependency to show";
        }
        Assert.assertEquals(ActualOutput, expectedOutput);
    }
}
