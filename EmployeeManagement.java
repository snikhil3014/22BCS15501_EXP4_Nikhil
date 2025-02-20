import java.util.Scanner;

public class EmployeeManagement {
    static final int MAX_EMPLOYEES = 10;
    static String[][] employees = new String[MAX_EMPLOYEES][4];
    static int employeeCount = 0;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.print("\n\t\tEmployee Management System\n"
                    + "1. Add\n"
                    + "2. View\n"
                    + "3. Update\n"
                    + "4. Delete\n"
                    + "5. Search\n"
                    + "6. Exit\n"
                    + "Choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    viewAllEmployees();
                    break;
                case 3:
                    updateEmployee();
                    break;
                case 4:
                    deleteEmployee();
                    break;
                case 5:
                    searchEmployee();
                    break;
                case 6:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 6);
        scanner.close();
    }

    public static void addEmployee() {
        if (employeeCount >= MAX_EMPLOYEES) {
            System.out.println("Employee list full.");
            return;
        }

        System.out.print("\nID: ");
        employees[employeeCount][0] = scanner.next();
        scanner.nextLine();

        System.out.print("Name: ");
        employees[employeeCount][1] = scanner.nextLine();

        System.out.print("Designation: ");
        employees[employeeCount][2] = scanner.nextLine();

        System.out.print("Salary: ");
        employees[employeeCount][3] = scanner.next();

        employeeCount++;
        System.out.println("Employee added!");
    }

    public static void viewAllEmployees() {
        if (employeeCount == 0) {
            System.out.println("\nNo employees.");
            return;
        }

        System.out.printf("\n%-8s %-20s %-15s %-10s%n", "ID", "Name", "Designation", "Salary");
        for (int i = 0; i < employeeCount; i++) {
            System.out.printf("%-8s %-20s %-15s $%-10s%n",
                    employees[i][0], employees[i][1], employees[i][2], employees[i][3]);
        }
    }

    public static void updateEmployee() {
        System.out.print("\nEnter ID to update: ");
        String id = scanner.next();
        scanner.nextLine();

        for (int i = 0; i < employeeCount; i++) {
            if (employees[i][0].equals(id)) {
                System.out.printf("\nCurrent: Name=%s, Role=%s, Salary=$%s\n",
                        employees[i][1], employees[i][2], employees[i][3]);

                System.out.print("New Name [Enter to skip]: ");
                String input = scanner.nextLine();
                if (!input.isEmpty()) employees[i][1] = input;

                System.out.print("New Role [Enter to skip]: ");
                input = scanner.nextLine();
                if (!input.isEmpty()) employees[i][2] = input;

                System.out.print("New Salary [Enter to skip]: ");
                input = scanner.nextLine();
                if (!input.isEmpty()) employees[i][3] = input;

                System.out.println("Updated!");
                return;
            }
        }
        System.out.println("Employee not found.");
    }

    public static void deleteEmployee() {
        System.out.print("\nEnter ID to delete: ");
        String id = scanner.next();

        for (int i = 0; i < employeeCount; i++) {
            if (employees[i][0].equals(id)) {
                System.out.printf("\nDelete %s (%s)? [y/n]: ", employees[i][1], employees[i][0]);
                if (scanner.next().equalsIgnoreCase("y")) {
                    System.arraycopy(employees, i + 1, employees, i, employeeCount - i - 1);
                    employees[--employeeCount] = new String[4];
                    System.out.println("Deleted!");
                }
                return;
            }
        }
        System.out.println("Employee not found.");
    }

    public static void searchEmployee() {
        System.out.print("\nEnter ID to search: ");
        String id = scanner.next();

        for (int i = 0; i < employeeCount; i++) {
            if (employees[i][0].equals(id)) {
                System.out.printf("\nID: %s\nName: %s\nRole: %s\nSalary: Rs. %s\n",
                        employees[i][0], employees[i][1], employees[i][2], employees[i][3]);
                return;
            }
        }
        System.out.println("Employee not found.");
    }
}