import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class PAYMENT_RCPT {
    public static void main(String[] args) {
        WebDriver driver;
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.jiscollege.ac.in/");

        String initialWH = driver.getWindowHandle();

        driver.findElement(By.xpath("//a[@href='login-help.php']")).click();

        String originalWindow = driver.getWindowHandle();
        assert driver.getWindowHandles().size() == 1;
        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        driver.findElement(By.xpath("//a[@class='btn btn-primary']")).click();

        driver.findElement(By.id("ctxt_user_name")).sendKeys("JIS/2020/0042");
        driver.findElement((By.id("ctxt_pass_word"))).sendKeys("JIS/2020/0042");

        driver.findElement(By.xpath("//*[@id=\"cmd_ligin_type\"]/option[2]")).click();

        driver.findElement(By.xpath("//div[@class='form-group text-center']/child::a")).click();
        driver.findElement(By.xpath("//div[@class='payment-student']")).click();

        String originalWindow2 = driver.getWindowHandle();
        assert driver.getWindowHandles().size() == 1;
        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow2.contentEquals(windowHandle) && !initialWH.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        //https://www.jisgroup.net/erp/forms/frmRptStudentMrPrint.aspx
        if(driver.getCurrentUrl().contentEquals("https://www.jisgroup.net/erp/forms/frmRptStudentMrPrint.aspx")){
            System.out.println("TEST PASSED : PAGE OPENED");
        }
        else{
            System.out.println("TEST FAILED : PAGE DID NOT OPENED");
        }
        driver.quit();
    }
}
