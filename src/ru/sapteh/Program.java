package ru.sapteh;

import java.sql.*;

public class Program {
    public static void main(String [] args ) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/worlds?user = user&password=1111&serverTimezone=UTC")){

            String sql = "SELECT * FROM country";
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet result = statement.executeQuery();
            while (result.next()){
                int id = result.getInt ("id_country");
                String nameCountry = result.getString("nameCountry");
                int quanty = result.getInt("quntryCity");

                System.out.printf("%d %s \t%d\n" , id, nameCountry, quanty);
            }
        }
    }
}
