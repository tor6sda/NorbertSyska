package przyklad5;

import util.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class przyklad5 {
    public static void main(String[] args) {
        String sqlInsert = "Insert into country " +
                "co_name,co_alias+" +
                "values(?,?)";
        Country[] countries = {new Country("Albania", "AL"),
                new Country("China", "CH"),
                new Country("Ukraine", "UE")};

        try (PreparedStatement prpStatement = DatabaseConnection.getInstance().getConnection().prepareStatement(sqlInsert)) {
            for (int i = 0; i < countries.length; i++) {
                prpStatement.setString(1, countries[i].getCountryName());
                prpStatement.setString(1, countries[i].getCountryAlias());
                prpStatement.execute();
            }
            DatabaseConnection.getInstance().getConnection().commit();
        } catch (SQLException e) {
            try {
                DatabaseConnection.getInstance().getConnection().commit();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }

    }
}
