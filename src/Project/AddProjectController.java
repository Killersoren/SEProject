package Project;

import Employee.Employee;
import Employee.EmployeeController;
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

public class AddProjectController {

    private EmployeeListAdapter employeeListAdapter = new EmployeeListAdapter("Employees.bin");
    private EmployeeList employeeList               = employeeListAdapter.getAllMembers();
    private ProjectListAdapter projectListAdapter   = new ProjectListAdapter("Projects.bin");
    private ProjectList projectList                 = projectListAdapter.getAllProjects();

    @FXML TextField nameTextField;
    @FXML VBox memberListContainer;
    @FXML Button addButton;

    GridPane employeeListGridPane;
    CheckBox[] memberCheckBoxes;

    public void initialize()
    {

        

        employeeListGridPane = new GridPane();
        memberCheckBoxes = new CheckBox[employeeList.size()];
        EmployeeList selectedEmployeeList = new EmployeeList();
        for(int i = 0 ; i < employeeList.size() ; i++){
            memberCheckBoxes[i] = new CheckBox(employeeList.get(i).getName());
            employeeListGridPane.add(memberCheckBoxes[i], i % 2, i / 2);
            memberCheckBoxes[i].setPadding(new Insets(3, 50, 3, 3));
        }

        memberListContainer.getChildren().add(employeeListGridPane);

        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {

                for(int i = 0 ; i < employeeList.size() ; i++){
                    if(memberCheckBoxes[i].isSelected()){
                        selectedEmployeeList.addMember(employeeList.get(i));
                    }
                }

                projectList.add(new Project(nameTextField.getText(), selectedEmployeeList));
                projectListAdapter.saveProjects(projectList);

                ProjectListController.getStage().close();

            }
        });

    }

}
