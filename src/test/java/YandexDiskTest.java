import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class YandexDiskTest {
    private WebDriver driver;
    private StartPage startPage;
    private DiskPage diskPage;

    boolean checkFiles;
    boolean checkSpace;
    String mail = "iqwidan@yandex.by";
    String password = "7O1135";
    String fileName = "C:\\Users\\- H P -\\Desktop\\Эскобар.jpg";


    @Before
    public void setup(){
        //System.setProperty("webdriver.gecko.driver", "c:\\Users\\- H P -\\IdeaProjects\\Test\\drivers\\geckodriver.exe");
        //driver = new FirefoxDriver();
        System.setProperty("webdriver.chrome.driver", "c:\\Users\\- H P -\\IdeaProjects\\Test\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://yandex.by/");
        startPage = new StartPage(driver);

        LogInPage logInPage = startPage.clickLogIn();
        logInPage.typeEmail(mail);
        logInPage.clickEnterFirst();
        logInPage.typePassword(password);
        logInPage.clickEnterSecond();
        diskPage = startPage.diskClick();
       // diskPage.clickFilesButton();
        checkFiles = diskPage.checkElements();
        if (checkFiles == false){
            diskPage.deleteElements();
            diskPage.checkElements();
        }
    }
    @Test
    public void test(){
        diskPage.uploadFile(fileName);
        diskPage.closeUploadWindow();
        diskPage.closeUploadProgressWindow();
        diskPage.deleteDragAndDrop();
        diskPage.checkElements();

        BasketPage basketPage = diskPage.enterBasket();
        basketPage.cleanTrash();
        basketPage.confirmClean();
        checkSpace = basketPage.checkInfoSpace();
        if(checkSpace == true){
            basketPage.clickFilesButton();// необходимо ли тут снова инициализировать diskPage???
        }
        diskPage.uploadFile(fileName);
        diskPage.closeUploadProgressWindow();
        checkFiles = diskPage.checkElements();
        Assert.assertFalse(checkFiles);
    }
}
