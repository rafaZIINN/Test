/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.controlador;

import empresa.Empresa;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author L
 */
public class TelaSobreController implements Initializable {   

    @FXML
    public void sair(ActionEvent event) {
        Empresa.trocaTela(Empresa.TELA_PRINCIPAL);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
