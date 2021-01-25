package selenium;
import utils.DataManager;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;


public class Sincronizacion {

	public static void main(String[] args) throws InterruptedException {
	         DataManager dato = new DataManager();
		 String usuario_valor = dato.getDato("usuario", 0);
		 String usuario_id = "//*[@id='user-name']";
		 String contrasenna =dato.getDato("pass", 0);		
	         //Obtenemos el dato que se encuentra en la primera posicion de la columna nombre..........
	    
        //Precondiciones
        System.setProperty("webdriver.chrome.driver", "./src/main/java/drivers/chromedriver.exe");
	WebDriver driver = new ChromeDriver(); 
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        //Accediendo a la p�gina
        //Vamos a utilizar este recurso donde vamos a poder utilizar una pagina adicional

        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.xpath(usuario_id)).sendKeys(usuario_valor);
        driver.findElement(By.xpath("//*[@id='password']")).sendKeys(contrasenna);
        driver.findElement(By.xpath("//*[@id='login-button']")).click();
         //Maximizando la pantalla
        driver.manage().window().maximize();         
        driver.quit();
    }
    
}