package suporte;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Web {
    public static final String USERNAME = "franciscomatsuna1";
    public static final String AUTOMATE_KEY = "9tcuAgGxp3UkKFvDy7PW";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";


    public static WebDriver createChrome() {
        System.setProperty("webdriver.chrome.driver", "/home/franciscom/drivers/chromedriver");
        WebDriver navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //navegando para a página do Taskit@!
        navegador.get("http://www.juliodelima.com.br/taskit/");

        return navegador;
    }

    public static WebDriver createBrownserStack() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browser", "Firefox");
        caps.setCapability("browser_version", "73.0 beta");
        caps.setCapability("os", "Windows");
        caps.setCapability("os_version", "10");
        caps.setCapability("resolution", "1024x768");
        caps.setCapability("name", "Bstack-[Java] Sample Test");

        WebDriver navegador =  null;

        try {
            navegador = new RemoteWebDriver(new URL(URL), caps);
            navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            //navegando para a página do Taskit@!
            navegador.get("http://www.juliodelima.com.br/taskit/");

        } catch (MalformedURLException e) {
            System.out.println("Houveram problemas com a URL" + e.getMessage());
        }
            return navegador;
        }
    }