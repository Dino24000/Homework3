
import static org.junit.Assert.*;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

/*   Change Language To French
 * */
public class LogOut {
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
    private static void login(String email,String password){
    	driver.findElement(By.xpath("//div[@class='email-login-btn btn']")).click();
        driver.findElement(By.xpath("//input[@id='login-email']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@id='login-password']")).sendKeys(password);
        driver.findElement(By.xpath("//button[@class='submit-btn btn']")).click();
    }

    private static boolean firstPageContainsQAANet() {

        login("Email","Password");
        
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                return webDriver.findElement(By.id("header-cart-image")) != null;
            }
        });
        new Actions(driver).moveToElement(driver.findElement(By.xpath("//div[@id='header-account-info']/div/a/div[2]"))).build().perform() ;
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                return webDriver.findElement(By.xpath("//div[@class='logout-icon']")) != null;
            }
        });
        
        driver.findElement(By.xpath("//div[@class='logout-icon']")).click();
        
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                return webDriver.findElement(By.xpath("//div[@class='title']")) != null;
            }
        });
        
        
        return driver.findElement(By.xpath("//div[@class='title']")).getText().contains("Inscrivez-vous pour voir nos produits !");
    }
}