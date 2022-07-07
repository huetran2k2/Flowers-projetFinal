package com.example.tranhue_flowersproject.DBconnection;

import com.example.tranhue_flowersproject.models.*;
import com.example.tranhue_flowersproject.models.Flowers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBConnection {
    private final Connection connection;

    public static final String URL = "jdbc:mysql://localhost/flower_shop";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";

    public DBConnection(){
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connect successfully!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Flowers> getFlowers(){
        ArrayList<Flowers> list = new ArrayList<>();
        String sql = "SELECT b.id, b.id_mf, b.name as flowerName, b.image, b.price, b.quantity, b.description, c.name_mf as manufacturerName FROM flowers as b LEFT JOIN manufacturer as c ON b.id_mf = c.id ORDER BY b.id ASC";
        try {
            ResultSet results = connection.prepareStatement(sql).executeQuery();
            while (results.next()){
                Flowers flower = new Flowers(
                        results.getInt("id"),
                        results.getString("flowerName"),
                        results.getString("image"),
                        new Manufacturer(results.getInt("id"), results.getString("manufacturerName")),

                        results.getInt("price"),
                        results.getInt("quantity"),
                        results.getString("description")
                );
                System.out.println(flower);
                list.add(flower);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public ArrayList<Manufacturer> getListManufacturer () {
        ArrayList<Manufacturer> listManufacturer = new ArrayList<>();
        String sql = "SELECT * FROM manufacturer";
        try {
            ResultSet result = connection.prepareStatement(sql).executeQuery();
            while (result.next()){
                Manufacturer manufacturer = new Manufacturer(
                        result.getInt("id"),
                        result.getString("name_mf")
                );
                listManufacturer.add(manufacturer);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listManufacturer;
    }

    public Flowers getFlowerUpdate (int id) {
        String sql = "SELECT b.id, b.id_mf, b.name as flowerName, b.image, b.price, b.quantity, b.description, c.name_mf as manufacturerName FROM flowers as b LEFT JOIN manufacturer as c ON b.id_mf = c.id WHERE id = " + id;
        Flowers flowers = null;
        try {
            ResultSet result = connection.prepareStatement(sql).executeQuery();
            while (result.next()) {
               flowers = new Flowers (
                        result.getString("name"),
                        result.getString("image"),
                        new Manufacturer(result.getInt("id"), result.getString("name_mf")),

                        result.getInt("price"),
                        result.getInt("quantity"),
                        result.getString("description"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(flowers.getName_mf().getName_mf());
        return flowers;
    }
    public void insertFlower(Flowers flower){
        String sql = "INSERT INTO flowers (name, image, id_mf, price, quantity, description) VALUE ('"+ flower.getName() +"','"+ flower.getImage() +"','"+flower.getName_mf().getId()+"','"+ flower.getPrice() +"','"+ flower.getQuantity() +"','"+ flower.getDescription() +"')";
        System.out.println(sql);
        try {
            connection.prepareStatement(sql).executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateFlower(int id, Flowers flower){
        String sql = "UPDATE flowers SET name = '"+ flower.getName() +"',image = '"+ flower.getImage() +"', id = "+flower.getName_mf().getId()+",price = "+ flower.getPrice() +", quantity ="+ flower.getQuantity() +", description = '"+ flower.getDescription() +"' WHERE id = "+ id;
        System.out.println(sql);
        try {
            connection.prepareStatement(sql).executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteFlower(int id){
        String sql = "DELETE FROM flowers WHERE id = "+ id;
        try {
            connection.prepareStatement(sql).executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Admin> getAdmin() {
        ArrayList<Admin> admins = new ArrayList<>();
        try {
            var result = this.connection.prepareStatement("Select * from admin").executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("userName");
                String password = result.getString("password");
                admins.add(new Admin(id, name, password));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return admins;
    }
}