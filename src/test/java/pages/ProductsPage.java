package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;
import java.util.List;

import driver.WebDriverManager;


public class ProductsPage extends WebDriverManager{
	private WebDriver driver;
	
	private static By filterHeladeraBy = By.cssSelector("li:nth-child(1) > ul > li:nth-child(2) > a > h4");
	private static By selectBrandHeladeraBy = By.cssSelector("ul > li:nth-child(1) > ul > li:nth-child(2)");
	private static By brandHeladeraBy = By.cssSelector("ul > li:nth-child(1) > ul > li:nth-child(2)>a>label");
	private static By cantidadMarcaHeladerasBy = By.cssSelector("ul > li:nth-child(1) > ul > li:nth-child(2)>a>label>span");
	private static By nextButtonBy = By.cssSelector("li.ant-pagination-next > a");
	private static By listOfElementsBy = By.cssSelector("[name='itemsGrid']>li");
	private static By titleOfElementsBy = By.cssSelector("li>div>a>article>figure+div>h4");
	private static By breadCrumbBy = By.name("breadcrumbItem");
	private static By cantidadResultadosBy = By.name("totalResult");
	
    protected static int TIME_OUT = 5;
	
	public ProductsPage (WebDriver driver) {
		this.driver = driver;
	}
	
	/** 
	 * Clickea heladera
	 */
	public ProductsPage clickFilterHeladera() {
		WebDriverWait wait = new WebDriverWait(driver,TIME_OUT);
        WebElement filterHeladera = wait.until(ExpectedConditions.elementToBeClickable(filterHeladeraBy));
        filterHeladera.click();
        return this;
	}
	
	/** 
	 * Clickea el primer elemento de la lista
	 */
	public ProductsPage clickBrandHeladera() {
		WebDriverWait wait = new WebDriverWait(driver,TIME_OUT);
        WebElement brandHeladera = wait.until(ExpectedConditions.elementToBeClickable(selectBrandHeladeraBy));
        brandHeladera.click();  
        return this;
       
	}
	
	/** 
	 * Extrae el la marca seleccionada
	 */
	public String getBrandHeladera() {
		WebDriverWait wait = new WebDriverWait(driver,TIME_OUT);
		WebElement brandHeladera =wait.until(ExpectedConditions.presenceOfElementLocated(brandHeladeraBy));
		String cantidad = driver.findElement(cantidadMarcaHeladerasBy).getText();
		String marca = driver.findElement(brandHeladeraBy).getText().replace(cantidad, "");
        return marca;
		
	}
	
	/** 
	 * Verifica la cantidad de elementos de la lista
	 */
	public int productList() {
		WebDriverWait wait = new WebDriverWait(driver,TIME_OUT);
		wait.until(ExpectedConditions.presenceOfElementLocated(listOfElementsBy));
		List<WebElement> productList = driver.findElements(listOfElementsBy);
		return productList.size();
	}
	
	/** 
	 * Clickea el boton Next
	 */
	public ProductsPage clickNextButton() {
		WebDriverWait wait = new WebDriverWait(driver,TIME_OUT);
        WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(nextButtonBy));
        nextButton.click();
        return this;
	}
	
	/**
	 * Suma la cantidad de elementos de la lista 
	 */
	public int productListTotal() {
		int productLista = productList();
		int productListTotal=0;
		clickNextButton();
		productListTotal = productLista+productList();
		return productListTotal;
	}
	
	/**
	 * Verifica que el titulo de cada elemento contenga la marca seleccionada
	 */	
	public boolean titleOfElements() {
		WebDriverWait wait = new WebDriverWait(driver,TIME_OUT);
		wait.until(ExpectedConditions.presenceOfElementLocated(titleOfElementsBy));
		List<WebElement> productList = driver.findElements(titleOfElementsBy);
		for(WebElement title : productList) {
			return title.getText().contains(getBrandHeladera());	
		}
		return false;
	}
	
	/**
	 * Verifica que la palabra "Heladeras" aparezca en el breadcrumb
	 */
	public String getHeladera() {
		WebDriverWait wait = new WebDriverWait(driver,TIME_OUT);
		WebElement breadHeladera =wait.until(ExpectedConditions.presenceOfElementLocated(breadCrumbBy));
		String titulo = ", Freezers y Cavas";
		String encabezado = driver.findElement(breadCrumbBy).getText().replace(titulo, "");
		return encabezado;		
	}
	
	/**
	 * Verifica la cantidad de resultados que aparecen en el front de la pagina
	 */
	public int getResultados() {
		WebDriverWait wait = new WebDriverWait(driver,TIME_OUT);
		WebElement cantidadResultados =wait.until(ExpectedConditions.presenceOfElementLocated(cantidadResultadosBy));
		String titulo = " resultados";
		String cantidad = driver.findElement(cantidadResultadosBy).getText().replace(titulo, "");
		return  Integer.parseInt(cantidad);
	}
	
	
}
