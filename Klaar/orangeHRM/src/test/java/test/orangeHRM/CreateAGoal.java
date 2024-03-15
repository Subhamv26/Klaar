package test.orangeHRM;

import java.time.Duration;

import javax.management.BadAttributeValueExpException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.MoreCollectors;

import net.bytebuddy.build.Plugin.Factory.UsingReflection.Priority;

public class CreateAGoal {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://klaaradmin-trials711.orangehrmlive.com/client/#/dashboard");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("SyN6Ktl@O0");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		Actions actions = new Actions (driver);
		WebElement More = driver.findElement(By.xpath("//span[text()=\"More\"]"));
		More.click();
		Thread.sleep(2000);
		More.click();
		
		
		driver.findElement(By.xpath("(//span[text()=\"Performance\"])[1]")).click();
		driver.findElement(By.xpath("(//a[@class=\"top-level-menu-item \"])[4]")).click();
		
		WebElement Goals = driver.findElement(By.xpath("(//a[@class=\"sub-menu-item-link truncate\"])[10]"));
		wait.until(ExpectedConditions.visibilityOf(Goals));
		actions.moveToElement(Goals).perform();
		
		
		driver.findElement(By.xpath("//a[text()=\"My Goals\"]")).click();
		driver.findElement(By.xpath("//span[text()=\"Create Goal\"]")).click();
		
		//Verify Goals Page
		wait.until(ExpectedConditions.titleContains("My Goals"));
		String GoalTitle = driver.getTitle();
		System.out.println("The title of Goal page : "+ GoalTitle);
		String expectedGoalPage = "My Goals";
		boolean TitleOfGoalPage = expectedGoalPage.equalsIgnoreCase(GoalTitle);
		System.out.println("Goal Page Title is "+TitleOfGoalPage);
		
		driver.findElement(By.xpath("//input[@id=\"name_value\"]")).sendKeys("Automation Engineer");
		
		
		WebElement toggletoolBar = driver.findElement(By.xpath("//div[@id=\"mceu_0\"]"));
		toggletoolBar.click();
		 driver.findElement(By.xpath("//div[@id=\"mceu_14\"]")).sendKeys("C:\\Users\\USER\\Downloads\\932575.jpg");
		 
		driver.findElement(By.xpath("//div[text()=\"Medium\"]")).click();
		WebElement GoalPriority = driver.findElement(By.xpath("//span[text()=\"High\"]"));
		String ActualPriority = GoalPriority.getText();
		GoalPriority.click();
		
		WebElement weight = driver.findElement(By.xpath("//input[@id=\"spinnerInputweight\"]"));
		weight.clear();
		weight.sendKeys("20");	
		WebElement GoalsDate = driver.findElement(By.xpath("//input[@id=\"dueDate\"]"));
		GoalsDate.sendKeys("2024-03-18");
		String ActualDate = GoalsDate.getText();
		
//		Weight Verification
		driver.findElement(By.xpath("//span[text()=\"Please enter a value between 1 and 10\"]")).isDisplayed();
		
		weight.clear();
		weight.sendKeys("8");
		
		driver.findElement(By.xpath("//button[@class=\"btn btn-secondary\"]")).click();
		
		String Priority = driver.findElement(By.xpath("//div[text()=\"High\"]")).getText();
		Priority.compareToIgnoreCase(ActualPriority);
		
 		 WebElement ExpectedDate = driver.findElement(By.xpath("//div[contains(text(),\"2024-03-18\")]"));
		 int validation = ExpectedDate.getText().compareToIgnoreCase(ActualDate);
		 
		 driver.quit();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	}

}
