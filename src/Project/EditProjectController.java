package Project;

import Employee.EmployeeList;
import Employee.EmployeeListAdapter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class EditProjectController {

    private EmployeeListAdapter employeeListAdapter = new EmployeeListAdapter("Employees.bin");
    private EmployeeList employeeList               = employeeListAdapter.getAllMembers();
    private ProjectListAdapter projectListAdapter   = new ProjectListAdapter("Projects.bin");
    private ProjectList projectList                 = projectListAdapter.getAllProjects();

    @FXML TextField nameTextField;
    @FXML VBox memberListContainer;
    @FXML Button saveAndCloseButton;

    GridPane employeeListGridPane;
    CheckBox[] memberCheckBoxes;

    EmployeeList selectedEmployeeList = new EmployeeList();

    public void initialize()
    {

        nameTextField.setText(ProjectListController.getSelectedProject().getName());
        employeeListGridPane = new GridPane();
        memberCheckBoxes = new CheckBox[employeeList.size()];
        selectedEmployeeList = ProjectListController.getSelectedProject().getTeam();

        for(int i = 0 ; i < employeeList.size() ; i++){
            memberCheckBoxes[i] = new CheckBox(employeeList.get(i).getName());
            for(int j = 0 ; j < selectedEmployeeList.size() ; j++){
                if(employeeList.get(i).equals(selectedEmployeeList.get(j))){
                    memberCheckBoxes[i].setSelected(true);
                }
            }
            employeeListGridPane.add(memberCheckBoxes[i], i % 2, i / 2);
            memberCheckBoxes[i].setPadding(new Insets(3, 50, 3, 3));
        }

        memberListContainer.getChildren().add(employeeListGridPane);

        saveAndCloseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {

                selectedEmployeeList = new EmployeeList();

                for(int i = 0 ; i < employeeList.size() ; i++){
                    if(memberCheckBoxes[i].isSelected()){
                        selectedEmployeeList.addMember(employeeList.get(i));
                    }
                }

                ProjectListController.getSelectedProject().setName(nameTextField.getText());
                ProjectListController.getSelectedProject().setTeam(selectedEmployeeList);
                projectListAdapter.saveProjects(ProjectListController.getProjectList());

                ProjectListController.getStage().close();

            }
        });



    }



}
