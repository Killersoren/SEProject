package Employee;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

public class RemoveEmployeeController {

    @FXML Button yesButton;
    @FXML Button noButton;

    private EmployeeListAdapter employeeListAdapter = new EmployeeListAdapter("Employees.bin");

    public void initialize()
    {
        yesButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                EmployeeController.getEmployeeList().removeMember(EmployeeController.getSelectedEmployee());
                employeeListAdapter.saveMembers(EmployeeController.getEmployeeList());

                System.out.println(employeeListAdapter.getAllMembers().size());

                EmployeeController.getStage().close();

            }
        });
    }

}
