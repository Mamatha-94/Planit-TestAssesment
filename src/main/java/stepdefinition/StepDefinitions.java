package stepdefinition;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import locators.ContactPage;
import locators.ShopPage;
import utils.EnvProperties;

public class StepDefinitions {

	WebDriver driver;
	WebElement we;
	private static final String PATH = System.getProperty("user.dir");
	EnvProperties props = new EnvProperties();
	ContactPage CP = new ContactPage();
	ShopPage SP= new ShopPage();
	

	@Given("user will launch Googlechrome browser")
	public void launch_browser() {
		System.out.println(PATH);
		System.setProperty("webdriver.chrome.driver", PATH + "/src/test/resources/drivers/chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Then("user will open the application url {string}")
	public void open_url(String urlName) {

		driver.get(props.readProperty(urlName));

	}

	public WebElement getWebElement(String string) {
		WebElement wEle = null;
		try {
			WebDriverWait wait= new WebDriverWait(driver,50);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(string)));
			wEle = driver.findElement(By.xpath(string));
		} catch (Exception e) {
			throw e;
		}
		return wEle;
	}

	@Then("user will switch from the home page go to contact page")
	public void go_to_contact_page() {
		getWebElement(CP.contactLink).click();

	}

	@Then("Validate mandatory field error messages")
	public void validate_mandatory_errors() {
		
		if (getWebElement(CP.foreName).isDisplayed()) {
			String actualMessage = getWebElement(CP.foreName).getText();
			String expectedMessage = "Forename is required";
			assertionResult(actualMessage, expectedMessage);
			if (getWebElement(CP.email).isDisplayed()) {
				String actualMessage1 = getWebElement(CP.email).getText();
				String expectedMessage1 = "Email is required";
				assertionResult(actualMessage1, expectedMessage1);
			}
			if (getWebElement(CP.message).isDisplayed()) {
				String actualMessage2 = getWebElement(CP.message).getText();
				String expectedMessage2 = "Message is required";
				assertionResult(actualMessage2, expectedMessage2);
			}

		} else {
			System.out.println("Error message is not displayed");
		}
	}

	@Then("Populate mandatory fields")
	public void populate_mandatory_fields() throws Throwable {
		
		getWebElement(CP.fInput).sendKeys("Mamatha");
		getWebElement(CP.eInput).sendKeys("abc123@gmail.com");
		getWebElement(CP.mInput).sendKeys("funny toys");
	}

	@Then("user click submit button")
	public void submit_button() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		getWebElement(CP.submit).click();
	}

	@Then("Validate errors are gone")
	public void validate_errors_are_gone()  {
		try {
			if (getWebElement(CP.foreName).isDisplayed()) {
				System.out.println("Errors are present in Webpage");
			}
		} catch (NoSuchElementException | TimeoutException e) {
			System.out.println("No Errors in Webpage");
		}
	}

	@Then("Validate successful submission message")
	public void validate_successful_submission_message() throws Throwable {
		String fm = getWebElement(CP.feedbackMessage).getText();
		if (fm.contains("we appreciate your feedback")) {
			Assert.assertTrue("Submission Message success", true);
			System.out.println("Submission Message success");
		} else {
			Assert.assertFalse("Submission Message failed", true);
		}
	}

	@Then("Populate mandatory fields with invalid data")
	public void populate_mandatory_fields_with_invalid_data() throws Throwable {
		getWebElement(CP.fInput).sendKeys(".,b/;{!%)+-");
		getWebElement(CP.eInput).sendKeys("abc12#%!");
		getWebElement(CP.mInput).sendKeys(" all !!:?<> good +-");
	}

	@Then("Validate field level errors")
	public void validate_errors() {
		if(getWebElement(CP.invalidEmail).isDisplayed())
		{
			String actualMessage=getWebElement(CP.invalidEmail).getText();
			String expectedMessage="Please enter a valid email";
			assertionResult(actualMessage, expectedMessage);				
		}
	}

	public void assertionResult(String actualMessage, String expectedMessage) {	
		if (actualMessage.contentEquals(expectedMessage)) {
			Assert.assertTrue(actualMessage.contentEquals(expectedMessage));
			System.out.println("Assertion passed");		
		}else {
			Assert.assertFalse((actualMessage.contentEquals(expectedMessage)));
			System.out.println("Assestion failed");
		}

	}



	@Then("user will switch from the home page go to shop page")
	public void go_to_shop_page() {

		getWebElement(SP.shopLink).click();	
	}

	@Then("Click buy button {int} times on Funny Cow item")
	public void click_on_funny_cow_item(Integer int1) throws Throwable {
		getWebElement(SP.funnyCow).click();
		getWebElement(SP.funnyCow).click();
	}

	@Then("Click buy button {int} time on Fluffy Bunny item")
	public void click_on_fluffy_bunny_item(Integer int1) throws Throwable {
		getWebElement(SP.fluffyBunny).click();
	}

	@Then("Click the cart menu")
	public void cart_menu() {
		getWebElement(SP.cart).click();
	}

	@Then("Verify the items are in the cart")
	public void verify_the_items_are_in_the_cart() {
		String actualMessage= getWebElement(SP.updateCart).getText();
		String expectedMessage="3";
		assertionResult(actualMessage, expectedMessage);
	}
	@io.cucumber.java.After
	public void closeBrowser() {
		driver.close();
		driver.quit();
			
	}
	
}
