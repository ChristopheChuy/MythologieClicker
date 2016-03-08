/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package launch;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Christophe CHUY , Nicolas LAS , Ugo SERRANO,Sylvain ESCASSUT
 */
public class MythologyClicker extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/vue/FXMLDocument.fxml"));
        stage.getIcons().add(new Image("image/G_Ico_Zeus.jpg"));
        Scene scene = new Scene(root,1000, 500);
        stage.setTitle("MythologicClicker");
        scene.getStylesheets().add("/vue/stylesheet.css");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) { 
        launch(args);    
    }
    
}
