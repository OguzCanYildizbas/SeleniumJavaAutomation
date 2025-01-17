        package day02;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.chrome.ChromeDriver;
        import org.openqa.selenium.By;
        import org.openqa.selenium.support.ui.WebDriverWait;
        import java.time.Duration;
        import org.openqa.selenium.support.ui.ExpectedConditions;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.chrome.ChromeOptions;
        import java.util.Collections; // otomasyon algilama korumasi icin ekledik

        import org.openqa.selenium.JavascriptExecutor;
        import org.testng.Assert;


        public class amazon {
            public static void main(String[] args) {
                String email = System.getenv("EMAIL");
                String password = System.getenv("PASSWORD");
                ChromeOptions options = new ChromeOptions(); // belirli ayarlari yapmamiza olanak tanir
                options.addArguments("--start-maximized"); // tarayici max acilir
                //options.addArguments("--headless"); // tarayici kullaniciya gostermeden arkada calistirir
                //options.addArguments("--proxy-server=http://proxyaddress:port"); // test ederken belirli bir proxy uzerinden etmeye yarar
                options.addArguments("--disable-notifications");// Bildirimleri kapatmak için ChromeOptions
                options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation")); // otomasyon algilama korumasi eklenir
                options.setExperimentalOption("useAutomationExtension", false); // otomasyon algilama korumasi eklenir
                options.addArguments("--disable-gpu"); // daha az kaynak tuketimi icin bazi ozell kapatir
                options.addArguments("--no-sandbox"); // daha az kaynak tuketimi icin bazi ozell kapatir
                options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36"); // belirli bir kullanici araci kullanarak tutarli arayuz saglamaya yarar

                WebDriver driver = new ChromeDriver(options); // WebDriver nesnesi oluştur
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // WebDriverWait oluştur
                //driver.manage().deleteAllCookies(); // cookieleri siler
                driver.get("https://www.amazon.de/?&tag=hydraamazon09-21&ref=pd_sl_781ozcfkw7_e&adgrpid=153217435865&hvpone=&hvptwo=&hvadid=674893335740&hvpos=&hvnetw=g&hvrand=15356225811606228054&hvqmt=e&hvdev=c&hvdvcmdl=&hvlocint=&hvlocphy=9196965&hvtargid=kwd-10573980&hydadcr=12763_2327837"); // siteye git

                // cerez cikarsa kabule tikla yoksa devam et burda bir degiskene attik pathi
                try {
                    WebElement cookieAcceptButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("sp-cc-accept")));
                    cookieAcceptButton.click();
                    System.out.println("Çerez onayı kabul edildi.");
                } catch (Exception e) {
                    System.out.println("Çerez onay butonu görünmüyor, devam ediliyor...");
                }

                WebElement searchBox = driver.findElement(By.xpath("//input[@id=\"twotabsearchtextbox\"]"));// arama kutusunu bul ve tikla
                searchBox.click();

                if (searchBox.equals(driver.switchTo().activeElement())) {
                    System.out.println("Arama kutusuna tıklanıldı ve aktif durumda.");
                } else {
                    System.out.println("Arama kutusu tıklanamadı veya aktif değil.");
                }
                searchBox.sendKeys("laptop"); // arama kutusuna yazildi
                String enteredText = searchBox.getAttribute("value"); // girilin yaziyi kont icin string olarak degisken tanimla

                if (enteredText.equals(" ")) {
                    System.out.println("Arama kutusuna yazılmadi.");
                } else {
                    System.out.println(String.format("Arama kutusuna '%s' yazıldı.", enteredText));
                }

                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id=\"nav-search-submit-button\"]"))).click(); // arama kutusuna basildi
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\"a-dropdown-prompt\"]"))).click(); // sirala kutusuna basildi
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id=\"s-result-sort-select_2\"]"))).click(); // yuksekten alcaga siralandi
                String firstProduct = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@aria-label='PcCom Revolt 4070 Laptop 17,3 Zoll 32 GB RAM 1 TB SSD Nvidia Geforce RTX 4070']"))).getText(); // ilk urununun bulundugu noktayi vererek ilk urunuj adini cektik
                System.out.println("Görüntülenen ilk ürün: " + firstProduct); // urunun adini bastik

                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'In den Einkaufswagen')]"))).click(); // add to basket
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),\"Einkaufswagen\")]"))).click(); // go to basket

                try {
                    Thread.sleep(1000); // 1 saniye bekle
                } catch (InterruptedException e) {
                    System.out.println("Bekleme işlemi kesintiye uğradı: " + e.getMessage());
                }

                String lastProductControl = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'PcCom Revolt 4070')]"))).getAttribute("textContent"); // daha genel bir eslesmeyle ve getattribute ile icerigi aldik bazen get text vermiyor
                System.out.println("Last Product in Cart: " + lastProductControl); // print the product in the basket

                try {
                    Thread.sleep(1000); // 1 saniye bekle
                } catch (InterruptedException e) {
                    System.out.println("Bekleme işlemi kesintiye uğradı: " + e.getMessage());
                }

                if (lastProductControl.equals(firstProduct)) { // check if the first product equals the last product
                    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@data-feature-id=\"proceed-to-checkout-action\"]"))).click(); // if it is ok go to checkout
                } else {
                    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@data-a-selector=\"decrement-icon\"]"))).click(); // if it is not ok clear the basket
                    WebElement searchBox2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id=\"twotabsearchtextbox\"]"))); // go to searchbox to find new product
                    searchBox2.click();
                    searchBox2.sendKeys("laptop");
                }


                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[contains(@id, 'ap_email')]"))).sendKeys(email);
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@aria-labelledby=\"continue-announce\"]"))).click(); // enter the customer mail
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[contains(@id, 'ap_password')]"))).sendKeys(password);
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id=\"signInSubmit\"]"))).click(); // enter the customer password

                driver.quit();

            }
        }