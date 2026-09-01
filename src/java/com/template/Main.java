package com.template;

import com.template.Controller.MainController;
import com.template.Validator.ILivroValidador;
import com.template.Validator.LivroValidador;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{
    @Override
    public void start(Stage stage) throws Exception
    {
        ILivroValidador livroValidador = new LivroValidador();

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("main.fxml"));

        loader.setControllerFactory(controllerClass -> {
            if(controllerClass == MainController.class) {
                return new MainController(LivroValidador);
            }
            try{
                return  controllerClass.newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        Scene scene = new Scene(loader.load(),850,650);

        stage.setTitle("Cadastro Livro");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch();
    }
}