
import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


public class GoBack {
    static WebDriver driver;
    static Wait<WebDriver> wait;

    @Test
    public void mainTest() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/DINO/Downloads/chromedriver_win32/chromedriver.exe");
        driver = new ChromeDriver();
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
    private static boolean firstPageContainsQAANet() throws InterruptedException, AWTException  {

    	
        login("email","password");
        
        
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                return webDriver.findElement(By.id("header-cart-image")) != null;
            }
        });
        String Name=driver.findElement(By.id("header-hello")).getText();
        driver.findElement(By.id("feed-product-item-atag")).click();
        
        
        
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                return webDriver.findElement(By.xpath("//div[@id='contest-photostrip']/div[1]/img")) != null;
            }
        });
        driver.navigate().back();

        
       
       wait.until(new ExpectedCondition<Boolean>() {
           public Boolean apply(WebDriver webDriver) {
               return webDriver.findElement(By.id("header-hello")) != null;
           }
       });
       Thread.sleep(3000);
        
        
        return driver.findElement(By.id("header-hello")).getText().contains(Name);
    }
}