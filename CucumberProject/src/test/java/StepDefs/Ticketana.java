package StepDefs;

import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import io.cucumber.java.en.*;

public class Ticketana {
    WebDriver driver;

    // Locator variables
    private By Username = By.id(":r0:");
    private By Password = By.id(":r1:");
    private By Text = By.xpath("//button[@class='text underline ']");
    private By AcceptButton = By.xpath("//button[text()='I Accept']");
    private By loginButton = By.xpath("//button[@variant='contained']");
    private By fileInput = By.xpath("//input[@type='file']");
    private By uploadButton = By.xpath("//button[normalize-space()='Upload']");
    private By deleteIcon = By.xpath("//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-colorSecondary MuiIconButton-sizeMedium cursor-pointer css-xh526o']//*[name()='svg']");
    private By category = By.xpath("//span[normalize-space()='Category']");
    private By childCategory = By.xpath("//span[normalize-space()='Child Category 1']");
    private By shortDesc = By.xpath("//span[normalize-space()='Short Desc']");
    private By moveSelectedRightButton = By.xpath("//button[@aria-label='move selected right']");
    private By submitButton = By.xpath("//button[@class='bg-[#563FD8] mt-5 rounded-lg w-full text-white p-2 ']");
    private By completedAnalysisButton = By.xpath("//button[normalize-space()='Completed Analysis']");
    private By downloadButton = By.xpath("//table//child::tbody//child::tr[last()]//child::td[last()]");
    private By profileMenuButton = By.xpath("//button[@aria-controls='profile-menu']");
    private By logout = By.xpath("//li[@tabindex='-1']");
    private By logoutConfirmButton = By.xpath("//button[text()='Yes']");

    public Ticketana() {
        System.setProperty("webdriver.edge.driver", "C:\\Users\\1000061697\\Downloads\\edgedriver_win64\\msedgedriver.exe");
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--inprivate");
        driver = new EdgeDriver(options);
        driver.manage().window().maximize();
    }
    
    private void uploadFile(String filePath) throws InterruptedException {
        WebElement fileInputElement = driver.findElement(fileInput);
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.display = 'block';", fileInputElement);
        fileInputElement.sendKeys(filePath);
        Thread.sleep(3000);
        driver.findElement(uploadButton).click();
        Thread.sleep(3000);
    }
    private void selectFieldsForAnalysis() throws InterruptedException {
        driver.findElement(category).click();
        driver.findElement(childCategory).click();
        driver.findElement(shortDesc).click();
        Thread.sleep(3000);
        driver.findElement(moveSelectedRightButton).click();
        Thread.sleep(3000);
    }


    @Given("Open url and enter username with {string} and password with {string} and accept terms & conditions and then click login")
    public void login(String username, String password) throws InterruptedException {
        driver.get("https://ticketanalyzer-demo.tensai.run/");
        Thread.sleep(3000);
        driver.findElement(Username).sendKeys(username);
        Thread.sleep(3000);
        driver.findElement(Password).sendKeys(password);
        Thread.sleep(3000);
        driver.findElement(Text).click();
        Thread.sleep(3000);
        driver.findElement(AcceptButton).click();
        Thread.sleep(3000);
        driver.findElement(loginButton).click();
        Thread.sleep(3000);
    }

    @Then("upload invalid file and then check the failure scenario")
    public void failure() throws InterruptedException {
        String filePath = System.getProperty("user.dir") + "/src/test/resources/testdatafiles/Car Lease Policy FAQs.pdf";
        uploadFile(filePath);
        driver.switchTo().alert().accept();
        Thread.sleep(3000);
    }

    @Then("upload the valid file and then select fileds for analysis")
    public void success() throws InterruptedException {
        String filePath = System.getProperty("user.dir") + "/src/test/resources/testdatafiles/Testdata_300.xlsx";
        uploadFile(filePath);
        driver.findElement(deleteIcon).click();
        Thread.sleep(3000);
        uploadFile(filePath);
        selectFieldsForAnalysis();
        driver.findElement(submitButton).click();
        Thread.sleep(3000);
        driver.switchTo().alert().accept();
    }
    @Then("proceed with Inprogress Analysis")
    public void inprogressAnalysis() throws InterruptedException {
        Thread.sleep(3 * 60 * 1000); // Waiting time for in-progress analysis
    }

    @Then("Proceed with completed analysis and download the generated report")
    public void completedAnalysis() throws InterruptedException {
        driver.findElement(completedAnalysisButton).click();
        Thread.sleep(3000);
        driver.findElement(downloadButton).click();
        Thread.sleep(3000);
        driver.findElement(profileMenuButton).click();
        Thread.sleep(3000);
        driver.findElement(logout).click();
        Thread.sleep(3000);
        driver.findElement(logoutConfirmButton).click();
        Thread.sleep(3000);
        driver.quit();
    }
}
