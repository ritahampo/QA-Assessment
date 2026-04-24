package com.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class LoginTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ✅ 1. Valid Login
    @Test(priority=1)
    public void validLoginTest() {
        driver.findElement(By.name("user-name")).sendKeys("standard_user");
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
        driver.findElement(By.name("login-button")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inventory_container")));

        Assert.assertTrue(
                driver.findElement(By.id("inventory_container")).isDisplayed(),
                "Login failed"
        );
    }

    // ❌ 2. Invalid Login
    @Test(priority=2)
    public void invalidLoginTest() {
        driver.findElement(By.name("user-name")).sendKeys("wrong_user");
        driver.findElement(By.name("password")).sendKeys("wrong_pass");
        driver.findElement(By.name("login-button")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3[data-test='error']")));

        Assert.assertTrue(
                driver.findElement(By.cssSelector("h3[data-test='error']")).isDisplayed(),
                "Error message not displayed"
        );
    }

    // 🛒 3. Cannot Add Same Product Twice
    @Test(priority=3)
    public void cannotAddSameProductTwice() {

        // Login first
        validLoginTest();

        // Add product
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

        // Wait for cart badge
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("shopping_cart_badge")));

        // Assert add button is gone
        Assert.assertTrue(
                driver.findElements(By.id("add-to-cart-sauce-labs-backpack")).isEmpty(),
                "Product can be added twice"
        );

        // Assert cart count is 1
        String count = driver.findElement(By.className("shopping_cart_badge")).getText();
        Assert.assertEquals(count, "1", "Duplicate product not added");
    }

    // ⚠️ 4. Last Name Field Not Accepting InputTest (Checkout)
    @Test(priority=4)
    public void lastNameFieldTest() {

        // Login + add product
        validLoginTest();
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

        // Go to cart
        driver.findElement(By.className("shopping_cart_link")).click();

        // Checkout
        driver.findElement(By.id("checkout")).click();

        // Wait for checkout page
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("last-name")));

        // Try to enter last name
        WebElement lastName = driver.findElement(By.id("last-name"));
        lastName.sendKeys("Test");

        // Assert input was accepted
        Assert.assertEquals(
                lastName.getAttribute("value"),
                "Test",
                "Last name field is not accepting input"
        );
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}