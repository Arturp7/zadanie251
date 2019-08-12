import java.sql.*;

public class TransactionDao {


    public void insert(Transaction transaction) {
        Connection connection = connect();

        String sql = "INSERT INTO transaction(type, description, amount, date ) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,transaction.getType());
            preparedStatement.setString(2,transaction.getDescription());
            preparedStatement.setDouble(3,transaction.getAmount());
            preparedStatement.setString(4,transaction.getDate());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Niepowodzenie podczas zapisu do bazy  " + e.getMessage());
        }

        closeConnection(connection);
    }

    public Transaction findByType(String type) {
        Connection connection = connect();

        PreparedStatement preparedStatement = null;
        try {
            String sql = "SELECT * FROM transaction WHERE type = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,type);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                long id = resultSet.getLong("id");
                String typeFromDb = resultSet.getString("type");
                String description = resultSet.getString("description");
                double amount = resultSet.getDouble("amount");
                String date = resultSet.getString("date");
                Transaction transaction = new Transaction(id, typeFromDb, description, amount, date);
                return transaction;
            }

        } catch (SQLException e) {
            System.out.println("Niepowodzenie  " + e.getMessage());
        }

        closeConnection(connection);

        return null;

    }
    public void update(Transaction transaction) {
        Connection connection = connect();

        String sql = "UPDATE transaction SET type = ? , description = ?, amount = ? , date = ? WHERE  id = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,transaction.getType());
            preparedStatement.setString(2,transaction.getDescription());
            preparedStatement.setDouble(3,transaction.getAmount());
            preparedStatement.setString(4,transaction.getDate());
            preparedStatement.setLong(5,transaction.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Niepowodzenie podczas zapisu do bazy  " + e.getMessage());
        }

        closeConnection(connection);
    }

    public void deleteById(long id) {
        Connection connection = connect();

        PreparedStatement preparedStatement = null;
        try {
            String sql = "DELETE FROM transaction WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            System.out.println("Niepowodzenie  " + e.getMessage());
        }

        closeConnection(connection);


    }

    private void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private Connection connect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String url = "jdbc:mysql://localhost:3306/budget?serverTimezone=UTC&charackterEncoding=utf8";
        try {
            return  DriverManager.getConnection(url, "root", "root");
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;

    }



}
