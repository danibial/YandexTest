import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StartPage {
    private WebDriver driver;

    public StartPage (WebDriver driver){
        this.driver = driver;
    }

    private By signUp = By.xpath("//div[@aria-label='Авторизация']//div[text()='Войти']");
    private By mailEnter = By.xpath("//div[@aria-label='Уведомления']//div[@class='desk-notif-card__details']//div[text()='Почта']");
    private By diskButton = By.xpath("//a [@rel='noopener' and text()='Диск']");
    public LogInPage clickLogIn(){
        driver.findElement(signUp).click();
        return new LogInPage(driver);
    }

    public MailPage mailClick(){
        driver.findElement(mailEnter).click();
         for (String tab: driver.getWindowHandles()) {
             driver.switchTo().window(tab);
         }
        return new MailPage(driver);
    }

    public DiskPage diskClick(){
        driver.findElement(diskButton).click();
        for (String tab: driver.getWindowHandles()) {
            driver.switchTo().window(tab);
        }
        return new DiskPage(driver);
    }
}
