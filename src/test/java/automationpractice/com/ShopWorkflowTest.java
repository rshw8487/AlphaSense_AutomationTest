package automationpractice.com;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import automationpractice.com.pageObject.Account;
import automationpractice.com.pageObject.Cart;
import automationpractice.com.pageObject.CartSummary;
import automationpractice.com.pageObject.Clothes;

import automationpractice.com.pageObject.ShoppingActions;
import automationpractice.com.pageObject.SignInForm;
import utils.EmailsGenerator;
import utils.Utils;

public class ShopWorkflowTest {

	public WebDriver driver;
	public Actions action;

	public Clothes clothes;
	public Cart cart;
	public ShoppingActions shoppingActions;
	public CartSummary summary;
	public SignInForm signinForm;
	public Account account;
	
	@BeforeClass
	public void setup() throws IOException {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		
		//Object Creation
		driver = new ChromeDriver();
		action = new Actions(driver);
		clothes = new Clothes(driver);
		cart = new Cart(driver);
		shoppingActions = new ShoppingActions(driver);
		signinForm = new SignInForm(driver);
		summary = new CartSummary(driver);
		account = new Account(driver);

		String baseUrl = "http://automationpractice.com/index.php";
		driver.manage().window().maximize();
		driver.get(baseUrl);
	}

	@AfterClass
	public void closeAll() throws IOException {
		account.getAccountLogout().click();
		Utils.takeSnapShot(driver);
		driver.quit();
	}
	
@Test(priority = 1)
	public void selectChlotes() throws IOException {
		
	   // Dress search and different Type of dresses selection
		Assert.assertTrue(clothes.getDressesBtn().isDisplayed());
		Utils.takeSnapShot(driver);

		action.moveToElement(clothes.getDressesBtn()).perform();
		
		
		Assert.assertTrue(clothes.getSummerDressesBtn().isDisplayed());
		
		Assert.assertTrue(clothes.getCasualDressesBtn().isDisplayed());
		
		Assert.assertTrue(clothes.getEveningDressesBtn().isDisplayed());
	

		action.moveToElement(clothes.getSummerDressesBtn()).perform();
		
	
		clothes.getSummerDressesBtn().click();
		Utils.takeSnapShot(driver);

		Assert.assertTrue(clothes.getSummerDressProduct(1).isDisplayed());
	
		Assert.assertTrue(clothes.getSummerDressProduct(2).isDisplayed());
		
		Assert.assertTrue(clothes.getSummerDressProduct(3).isDisplayed());
	
		Assert.assertEquals(clothes.getDressesCount().size(), 3);

		action.moveToElement(clothes.getSummerDressProduct(1)).perform();
	
		
		action.moveToElement(shoppingActions.getAddToCartBtn()).perform();
		
		

		Assert.assertTrue(shoppingActions.getAddToCartBtn().isDisplayed());
	

		action.click(shoppingActions.getAddToCartBtn()).build().perform();
		
		action.click(shoppingActions.getContinueShopingBtn()).build().perform();
		

		Assert.assertTrue(shoppingActions.getContinueShopingBtn().isDisplayed());
		

		action.moveToElement(cart.getCartTab()).perform();
		
        
		//Verify quantity of product added
		Assert.assertEquals(cart.getCartProductsQty().size(), 1);

		// 2. dress
		action.moveToElement(clothes.getDressesBtn()).perform();
		

		Assert.assertTrue(clothes.getCasualDressesBtn().isDisplayed());
	

		action.moveToElement(clothes.getCasualDressesBtn()).perform();
	
	
		action.moveToElement(clothes.getCasualDressProduct(1)).perform();
		
		
		action.moveToElement(shoppingActions.getAddToCartBtn()).perform();
		
		
		action.click(shoppingActions.getAddToCartBtn()).build().perform();
		
		

		Assert.assertTrue(shoppingActions.getAddToCartBtn().isDisplayed());
		Utils.takeSnapShot(driver);

		action.click(shoppingActions.getContinueShopingBtn()).build().perform();
		
		
		action.moveToElement(cart.getCartTab()).perform();
		
	
		
        // Verify added quantity of product
		Assert.assertEquals(cart.getCartProductsQty().size(), 2);

		
		action.moveToElement(clothes.getDressesBtn()).perform();
		
		

		Assert.assertTrue(clothes.getEveningDressesBtn().isDisplayed());
		Utils.takeSnapShot(driver);

		action.moveToElement(clothes.getEveningDressesBtn()).perform();
		
		
		clothes.getEveningDressesBtn().click();
		Utils.takeSnapShot(driver);
		action.moveToElement(clothes.getEveningDressProduct(1)).perform();
		
		
		action.moveToElement(shoppingActions.getAddToCartBtn()).perform();
		
		
		action.click(shoppingActions.getAddToCartBtn()).build().perform();
		
		

		Assert.assertTrue(shoppingActions.getAddToCartBtn().isDisplayed());
		Utils.takeSnapShot(driver);

		action.click(shoppingActions.getContinueShopingBtn()).build().perform();
		
		action.moveToElement(cart.getCartTab()).perform();
		
	

		Assert.assertEquals(cart.getCartProductsQty().size(), 3);
	}

	@Test(priority = 3)
	public void deleteCartProducts() throws IOException {
		
		//Verify deletion of product from cart
		Assert.assertEquals(cart.getCartProductsQty().size(), 3);

		action.moveToElement(cart.getCartTab()).perform();
		
		
		action.moveToElement(cart.getCartProductDeleteX(2)).perform();
		
		
		action.click(cart.getCartProductDeleteX(2)).build().perform();
		Utils.takeSnapShot(driver);
		
		action.moveToElement(clothes.getDressesBtn()).perform();
		
		
		action.moveToElement(clothes.getEveningDressesBtn()).perform();
		
		
		clothes.getEveningDressesBtn().click();
		Utils.takeSnapShot(driver);
		action.moveToElement(cart.getCartTab()).perform();
		

		Assert.assertEquals(cart.getCartProductsQty().size(), 2);
	}

	@Test(priority = 4)
	public void checkingCartProductsQtyAndPrice() throws IOException {
		Assert.assertEquals(cart.getCartProductsQty().size(), 2);

		action.moveToElement(clothes.getDressesBtn()).perform();
		
	
		action.moveToElement(clothes.getEveningDressesBtn()).perform();
		
	
		action.moveToElement(clothes.getEveningDressProduct(1)).perform();
		
		
		action.moveToElement(shoppingActions.getAddToCartBtn()).perform();
		
		
		action.click(shoppingActions.getAddToCartBtn()).build().perform();
	
		
		action.click(shoppingActions.getContinueShopingBtn()).build().perform();
		
	

		action.moveToElement(cart.getCartTab()).perform();
		
		
		action.moveToElement(cart.getCartProductsQty(1)).perform();
		
	
         
		//Verify product quantity
		Assert.assertEquals(cart.getCartProductsQty(1).getText(), "1");

		action.moveToElement(cart.getCartProductsQty(2)).perform();
		
		

		Assert.assertEquals(cart.getCartProductsQty(2).getText(), "2");
		
		//Verify cart shippping cost
		action.moveToElement(cart.getCartShipingCost()).perform();
		
		

		Assert.assertEquals(cart.getCartShipingCost().getText(), "$2.00");
		
		//Verify cart Total dress cost

		action.moveToElement(cart.getCartTotalPrice()).perform();
		

		Assert.assertEquals(cart.getCartTotalPrice().getText(), "$132.96");
	}

	@Test(priority = 5)
	public void continueToShoppingSummary() throws IOException {
		
		//Verify added Shopping summary
		action.moveToElement(cart.getCartTab()).perform();
		
		
		action.moveToElement(cart.getCartTabCheckOutBtn()).perform();
		
		

		Assert.assertTrue(cart.getCartTabCheckOutBtn().isDisplayed());
		Utils.takeSnapShot(driver);

		action.click(cart.getCartTabCheckOutBtn()).build().perform();
		
		
		Assert.assertTrue(summary.getCartSummaryTable().isDisplayed());
		Utils.takeSnapShot(driver);
		Assert.assertEquals(summary.getCartSummTotalProductsNum().size(), 2);
		Assert.assertEquals(summary.getCartSummTotalProductsPrice().getText(), "$130.96");
		Assert.assertEquals(summary.getCartSummaryTotalPrice().getText(), "$132.96");
		Assert.assertEquals(summary.getCartSummTotalShipping().getText(), "$2.00");
		Assert.assertTrue(summary.getCartSummQtyPlus(1).isDisplayed());
		Utils.takeSnapShot(driver);
		Assert.assertTrue(summary.getCartSummQtyPlus(1).isDisplayed());
		Utils.takeSnapShot(driver);
		Assert.assertTrue(summary.getCartSummQtyMinus(1).isDisplayed());
		Utils.takeSnapShot(driver);
		Assert.assertTrue(summary.getCartSummQtyMinus(1).isDisplayed());
		Utils.takeSnapShot(driver);
		Assert.assertTrue(summary.getCartSummQtyInput(1).isDisplayed());
		Utils.takeSnapShot(driver);
		Assert.assertTrue(summary.getCartSummQtyInput(1).isDisplayed());
		Utils.takeSnapShot(driver);
		Assert.assertTrue(summary.getCartSummQtyPlus(2).isDisplayed());
		Utils.takeSnapShot(driver);
		Assert.assertTrue(summary.getCartSummQtyPlus(2).isDisplayed());
		Utils.takeSnapShot(driver);
		Assert.assertTrue(summary.getCartSummQtyMinus(2).isDisplayed());
		Utils.takeSnapShot(driver);
		Assert.assertTrue(summary.getCartSummQtyMinus(2).isDisplayed());
		Utils.takeSnapShot(driver);
		Assert.assertTrue(summary.getCartSummQtyInput(2).isDisplayed());
		Utils.takeSnapShot(driver);
		Assert.assertTrue(summary.getCartSummQtyInput(2).isDisplayed());
		Utils.takeSnapShot(driver);
		Assert.assertTrue(summary.getCartSummDeleteBtn(1).isDisplayed());
		Utils.takeSnapShot(driver);
		Assert.assertTrue(summary.getCartSummDeleteBtn(2).isDisplayed());
		Utils.takeSnapShot(driver);
	}

	@Test(priority = 6)
	public void increaseQtyOfProduct1() throws IOException {
		
		//Verify current summary of total price of added products
		Assert.assertEquals(summary.getCartSummTotalProductsPrice().getText(), "$130.96");
		Assert.assertEquals(summary.getCartSummaryTotalPrice().getText(), "$132.96");
		Assert.assertEquals(summary.getCartSummTotalShipping().getText(), "$2.00");
        
		//Added one more product in cart
		summary.getCartSummQtyPlus(1).click();
		driver.navigate().refresh();
		Utils.takeSnapShot(driver);
        
		// Verify newly added summary of total price for added products
		Assert.assertEquals(summary.getCartSummTotalProductsPrice().getText(), "$159.94");
		Assert.assertEquals(summary.getCartSummaryTotalPrice().getText(), "$161.94");
		Assert.assertEquals(summary.getCartSummTotalShipping().getText(), "$2.00");
	}

	@Test(priority = 7)
	public void signinRequest() throws IOException {
		
		// Sign in on portal with valid credentials 
		summary.getCartProceedBtn().click();
		Utils.takeSnapShot(driver);

		Assert.assertTrue(signinForm.getSignInForm().isDisplayed());
		Utils.takeSnapShot(driver);

		signinForm.setEmailField(EmailsGenerator.getCurrentEmail());
		signinForm.setPasswordField("travel123");
		signinForm.getSignInBtn().click();
	}

	@Test(priority = 8)
	
	// Verify Billing and Delivery address on summary page
	public void billingAndDeliveryAddress() {
		Assert.assertEquals(summary.getCartSummBillingAdressName().getText(), "Adam Roy");
		Assert.assertEquals(summary.getCartSummBillingAdressOne().getText(), "Main Road");
		Assert.assertEquals(summary.getCartSummBillingAdressCityState().getText(), "Chicago, Connecticut 21000");
		Assert.assertEquals(summary.getCartSummBillingAdressCountry().getText(), "United States");
		Assert.assertEquals(summary.getCartSummBillingAdressHomePhone().getText(), "056");
		Assert.assertEquals(summary.getCartSummBillingAdressMobile().getText(), "065");
	}

	@Test(priority = 9)
	public void termsOfServiceModal() throws IOException {
		
		// Verify Term of service
		summary.getCartProceedBtnTwo().click();
		Utils.takeSnapShot(driver);
		summary.getCartProceedBtnTwo().click();
		Utils.takeSnapShot(driver);

		action.moveToElement(summary.getCartSummTermsOfServiceDialog()).perform();
		

		Assert.assertTrue(summary.getCartSummTermsOfServiceDialog().isDisplayed());
		Utils.takeSnapShot(driver);

		action.moveToElement(summary.getCartSummTermsOfServiceDialogClose()).perform();
		
		action.click(summary.getCartSummTermsOfServiceDialogClose()).build().perform();
		

		driver.navigate().refresh();

		summary.getCartSummTermsOfServiceCheck().click();
		Utils.takeSnapShot(driver);
		summary.getCartProceedBtnTwo().click();
		Utils.takeSnapShot(driver);
	}

	@Test(priority = 10)
	public void payment() throws IOException {
		
		// Perform payment on payment page
		summary.getCartSummPayByBankWire().click();
		Utils.takeSnapShot(driver);

		Assert.assertEquals(summary.getCartSummPayByBankWireConfirm().getText(), "BANK-WIRE PAYMENT.");

		summary.getCartSummOtherPaymentMethods().click();
		Utils.takeSnapShot(driver);
		summary.getCartSummPayByCheck().click();
		Utils.takeSnapShot(driver);

		Assert.assertEquals(summary.getCartSummPayByCheckConfirm().getText(), "CHECK PAYMENT");
	}

	@Test(priority = 11)
	public void confirmOrder() throws IOException {
		
		//Perform action and confirm the order 
		summary.getCartSummConfirmOrderBtn().click();
		Utils.takeSnapShot(driver);

		Assert.assertTrue(summary.getCartSummSuccessMsg().isDisplayed());
		Utils.takeSnapShot(driver);
		
		//Check the Successful message after Order is completed
		Assert.assertEquals(summary.getCartSummSuccessMsg().getText(), "Your order on My Store is complete.");
	}

	@Test(priority = 12)
	public void checkIsOrderVisibleInOrderHistorySection() throws IOException {
		
		// Verify the details of order in Order History section
		account.getAccountBtn().click();
		Utils.takeSnapShot(driver);

		Assert.assertTrue(account.getAccountOrderHistoryBtn().isDisplayed());
		Utils.takeSnapShot(driver);

		account.getAccountOrderHistoryBtn().click();
		Utils.takeSnapShot(driver);

		Assert.assertTrue(account.getAccountOrderListTable().isDisplayed());
		Utils.takeSnapShot(driver);

		account.getAccountBtn().click();
		Utils.takeSnapShot(driver);
		account.getAccountOrderHistoryBtn().click();
		Utils.takeSnapShot(driver);

		Assert.assertEquals(account.getAccountOrdersLis().size(), 1);		
		
}

}