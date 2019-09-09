package automationpractice.com;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import automationpractice.com.pageObject.Account;
import automationpractice.com.pageObject.CreateAccountForm;
import automationpractice.com.pageObject.SignInForm;
import utils.EmailsGenerator;
import utils.Utils;

public class LoginFormTest {

	public WebDriver driver;
	public CreateAccountForm createAccountForm;
	public SignInForm signin;
	public Account account;

	@BeforeClass
	public void setup() throws IOException {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		
		//Object creation
		driver = new ChromeDriver();

		createAccountForm = new CreateAccountForm(driver);
		signin = new SignInForm(driver);
		account = new Account(driver);

		String baseUrl = "http://automationpractice.com/index.php?controller=authentication";
		driver.manage().window().maximize();
		driver.get(baseUrl);
	}

	@AfterClass
	public void closeAll() throws IOException {
		
		//Account Logout
		account.getAccountLogout().click();
		Utils.takeSnapShot(driver);
		driver.quit();
	}

	@Test(priority = 1)
	public void loginPage() throws IOException {
		Assert.assertTrue(signin.getSignInForm().isDisplayed());
		Utils.takeSnapShot(driver);
		Assert.assertTrue(signin.getSignInEmailField().isDisplayed());
		Utils.takeSnapShot(driver);
		Assert.assertTrue(signin.getSignInPasswordField().isDisplayed());
		Utils.takeSnapShot(driver);
		Assert.assertTrue(signin.getSignInBtn().isEnabled());
		Utils.takeSnapShot(driver);
	}

	@Test(priority = 2)
	public void invalidCredentials() throws IOException {
		
       //Validation for Invalid login details on Sign In form
		
		signin.setEmailField("xyz123@email.com");
		signin.setPasswordField("sifra");
		signin.getSignInBtn().click();
		Utils.takeSnapShot(driver);

		Assert.assertTrue(signin.getAuthenticationFailedError().isDisplayed());
		Utils.takeSnapShot(driver);

		signin.setEmailField("email@email.email");
		signin.setPasswordField("sifra");
		signin.getSignInBtn().click();
		Utils.takeSnapShot(driver);

		Assert.assertTrue(signin.getAuthenticationFailedError().isDisplayed());
		Utils.takeSnapShot(driver);

		signin.setEmailField("mapkoct@gmail.com");
		signin.setPasswordField("asddsa");
		signin.getSignInBtn().click();
		Utils.takeSnapShot(driver);

		Assert.assertTrue(signin.getAuthenticationFailedError().isDisplayed());
		Utils.takeSnapShot(driver);

	}

	@Test(priority = 3)
	public void loginWithoutCredentials() throws IOException {
		
		// Validation for Sign In form without adding any credentials
		signin.setEmailField("");
		signin.setPasswordField("asddsa");
		signin.getSignInBtn().click();
		Utils.takeSnapShot(driver);

		Assert.assertTrue(signin.getEmailRequiredError().isDisplayed());
		Utils.takeSnapShot(driver);

		signin.setEmailField("mapkoct@gmail.com");
		signin.setPasswordField("");
		signin.getSignInBtn().click();
		Utils.takeSnapShot(driver);

		Assert.assertTrue(signin.getPasswordRequiredError().isDisplayed());
		Utils.takeSnapShot(driver);

		signin.setEmailField("");
		signin.setPasswordField("");
		signin.getSignInBtn().click();
		Utils.takeSnapShot(driver);

		Assert.assertTrue(signin.getEmailRequiredError().isDisplayed());
		Utils.takeSnapShot(driver);
	}

	@Test(priority = 4)
	public void emailFormat() throws IOException {
		
		//Validation adding wrong email format
		signin.setEmailField("email");
		signin.getSignInPasswordField().click();
		Utils.takeSnapShot(driver);

		Assert.assertTrue(signin.getEmailHighlightedRed().isDisplayed());
		Utils.takeSnapShot(driver);

		signin.setEmailField("email@email");
		signin.getSignInPasswordField().click();
		Utils.takeSnapShot(driver);

		Assert.assertTrue(signin.getEmailHighlightedRed().isDisplayed());

		signin.setEmailField("email@email.email");
		signin.getSignInPasswordField().click();
		Utils.takeSnapShot(driver);

		Assert.assertTrue(signin.getEmailHighlightedGreen().isDisplayed());
		Utils.takeSnapShot(driver);
	}

	@Test(priority = 5)
	public void successfulLogin() throws IOException {
		
		//successful Email Login 
		signin.setEmailField(EmailsGenerator.getCurrentEmail());
		signin.setPasswordField("travel123");
		signin.getSignInBtn().click();
		Utils.takeSnapShot(driver);

		Assert.assertTrue(createAccountForm.successfullyCreatedAccount().isDisplayed());
		Utils.takeSnapShot(driver);
	}

}
