/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import empresa.Empresa;
import javafx.application.Platform;

/**
 *
 * @author L
 */
public class TelaPrincipalController implements Initializable {

    @FXML
    void exit(){
        Platform.exit();
    }

    @FXML
    void abraTelaCadastroCargos(ActionEvent event) {
        Empresa.trocaTela(Empresa.TELA_CADASTRO_CARGOS);
    }

    @FXML
    void abraTelaCadastroClientes(ActionEvent event) {
        Empresa.trocaTela(Empresa.TELA_CADASTRO_FUNCIONARIO);
    }

    @FXML
    void abraTelaSobre(ActionEvent event) {
        Empresa.trocaTela(Empresa.TELA_SOBRE);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
