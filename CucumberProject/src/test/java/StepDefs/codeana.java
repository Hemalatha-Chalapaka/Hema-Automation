package StepDefs;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import io.cucumber.java.en.*;

public class codeana {

    WebDriver driver;

    // XPath variables
    private By ssoButton = By.xpath("//button[@class ='sso-button']");
    private By emailInput = By.xpath("//input[@type='email']");
    private By nextButton = By.xpath("//input[@type='submit']");
    private By passwordInput = By.xpath("//input[@name='passwd']");
    private By signInButton = By.xpath("//input[@id='idSIButton9']");
    private By confirmButton = By.xpath("//input[@id='idBtn_Back']");
    private By addNewProject = By.xpath("//button[@aria-label ='Add New Project']");
    private By projectNameInput = By.xpath("//input[@type='text']");
    private By errorMessage = By.xpath("//p[@class='text-red-500']");
    private By fileInput = By.xpath("//input[@type='file']");
    private By uploadButton = By.xpath("//button[text()='UPLOAD']");
    private By proceedButton = By.xpath("//button[text()='Proceed']");
    private By logoutOption = By.xpath("//li");

    public codeana() {
    	System.setProperty("webdriver.edge.driver", "C:\\Users\\1000061697\\Downloads\\edgedriver_win64\\msedgedriver.exe");
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--inprivate");
        driver = new EdgeDriver(options);
        driver.manage().window().maximize();
    }

    @Given("Open url and click sso button")
    public void openUrlAndClickSSO() throws InterruptedException {
        driver.get("https://codeanalyzer-demo.tensai.run/");
        driver.findElement(ssoButton).click();
        Thread.sleep(3000);
    }

    @Then("enter emailaddress with {string} and click next button and enter password with {string} and click signin button and then click No")
    public void enterCredentials(String email, String password) throws InterruptedException {
        driver.findElement(emailInput).sendKeys(email);
        Thread.sleep(3000);
        driver.findElement(nextButton).click();
        Thread.sleep(3000);
        driver.findElement(passwordInput).sendKeys(password);
        Thread.sleep(3000);
        driver.findElement(signInButton).click();
        Thread.sleep(3000);
        driver.findElement(confirmButton).click();
        Thread.sleep(3000);
    }

    @Then("click add new project and enter project name with {string} and verify error message with {string} and then clear input")
    public void addNewProjectAndVerifyError(String projectName, String expectedMessage) throws InterruptedException {
        driver.findElement(addNewProject).click();
        Thread.sleep(3000);
        System.out.println("Add New Project button clicked");
        WebElement name1 = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div[1]/input"));
        name1.sendKeys(projectName);
        System.out.println("Entered project name: " + projectName);
        WebElement msg1 = driver.findElement(By.xpath("//p[@class='text-red-500']"));
        String actualMessage = msg1.getText();
        System.out.println("Actual error message: " + actualMessage);
        if (actualMessage.equals(expectedMessage)) {
            System.out.println("Error message verified: " + actualMessage);
        }
        name1.clear();
    }


    @Then("check for existing project and enter project name with {string} and verify error message with {string} and then clear input")
    public void checkForExistingProject(String projectName, String expectedMessage) throws InterruptedException {
        WebElement projectNameElement2 = driver.findElement(projectNameInput);
        projectNameElement2.sendKeys(projectName);
        Thread.sleep(3000);
        String actualMessage = driver.findElement(errorMessage).getText();
        if (actualMessage.equals(expectedMessage)) {
            System.out.println("Error message verified: " + actualMessage);
        }
        projectNameElement2.clear();
    }

    @Then("enter valid projectname with {string}")
    public void enterValidProjectName(String projectName) {
        driver.findElement(addNewProject).click();
        WebElement projectNameElement = driver.findElement(projectNameInput);
        projectNameElement.sendKeys(projectName);
    }

    @Then("upload the invalid file")
    public void uploadInvalidFile() throws InterruptedException {
        String filePath = System.getProperty("user.dir") + "/src/test/resources/testdatafiles/Car Lease Policy FAQs.pdf";
        WebElement fileInputElement = driver.findElement(fileInput);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.display = 'block';", fileInputElement);
        fileInputElement.sendKeys(filePath);
        Thread.sleep(1000);
        WebElement uploadButtonElement = driver.findElement(uploadButton);
        uploadButtonElement.click();
        Thread.sleep(3000);
        WebElement proceedButtonElement = driver.findElement(proceedButton);
        proceedButtonElement.click();
    }

    @Then("create valid project and upload valid files and proceed next steps")
    public void createValidProjectAndProceed() throws InterruptedException {
        String files = System.getProperty("user.dir") + "/src/test/resources/testdatafiles/Pythonfile.py\n" + System.getProperty("user.dir") + "/src/test/resources/testdatafiles/Cobolfile.cbl";
        WebElement projectNameElement = driver.findElement(projectNameInput);
        projectNameElement.sendKeys("AutomateTest");
        WebElement fileInputElement = driver.findElement(fileInput);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.display = 'block';", fileInputElement);
        fileInputElement.sendKeys(files);
        WebElement uploadButtonElement = driver.findElement(uploadButton);
        uploadButtonElement.click();
        WebElement proceedButtonElement = driver.findElement(proceedButton);
        proceedButtonElement.click();
        Thread.sleep(3000);
    }

    @Then("logout of code analyzer")
    public void logout() {
        WebElement logout = driver.findElement(logoutOption);
        logout.click();
        driver.quit();
    }
}
