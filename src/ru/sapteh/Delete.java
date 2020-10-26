package ru.sapteh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class Delete {
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/worlds?serverTimezone=UTC";
    private static final String DB_USER ="user@localhost";
    private static final String DB_PASSWORD = "1111";


    public static void main (String [] args) throws ClassNotFoundException, SQLException, IOException {
        Class.forName(DB_DRIVER);

        String nameCountry = "";
        int quntryCity = 0;

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите id страны");
        int id_country = Integer.parseInt(bufferedReader.readLine());

        try (Connection connection = DriverManager.getConnection(DB_CONNECTION,DB_USER,DB_PASSWORD)) {
            String sql = "SELECT * FROM country WHERE id_country="+ id_country;
            PreparedStatement query = connection.prepareStatement(sql);
            ResultSet result1 = query.executeQuery();
            while (result1.next()){
                nameCountry = result1.getString("nameCountry ");
                quntryCity = result1.getInt("quntryCity");
            }

            String sqlDelete = "DELETE FROM country WHERE id_country=?";

            PreparedStatement statement = connection.prepareStatement(sqlDelete);
            statement.setInt(1,id_country);
            int result = statement.executeUpdate();

            if (result == 1 ){
                System.out.println("Страна"+ nameCountry + "имеющая"+ quntryCity + "города" +"c id " + id_country +"успешно удалена" );
            }
        }
    }
}




