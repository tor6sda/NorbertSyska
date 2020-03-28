import util.DatabaseConnection;

import java.sql.*;

public class przyklad2 {
    public static void main(String[] args) {
        String query = "SELECT * from address where ADD_CITY like \"%\"?\"%\";";
        try (
                PreparedStatement preStatement = DatabaseConnection.getInstance().getConnection().prepareStatement(query)
        )
        {
            preStatement.setString(1,"Paris");
            ResultSet resultSet = preStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("ADD_ID");
                String street = resultSet.getString("Add_street");
                String city = resultSet.getString("Add_city");
                System.out.print(id +"  "+street+"  "+city);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
