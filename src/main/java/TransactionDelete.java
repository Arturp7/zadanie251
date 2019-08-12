import java.util.Scanner;

public class TransactionDelete {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj id  wpisu, do usunięcia");
        long id = scanner.nextLong();

        TransactionDao transactionDao = new TransactionDao();

        transactionDao.deleteById(id);
        System.out.println("Usunięto");
    }
}
