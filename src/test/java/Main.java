import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;



public class Main {


    WebDriver driver;
    Actions actions;

    @BeforeClass
    public void setUp() {
        // Initialize WebDriver and Actions class
        driver = new ChromeDriver();
        actions = new Actions(driver);
        driver.get("https://demoqa.com/buttons");
        driver.manage().window().maximize();
        WebElement element = driver.findElement(By.id("doubleClickBtn"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

        // Double-click the button
        // Locate the button element by its id and do a right-click
        @Test
        public void testDoubleClickButton() {
            WebElement btnElement = driver.findElement(By.xpath("//button[@id='doubleClickBtn']"));
            actions.doubleClick(btnElement).perform();
            String actRes = driver.findElement(By.xpath("//p[@id='doubleClickMessage']")).getText();
            String expectedResult = "You have done a double click";
            // Verifying that the displayed message is same as the expected message
            Assert.assertEquals(actRes, expectedResult);
        }

        @Test
        public void testRightClickButton() {
            // Locate the button element by its id and do a right-click
            WebElement btnElement2 = driver.findElement(By.id("rightClickBtn"));
            actions.contextClick(btnElement2).perform();
            String doubleText = driver.findElement(By.cssSelector("p#rightClickMessage")).getText();
            String expectResult = doubleText;
            // Verifying that the displayed message is same as the expected message
            Assert.assertEquals(doubleText, expectResult);
        }

    @Test
    public void testDynamicClickButton() {
        //Locating Dynamic click button,scrolling to it,waiting to be visible and clicking on it
        WebElement element = driver.findElement(By.xpath("//button[contains(@class, 'btn-primary')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Click Me']"))).click();
        String dynamicText = driver.findElement(By.id("doubleClickMessage")).getText();
        String expectedDynamicText = "You have done a double click";
        Assert.assertEquals(expectedDynamicText, dynamicText);


    }

}
