import java.util.Scanner;

public class TransactionRead {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj typ wpisu, wydatek lub przychód");
        String type = scanner.nextLine();

        TransactionDao transactionDao = new TransactionDao();

        Transaction transaction = transactionDao.findByType(type);
        System.out.println(transaction);
    }
}
