
import static org.junit.Assert.*;

import java.util.List;

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
public class Change_Currency {
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

        login("email","password");
        
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
        
        driver.findElement(By.xpath("//div[@class='settings-icon']")).click();
        
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                return webDriver.findElement(By.id("settings-currency")) != null;
            }
        });
        boolean tr=false;
        int k =1;
        for (int i = 1; i < 30&&!tr; i++) {
        	if (driver.findElement(By.xpath("//select[@id='settings-currency']/option["+i+"]")).getAttribute("value")=="EUR"){
        		k=i;
        		tr=true;}
		}
        for (int i = 1; i < 100&&!tr; i++) {
        	if (driver.findElement(By.xpath("//select[@id='settings-currency']/option["+i+"]")).getAttribute("selected")!=null){
        		System.out.println("Current currency is : "+driver.findElement(By.xpath("//select[@id='settings-currency']/option["+i+"]")).getText()+" and it will be EUR");
        		tr=true;}
		}
        driver.findElement(By.xpath("//select[@id='settings-currency']/option["+k+"]")).click();
        
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                return webDriver.findElement(By.id("settings-currency")) != null;
            }
        });
        
        return driver.findElement(By.xpath("//select[@id='settings-currency']/option["+k+"]")).getAttribute("selected")!=null;
    }
}