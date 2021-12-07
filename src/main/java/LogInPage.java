import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogInPage {
    private WebDriver driver;

    public LogInPage(WebDriver driver){
        this.driver=driver;
    }

    private By emailField = By.xpath("//input[@id='passp-field-login']");
    private By enterButton = By.xpath("//button[@type='submit']");
    private By passwordField = By.xpath("//input[@name='passwd']");

    public LogInPage typeEmail(String email){
        driver.findElement(emailField).sendKeys(email);
        return this;
    }

    public LogInPage clickEnterFirst(){
        driver.findElement(enterButton).click();
        return this;
    }

    public LogInPage typePassword(String password){
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    public StartPage clickEnterSecond(){
        driver.findElement(enterButton).click();
        return new StartPage(driver);
    }

}

