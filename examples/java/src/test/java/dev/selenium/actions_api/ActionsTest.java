package dev.selenium.actions_api;

import dev.selenium.BaseChromeTest;
import org.junit.jupiter.api.Assertions; // junit5
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;

public class ActionsTest extends BaseChromeTest {
    @Test
    public void pause() {
        driver.get("https://www.selenium.dev/selenium/web/mouse_interaction.html");

        long start = System.currentTimeMillis(); // start a time...

        WebElement clickable = driver.findElement(By.id("clickable"));
        new Actions(driver)
                .moveToElement(clickable)
                .pause(Duration.ofSeconds(1))  // give a pause
                .clickAndHold()
                .pause(Duration.ofSeconds(1))
                .sendKeys("abc")
                .perform();  // this code will gonna run now...

        long duration = System.currentTimeMillis() - start; // stop the time  --- performance test... // there are some ready libraries...
        System.out.println(duration);
        Assertions.assertTrue(duration > 2000);
        Assertions.assertTrue(duration < 3000);
    }

    @Test
    public void releasesAll() {
        driver.get("https://www.selenium.dev/selenium/web/mouse_interaction.html");

        WebElement clickable = driver.findElement(By.id("clickable"));
        Actions actions = new Actions(driver);

        actions.clickAndHold(clickable)
                .keyDown(Keys.SHIFT)
                .sendKeys("a") // A
                .perform();

        ((RemoteWebDriver) driver).resetInputState();

        actions.sendKeys("a").perform();

        // Aa

        Assertions.assertEquals("A", String.valueOf(clickable.getAttribute("value").charAt(0)));
        Assertions.assertEquals("a", String.valueOf(clickable.getAttribute("value").charAt(1)));
    }
}
