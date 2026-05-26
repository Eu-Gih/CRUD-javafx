package com.template;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

public class MainController
{
    @FXML private Button btnSalvar;
    @FXML private Button btnDeletar;
    @FXML private Button btnAlterar;
    @FXML private Button btnLimpar;
    @FXML private TextField txtNome;
    @FXML private TextField txtId;
    @FXML private TableView<LivroDTO> tblLivro;
    @FXML private TableColumn<LivroDTO, Integer> colId;
    @FXML private TableColumn<LivroDTO, String> colNome;
    @FXML private TableColumn<LivroDTO, String> colTitulo;
    @FXML private TableColumn<LivroDTO, String> colAutor;
    @FXML private TableColumn<LivroDTO, String> colGenero;
    @FXML private TableColumn<LivroDTO, Integer> colPaginas;

    @FXML private void btnSalvarAction(ActionEvent event){
        String nome = txtNome.getText();
        int id = Integer.parseInt(txtId.getText());
    }

    @FXML private void btnDeletarAction(ActionEvent event){

    }

    @FXML private void BtnLimparAction(ActionEvent event){
        txtNome.clear();
        txtId.clear();
    }

    @FXML
    private void initialize()
    {
        System.out.println("FXML loaded successfully!");
    }
}