import java.util.ArrayList;

// Interface to enforce salary calculation
interface Payable {
    double calculateSalary();
}

// Abstract class Employee implements Payable
abstract class Employee implements Payable {
    private String name;
    private int id;

    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    // Abstract method to calculate salary
    public abstract double calculateSalary();

    @Override
    public String toString() {
        // Better formatted output
        return String.format("Employee [Name: %-10s | ID: %3d | Salary: %.2f]", name, id, calculateSalary());
    }
}

// FullTimeEmployee class
class FullTimeEmployee extends Employee {
    private double monthlySalary;
    private double bonus; // new field added

    public FullTimeEmployee(String name, int id, double monthlySalary) {
        super(name, id);
        this.monthlySalary = monthlySalary;
        this.bonus = 0; // Default bonus 0
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    @Override
    public double calculateSalary() {
        return monthlySalary + bonus;
    }
}

// PartTimeEmployee class
class PartTimeEmployee extends Employee {
    private int hoursWorked;
    private double hourlyRate;

    public PartTimeEmployee(String name, int id, int hoursWorked, double hourlyRate) {
        super(name, id);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculateSalary() {
        if (hoursWorked > 40) {
            int overtimeHours = hoursWorked - 40;
            return (40 * hourlyRate) + (overtimeHours * hourlyRate * 1.5); // Overtime 1.5x rate
        }
        return hoursWorked * hourlyRate;
    }
}

// PayrollSystem class
class PayrollSystem {
    private ArrayList<Employee> employeeList;

    public PayrollSystem() {
        employeeList = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    public void removeEmployee(int id) {
        Employee employeeToRemove = null;
        for (Employee employee : employeeList) {
            if (employee.getId() == id) {
                employeeToRemove = employee;
                break;
            }
        }
        if (employeeToRemove != null) {
            employeeList.remove(employeeToRemove);
        }
    }

    public void displayEmployees() {
        for (Employee employee : employeeList) {
            System.out.println(employee);
        }
    }

    // New Method: calculate total salary expenses
    public double totalSalaryExpense() {
        double total = 0;
        for (Employee employee : employeeList) {
            total += employee.calculateSalary();
        }
        return total;
    }
}

// Main class to run the program
public class PayRollSystem {
    public static void main(String[] args) {
        PayrollSystem payrollSystem = new PayrollSystem();

        // Creating employees
        FullTimeEmployee emp1 = new FullTimeEmployee("Gourav", 1, 1000000);
        emp1.setBonus(50000); // Giving bonus to full time employee

        PartTimeEmployee emp2 = new PartTimeEmployee("Jaydeep", 2, 45, 500); // 45 hours worked

        // Adding employees
        payrollSystem.addEmployee(emp1);
        payrollSystem.addEmployee(emp2);

        System.out.println("Initial Employee details:");
        payrollSystem.displayEmployees();

        System.out.println("\nTotal Salary Expense: " + payrollSystem.totalSalaryExpense());

        System.out.println("\nRemoving employee:");
        payrollSystem.removeEmployee(emp2.getId());

        System.out.println("\nEmployee details after removal:");
        payrollSystem.displayEmployees();
    }
}
