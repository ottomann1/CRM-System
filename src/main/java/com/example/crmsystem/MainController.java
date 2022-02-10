package com.example.crmsystem;

import Backend.BusinessObjects.Customer;
import Backend.BusinessObjects.Representative;
import Backend.DAO.RepresentativeDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    @FXML
    private TextField textName;
    @FXML
    private TextField textId;
    private Stage stage;
    private Scene scene;
    public Representative loginRep;
    MenuController menuController;

    @FXML
    public void loginButtonClick(ActionEvent event) throws IOException, ClassNotFoundException {
        RepresentativeDao reps = new RepresentativeDao();
        try {
            long repId = Long.parseLong(textId.getText());
            if (!(reps.getAll().size() >= repId)) {
                alert(event);
            } else {
                loginRep = reps.getAll().get(Integer.parseInt(textId.getText())-1);
                if (loginRep.getName().equals(textName.getText())) {
                    stage = new Stage();
                    FXMLLoader loader = new FXMLLoader(this.getClass().getResource("MainMenu.fxml"));
                    try {
                        Pane pane = loader.load();
                        menuController = loader.getController();
                        menuController.setLoginRep(loginRep);
                        menuController.setMainController(this);
                        Scene scene = new Scene(pane, 1100, 800);
                        stage.setTitle("CRM system");
                        stage.setScene(scene);
                        stage.show();
                        Stage thisStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        thisStage.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else
                    alert(event);
            }
        } catch(NumberFormatException e){
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("ERROR!");
            error.setContentText("Invalid ID: ID must be a number");
            error.showAndWait();
        } catch(RuntimeException e){
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("ERROR!");
            error.setContentText("Invalid ID: ID must be above 0");
            error.showAndWait();
        }
    }

    public void alert(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Representative not found");
        alert.setContentText("Representative not found\nCreate new representative account?");
        ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(okButton, noButton);
        alert.showAndWait().ifPresent(type -> {
            if (type.getText().equals("Yes")) {
                try {
                    loadStage(event, "CreateNewRep.fxml", 600, 325);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (type.getText().equals("No")) {
                try {
                    loadStage(event, "Start.fxml", 1100, 800);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void loadStage(ActionEvent event, String fxmlfile, int width, int height) throws IOException {
        FXMLLoader reploader = new FXMLLoader(getClass().getResource(fxmlfile));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(reploader.load(), width, height);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private TextField textNameRep;
    @FXML
    private TextField textAddressRep;

    public void registerButtonClicked(ActionEvent event) throws IOException, ClassNotFoundException {
        RepresentativeDao reps = new RepresentativeDao();
        Representative newrep = new Representative(textNameRep.getText(), textAddressRep.getText());
        reps.save(newrep);
        loginRep=reps.getLastRep();
        Alert idAlert = new Alert(Alert.AlertType.INFORMATION);
        idAlert.setTitle("New representative created");
        idAlert.setContentText("Name: "+newrep.getName()+"\n"+
                "Address: "+newrep.getAddress()+"\nID: "+loginRep.getId());
        idAlert.showAndWait();
        stage = new Stage();
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("MainMenu.fxml"));
        try {
            Pane pane = loader.load();
            menuController = loader.getController();
            menuController.setLoginRep(loginRep);
            menuController.setMainController(this);
            Scene scene = new Scene(pane, 1100, 800);
            stage.setTitle("CRM system");
            stage.setScene(scene);
            stage.show();
            Stage thisStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            thisStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}