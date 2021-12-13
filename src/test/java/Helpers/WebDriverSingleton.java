package Helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverSingleton{
    public static class WebBrowser {
        private static WebDriver instance;

        private WebBrowser() { }

        public static WebDriver getInstance() {
            if (instance==null) {
                instance = new FirefoxDriver();
                System.out.println("get instance FirefoxDriver()");
            }
            return instance;
        }

        public void close() {
            System.out.println("close ");
            if (instance!=null) {
                instance.quit();
                instance = null;
            }
            else
                System.out.println("null error at close()");
        }
    }
}
