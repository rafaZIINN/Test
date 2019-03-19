/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa1.controlador;

import empresa.modelo.CargoDAO;
import empresa.modelo.FuncionarioDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author L
 */
public class TelaCadastroFuncionariosController implements Initializable {
    
    private int selected = -1;
    
    @FXML
    private TextField tfNome;
    
    @FXML
    private TextField tfCPF;
    
    @FXML
    private TextField tfLogradouro;
    
    @FXML
    private TextField tfBairro;
    
    @FXML
    private ComboBox<String> cbEstado;

    @FXML
    private ComboBox<Cargo> cbCargo;
    
    @FXML
    private ComboBox<String> cbCidade;
    
    @FXML
    private ComboBox<String> cbPais;
    
    @FXML
    private TextField tfCEP;
    
    @FXML
    private TableView<Endereco> tvEnderecos;
    
    @FXML
    private TableColumn<Endereco, String> colLogradouro;
    
    @FXML
    private TableColumn<Endereco, String> colBairro;
    
    @FXML
    private TableColumn<Endereco, String> colCidade;
    
    @FXML
    private TableColumn<Endereco, String> colEstado;
    
    @FXML
    private TableColumn<Endereco, String> colPais;
    
    @FXML
    private TableColumn<Endereco, String> colCEP;
    
    @FXML
    private ListView<Funcionario> lvFuncionario;
    
    @FXML
    void excluir(ActionEvent event) {
        
    }
    
    @FXML
    void salvarEndereco(ActionEvent event) {
        if (selected==-1){
            Endereco aux = new Endereco(0,
                               tfLogradouro.getText(), 
                               tfBairro.getText(),
                               cbCidade.getValue(),
                               cbEstado.getValue(),
                               cbPais.getValue(),
                               tfCEP.getText());
            tvEnderecos.getItems().add(aux);
        } else {
            tvEnderecos.getItems().get(selected).setLogradouro(tfLogradouro.getText());
            tvEnderecos.getItems().get(selected).setBairro(tfBairro.getText());
            tvEnderecos.getItems().get(selected).setCidade(cbCidade.getValue());
            tvEnderecos.getItems().get(selected).setEstado(cbEstado.getValue());
            tvEnderecos.getItems().get(selected).setCep(tfCEP.getText());
            tvEnderecos.getItems().get(selected).setPais(cbPais.getValue());

            tvEnderecos.refresh(); 
            
            selected = -1;
        }
         limpar();        
    }
    
    private void limpar(){
        tfLogradouro.setText("");
        tfBairro.setText("");
        tfCEP.setText("");
        cbPais.getSelectionModel().clearSelection();
        cbEstado.getSelectionModel().clearSelection();
        cbCidade.getSelectionModel().clearSelection();
    }
    
    @FXML
    void alterarEndereco(MouseEvent event) {
        if (event.getClickCount()==2){
            selected = tvEnderecos.getSelectionModel().getSelectedIndex();
            Endereco aux = tvEnderecos.getSelectionModel().getSelectedItem();
            
            tfBairro.setText(aux.getBairro());
            tfCEP.setText(aux.getCep());
            tfLogradouro.setText(aux.getLogradouro());
            cbPais.setValue(aux.getPais());
            cbEstado.setValue(aux.getEstado());
            cbCidade.setValue(aux.getCidade());
        }
    }
    


    @FXML
    void excluirEndereco(ActionEvent event) {
        tvEnderecos.getItems().
                remove(
                  tvEnderecos.getSelectionModel().getSelectedIndex()
                );
    }
    
    @FXML
    void novo(ActionEvent event) {
        
    }
    
    @FXML
    void saveAll(ActionEvent event) {
        Funcionario f = new Funcionario(tfNome.getText(), 
                new Cpf(tfCPF.getText()),
                cbCargo.getSelectionModel().getSelectedItem());
        
        for (Endereco aux : tvEnderecos.getItems()){
            f.getEnderecos().add(aux);
        }
        
        FuncionarioDAO.create(f);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lvFuncionario.setItems(FXCollections.observableArrayList(FuncionarioDAO.retreaveAll()));
        
        cbCargo.setItems(FXCollections.observableArrayList(CargoDAO.retreaveAll()));
        
        cbPais.getItems().add("Brasil");
        cbPais.getItems().add("Portugal");
        
        colBairro.setCellValueFactory(new PropertyValueFactory<>("bairro"));
        colCEP.setCellValueFactory(new PropertyValueFactory<>("cep"));
        colCidade.setCellValueFactory(new PropertyValueFactory<>("cidade"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        colPais.setCellValueFactory(new PropertyValueFactory<>("pais"));
        colLogradouro.setCellValueFactory(new PropertyValueFactory<>("logradouro"));        
        
        
    }
    
    @FXML
    void scroll(ActionEvent event) {
        cbEstado.setDisable(false);
        switch (cbPais.getSelectionModel().getSelectedIndex()) {
            case 0:                
                cbEstado.getItems().clear();                
                cbEstado.getItems().add("GO");
                cbEstado.getItems().add("SP");
                cbEstado.getItems().add("RJ");
                cbEstado.getItems().add("MG");
                break;
            case 1:
                cbEstado.getItems().clear();
                cbEstado.getItems().add("Lisboa");
                cbEstado.getItems().add("Bragança");
                cbEstado.getItems().add("Braga");
                cbEstado.getItems().add("Porto");
                break;            
        }
    }
    
    @FXML
    void setCidades(ActionEvent event) {
        cbCidade.setDisable(false);
        cbCidade.getItems().clear();
        cbCidade.getItems().add("Morrinhos");
        cbCidade.getItems().add("Anápolis");
        cbCidade.getItems().add("Rio Preto");
        cbCidade.getItems().add("Uberlândia");
    }    
}
