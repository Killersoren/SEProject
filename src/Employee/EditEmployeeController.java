package Employee;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class EditEmployeeController {

    @FXML TextField nameTextField;
    @FXML Button saveAndCloseButton;

    private EmployeeListAdapter employeeListAdapter = new EmployeeListAdapter("Employees.bin");
    private EmployeeList employeeList = employeeListAdapter.getAllMembers();

    public void initialize()
    {

        nameTextField.setText(EmployeeController.getSelectedEmployee().getName());

        saveAndCloseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {

                EmployeeController.getSelectedEmployee().setName(nameTextField.getText());
                employeeListAdapter.saveMembers(EmployeeController.getEmployeeList());

                EmployeeController.getStage().close();


            }
        });
    }

}
