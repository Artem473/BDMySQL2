package ru.sapteh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Updata {

    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/worlds?serverTimezone=UTC";
    private static final String DB_USER ="user@localhost";
    private static final String DB_PASSWORD = "1111";

    public static void main (String [] args) throws ClassNotFoundException, SQLException, IOException {
        Class.forName(DB_DRIVER);


        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите название страны");
            String nameCountry = bufferedReader.readLine();
        System.out.println("Введите количество городов ");
            int quntryCity = Integer.parseInt(bufferedReader.readLine());
        System.out.println("Введите id страны ");
            int id_country = Integer.parseInt(bufferedReader.readLine());


        try (Connection connection = DriverManager.getConnection(DB_CONNECTION,DB_USER,DB_PASSWORD)) {
            String sqlInsert = "UPDATE country set nameCountry=?, quntryCity=?  WHERE id_country= ?";

            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            PreparedStatement statement = connection.prepareStatement(sqlInsert);
            statement.setString(1,nameCountry);
            statement.setInt(2,quntryCity);
            statement.setInt(3,id_country);
            int result = statement.executeUpdate();

            if (result == 1 ){
                connection.commit();
                System.out.println("Данные успешно обновлены  ");
            }
        }
    }
}


