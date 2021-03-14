package test;

import org.testng.Assert;
import org.testng.annotations.Test;

import driver.WebDriverManager;
import pages.HomePage;
import pages.ProductsPage;

public class BusquedaHeladera extends WebDriverManager{
	@Test(testName="Busqueda de Heladeras", 
			  description= "Realizar la busqueda de un producto")
	public void test() {
		//Arrange 
		final String product="Heladeras";
		
		
		//Act
		HomePage homePage = new HomePage(driver);
		homePage.searchProduct(product)
		.goToProduct()
		.clickFilterHeladera()
		.clickBrandHeladera();
		
		ProductsPage productList = new ProductsPage(driver);
		/*
		System.out.println("cantidad de heladeras "+productList.productListTotal());
		System.out.println("Contiene el texto "+productList.titleOfElements());
		System.out.println("breadcrumb: "+productList.getHeladera());
		*/
		//Assert
		Assert.assertTrue(productList.titleOfElements(),
		        "Los elementos no contienen la marca filtrada");
        Assert.assertEquals(product,productList.getHeladera(),
        		"La palabra heladeras no se encuentra en el breadcrumb de la pagina");
        Assert.assertEquals(productList.getResultados(),productList.productListTotal(),
        		"La cantidad de elementos encontrados no coincide con la de la pagina");
		
        
	}	

}
