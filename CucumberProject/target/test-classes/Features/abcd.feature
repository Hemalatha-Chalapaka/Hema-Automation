Feature: Testing code analyzer and Ticket Analyzer applications

  @CodeAnalyzer
  Scenario Outline: Code Analyzer
    Given Open url and click sso button
    Then enter emailaddress with "<email>" and click next button and enter password with "<password>" and click signin button and then click No
    Then click add new project and enter project name with "<projectName>" and verify error message with "<expectedMessage>" and then clear input
    Then check for existing project and enter project name with "Testing111" and verify error message with "Project name already exists. Please choose a different name." and then clear input
    Then enter valid projectname with "Hema"
    Then upload the invalid file
    Then create valid project and upload valid files and proceed next steps
    Then logout of code analyzer

    Examples:
      | email                  | password     | projectName | expectedMessage |
      | 1000061697@hexaware.com| Hema@1357971 | dfe@ef!@   | Please enter only alphabets and numbers. No special characters allowed! |

  @TicketAnalyzer
  Scenario Outline: Ticket Analyzer
    Given Open url and enter username with "<Username>" and password with "<Password>" and accept terms & conditions and then click login
    Then upload invalid file and then check the failure scenario
    Then upload the valid file and then select fileds for analysis
    Then proceed with Inprogress Analysis
    Then Proceed with completed analysis and download the generated report
    
    Examples:
  | Username   | Password       |
  | 1000077777 | Hexaware@123   |
  | 1000055555 | Hexaware@123   |