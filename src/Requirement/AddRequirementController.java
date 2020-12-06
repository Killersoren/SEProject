package Requirement;

import Employee.EmployeeList;
import Main.Controller;
import Project.ProjectList;
import Project.ProjectListAdapter;
import Project.ProjectListController;
import Project.STATUS;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.time.LocalDate;

public class AddRequirementController {

    private ProjectListAdapter projectListAdapter   = new ProjectListAdapter("Projects.bin");
    private ProjectList projectList                 = projectListAdapter.getAllProjects();

    @FXML TextField nameTextField;
    @FXML TextField userstoryTextField;
    @FXML ComboBox statusComboBox;
    @FXML DatePicker deadlineDatePicker;
    @FXML VBox memberListContainer;
    @FXML Button addButton;

    GridPane employeeListGridPane;
    CheckBox[] memberCheckBoxes;

    public void initialize()
    {

        //ComboBox
        statusComboBox.getItems().add(0,STATUS.APPROVED);
        statusComboBox.getItems().add(1,STATUS.ENDED);
        statusComboBox.getItems().add(2,STATUS.NOT_STARTED);
        statusComboBox.getItems().add(3,STATUS.REJECTED);
        statusComboBox.getItems().add(4,STATUS.STARTED);

        //DatePicker
        deadlineDatePicker.setShowWeekNumbers(false);
        final DatePicker datePicker = new DatePicker();
        datePicker.setOnAction(new EventHandler()
        {
            public void handle(Event t)
            {
                LocalDate date = datePicker.getValue();
                System.err.println("Selected date: " + date);
            }
        });
        deadlineDatePicker.setDayCellFactory(picker -> new DateCell()
        {
            public void updateItem(LocalDate date, boolean empty)
            {
                super.updateItem(date, empty);
                setDisable(empty || date.compareTo(LocalDate.now()) < 1);
            }
        });
        deadlineDatePicker.setOnAction(new EventHandler()
        {
            public void handle(Event t)
            {
                System.err.println("Selected date: " + deadlineDatePicker.getValue());
            }
        });
        deadlineDatePicker.setPromptText("Set deadline..");

        //GridPane
        employeeListGridPane = new GridPane();
        memberCheckBoxes = new CheckBox[Controller.getSelectedProject().getTeam().size()];
        EmployeeList selectedEmployeeList = new EmployeeList();

        for(int i = 0 ; i < Controller.getSelectedProject().getTeam().size() ; i++){
            memberCheckBoxes[i] = new CheckBox(Controller.getSelectedProject().getTeam().get(i).getName());
            employeeListGridPane.add(memberCheckBoxes[i], i % 2, i / 2);
            memberCheckBoxes[i].setPadding(new Insets(3, 50, 3, 3));
        }

        memberListContainer.getChildren().add(employeeListGridPane);

        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {

                for(int i = 0 ; i < Controller.getSelectedProject().getTeam().size() ; i++){
                    if(memberCheckBoxes[i].isSelected()){
                        selectedEmployeeList.addMember(Controller.getSelectedProject().getTeam().get(i));
                    }
                }

                Requirement requirement = new Requirement(nameTextField.getText(),userstoryTextField.getText(),statusComboBox.getValue().toString(),deadlineDatePicker.getValue(),selectedEmployeeList);
                Controller.getSelectedProject().add(requirement);
                ProjectListController.getProjectListAdapter().saveProjects(ProjectListController.getProjectListAdapter().getAllProjects());
                ProjectDetailsController.getStage().close();

            }
        });
    }

}
