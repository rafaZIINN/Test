/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.controlador;

import empresa.modelo.CargoDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
public class TelaCadastroCargosController implements Initializable {
    
    private int selected = -1;
    
    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfDescricao;

    @FXML
    private TableView<Cargo> tvCargo;

    @FXML
    private TableColumn<Cargo, String> colNome;

    @FXML
    private TableColumn<Cargo, String> colDesc;

    @FXML
    private TableColumn<?, String> colStatus;

    @FXML
    void excluir(ActionEvent event) {
    
        CargoDAO.delete(tvCargo.getSelectionModel().getSelectedItem());
        
        tvCargo.getItems().
                remove(
                  tvCargo.getSelectionModel().getSelectedIndex()
                );
    }


    @FXML
    public void salvar(ActionEvent event) {
        if (selected==-1){
            Cargo aux = new Cargo(
                     tfNome.getText(), 
                     tfDescricao.getText());
            CargoDAO.create(aux);
            tvCargo.getItems().add(aux);
        } else {
            tvCargo.getItems().get(selected).setNome(tfNome.getText());
            tvCargo.getItems().get(selected).setDescricao(tfDescricao.getText());            
            tvCargo.refresh(); 
            
            CargoDAO.update(tvCargo.getItems().get(selected));
            
            selected = -1;
        }
         limpar();
    }   
    
    @FXML
    public void alterar(MouseEvent event) {
        if (event.getClickCount()==2){
            tfNome.setText(
               tvCargo.getSelectionModel().getSelectedItem().getNome()
            );
            
            tfDescricao.setText(
               tvCargo.getSelectionModel().getSelectedItem().getDescricao()
            );
            selected = tvCargo.getSelectionModel().getSelectedIndex();
        }

    }
    
    public boolean valideForm(){
        return !tfDescricao.getText().isEmpty() && !tfNome.getText().isEmpty();
    }
    
    public void limpar(){
        tfNome.clear();
        tfDescricao.clear();
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //ensinando como as colunas devem receber os atributos de um objeto Cargo
        //o nome da propriedade deve coincidir com o nome do atributo de instancia da classe Cargo
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        
        tvCargo.setItems(FXCollections.observableArrayList(CargoDAO.retreaveAll()));
        
//        tvData.setItems(FXCollections.observableArrayList(
//                CargoDAO.retreaveAll()));
    }    
    
}
