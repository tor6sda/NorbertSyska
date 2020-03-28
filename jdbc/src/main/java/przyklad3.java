import util.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class przyklad3 {
    public static void main(String[] args) {
        String sqlupdate = "update address"+
                " SET add_building_no = ?"+
                " where add_postal_code = ?";
        String query = "select * from address";
        try (PreparedStatement preparedStatement = DatabaseConnection.getInstance().getConnection().prepareStatement(sqlupdate))
        {
            preparedStatement.setString(1,"10");
            preparedStatement.setString(2,"38600");
            int coutUpdatedRecords = preparedStatement.executeUpdate();

            System.out.println("Zaktualizowano "+coutUpdatedRecords+" wierszy");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try ( PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(query)){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("Add_id");
                String street = resultSet.getString("add_street");
                String buildingNo = resultSet.getString("add_building_no");
                String postalcode = resultSet.getString("add_postal_code");
                System.out.println(id + "  " + street+"  "+buildingNo+ "  "+postalcode);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
