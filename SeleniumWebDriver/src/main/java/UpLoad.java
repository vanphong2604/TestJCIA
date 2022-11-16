import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class UpLoad extends TestJCIA{

    @DataProvider
    public Object[][] Authentication() throws Exception {
        webDriver.findElement(By.id("username")).sendKeys("NXPGaming");
        webDriver.findElement(By.id("password")).sendKeys("phong2002");
        webDriver.findElement(By.xpath("//*[@id=\"login-btn\"]")).click();
        Object[][] testObjArray = ExcelUtils.getTableArray("C:\\Users\\asus\\OneDrive\\Máy tính\\Testing\\btl\\Test.xlsx",4,"Sheet3");
        return (testObjArray);
    }

    public void upLoad(String projectName, String uploadYourProject) {
        webDriver.get("http://localhost:9000/upload");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        webDriver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div/form/button[2]")).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // Khởi tạo Robot class
        Robot rb = null;
        try {
            rb = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        // Copy File path vào Clipboard
        StringSelection str = new StringSelection(uploadYourProject);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Nhấn Control+V để dán
        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_V);

        // Xác nhận Control V trên
        rb.keyRelease(KeyEvent.VK_CONTROL);
        rb.keyRelease(KeyEvent.VK_V);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Nhấn Enter
        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        webDriver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div/form/button[1]")).click();
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Test(dataProvider="Authentication")
    public void upLoad(String projectName, String uploadYourProject, String expectedOutput) {
        upLoad(projectName, uploadYourProject);
        Assert.assertEquals(webDriver.getCurrentUrl(), expectedOutput);
    }

}