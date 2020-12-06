package Project;

import Employee.EmployeeListAdapter;
import Main.AdapterGUI;
import Main.Controller;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.Line;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ProjectListController extends Controller {

    @FXML private TableView<Project> projectField;
    @FXML private TableColumn<Project, String> projectName;
    @FXML private TableColumn<Project, String> projectTeam;
    @FXML private RadioButton searchByName, searchByEmployee;
    @FXML private TextField searchField;
    @FXML private Button addProjectButton;
    @FXML private Button editProjectButton;
    @FXML private Button removeProjectButton;

    private static ProjectListAdapter projectListAdapter = new ProjectListAdapter("Projects.bin");
    private static ProjectList projectList = projectListAdapter.getAllProjects();

    private static Stage stage;

    public static ProjectList getProjectList(){
        return projectList;
    }
    public static ProjectListAdapter getProjectListAdapter(){
        return projectListAdapter;
    }
    public static Stage getStage(){
        return stage;
    }

    public void initialize()
    {
        projectName.setCellValueFactory(new PropertyValueFactory<Project, String>("Name"));
        projectTeam.setCellValueFactory(new PropertyValueFactory<Project, String>("Team"));

        addProjectButton.setOnAction(new Listener());
        editProjectButton.setOnAction(new Listener());
        removeProjectButton.setOnAction(new Listener());

        updateProjectArea();
        setSelectedProject();

    }

    private void setSelectedProject()
    {
        projectField.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {

                TabPane tabPane = (TabPane) AdapterGUI.getScene().lookup("#tabPane");
                Tab projectDetailsTab = (Tab) tabPane.getTabs().get(2);


                if (projectField.getSelectionModel().getSelectedItem() != null) {
                    int index = projectField.getSelectionModel().getSelectedIndex();
                    Controller.setSelectedProject(projectField.getItems().get(index));

                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/FXML/projectDetailsFXML.fxml"));
                        try {
                            projectDetailsTab.setContent(loader.load());
                            projectDetailsTab.setText(Controller.getSelectedProject().getName() + " project details");
                            projectDetailsTab.setDisable(false);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                    projectDetailsTab.setContent(null);
                    projectDetailsTab.setDisable(true);
                }
                }
        });
    }


    private void updateProjectArea()
    {
        projectField.getItems().clear();
        if (projectListAdapter != null)
        {
            projectList = projectListAdapter.getAllProjects();
            for (int i = 0; i < projectList.size(); i++)
            {
                projectField.getItems().add(projectList.get(i));
            }
        }
    }

    @FXML public void searchClick()
    {
        projectField.getItems().clear();
        if (projectListAdapter != null)
        {
            if (searchByName.isSelected())
            {
                ProjectList projects = projectListAdapter
                        .getProjectByName(searchField.getText());
                for (int i = 0; i < projects.size(); i++)
                {
                    projectField.getItems().add(projects.get(i));
                }
            }
            else if (searchByEmployee.isSelected())
            {
                ProjectList projects = projectListAdapter
                        .getProjectByEmployeeName(searchField.getText());
                for (int i = 0; i < projects.size(); i++)
                {
                    projectField.getItems().add(projects.get(i));
                }
            }
        }

    }

    private class Listener implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent actionEvent) {

            if(actionEvent.getSource() == addProjectButton){

                Stage window = new Stage();
                window.initModality(Modality.APPLICATION_MODAL);
                window.setTitle("Add Project");
                window.setMinWidth(300);
                FXMLLoader loader = new FXMLLoader();

                loader.setLocation(getClass().getResource("/FXML/addProjectTemplate.fxml"));

                try {
                    Scene scene = new Scene(loader.load());
                    window.setScene(scene);
                    stage = window;
                    window.showAndWait();
                    updateProjectArea();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else if(actionEvent.getSource() == editProjectButton){
                Controller.setSelectedProject(projectField.getSelectionModel().getSelectedItem());

                Stage window = new Stage();
                window.initModality(Modality.APPLICATION_MODAL);
                window.setTitle("Edit Project");
                window.setMinWidth(300);
                FXMLLoader loader = new FXMLLoader();

                loader.setLocation(getClass().getResource("/FXML/editProjectTemplate.fxml"));

                try {
                    Scene scene = new Scene(loader.load());
                    window.setScene(scene);
                    stage = window;
                    window.showAndWait();
                    updateProjectArea();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else if(actionEvent.getSource() == removeProjectButton){
                Controller.setSelectedProject(projectField.getSelectionModel().getSelectedItem());

                Stage window = new Stage();
                window.initModality(Modality.APPLICATION_MODAL);
                window.setTitle("Remove Project");
                window.setMinWidth(300);
                FXMLLoader loader = new FXMLLoader();

                loader.setLocation(getClass().getResource("/FXML/removeProjectTemplate.fxml"));

                try {
                    Scene scene = new Scene(loader.load());
                    window.setScene(scene);
                    stage = window;
                    window.showAndWait();
                    updateProjectArea();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



}
