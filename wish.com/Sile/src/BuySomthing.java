
import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

/*   Change Language To French
 * */
public class BuySomthing {
    static WebDriver driver;
    static Wait<WebDriver> wait;

    @Test
    public void mainTest() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/DINO/Downloads/chromedriver_win32/chromedriver.exe");
        driver = new ChromeDriver();
        // https://seleniumhq.github.io/selenium/docs/api/java/org/openqa/selenium/support/ui/WebDriverWait.html
        wait = new WebDriverWait(driver, 30);
        driver.get("https://www.wish.com/");

        boolean result;
        try {
            result = firstPageContainsQAANet();
        } catch(Exception e) {
            e.printStackTrace();
            result = false;
        } finally {
            driver.close();
        }

        System.out.println("Test " + (result? "passed." : "failed."));
        if (!result) {
            System.exit(1);
        }
    }
   
    private static boolean firstPageContainsQAANet() throws AWTException, InterruptedException {

    	
        new cheklanguage().login("email","password",driver);
        
        
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                return webDriver.findElement(By.id("header-cart-image")) != null;
            }
        });
        
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_DOWN);
        Thread.sleep(1000);
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_DOWN);
        Thread.sleep(1000);
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_DOWN);
        Thread.sleep(1000);
        robot.keyPress(KeyEvent.VK_UP);
        robot.keyPress(KeyEvent.VK_UP);
        robot.keyPress(KeyEvent.VK_UP);
        robot.keyPress(KeyEvent.VK_UP);
        Thread.sleep(1000);
        robot.keyPress(KeyEvent.VK_UP);
        robot.keyPress(KeyEvent.VK_UP);
        robot.keyPress(KeyEvent.VK_UP);
        robot.keyPress(KeyEvent.VK_UP);
        Thread.sleep(1000);
        robot.keyPress(KeyEvent.VK_UP);
        robot.keyPress(KeyEvent.VK_UP);
        robot.keyPress(KeyEvent.VK_UP);
        robot.keyPress(KeyEvent.VK_UP);
        Thread.sleep(1000);

        driver.findElement(By.id("feed-product-item-atag")).click();
        
        
       
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                return webDriver.findElement(By.xpath("//div[@class='fancy-select-label']")) != null;
            }
        });
        String a = driver.findElement(By.id("product-name")).getText();
        driver.findElement(By.xpath("//div[@class='fancy-select-label']")).click();
        driver.findElement(By.xpath("//li[@class='fancy-select-option ']")).click();
        driver.findElement(By.xpath("//div[@class='join-buy-button clickable']")).click();
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                return webDriver.findElement(By.id("header-hello")) != null;
            }
        });
        
        
        return driver.findElement(By.xpath("//div[@class='product-name']")).getText().contains(a);
    }
}