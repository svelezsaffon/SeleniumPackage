package MainRunner;

import core.API_CORE;
import core.test_rail_case.CaseCodes;
import core.test_rail_case.Case_Helper;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Santiago on 04/09/2015.
 */


@RunWith(BlockJUnit4ClassRunner.class)
public class MainClass extends TestCase {

    private static ChromeDriverService service;
    private static WebDriver driver;

    @Before
    public void set_up_test(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Santiago\\Documents\\WebDrivers\\chromedriver.exe");
        driver=new ChromeDriver();

    }

    @Test
    public void testGloogleSearch(){
            driver.get("http://www.google.com/xhtml");
        WebElement searchBox=driver.findElement(By.name("q"));
        searchBox.sendKeys("Santiago Velez Saffon");
        if(searchBox.isDisplayed()){
            searchBox.submit();
        }


    }

    @After
    public void send_test_status(){



        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.close();

        try {
            API_CORE test_rail = new API_CORE();

            test_rail.set_password("Santi1053");
            test_rail.set_username("svelez@velocitypartners.net");
            test_rail.set_url("https://svelez.testrail.net");

            test_rail.connect();


            Case_Helper caze= new Case_Helper().run_id("5").type(CaseCodes.ADD_RESULTS_TYPE).case_id("1");

            Map data=new HashMap();

            data.put(CaseCodes.STATUS_ID, CaseCodes.PASSED_STATUS_CODE);
            data.put(CaseCodes.COMMENT, "Thsi is from Selenium web driver");


            test_rail.add_result_for_case(data,caze);



        }catch(Exception e){

        }



    }






}
