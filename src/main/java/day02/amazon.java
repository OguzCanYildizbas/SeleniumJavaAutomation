        package day02;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.chrome.ChromeDriver;
        import org.openqa.selenium.By;
        import org.openqa.selenium.support.ui.WebDriverWait;
        import java.time.Duration;
        import org.openqa.selenium.support.ui.ExpectedConditions;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.chrome.ChromeOptions;
        import java.util.Collections; 

        import org.openqa.selenium.JavascriptExecutor;
        import org.testng.Assert;


        public class amazon {
            public static void main(String[] args) {
                String email = System.getenv("EMAIL");
                String password = System.getenv("PASSWORD");
                ChromeOptions options = new ChromeOptions(); 
                options.addArguments("--start-maximized"); 
                //options.addArguments("--headless"); 
                //options.addArguments("--proxy-server=http://proxyaddress:port"); 
                options.addArguments("--disable-notifications");
                options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation")); 
                options.setExperimentalOption("useAutomationExtension", false); 
                options.addArguments("--disable-gpu"); 
                options.addArguments("--no-sandbox"); 
                options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36"); 

                WebDriver driver = new ChromeDriver(options);
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
                //driver.manage().deleteAllCookies(); 
                driver.get("https://www.amazon.de/?&tag=hydraamazon09-21&ref=pd_sl_781ozcfkw7_e&adgrpid=153217435865&hvpone=&hvptwo=&hvadid=674893335740&hvpos=&hvnetw=g&hvrand=15356225811606228054&hvqmt=e&hvdev=c&hvdvcmdl=&hvlocint=&hvlocphy=9196965&hvtargid=kwd-10573980&hydadcr=12763_2327837"); 

                
                try {
                    WebElement cookieAcceptButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("sp-cc-accept")));
                    cookieAcceptButton.click();
                    System.out.println("Çerez onayı kabul edildi.");
                } catch (Exception e) {
                    System.out.println("Çerez onay butonu görünmüyor, devam ediliyor...");
                }

                WebElement searchBox = driver.findElement(By.xpath("//input[@id=\"twotabsearchtextbox\"]"));
                searchBox.click();

                if (searchBox.equals(driver.switchTo().activeElement())) {
                    System.out.println("Arama kutusuna tıklanıldı ve aktif durumda.");
                } else {
                    System.out.println("Arama kutusu tıklanamadı veya aktif değil.");
                }
                searchBox.sendKeys("laptop"); 
                String enteredText = searchBox.getAttribute("value"); 

                if (enteredText.equals(" ")) {
                    System.out.println("Arama kutusuna yazılmadi.");
                } else {
                    System.out.println(String.format("Arama kutusuna '%s' yazıldı.", enteredText));
                }

                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id=\"nav-search-submit-button\"]"))).click(); 
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\"a-dropdown-prompt\"]"))).click(); 
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id=\"s-result-sort-select_2\"]"))).click(); 
                String firstProduct = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@aria-label='PcCom Revolt 4070 Laptop 17,3 Zoll 32 GB RAM 1 TB SSD Nvidia Geforce RTX 4070']"))).getText(); 
                System.out.println("Görüntülenen ilk ürün: " + firstProduct); 

                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'In den Einkaufswagen')]"))).click(); 
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),\"Einkaufswagen\")]"))).click(); 
                try {
                    Thread.sleep(1000); e
                } catch (InterruptedException e) {
                    System.out.println("Bekleme işlemi kesintiye uğradı: " + e.getMessage());
                }

                String lastProductControl = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'PcCom Revolt 4070')]"))).getAttribute("textContent"); 
                System.out.println("Last Product in Cart: " + lastProductControl); 

                try {
                    Thread.sleep(1000); 
                } catch (InterruptedException e) {
                    System.out.println("Bekleme işlemi kesintiye uğradı: " + e.getMessage());
                }

                if (lastProductControl.equals(firstProduct)) { 
                    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@data-feature-id=\"proceed-to-checkout-action\"]"))).click(); 
                } else {
                    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@data-a-selector=\"decrement-icon\"]"))).click(); 
                    WebElement searchBox2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id=\"twotabsearchtextbox\"]"))); 
                    searchBox2.click();
                    searchBox2.sendKeys("laptop");
                }


                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[contains(@id, 'ap_email')]"))).sendKeys(email);
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@aria-labelledby=\"continue-announce\"]"))).click(); 
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[contains(@id, 'ap_password')]"))).sendKeys(password);
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id=\"signInSubmit\"]"))).click(); 
                driver.quit();

            }
        }
