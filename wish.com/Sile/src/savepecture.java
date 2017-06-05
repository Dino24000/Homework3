
import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;

import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

/*   Change Language To French
 * */
public class savepecture {
    static WebDriver driver;
    static Wait<WebDriver> wait;

    @Test
    public void mainTest() throws IOException, InterruptedException, AWTException {
        System.setProperty("webdriver.chrome.driver", "C:/Users/DINO/Downloads/chromedriver_win32/chromedriver.exe");
        driver = new ChromeDriver();
        // https://seleniumhq.github.io/selenium/docs/api/java/org/openqa/selenium/support/ui/WebDriverWait.html
        wait = new WebDriverWait(driver, 30);
        driver.get("https://www.wish.com/");

       firstPageContainsQAANet();
    }
    /**
     * @throws IOException
     * @throws InterruptedException
     * @throws AWTException
     */
    private static void login(String email,String password){
    	driver.findElement(By.xpath("//div[@class='email-login-btn btn']")).click();
        driver.findElement(By.xpath("//input[@id='login-email']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@id='login-password']")).sendKeys(password);
        driver.findElement(By.xpath("//button[@class='submit-btn btn']")).click();
    }
    @Test
    public void firstPageContainsQAANet() throws IOException, InterruptedException, AWTException{

    	
    	login("Email","Password");
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                return webDriver.findElement(By.id("header-cart-image")) != null;
            }
        });
        driver.findElement(By.id("feed-product-item-atag")).click();
        
        
       
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                return webDriver.findElement(By.xpath("//div[@id='contest-photostrip']/div[1]/img")) != null;
            }
        });
        WebElement Image =driver.findElement(By.xpath("//div[@id='contest-photostrip']/div[1]/img"));
        Actions action= new Actions(driver);
        action.contextClick(Image).build().perform();
        action.sendKeys(Keys.DOWN);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        Thread.sleep(3000);
        
        robot.keyPress(KeyEvent.VK_ENTER);  
    }
}