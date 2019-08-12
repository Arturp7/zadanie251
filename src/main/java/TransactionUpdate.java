import java.util.Scanner;

public class TransactionUpdate {

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj id wpisu który chcesz modyfikować");
        long id = scanner.nextLong();
        scanner.nextLine();


        System.out.println("Podaj typ wpisu, wydatek lub przychód");
        String type = scanner.nextLine();

        System.out.println("Opisz transakcję");
        String description = scanner.nextLine();

        System.out.println("Podaj kwotę transakcji");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Podaj datę transakcji w formacie YYYY-MM-DD");
        String date = scanner.nextLine();

        Transaction transaction = new Transaction(id, type, description, amount, date);

        TransactionDao transactionDao = new TransactionDao();

        transactionDao.update(transaction);

    }
}
