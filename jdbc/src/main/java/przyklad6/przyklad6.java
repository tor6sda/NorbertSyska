package przyklad6;

import przyklad5.Country;
import util.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class przyklad6 {
    public static void main(String[] args) {
        String sqlSelect = "select * from address join country on add_co_id = co_id";

        List<Address> addressList = new ArrayList<>();
        try (PreparedStatement preparedStatement = DatabaseConnection.getInstance().getConnection().prepareStatement(sqlSelect)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Address address = new Address();
                address.setAddressId(resultSet.getInt("ADD_ID"));
                address.setAddressStreet(resultSet.getString("ADD_STREET"));
                address.setAddressApartamentNo(resultSet.getString("ADD_APARTAMENT_NO"));
                address.setAddressBuildingNo(resultSet.getString("ADD_BUILDING_NO"));
                address.setAddressCity(resultSet.getString("ADD_CITY"));
                address.setAddressPostalCode(resultSet.getString("ADD_POSTAL_CODE"));
                address.setAddressCountry(new Country(resultSet.getInt("CO_ID"),
                        resultSet.getString("CO_NAME"),
                        resultSet.getString("CO_ALIAS")));
                addressList.add(address);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        addressList.forEach(address -> System.out.println("Address Id: "+address.getAddressId()+
                " Address Street: "+address.getAddressStreet()+
                " Address City: "+address.getAddressCity()+
                " Address Country: "+address.getAddressCountry().getCountryName()));

    }
}
