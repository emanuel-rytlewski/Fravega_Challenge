package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import driver.WebDriverManager;

public class HomePage extends WebDriverManager {
	private WebDriver driver;
	
	protected static By searchFieldBy = By.cssSelector("[placeholder='Buscar productos']");
	protected static By searchButtonBy = By.cssSelector("header >div >div> div>form>[type='submit']");
	
	
    protected static int TIME_OUT = 5;
	
    public HomePage (WebDriver driver) {
		this.driver = driver;
	}

	/** 
	 * Ingresa texto en el campo de busqueda
	 */
	public HomePage searchProduct(String product) {
		WebDriverWait wait = new WebDriverWait(driver,TIME_OUT);
        WebElement searchField = wait.until(ExpectedConditions.presenceOfElementLocated(searchFieldBy));
        searchField.sendKeys(product);
        return this;
	}

	/** 
	 * Clickea el boton de buscar
	 */
	public ProductsPage goToProduct() {
		WebDriverWait wait = new WebDriverWait(driver,TIME_OUT);
        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(searchButtonBy));
        searchButton.click(); 
        ProductsPage productsPage = new ProductsPage(driver);
        return productsPage;
	}
	
	
}
