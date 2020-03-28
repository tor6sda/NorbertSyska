import java.sql.*;

public class zad1 {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/shop?serverTimezone=CET";
        String user= "root";
        String password = "test";
        String query = "SELECT * from user";
        try (
                Connection connection = DriverManager.getConnection(url,user,password);
                Statement statement = connection.createStatement()
        )
        {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                System.out.print(resultSet.getString("USR_FIRSTNAME")+"  ");
                System.out.print(resultSet.getString("USR_LASTNAME")+"  \n");
              //  System.out.print(resultSet.getString("CO_ALIAS")+"  \n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
