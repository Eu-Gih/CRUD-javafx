package com.template.Controller;

import com.template.Model.dto.LivroDTO;
import com.template.Service.LivroService;
import com.template.Validator.LivroValidator;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

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

    // Instanciando o Service e o Validator ao invés do DAO
    private final LivroService livroService = new LivroService();
    private final LivroValidator livroValidator = new LivroValidator();

    @FXML
    private void btnSalvarAction(ActionEvent event) {
        try {
            LivroDTO objLivroDTO = new LivroDTO();
            objLivroDTO.setTitulo(txtTitulo.getText());
            objLivroDTO.setAutor(txtAutor.getText());
            objLivroDTO.setGenero(cbGenero.getValue() != null ? cbGenero.getValue() : "");

            // Tratando campos vazios para não quebrar o parseInt
            if(!txtPaginas.getText().isEmpty()) {
                objLivroDTO.setPaginas(Integer.parseInt(txtPaginas.getText()));
            }

            // Chama a validação antes de salvar
            if (livroValidator.validarLivro(objLivroDTO)) {
                livroService.cadastrarLivro(objLivroDTO); // Chama o Service
                lblMensagem.setText("Livro cadastrado com sucesso!");
                carregarLivros();
                btnLimparAction(null);
            }
        } catch (NumberFormatException e) {
            System.out.println("Por favor, preencha o número de páginas corretamente.");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar: " + e.getMessage());
        }
    }

    @FXML
    private void btnAlterarAction(ActionEvent event) {
        try {
            if (txtId.getText().isEmpty()) return;

            LivroDTO atualizalivro = new LivroDTO();
            atualizalivro.setId(Integer.parseInt(txtId.getText()));
            atualizalivro.setTitulo(txtTitulo.getText());
            atualizalivro.setAutor(txtAutor.getText());
            atualizalivro.setGenero(cbGenero.getValue() != null ? cbGenero.getValue() : "");

            if(!txtPaginas.getText().isEmpty()) {
                atualizalivro.setPaginas(Integer.parseInt(txtPaginas.getText()));
            }

            // Valida antes de alterar
            if (livroValidator.validarLivro(atualizalivro)) {
                livroService.atualizarLivro(atualizalivro); // Chama o Service
                lblMensagem.setText("Livro atualizado com sucesso!");
                carregarLivros();
                btnLimparAction(null);
            }
        } catch (NumberFormatException e) {
            System.out.println("Erro ao atualizar: verifique os campos numéricos.");
        } catch (Exception e) {
            System.out.println("Erro ao atualizar o banco de dados: " + e.getMessage());
        }
    }

    @FXML
    private void btnDeletarAction(ActionEvent event) {
        try {
            if (txtId.getText().isEmpty()) return;

            int id = Integer.parseInt(txtId.getText());

            livroService.excluirLivro(id); // Chama o Service
            lblMensagem.setText("Livro excluído com sucesso!");

            carregarLivros();
            btnLimparAction(null);
        } catch (NumberFormatException e) {
            System.out.println("Selecione um livro válido para excluir!");
        } catch (Exception e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
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

        carregarLivros();

        tblLivro.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                carregarCampos(newValue);
            }
        });
    }

    private void carregarLivros() {
        try {
            // Chama o Service em vez do DAO
            List<LivroDTO> listaLivros = livroService.listarLivro();
            tblLivro.setItems(FXCollections.observableArrayList(listaLivros));
            lblTotal.setText(String.valueOf(listaLivros.size()));
        } catch (Exception e) {
            System.out.println("Erro ao carregar livros: " + e.getMessage());
        }
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