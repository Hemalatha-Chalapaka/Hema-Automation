IDENTIFICATION DIVISION.
PROGRAM-ID. EmployeePayrollSystem.

DATA DIVISION.
WORKING-STORAGE SECTION.
01 Employee-Record.
   05 Employee-Name         PIC X(30).
   05 Hours-Worked          PIC 9(3).
   05 Hourly-Rate           PIC 9(5)V99.
   05 Gross-Pay             PIC 9(6)V99.
   05 Tax-Rate              PIC 9(3)V99 VALUE 0.10.
   05 Tax-Deduction         PIC 9(6)V99.
   05 Net-Pay               PIC 9(6)V99.

01 Total-Employees           PIC 99 VALUE ZEROS.

PROCEDURE DIVISION.

Main-Program.
    PERFORM Until-Exit-Loop
    STOP RUN.

Until-Exit-Loop.
    DISPLAY "Employee Payroll System"
    DISPLAY "Enter 1 to Add Employee, 2 to Calculate Payroll, 3 to Display Payroll Report, or 4 to Exit: ".
    ACCEPT Choice

    EVALUATE Choice
        WHEN 1
            PERFORM Add-Employee
        WHEN 2
            PERFORM Calculate-Payroll
        WHEN 3
            PERFORM Display-Payroll-Report
        WHEN 4
            EXIT PROGRAM
        WHEN OTHER
            DISPLAY "Invalid choice. Please enter a valid option (1-4)".
    END-EVALUATE.

Add-Employee.
    DISPLAY "Enter Employee Name (up to 30 characters): ".
    ACCEPT Employee-Name
    DISPLAY "Enter Hours Worked: ".
    ACCEPT Hours-Worked
    DISPLAY "Enter Hourly Rate (e.g., 15.50): ".
    ACCEPT Hourly-Rate

    ADD 1 TO Total-Employees
    DISPLAY "Employee added successfully.".

Calculate-Payroll.
    IF Total-Employees = 0
        DISPLAY "No employees added yet."
    ELSE
        PERFORM Varying Employee-Name FROM 1 BY 1 UNTIL Employee-Name > Total-Employees
            COMPUTE Gross-Pay = Hours-Worked * Hourly-Rate
            COMPUTE Tax-Deduction = Gross-Pay * Tax-Rate
            COMPUTE Net-Pay = Gross-Pay - Tax-Deduction
        END-PERFORM
        DISPLAY "Payroll calculation complete."
    END-IF.

Display-Payroll-Report.
    IF Total-Employees = 0
        DISPLAY "No employees added yet."
    ELSE
        DISPLAY "Payroll Report:"
        PERFORM Varying Employee-Name FROM 1 BY 1 UNTIL Employee-Name > Total-Employees
            DISPLAY "Employee Name: " Employee-Name
            DISPLAY "Hours Worked: " Hours-Worked
            DISPLAY "Hourly Rate: $" Hourly-Rate
            DISPLAY "Gross Pay: $" Gross-Pay
            DISPLAY "Tax Deduction: $" Tax-Deduction
            DISPLAY "Net Pay: $" Net-Pay
        END-PERFORM
    END-IF.

    EXIT.

END PROGRAM EmployeePayrollSystem.
