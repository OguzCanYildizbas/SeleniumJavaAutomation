package day01;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;


public class FirstProject {

    public static void main(String[] args) {

        // WebDriver nesnesi oluştur
        WebDriver driver = new ChromeDriver();

        // Bir web sayfası aç
        driver.get("https://www.amazon.com/");

    }
}
