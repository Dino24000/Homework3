
import static org.junit.Assert.*;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Connected_with_Gmail {
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

    private static boolean firstPageContainsQAANet() {

    	String winHandleBefore = driver.getWindowHandle();
        // click search
        driver.findElement(By.xpath("//div[@class='gplus-login-btn btn cronkite']")).click();
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                return webDriver.findElement(By.id("identifierId")) != null;
            }
        });
        driver.findElement(By.id("identifierId")).sendKeys("Gmail Address");
        driver.findElement(By.id("identifierNext")).click();
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                return webDriver.findElement(By.id("password")) != null;
            }
        });
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("passwordofyourgmail");
        driver.findElement(By.id("passwordNext")).click();
        /* if you are new in wish do this
         * wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                return webDriver.findElement(By.id("submit_approve_access")) != null;
            }
        });
        driver.findElement(By.id("submit_approve_access")).click();*/
        driver.switchTo().window(winHandleBefore);
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                return webDriver.findElement(By.id("header-hello")) != null;
            }
        });
        return driver.findElement(By.id("header-hello")).getText().contains("Bonjour");
    }
}