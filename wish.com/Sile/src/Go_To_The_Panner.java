
import static org.junit.Assert.*;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Go_To_The_Panner {
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

        login("email","password");
        
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                return webDriver.findElement(By.id("header-cart-image")) != null;
            }
        });
        driver.findElement(By.id("header-cart-image")).click();
       
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                return webDriver.findElement(By.xpath("//span[@class='left']")) != null;
            }
        });
        String k = driver.findElement( By.xpath("//div[@id='order-summary']/h2")).getText()+"\n"+
        		driver.findElement(By.xpath("//div[@class='shipping-summary-col-1']")).getText()+" "+driver.findElement(By.xpath("//div[@class='shipping-summary-col-2']")).getText()+"\n"+
        		driver.findElement(By.xpath("//div[@class='billing-summary-col-1']")).getText()+" "+driver.findElement(By.xpath("//div[@class='billing-summary-col-2']")).getText()+"\n"+
        		driver.findElement(By.xpath("//div[@id='order-total']/span[@class='left']")).getText()+" "+driver.findElement(By.xpath("//div[@id='order-total']/span[@class='right']")).getText();
      System.out.println(k); 
        return driver.findElement(By.xpath("//div[@id='order-total']/span[@class='right']")).getText().contains("HUF");
    }
}