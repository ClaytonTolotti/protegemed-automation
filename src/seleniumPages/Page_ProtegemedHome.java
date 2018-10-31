package seleniumPages;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import common.Page_BasePage;
 
public class Page_ProtegemedHome extends Page_BasePage {
	
	private static Actions actions;
	private static WebElement webElement;
	private static final String file ="/opt/apache-tomcat-9.0.12/logs/drools-application.txt";
	
	public void abrirBrowser() {
		if (System.getProperty("os.name").equals("Linux")) {
			System.setProperty("webdriver.chrome.driver", 
					"/home/clayton/Downloads/chromedriver_linux64/chromedriver");	
		}
		driver = new ChromeDriver();
		actions = new Actions(driver);
	}
	
	public void openProtegemedHomeURL() {
		driver.get("http://localhost/qualidadesoftware/index.html");
	}
		
	public void verificarElementosDeInput() {
		if(driver.findElement(By.id("type")).isEnabled()
				&& driver.findElement(By.id("outlet")).isEnabled()
				&& driver.findElement(By.id("rfid")).isEnabled()
				&& driver.findElement(By.id("offset")).isEnabled()
				&& driver.findElement(By.id("gain")).isEnabled()
				&& driver.findElement(By.id("rms")).isEnabled()
				&& driver.findElement(By.id("mv")).isEnabled()
				&& driver.findElement(By.id("mv2")).isEnabled()
				&& driver.findElement(By.id("under")).isEnabled()
				&& driver.findElement(By.id("over")).isEnabled()
				&& driver.findElement(By.id("duration")).isEnabled()
				&& driver.findElement(By.id("sin")).isEnabled()
				&& driver.findElement(By.id("cos")).isEnabled()) {
			System.out.println("Search text box is displayed");
		} else {
			System.out.println("Search text box is NOT displayed");
		}
	}
	
	public void setValoresEventos() {
		driver.findElement(By.id("type")).sendKeys("01");
		driver.findElement(By.id("outlet")).sendKeys("01");
		driver.findElement(By.id("rfid")).sendKeys("FFFF0002");
		driver.findElement(By.id("offset")).sendKeys("2093");
		driver.findElement(By.id("gain")).sendKeys("444E6B09");
		driver.findElement(By.id("rms")).sendKeys("3FA277C5");
		driver.findElement(By.id("mv")).sendKeys("00000000");
		driver.findElement(By.id("mv2")).sendKeys("00000000");
		driver.findElement(By.id("under")).sendKeys("0000");
		driver.findElement(By.id("over")).sendKeys("0000");
		driver.findElement(By.id("duration")).sendKeys("0000");
		driver.findElement(By.id("sin")).sendKeys("44A74464;3BC232764E;3BC02F3D9A;3BC12780FC;3B42618EF4;3BC1CC261E;3BC21668A5;3BC0E1178A;3BC164BAB6;3BC0A07318;3BC10AEC48;3BC095ACE4");
		driver.findElement(By.id("cos")).sendKeys("C41D1918;3B418A1530;3B41B06ECC;3B40F0FE2A;3B42321A4A;3BC0C2D6BE;3BBE3880E4;3BC01D72A3;3BC12024DE;3B3FF28A7C;3BC05C0DE6;3B4014D9FD");
	}
	
	public void checkImFeelingLuckyButtonIsDisplayed() {
		driver.findElement(By.id("enviar")).click();
	}
	
	public void openFileLogFrequencia() throws FileNotFoundException, IOException {
		driver.findElement(By.id("fileInput")).sendKeys(file);
		webElement = driver.findElement(By.id("fileDisplayArea"));
	}
	
	public void openFileLogCorrente() throws FileNotFoundException, IOException {
		driver.findElement(By.id("fileInput")).sendKeys(file);
		webElement = driver.findElement(By.id("fileDisplayArea"));
	}
}