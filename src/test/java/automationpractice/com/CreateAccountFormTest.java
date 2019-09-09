package automationpractice.com;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import automationpractice.com.pageObject.Account;
import automationpractice.com.pageObject.CreateAccount;
import automationpractice.com.pageObject.CreateAccountForm;
import automationpractice.com.pageObject.Homepage;
import automationpractice.com.pageObject.SignInForm;
import utils.EmailsGenerator;
import utils.Utils;

public class CreateAccountFormTest {

	public WebDriver driver;
	private Homepage homepage;
	private CreateAccount createAccount;
	private CreateAccountForm createAccountForm;
	private SignInForm signin;
	private Account account;
	

	@BeforeClass
	public void setup() throws IOException {
		
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		
		//Object creation
		
		driver = new ChromeDriver();
		homepage = new Homepage(driver);
		createAccount = new CreateAccount(driver);
		createAccountForm = new CreateAccountForm(driver);
		signin = new SignInForm(driver);
		account = new Account(driver);

		String baseUrl = "http://automationpractice.com/index.php";
		
		// Maximize the window
		driver.manage().window().maximize();

		driver.get(baseUrl);
	
	}

	@AfterClass
	
	
	public void closeAll() throws IOException {
		account.getAccountLogout().click();
		driver.quit();
	}

	@Test(priority = 1)
	public void authenticationPage() throws IOException {
		homepage.getSignInBtn().click();
        Utils.takeSnapShot(driver);
		Assert.assertTrue(createAccount.getCreateAccountForm().isDisplayed());
		Utils.takeSnapShot(driver);
		Assert.assertTrue(createAccount.getCreatAccountEmailField().isDisplayed());
		Utils.takeSnapShot(driver);
		Assert.assertTrue(createAccount.getCreateAccountBtn().isDisplayed());
		Utils.takeSnapShot(driver);
		Assert.assertTrue(signin.getSignInForm().isDisplayed());
		Utils.takeSnapShot(driver);
		

	}

	@Test(priority = 2)
	public void authenticationPageEmailField() throws IOException {
		
		// Without email
		createAccount.getCreateAccountBtn().click();
		Utils.takeSnapShot(driver);

		Assert.assertTrue(createAccount.getEmailErrorMessage().isDisplayed());
		Utils.takeSnapShot(driver);

		// Wrong email format (rshw8488)
		createAccount.setCreateAccountEmailField("rshw8488");
		createAccount.getCreateAccountBtn().click();
		Utils.takeSnapShot(driver);

		Assert.assertTrue(createAccount.getEmailErrorMessage().isDisplayed());
		Assert.assertTrue(createAccount.getEmailFieldHighlightedRed().isDisplayed());

		// Registered email
		createAccount.setCreateAccountEmailField("rshw8487@gmail.com");
		createAccount.getCreateAccountBtn().click();
		Utils.takeSnapShot(driver);

		Assert.assertTrue(createAccount.getEmailBeenRegistered().isDisplayed());
		Utils.takeSnapShot(driver);

		// Correct email
		createAccount.setCreateAccountEmailField("rahulsonawane75@gmail.com");
		createAccount.getCreateAccountBtn().click();
		Utils.takeSnapShot(driver);

		Assert.assertTrue(createAccountForm.getAccountCreationForm().isDisplayed());
		Utils.takeSnapShot(driver);
	}

	@Test(priority = 3)
	public void personalInfoFields() throws IOException {
		
		// With values on registration form and verify highlighted box in green color
		createAccountForm.setCustomerFirstNameField("Rahul");
		createAccountForm.setCustomerLastNameField("Sonawane");
		createAccountForm.setCustomerEmailField("rahulsonawane75@gmail.com");
		createAccountForm.setCustomerPasswordField("travel123");

		createAccountForm.getAccountCreationForm().click();
		Utils.takeSnapShot(driver);

		Assert.assertTrue(createAccountForm.getFirstNameHighlightedGreen().isDisplayed());
		Utils.takeSnapShot(driver);
		Assert.assertTrue(createAccountForm.getLastNameHighlightedGreen().isDisplayed());
		Utils.takeSnapShot(driver);
		Assert.assertTrue(createAccountForm.getEmailHighlightedGreen().isDisplayed());
		Utils.takeSnapShot(driver);
		Assert.assertTrue(createAccountForm.getPasswordHighlightedGreen().isDisplayed());
		Utils.takeSnapShot(driver);

		// Without values in registration form to verify error messages
		createAccountForm.setCustomerFirstNameField("");
		createAccountForm.setCustomerLastNameField("");
		createAccountForm.setCustomerEmailField("");
		createAccountForm.setCustomerPasswordField("");

		createAccountForm.getAccountCreationForm().click();
		Utils.takeSnapShot(driver);

		Assert.assertTrue(createAccountForm.getFirstNameHighlightedRed().isDisplayed());
		Utils.takeSnapShot(driver);
		Assert.assertTrue(createAccountForm.getLastNameHighlightedRed().isDisplayed());
		Utils.takeSnapShot(driver);
		Assert.assertTrue(createAccountForm.getEmailHighlightedRed().isDisplayed());
		Utils.takeSnapShot(driver);
		Assert.assertTrue(createAccountForm.getPasswordHighlightedRed().isDisplayed());
		Utils.takeSnapShot(driver);
	}

	@Test(priority = 4)
	public void requiredFieldsEmpty() throws IOException {
		
		// Again verify error messages by deleting values for few fields
		createAccountForm.getAddressAliasField().clear();
		createAccountForm.setCustomerEmailField("");
		createAccountForm.selectCountry("-");
		createAccountForm.getRegisterBtn().click();
		Utils.takeSnapShot(driver);

		Assert.assertTrue(createAccountForm.getPhoneNumberError().isDisplayed());
		Utils.takeSnapShot(driver);
		Assert.assertTrue(createAccountForm.getLastNameError().isDisplayed());
		Utils.takeSnapShot(driver);
		Assert.assertTrue(createAccountForm.getFirstNameError().isDisplayed());
		Utils.takeSnapShot(driver);
		Assert.assertTrue(createAccountForm.getEmailRequiredError().isDisplayed());
		Utils.takeSnapShot(driver);
		Assert.assertTrue(createAccountForm.getPasswordRequiredError().isDisplayed());
		Utils.takeSnapShot(driver);
		Assert.assertTrue(createAccountForm.getCountryRequiredError().isDisplayed());
		Utils.takeSnapShot(driver);
		Assert.assertTrue(createAccountForm.getAddressRequiredError().isDisplayed());
		Utils.takeSnapShot(driver);
		Assert.assertTrue(createAccountForm.getAddressAliasRequiredError().isDisplayed());
		Utils.takeSnapShot(driver);
		Assert.assertTrue(createAccountForm.getCityRequiredError().isDisplayed());
		Utils.takeSnapShot(driver);
		Assert.assertTrue(createAccountForm.getCountryUnselectedError().isDisplayed());
		Utils.takeSnapShot(driver);

		createAccountForm.selectCountry("United States");
		createAccountForm.getRegisterBtn().click();
		Utils.takeSnapShot(driver);

		// Checking relavent error messages
		Assert.assertTrue(createAccountForm.getStateRequredError().isDisplayed());
		Utils.takeSnapShot(driver);
		Assert.assertTrue(createAccountForm.getPostalCodeError().isDisplayed());
		Utils.takeSnapShot(driver);
	}

	@Test(priority = 5)
	public void requiredFieldsInputFormat() throws Exception {
		// added Wrong format values
		createAccountForm.setCustomerEmailField("rshw8488@gmail");
		createAccountForm.setCustomerPasswordField("--");
		createAccountForm.setPostalCodeField("asd");
		createAccountForm.setHomePhoneField("asd");
		createAccountForm.setMobilePhoneField("asd");

		createAccountForm.getRegisterBtn().click();
		Utils.takeSnapShot(driver);

		Assert.assertTrue(createAccountForm.getEmailInvalidError().isDisplayed());
		Assert.assertTrue(createAccountForm.getPasswordInvalidError().isDisplayed());
		Assert.assertTrue(createAccountForm.getPostalCodeError().isDisplayed());
		Assert.assertTrue(createAccountForm.getHomePhoneInvalidError().isDisplayed());
		Assert.assertTrue(createAccountForm.getMobilePhoneInvalidError().isDisplayed());
		Utils.takeSnapShot(driver);

		// Correct format but invalid email
		createAccountForm.setCustomerEmailField("email@email.com");
		createAccountForm.setCustomerPasswordField("travel123");
		createAccountForm.setPostalCodeField("210000");
		createAccountForm.setHomePhoneField("056");
		createAccountForm.setMobilePhoneField("065");

		Assert.assertTrue(createAccountForm.getEmailInvalidError().isDisplayed());
		Utils.takeSnapShot(driver);
		Assert.assertTrue(createAccountForm.getPasswordInvalidError().isDisplayed());
		Utils.takeSnapShot(driver);
		Assert.assertTrue(createAccountForm.getPostalCodeError().isDisplayed());
		Utils.takeSnapShot(driver);
		Assert.assertTrue(createAccountForm.getHomePhoneInvalidError().isDisplayed());
		Utils.takeSnapShot(driver);
		Assert.assertTrue(createAccountForm.getMobilePhoneInvalidError().isDisplayed());
		Utils.takeSnapShot(driver);
		Utils.takeSnapShot(driver);
	}

	@Test(priority = 6)
	public void createAccountSuccessfully() throws IOException {
		// Added all required fields filled with correct values
		createAccountForm.setCustomerFirstNameField("Adam");
		createAccountForm.setCustomerLastNameField("Roy");
	
		createAccountForm.setCustomerPasswordField("travel123");
		createAccountForm.selectCustomerDateOfBirthDay("18");
		createAccountForm.selectCustomerDateOfBirthMonth("10");
		createAccountForm.selectCustomerDateOfBirthYear("2000");
		createAccountForm.setAddressField("Main Road");
		createAccountForm.setCityField("Chicago");
		createAccountForm.selectState("7");
		createAccountForm.setPostalCodeField("21000");
		createAccountForm.setHomePhoneField("056");
		createAccountForm.setMobilePhoneField("065");
		createAccountForm.setAddressAliasField("My Address");
		createAccountForm.getRegisterBtn().click();
		Utils.takeSnapShot(driver);

		Assert.assertTrue(createAccountForm.getEmailBeenRegistered().isDisplayed());
		Utils.takeSnapShot(driver);
        
		//Created Email from EmailGenerator class and filled in registration form
		createAccountForm.setCustomerEmailField(EmailsGenerator.getNextEmail());
		createAccountForm.setCustomerPasswordField("travel123");
		createAccountForm.getRegisterBtn().click();
		Utils.takeSnapShot(driver);
        
		//Verify account created successfully
		Assert.assertTrue(createAccountForm.successfullyCreatedAccount().isDisplayed());
		Utils.takeSnapShot(driver);
	}
}
