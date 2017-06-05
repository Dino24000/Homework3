

import static org.junit.Assert.*;
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
public class Search_And_Buy {
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

    private static boolean firstPageContainsQAANet() {

        login("Email","Password");
        
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                return webDriver.findElement(By.id("header-cart-image")) != null;
            }
        });
        driver.findElement(By.id("nav-search")).sendKeys("Nike");
        driver.findElement(By.id("nav-search-btn")).click();
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                return webDriver.findElement(By.id("feed-product-item-atag")) != null;
            }
        });    
        
        driver.findElement(By.id("feed-product-item-atag")).click();
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                return webDriver.findElement(By.xpath("//div[@class='fancy-select-label']")) != null;
            }
        });
        String a = driver.findElement(By.id("product-name")).getText();
        driver.findElement(By.xpath("//div[@class='fancy-select-label']")).click();
        driver.findElement(By.xpath("//li[@class='fancy-select-option ']")).click();
        driver.findElement(By.xpath("//div[@class='variant-section']/div[2]")).click();
        driver.findElement(By.xpath("//ul[@id='fancy-select-options-color']/li[2]")).click();
        driver.findElement(By.xpath("//div[@class='join-buy-button clickable']")).click();
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                return webDriver.findElement(By.xpath("//div[@class='product-name']")) != null;
            }
        });
        System.out.println("you are buy : " + a);
        return driver.findElement(By.xpath("//div[@class='product-name']")).getText().contains(a);
    }
}
