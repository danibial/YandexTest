import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MailPage {
    private WebDriver driver;
    private WebDriverWait wait;


    public MailPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 60);
    }


    private By writeButton = By.xpath("//a[@title='Написать (w, c)']");
    private By attachButton = By.xpath("//input[@type='file']");
    private By toWhomField = By.xpath("//div[@class='ComposeRecipients']//div[@class='ComposeRecipients-TopRow']//div[@class='compose-LabelRow']//div[@class='composeYabbles']");
    private By themeField = By.xpath("//input[@name='subject']");
    private By sendButton = By.xpath("//span[text()='Отправить']/parent::*/parent::*/parent::*");
    private By themeName = By.xpath("//span[contains (@class,'Item_subjectW')]/span[contains(@class,'Item_subject')]/span");
    private By refreshButton = By.xpath("//span[@data-params='scroll=true']");
    private By lastMailField = By.xpath("(//div[contains(@data-key,'view=messages-item-inner')]//div[contains(@data-key,'view=messages-item-wrap')])[1]");
    private By attachnment = By.xpath("//div[contains(@class,'AttachmentItem__attachmentItem')]");

    public MailPage writeLetter() {
        // wait.until(ExpectedConditions.elementToBeClickable(writeButton));
        driver.findElement(writeButton).click();
        return this;
    }

    public MailPage attachFile(String filename) {
        driver.findElement(attachButton).sendKeys(filename);
        return this;
    }

    public MailPage writeToWhom(String name) {
        // wait.until(ExpectedConditions.elementToBeClickable(toWhomField));
        driver.findElement(toWhomField).sendKeys(name);
        return this;
    }

    public MailPage writeTheme(String theme) {
        // wait.until(ExpectedConditions.visibilityOfElementLocated(themeField));
        driver.findElement(themeField).sendKeys(theme);
        return this;
    }

    public MailPage sendLetter() {
        driver.findElement(sendButton).click();
        return this;
    }

    public MailPage refresh() {
        // //div [@class='ComposeDoneScreen-Wrapper']
        //WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='ComposeDoneScreen-Wrapper']")));
        driver.findElement(refreshButton).click();
        return this;
    }

    public String findThemeName(String theme) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException interruptedException) {
        }
        String testTheme = null;
        List<WebElement> themeList = driver.findElements(themeName);
        for (WebElement themeName : themeList) {
            System.out.println(themeName.getText());
            if (themeName.getText().equals(theme)) {
                System.out.println("Ебать свершилось! Нашлась " + themeName.getText());
                testTheme = themeName.getText();
            }
        }
        return testTheme;
    }

    public MailPage clickOnLastMail(String theme) {
        List<WebElement> themeList = driver.findElements(themeName);
        for (WebElement themeName : themeList) {
            System.out.println(themeName.getText());
            if (themeName.getText().equals(theme)) {
                themeName.click();
            }
        }
        return this;
    }

    public MailPage enterLastMail() {
        driver.findElement(lastMailField).click();
        return this;
    }

    public boolean checkAttach() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException interruptedException) {
        }
        boolean attach = driver.findElement(attachnment).isDisplayed();

        return attach;
    }

}
