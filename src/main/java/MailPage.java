import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MailPage {
    private WebDriver driver;

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

    public MailPage writeTeme(String theme){
        driver.findElement(themeField).sendKeys(theme);
        return this;
    }

    public MailPage sendLetter(){
        driver.findElement(sendButton).click();
        return this;
    }


}
