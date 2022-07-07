package com.example.pnvproject.dataa;

import com.example.pnvproject.models.Flowers;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBConnect {
    private Connection connection;

    public static final String URL = "jdbc:mysql://localhost/hujavafx";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";

    public DBConnect(){
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connect successfully!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Flowers> getFlowers(){
        ArrayList<Flowers> list = new ArrayList<>();
        String sql = "SELECT * FROM flowers";
        try {
            ResultSet results = connection.prepareStatement(sql).executeQuery();
            while (results.next()){

                Flowers flowers = new Flowers(
                        results.getInt("id"),
                        results.getString("name"),
                        results.getString("image"),
                        results.getInt("price"),
                        results.getString("description")
                );
                list.add(flowers);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
    public void insertFlowers(Flowers flowers){
        String sql = "INSERT INTO flowers (name, image, price, description) VALUE ('"+flowers.name+"','"+flowers.image+"','"+flowers.price+"','"+flowers.description+"')";
        try {
            connection.prepareStatement(sql).executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateFlowers(Flowers flowers){
        String sql = "UPDATE flowers SET name = '"+ flowers.name +"', image = '"+flowers.image+"', price = '"+flowers.price+"', description = '"+flowers.description+"' WHERE id = "+ flowers.id;
        try {
            connection.prepareStatement(sql).executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteFlowers(int id){
        String sql = "DELETE FROM flowers WHERE id = "+ id;
        try {
            connection.prepareStatement(sql).executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
