import util.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class zad2 {
    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder("? ");
        String questionMark = ",?";
        int tablica[] = new int[]{1,3,5};

        for (int i=1; i<tablica.length;i++){
            stringBuilder.append(questionMark);
        }
        String questy = " update product "+
                "set pro_description = CONCAT(pro_description, ? )"+
                "where pro_id in ("+stringBuilder.toString()+")";

        try (PreparedStatement preStatement = DatabaseConnection.getInstance().getConnection().prepareStatement(questy))
        {
            preStatement.setString(1,"brak");
            for (int i =1;i<tablica.length;i++){
                preStatement.setInt(i+1,tablica[i]);
            }
            ResultSet resultSet = preStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("pro_id");
                String name = resultSet.getString("pro_name");
                String description = resultSet.getString("pro_description");
                System.out.println(id + "  "+name+"  "+ description);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
