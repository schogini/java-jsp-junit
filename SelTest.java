import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class SelTest {
    public static void main(String[] args) {

        WebDriver driver = new HtmlUnitDriver();
        driver.get("TEST_URL");

        // Replace INPUT1, INPUT2 & RESULT - during runtime
        // String t1 = "10";
        // String t2 = "5";
        String res = "TEST_RESULT";


        // WebElement element1 = driver.findElement(By.name("t1"));
        // WebElement element2 = driver.findElement(By.name("t2"));

        // element1.sendKeys(t1);
        // element2.sendKeys(t2);
        // element1.submit();

        // System.out.println("Page title is: " + driver.getTitle());

   		System.out.println(driver.getPageSource());

        if (driver.getPageSource().contains(res)) {
          System.out.println("SUCCESS");
        } else {
          System.out.println("FAILED");
        }

        driver.quit();
    }
}
