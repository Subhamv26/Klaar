package test.orangeHRM;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import org.apache.hc.core5.net.PercentCodec;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddAnEmployee {

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
		
		//Add an Employee
		driver.findElement(By.xpath("//i[normalize-space()='add']")).click();
		
		driver.findElement(By.xpath("//input[@id=\"first-name-box\"]")).sendKeys(p.getProperty("firstName"));
		driver.findElement(By.xpath("//input[@id=\"last-name-box\"]")).sendKeys(p.getProperty("lastName"));
		
		//Disable Auto Generate Employee ID
		driver.findElement(By.xpath("(//div[@class='custom-control custom-switch'])[1]")).click();
		
		//Get The Employee ID From file
		driver.findElement(By.xpath("//input[@id=\"employeeId\"]")).sendKeys(p.getProperty("employeeId"));
		
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		driver.findElement(By.id("joinedDate")).clear();
		driver.findElement(By.id("joinedDate")).sendKeys(currentDate.plusDays(5).format(formatter));
		Select location = new Select(driver.findElement(By.xpath("//select[@id='location']")));
		location.selectByVisibleText("India Office");

		driver.findElement(By.xpath("(//div[@class=\"custom-control custom-switch\"])[2]")).click();

		driver.findElement(By.xpath("//input[@id=\"username\"]")).sendKeys("subham876");
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

		WebElement Next = driver.findElement(By.xpath("//*[@id=\"modal-save-button\"]"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", Next);

		Actions actions = new Actions(driver);
		wait.until(ExpectedConditions.elementToBeClickable(Next));
		Next.click();
		Thread.sleep(2000);
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

		WebElement Next2 = driver.findElement(By.xpath("//button[normalize-space()='Next']"));
		WebElement requiredField = driver.findElement(By.xpath("//div[text()=\" Required field\"]"));
		js.executeScript("arguments[0].scrollIntoView();", requiredField);
		wait.until(ExpectedConditions.elementToBeClickable(Next2));
		Next2.click();

		WebElement employeeStatus = driver.findElement(
				By.xpath("//label[@for=\"employment_status_id\"]/..//div[@class=\"filter-option-inner-inner\"]"));
		employeeStatus.click();

		WebElement fulltimePermanent = driver.findElement(By.xpath("//span[text()='Full-Time Permanent']"));
		wait.until(ExpectedConditions.elementToBeClickable(fulltimePermanent));
		fulltimePermanent.click();

		// Page Verification- Employee Details
		String employeeDetailsActualTitle = driver.getTitle();
		System.out.println("The EmployeeDetails Page Title is :" + employeeDetailsActualTitle);
		String employeeDetailsExpectedTitle = "Job";
		boolean VerificationEmployeeDetails = employeeDetailsActualTitle.equalsIgnoreCase(employeeDetailsExpectedTitle);
		System.out.println("EmployeeDetails Page is : " + VerificationEmployeeDetails);

		WebElement comments = driver.findElement(By.xpath("//textarea[@id=\"comment\"]"));
		String data = p.getProperty("comment");
		comments.sendKeys(data);
		WebElement Next3 = driver.findElement(By.xpath("//button[text()='Next']"));
		js.executeScript(
				"document.querySelector(\"#wizard-nav-button-section > button:nth-child(2)\").scrollIntoView();",
				Next3);
		wait.until(ExpectedConditions.elementToBeClickable(Next3));
		Next3.click();

		// Page Verification- Contact Details

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"contact_details_tab\"]/h4")));
		String ContactDetailsPageTitle = driver.getTitle();
		System.out.println("The Contact Details Page title is :" + ContactDetailsPageTitle);
		String expectedContactDetailsPageTitle = "Contact Details";
		boolean titleVerificationContactDetails = ContactDetailsPageTitle
				.equalsIgnoreCase(expectedContactDetailsPageTitle);
		System.out.println("ContactDetails Page Verification is :  " + titleVerificationContactDetails);

		driver.findElement(By.xpath("//h4[text()=\"Contact Details\"]")).click();

		WebElement contactDetailsNext = driver.findElement(By.xpath("//button[@class=\"btn btn-secondary right\"]"));
		js.executeScript("arguments[0].scrollIntoView();", contactDetailsNext);
		contactDetailsNext.click();

		WebElement OnBoarding = driver.findElement(By.xpath("//*[@id=\"eventTemplate_inputfileddiv\"]/div/input"));
		wait.until(ExpectedConditions.elementToBeClickable(OnBoarding));
		OnBoarding.click();

		// Page Verification- On BoardedPage
		String OnBoardedPageTitle = driver.getTitle();
		System.out.println("The Page title is :" + OnBoardedPageTitle);
		String expectedOnBoardedPageTitle = "Onboarding";
		boolean titleVerificationOnBoarded = expectedOnBoardedPageTitle.equalsIgnoreCase(OnBoardedPageTitle);
		System.out.println("Onboarding Page Verification is :  " + titleVerificationOnBoarded);

		driver.findElement(By.xpath("(//span[text()=\"Onboarding - India\"])[1]")).click();

		WebElement Save = driver.findElement(By.xpath("//button[text()='Save']"));
		actions.scrollToElement(Save).perform();
		Save.click();

		// Verification of Employee Details
		String EmployeeDetailsPage = driver.getTitle();
		System.out.println("The Title of Employee Details Page : " + EmployeeDetailsPage);
		String expectedEmployeeDetailsPage = "subham vishwakarma";
		boolean titleVerificationEmployeeDetails = expectedEmployeeDetailsPage.equalsIgnoreCase(EmployeeDetailsPage);
		System.out.println("EmployeeDetailsPage : " + titleVerificationEmployeeDetails);

		WebElement Job = driver.findElement(By.xpath("//a[text()=\"Job \"]"));
		Job.click();

//		Location Verification

		WebElement LocationVerification = driver.findElement(By.xpath("//div[text()=\"India Office\"]"));
		System.out.println(LocationVerification.getText());
		driver.quit();

	}
}