import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasketPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public BasketPage(WebDriver driver){
        this.driver=driver;
        wait = new WebDriverWait(driver,60);
    }

    private By cleanTrashButton =  By.xpath("//button[contains(@class,'clean-trash-button')]");
    private By infoSpace = By.xpath("//div[@class='InfoSpace__Text']");
    private By filesButton=By.xpath("//a[@id='/disk']");
    private By confirmCleanButton = By.xpath("//button[contains(@class,'dialog__button_submit')]");

    public BasketPage cleanTrash(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(filesButton));
        driver.findElement(cleanTrashButton).click();
        return this;
    }

    public BasketPage confirmClean(){
        driver.findElement(confirmCleanButton).click();
        return this;
    }

    public boolean checkInfoSpace(){
        wait.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(cleanTrashButton)));
        String space = driver.findElement(infoSpace).getText();
        if(space.contains("Свободно 10 ГБ из 10 ГБ")){
            System.out.println("Корзина пуста!");
            return true;
        }else{
            System.out.println("В корзине есть лишние файлы!");
            return false;
        }
    }

    public DiskPage clickFilesButton(){
        wait.until(ExpectedConditions.elementToBeClickable(filesButton));
        driver.findElement(filesButton).click();
        return new DiskPage(driver);
    }
}
