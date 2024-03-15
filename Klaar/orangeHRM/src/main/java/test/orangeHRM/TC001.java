package test.orangeHRM;

import java.awt.JobAttributes;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import javax.swing.Popup;

import org.bouncycastle.oer.its.ieee1609dot2.VerificationKeyIndicator;
import org.bouncycastle.pkcs.jcajce.JcaPKCS10CertificationRequest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v120.debugger.model.Location;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.annotation.JacksonInject.Value;
import com.github.dockerjava.api.command.SaveImageCmd;

import io.opentelemetry.sdk.metrics.data.Data;
import net.bytebuddy.asm.Advice.AllArguments;

public class TC001 {

	public static void main(String[] args) throws IOException, InterruptedException {

		FileInputStream fis = new FileInputStream(
				"C:\\Users\\USER\\eclipse-workspace\\orangeHRM\\src\\test\\resources\\Config.properties");
		Properties p = new Properties();
		p.load(fis);
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://klaaradmin-trials711.orangehrmlive.com/client/#/dashboard");
		wait.until(ExpectedConditions.titleIs("OrangeHRM"));
		if (!driver.getTitle().equals("OrangeHRM")) {
			System.out.println("Wrong page title");
		}
		driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("SyN6Ktl@O0");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.findElement(By.xpath("//li[@id='left_menu_item_11']//a[1]//span[1]")).click();
		driver.findElement(By.xpath("//i[normalize-space()='add']")).click();
		driver.findElement(By.xpath("//input[@id=\"first-name-box\"]")).sendKeys(p.getProperty("firstName"));
		driver.findElement(By.xpath("//input[@id=\"last-name-box\"]")).sendKeys(p.getProperty("lastName"));
		driver.findElement(By.xpath("(//div[@class='custom-control custom-switch'])[1]")).click();
		driver.findElement(By.xpath("//input[@id=\"employeeId\"]")).sendKeys(p.getProperty("employeeId"));
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		driver.findElement(By.id("joinedDate")).clear();
		driver.findElement(By.id("joinedDate")).sendKeys(currentDate.plusDays(5).format(formatter));
		Select location = new Select(driver.findElement(By.xpath("//select[@id='location']")));
		location.selectByVisibleText("India Office");

		driver.findElement(By.xpath("(//div[@class=\"custom-control custom-switch\"])[2]")).click();

		driver.findElement(By.xpath("//input[@id=\"username\"]")).sendKeys("subham9088");
		driver.findElement(By.xpath("//input[@id=\"password\"]")).sendKeys("subham123");
		driver.findElement(By.xpath("//input[@id=\"confirmPassword\"]")).sendKeys("subham123");

		Select adminRole = new Select(driver.findElement(By.xpath("//select[@id='adminRoleId']")));
		adminRole.selectByVisibleText("Regional HR Admin");
		driver.findElement(By.xpath("(//div[@class=\"custom-control custom-switch\"])[3]")).click();

		driver.findElement(By.xpath("//input[@placeholder='Select Regions']")).click();
		driver.findElement(By.xpath("(//span[text()=\"India\"])[2]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()=\"India\"]")));
		driver.findElement(By.xpath("(//div[@class=\"custom-control custom-switch\"])[4]")).click();

		driver.findElement(By.xpath("//input[@placeholder=\"Select Locations\"]")).click();
		driver.findElement(By.xpath("(//span[text()=\"India Office\"])[2]")).click();

		WebElement Next = driver.findElement(By.id("modal-save-button"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", Next);

		Actions actions = new Actions(driver);
		wait.until(ExpectedConditions.elementToBeClickable(Next));
		Thread.sleep(2000);
		actions.click(Next).perform();
		Next.click();

		// Element Verification
		boolean value = driver.findElement(By.xpath("//h4[.=\"Personal Details\"]")).isDisplayed();
		System.out.println(value);

		// Page Verification- Personal Details
		String Actualtitle = driver.getTitle();
		System.out.println("The PersonalDetails Page title is :" + Actualtitle);
		String expectedtitle = "Personal Details";
		boolean verification = Actualtitle.equalsIgnoreCase(expectedtitle);
		System.out.println("Personal Details Page Verification is :  " + verification);

		// Page Verification- Employee Details
		String employeeDetailsActualTitle = driver.getTitle();
		System.out.println(employeeDetailsActualTitle);
		System.out.println("The EmployeeDetails Page Title is :" + employeeDetailsActualTitle);
		String employeeDetailsExpectedTitle = "Employee Details";
		boolean VerificationEmployeeDetails = employeeDetailsActualTitle.equalsIgnoreCase(employeeDetailsExpectedTitle);
		System.out.println("EmployeeDetails Page is : " + VerificationEmployeeDetails);
	}
}