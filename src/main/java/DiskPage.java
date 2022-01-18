import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;
import java.sql.SQLOutput;
import java.util.List;

public class DiskPage {
    private WebDriver driver;

    public DiskPage(WebDriver driver){
        this.driver=driver;
    }

    private By filesButton=By.xpath("//a[@id='/disk']");
    private By files = By.xpath("//div[contains(@class,'listing-item_type_file')]");
    private By deliteFile = By.xpath("//div[@value='delete']");
    private By uploadButton = By.xpath("//input[@title='Загрузить файлы']");
    private By basket = By.xpath("//div[contains(@class,'listing-item_type_dir')]");
    private By closeUploadWindowButton = By.xpath("//button[contains(@class,'uploader-promo-notification__close-button')]");
    private By closeUploadProgressWindowButton = By.xpath("//button[contains(@class,'uploader-progress__close-button')]");

    public DiskPage clickFilesButton(){
        driver.findElement(filesButton).click();
        return this;
    }

    public boolean checkElements(){
        List<WebElement> allFiles = driver.findElements(files);

        if (allFiles.size()==0){
            System.out.println("Файлов нет!");
            return true;
        } else {
            System.out.println("Есть лишние файлы!");
            return false;
        }
    }

    public DiskPage deleteElements(){
        List<WebElement> allFiles = driver.findElements(files);
        Actions actions = new Actions(driver);
        for (WebElement file : allFiles) {
            if(allFiles.size()==0){
               break;
            } else{
                    actions.contextClick(file).perform();
                    driver.findElement(deliteFile).click();
            }
        }
        return this;
    }

    public DiskPage uploadFile(String fileName){
        driver.findElement(uploadButton).sendKeys(fileName);
        return this;
    }

    public DiskPage deleteDragAndDrop(){
        List<WebElement> allFiles = driver.findElements(files);
        Actions actions = new Actions(driver);
        for (WebElement file : allFiles) {
            //if (!file.getText().contains("Корзина")) {
                actions.dragAndDrop(file,driver.findElement(basket)).build().perform();
            //}
        }
        return this;
    }

    public BasketPage enterBasket(){
        Actions actions = new Actions(driver);
        actions.doubleClick(driver.findElement(basket)).build().perform();

        return new BasketPage(driver);
    }

    public DiskPage closeUploadWindow(){
        driver.findElement(closeUploadWindowButton).click();
        return this;
    }

    public DiskPage closeUploadProgressWindow(){
        driver.findElement(closeUploadProgressWindowButton).click();
        return this;
    }
}
