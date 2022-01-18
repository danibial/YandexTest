import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MainClassTest {

    private  WebDriver driver;
    private  StartPage startPage;



    String mail = "iqwidan@yandex.by";
    String password = "7O1135";
    String fileName = "C:\\Users\\- H P -\\Desktop\\Эскобар.jpg";
    static double themeRandom = Math.random();
    static String theme = Double.toString(themeRandom);

    @Before
    public  void setUp(){
        System.setProperty("webdriver.gecko.driver", "c:\\Users\\- H P -\\IdeaProjects\\Test\\drivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://yandex.by/");

        startPage = new StartPage(driver);
    }

    @Test
    public void test1(){
        LogInPage logInPage = startPage.clickLogIn();
        logInPage.typeEmail(mail);
        logInPage.clickEnterFirst();
        logInPage.typePassword(password);
        logInPage.clickEnterSecond();
        MailPage mailPage = startPage.mailClick();

        mailPage.writeLetter();
        mailPage.attachFile(fileName);
        mailPage.writeTheme(theme);
        mailPage.writeToWhom(mail);
        mailPage.sendLetter();
        mailPage.refresh();
        String testTheme = mailPage.findThemeName(theme);
        Assert.assertEquals(theme,testTheme);



    }

    @Test
    public void test2(){
        LogInPage logInPage = startPage.clickLogIn();
        logInPage.typeEmail(mail);
        logInPage.clickEnterFirst();
        logInPage.typePassword(password);
        logInPage.clickEnterSecond();
        MailPage mailPage = startPage.mailClick();

        mailPage.clickOnLastMail(theme);
        mailPage.enterLastMail();
        boolean attach = mailPage.checkAttach();
        Assert.assertTrue(attach);

    }

   @After
    public void setDown(){
        driver.quit();
    }

}
