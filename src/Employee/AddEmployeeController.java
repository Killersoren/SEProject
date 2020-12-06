package Employee;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddEmployeeController {

    @FXML TextField nameTextField;
    @FXML Button addButton;



    private EmployeeListAdapter employeeListAdapter = new EmployeeListAdapter("Employees.bin");
    private EmployeeList employeeList = employeeListAdapter.getAllMembers();

    public void initialize()
    {
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {

                employeeList.addMember(new Employee(nameTextField.getText()));
                employeeListAdapter.saveMembers(employeeList);

                EmployeeController.getStage().close();

            }
        });
    }



}
