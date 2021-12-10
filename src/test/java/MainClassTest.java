import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class MainClassTest {

    private WebDriver driver;
    private StartPage startPage;
    private MailPage mailPage;

    String mail = "iqwidan@yandex.by";
    String password = "7O1135";
    String fileName = "C:\\Users\\- H P -\\Desktop\\Эскобар.jpg";
    String theme = "Test";

    @Before
    public void setUp(){
        System.setProperty("webdriver.gecko.driver", "c:\\Users\\- H P -\\IdeaProjects\\Test\\drivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://yandex.by/");

        startPage = new StartPage(driver);

    }

    @Test
    public void signUp(){
        LogInPage logInPage = startPage.clickLogIn();
        logInPage.typeEmail(mail);
        logInPage.clickEnterFirst();
        logInPage.typePassword(password);
        logInPage.clickEnterSecond();

        MailPage mailPage = startPage.mailClick();
        mailPage.writeLetter();
        mailPage.attachFile(fileName);
        mailPage.writeToWhom(mail);
        mailPage.writeTheme(theme);
        mailPage.sendLetter();
    }

    @After
    public void setDown(){
        driver.close();
    }

}
