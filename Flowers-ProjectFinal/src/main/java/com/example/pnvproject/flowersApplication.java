package com.example.pnvproject;

import com.example.pnvproject.dataa.DBConnect;
import com.example.pnvproject.models.Flowers;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.StageStyle;
import java.sql.*;

public class flowersApplication extends Application {

    private Scene scene;
    private static final String EMPTY = "";
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);
        DBConnect DB = new DBConnect();
        ArrayList<Flowers> paintingList = DB.getFlowers();

        grid.add(new Label("Name:"), 0, 0);
        var tfName = new TextField();
        grid.add(tfName, 0, 1);
        //
        grid.add(new Label("Image:"), 1, 0);
        var tfImage = new TextField();
        grid.add(tfImage, 1, 1);
        //
        grid.add(new Label("Price:"), 2, 0);
        var tfPrice = new TextField();
        grid.add(tfPrice, 2, 1);
        //
        grid.add(new Label("Description:"),3,  0);
        var tfDescription = new TextField();
        grid.add(tfDescription, 3, 1);
    //add
        var btnAdd = new Button("Add");
        btnAdd.setPadding(new Insets(5, 15, 5, 15));
        btnAdd.setOnAction(e -> {
            String name = tfName.getText();
            String image = tfImage.getText();
            Integer price = Integer.valueOf(tfPrice.getText());
            String description = tfDescription.getText();
            if (!name.equals(EMPTY) && !image.equals(EMPTY) && !price.equals(EMPTY) && !description.equals(EMPTY)) {
                DB.insertFlowers(new Flowers(name, image, price, description));
                try {
                    start(stage);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                return;
            }
            var alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank!");
            alert.showAndWait();
        });
        grid.add(btnAdd, 4, 1);

        //show
        for(int i = 0; i < paintingList.size(); i++){

            Image image = new Image(paintingList.get(i).getImage());
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            imageView.setFitWidth(110);
            imageView.setFitHeight(110);

            grid.add(new Label (paintingList.get(i).getName()), 0, i+2);
            grid.add(imageView, 1, i+2);
            grid.add(new Label ("$"+String.valueOf(paintingList.get(i).getPrice())), 2, i+2);
            grid.add(new Label (paintingList.get(i).getDescription()), 3, i+2);

            // Update
            var btnUpdate = new Button("Update");
            btnUpdate.setId(String.valueOf(i));
            btnUpdate.setOnAction(e -> {
                btnAdd.setVisible(false);
                int id1 = Integer.parseInt(btnUpdate.getId());
                TextField tfname = (TextField) grid.getChildren().get(1);
                tfname.setText("" + paintingList.get(id1).getName());
                TextField tfimage = (TextField) grid.getChildren().get(3);
                tfimage.setText("" + paintingList.get(id1).getImage());
//                name.setText(stdList.get(Integer.parseInt(btnUpdate.getId())).getName());
                TextField tfprice = (TextField) grid.getChildren().get(5);
                tfprice.setText("" + paintingList.get(id1).getPrice());
                TextField tfdescription = (TextField) grid.getChildren().get(7);
                tfdescription.setText("" + paintingList.get(id1).getDescription());
                var newbtnAdd = new Button("Update");
                newbtnAdd.setPadding(new Insets(5, 15, 5, 15));
                newbtnAdd.setOnAction(newe -> {
                    Integer Newid = paintingList.get(id1).id;
                    String Newname = tfname.getText();
                    String Newimage = tfimage.getText();
                    Integer Newprice = Integer.valueOf(tfprice.getText());
                    String Newdescription = tfdescription.getText();
                    if (!Newname.equals(EMPTY) && !Newimage.equals(EMPTY) && !Newprice.equals(EMPTY) && !Newdescription.equals(EMPTY)) {
                        DB.updateFlowers(new Flowers(Newid, Newname, Newimage, Newprice, Newdescription));
                        try {
                            start(stage);
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                        return;
                    }
                    var alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Please fill all blank!");
                    alert.showAndWait();
                });
                grid.add(newbtnAdd, 4, 1);
            });
            grid.add(btnUpdate, 4, i+2);

            // Delete
            var btnDelete = new Button("Delete");
            btnDelete.setId(String.valueOf(paintingList.get(i).id));
            btnDelete.setOnAction(e -> {
                int id3 = Integer.parseInt(btnDelete.getId());
                DB.deleteFlowers(id3);
                var alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Deleted!");
                alert.showAndWait();
                try {
                    start(stage);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            });
            grid.add(btnDelete, 5, i+2);
        }

        scene = new Scene(grid, 1500, 1000);
        stage.setTitle("Painting");
        stage.setScene(scene);
        stage.show();
    }
}
