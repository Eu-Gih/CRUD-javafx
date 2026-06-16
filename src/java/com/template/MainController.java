package com.template;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class MainController {
    @FXML private Button btnSalvar;
    @FXML private Button btnDeletar;
    @FXML private Button btnAlterar;
    @FXML private Button btnLimpar;
    @FXML private TextField txtTitulo;
    @FXML private TextField txtId;
    @FXML private TextField txtAutor;
    @FXML private TextField txtGenero;
    @FXML private TextField txtPaginas;
    @FXML private TableView<LivroDTO> tblLivro;
    @FXML private TableColumn<LivroDTO, Integer> colId;
    @FXML private TableColumn<LivroDTO, String> colTitulo;
    @FXML private TableColumn<LivroDTO, String> colAutor;
    @FXML private TableColumn<LivroDTO, String> colGenero;
    @FXML private TableColumn<LivroDTO, Integer> colPaginas;
    @FXML private ComboBox<String> cbGenero;
    @FXML private Label lblMensagem;
    @FXML private Label lblTotal;

    @FXML
    private void btnSalvarAction(ActionEvent event) {
        try {
            String titulo = txtTitulo.getText();
            String autor = txtAutor.getText();
            String genero = cbGenero.getValue();
            int paginas = Integer.parseInt(txtPaginas.getText());


            LivroDTO objLivroDTO = new LivroDTO();
            objLivroDTO.setTitulo(titulo);
            objLivroDTO.setAutor(autor);
            objLivroDTO.setGenero(genero);
            objLivroDTO.setPaginas(paginas);

            LivroDAO objLivroDAO = new LivroDAO();
            lblMensagem.setText("Livro cadastrado com sucesso!");
            objLivroDAO.cadastrarLivro(objLivroDTO);

            carregarLivros(); // Atualiza a tabela
            btnLimparAction(null); // Limpa os campos
        } catch (NumberFormatException e) {
            System.out.println("Por favor, preencha o número de páginas corretamente.");
        }
    }

    @FXML
    private void btnAlterarAction(ActionEvent event) {
        try {
            if (txtId.getText().isEmpty()) return;

            String titulo = txtTitulo.getText();
            String autor = txtAutor.getText();
            String genero = cbGenero.getValue();
            int paginas = Integer.parseInt(txtPaginas.getText());
            int id = Integer.parseInt(txtId.getText());

            LivroDTO atualizalivro = new LivroDTO();
            atualizalivro.setId(id);
            atualizalivro.setTitulo(titulo);
            atualizalivro.setAutor(autor);
            atualizalivro.setGenero(genero);
            atualizalivro.setPaginas(paginas);

            LivroDAO dao = new LivroDAO();
            lblMensagem.setText("Livro atualizado com sucesso!");
            dao.atualizarLivro(atualizalivro);

            carregarLivros();
            btnLimparAction(null);
        } catch (NumberFormatException e) {
            System.out.println("Erro ao atualizar: verifique os campos numéricos.");
        }
    }

    @FXML
    private void btnDeletarAction(ActionEvent event) {
        try {
            if (txtId.getText().isEmpty()) return;

            int id = Integer.parseInt(txtId.getText());

            LivroDAO dao = new LivroDAO();
            lblMensagem.setText("Livro excluído com sucesso!");
            dao.excluirLivro(id);

            carregarLivros();
            btnLimparAction(null);
        } catch (NumberFormatException e) {
            System.out.println("Selecione um livro válido para excluir!");
        }
    }

    @FXML
    private void btnLimparAction(ActionEvent event) {
        txtTitulo.clear();
        txtAutor.clear();
        cbGenero.setValue(null);
        txtPaginas.clear();
        txtId.clear();
        tblLivro.getSelectionModel().clearSelection();
    }

    @FXML
    private void initialize() {
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
        colGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
        colPaginas.setCellValueFactory(new PropertyValueFactory<>("paginas"));
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        cbGenero.getItems().addAll("Romance","Ficção Científica", "Fantasia", "Terror", "Suspense", "Drama", "Aventura", "Biografia", "História", "Acadêmico");

        carregarLivros(); // Preenche a tabela ao iniciar

        tblLivro.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                carregarCampos(newValue);
            }
        });
    }

    private void carregarLivros() {
        LivroDAO slaLivroDAO = new LivroDAO();
        ArrayList<LivroDTO> listaLivros = slaLivroDAO.listarLivros();
        tblLivro.setItems(FXCollections.observableArrayList(listaLivros));

        lblTotal.setText(String.valueOf(listaLivros.size()));
    }

    private void carregarCampos(LivroDTO livroDTO) {
        if (livroDTO != null) {
            txtId.setText(String.valueOf(livroDTO.getId()));
            txtPaginas.setText(String.valueOf(livroDTO.getPaginas()));
            txtTitulo.setText(livroDTO.getTitulo());
            txtAutor.setText(livroDTO.getAutor());
            cbGenero.setValue(livroDTO.getGenero());
        }
    }
}