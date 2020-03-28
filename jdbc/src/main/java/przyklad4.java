import util.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class przyklad4 {
    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder("? ");
        String questionMark = ",?";
        int tablica[] = new int[]{1,3};

        for (int i=1; i<tablica.length;i++){
            stringBuilder.append(questionMark);
        }
        String questy = " select * from category where cat_id in ("+stringBuilder.toString()+")";

        try (PreparedStatement preStatement = DatabaseConnection.getInstance().getConnection().prepareStatement(questy))
        {
            for (int i =0;i<tablica.length;i++){
                preStatement.setInt(i+1,tablica[i]);
            }
            ResultSet resultSet = preStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("cat_id");
                String name = resultSet.getString("cat_name");
                System.out.println(id + "  "+name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
