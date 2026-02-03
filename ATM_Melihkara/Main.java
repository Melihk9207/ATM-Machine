import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Verify verify = new Verify();
        FileManager fileManager = new FileManager();
        ATM atm = new ATM(fileManager);

        atm.readCashFile();

        System.out.print("Login: ");
        String login = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (!verify.check(login, password)) {
            System.out.println("Wrong login or password");
            return;
        }

        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1 - Deposit");
            System.out.println("2 - Withdraw");
            System.out.println("0 - Exit");
            System.out.print("Choice: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid choice");
                continue;
            }

            switch (choice) {
                case 1:
                    // Deposit
                    try {
                        System.out.print("Banknote value: ");
                        int value = Integer.parseInt(scanner.nextLine());

                        System.out.print("Number of pieces: ");
                        int pieces = Integer.parseInt(scanner.nextLine());

                        atm.Deposit(value, pieces);
                    } catch (Exception e) {
                        System.out.println("Invalid deposit input");
                    }
                    break;

                case 2:
                    try {
                        System.out.print("Amount to withdraw: ");
                        int amount = Integer.parseInt(scanner.nextLine());

                        boolean okay = atm.Withdraw(amount);
                        if (okay)
                            System.out.println("Withdraw completed");
                        else
                            System.out.println("Withdraw refused (not enough money or no combination)");
                    } catch (Exception e) {
                        System.out.println("Invalid withdraw input");
                    }
                    break;

                case 0:
                    System.out.println("Exit");
                    break;

                default:
                    System.out.println("Invalid choice");
            }

            if (choice == 0) {
                break;
            }
        }

        scanner.close();
    }
}