package Requirement;

import Main.AdapterGUI;
import Main.Controller;
import Project.ProjectListController;
import Requirement.Requirement;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ProjectDetailsController {

    @FXML private TableView<Requirement> requirementField;
    @FXML private TableColumn<Requirement, String> requirementName;
    @FXML private TableColumn<Requirement, String> requirementStatus;
    @FXML private TableColumn<Requirement, String> requirementDeadline;
    @FXML private Button addRequirementButton;
    @FXML private Button editRequirementButton;
    @FXML private Button removeRequirementButton;
    @FXML private Label requirementNameLabel;
    @FXML private Label requirementStatusLabel;
    @FXML private Label requirementTeamLabel;
    @FXML private Label requirementDeadlineLabel;
    @FXML private Label requirementUserStoryLabel;

    private static Stage stage;

    public static Stage getStage(){
        return stage;
    }

    public void initialize()
    {
        requirementName.setCellValueFactory(new PropertyValueFactory<Requirement, String>("Name"));
        requirementStatus.setCellValueFactory(new PropertyValueFactory<Requirement, String>("Status"));
        requirementDeadline.setCellValueFactory(new PropertyValueFactory<Requirement, String>("Deadline"));

        addRequirementButton.setOnAction(new Listener());
        editRequirementButton.setOnAction(new Listener());
        removeRequirementButton.setOnAction(new Listener());

        updateRequirementArea();
        setSelectedRequirement();
    }

    private void setSelectedRequirement()
    {
        requirementField.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener()
                {
                    public void changed(ObservableValue observableValue, Object oldValue,
                                        Object newValue)
                    {
                        if (requirementField.getSelectionModel().getSelectedItem() != null)
                        {
                            requirementNameLabel
                                    .setText(requirementNameLabel.getText().substring(0, 7));
                            requirementStatusLabel
                                    .setText(requirementStatusLabel.getText().substring(0, 9));
                            requirementUserStoryLabel.setText(
                                    requirementUserStoryLabel.getText().substring(0, 13));
                            requirementDeadlineLabel
                                    .setText(requirementDeadlineLabel.getText().substring(0, 11));
                            int index = requirementField.getSelectionModel()
                                    .getSelectedIndex();
                            Controller.setSelectedRequirement(requirementField.getItems().get(index));

                            TabPane tabPane = (TabPane) AdapterGUI.getScene().lookup("#tabPane");
                            Tab requirementDetailsTab = (Tab) tabPane.getTabs().get(3);

                            requirementDetailsTab.setText(Controller.getSelectedRequirement().getName() + " requirement details");
                            requirementDetailsTab.setDisable(false);
                            System.out.println(Controller.getSelectedRequirement().getName());
                            requirementNameLabel.setText(
                                    requirementNameLabel.getText() + Controller.getSelectedRequirement()
                                            .getName());
                            requirementStatusLabel.setText(
                                    requirementStatusLabel.getText() + Controller.getSelectedRequirement()
                                            .getStatus());
                            requirementDeadlineLabel.setText(
                                    requirementDeadlineLabel.getText() + Controller.getSelectedRequirement()
                                            .getDeadline().toString());
                            requirementUserStoryLabel.setText(
                                    requirementUserStoryLabel.getText() + Controller.getSelectedRequirement()
                                            .getUserstory());
                        }
                    }
                });
    }

    private void updateRequirementArea()
    {
        requirementField.getItems().clear();
        if (ProjectListController.getProjectListAdapter() != null)
        {
            for (int i = 0; i < Controller.getSelectedProject().getRequirements().size(); i++)
            {
                System.out.println(i);
                requirementField.getItems()
                        .add(Controller.getSelectedProject().getRequirements().getRequirement(i));
            }
        }
    }

    private class Listener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {

            if(actionEvent.getSource() == addRequirementButton){
                Stage window = new Stage();
                window.initModality(Modality.APPLICATION_MODAL);
                window.setTitle("Add Requirement");
                window.setMinWidth(300);
                FXMLLoader loader = new FXMLLoader();

                loader.setLocation(getClass().getResource("/FXML/addRequirementTemplate.fxml"));

                try {
                    Scene scene = new Scene(loader.load());
                    window.setScene(scene);
                    stage = window;
                    window.showAndWait();
                    updateRequirementArea();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }



}
