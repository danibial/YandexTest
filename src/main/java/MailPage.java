import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MailPage {
    private WebDriver driver;

    WebDriverWait wait = new WebDriverWait(this.driver, 10);

    public MailPage(WebDriver driver){
        this.driver = driver;
    }


    private By writeButton = By.xpath("//a[@title='Написать (w, c)']");
    private By attachButton = By.xpath("//input[@type='file']");
    private By toWhomField = By.xpath("//div[@class='ComposeRecipients']//div[@class='ComposeRecipients-TopRow']//div[@class='compose-LabelRow']//div[@class='composeYabbles']");
    private By themeField = By.xpath("//input[@name='subject']");
    private By sendButton = By.xpath("//span[text()='Отправить']/parent::*/parent::*/parent::*");

    public MailPage writeLetter(){
        driver.findElement(writeButton).click();
        return this;
    }

    public MailPage attachFile(String filename){
        driver.findElement(attachButton).sendKeys(filename);
        return this;
    }

    public MailPage writeToWhom(String name){
        driver.findElement(toWhomField).sendKeys(name);
        return this;
    }

    public MailPage writeTheme(String theme){
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(themeField));
        driver.findElement((By) element).sendKeys(theme);
        return this;
    }

    public MailPage sendLetter(){
        driver.findElement(sendButton).click();
        return this;
    }


}
