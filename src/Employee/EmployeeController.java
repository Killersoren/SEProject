package Employee;

import Main.Controller;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class EmployeeController {

    @FXML private TableView<Employee> employeeField;
    @FXML private TableColumn<Employee, String> employeeName;
    @FXML private Button addEmployeeButton;
    @FXML private Button editEmployeeButton;
    @FXML private Button removeEmployeeButton;

    private static EmployeeListAdapter employeeListAdapter = new EmployeeListAdapter("Employees.bin");
    private static EmployeeList employeeList = employeeListAdapter.getAllMembers();
    private static Employee selectedEmployee;

    private static Stage stage;

    public static EmployeeList getEmployeeList(){
        return employeeList;
    }

    public static Employee getSelectedEmployee(){
        return selectedEmployee;
    }

    public static Stage getStage(){
        return stage;
    }

    public void initialize()
    {
        employeeName.setCellValueFactory(new PropertyValueFactory<Employee, String>("Name"));

        addEmployeeButton.setOnAction(new Listener());
        editEmployeeButton.setOnAction(new Listener());
        removeEmployeeButton.setOnAction(new Listener());

        updateEmployeeArea();
        setSelectedEmployee();

    }

    private void setSelectedEmployee() {
        employeeField.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                if (employeeField.getSelectionModel().getSelectedItem() != null) {
                    int index = employeeField.getSelectionModel().getSelectedIndex();
                    selectedEmployee = employeeField.getItems().get(index);
                }
            }
        });
    }

    public void updateEmployeeArea()
    {
        employeeField.getItems().clear();
        if (employeeListAdapter != null)
        {
            employeeList = employeeListAdapter.getAllMembers();
            for (int i = 0; i < employeeList.size(); i++)
            {
                employeeField.getItems().add(employeeList.get(i));
            }
        }
    }

    public class Listener implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent actionEvent) {

            Employee selectedMember;

            if(actionEvent.getSource() == addEmployeeButton){

                Stage window = new Stage();
                window.initModality(Modality.APPLICATION_MODAL);
                window.setTitle("Add Member");
                window.setMinWidth(300);
                FXMLLoader loader = new FXMLLoader();

                loader.setLocation(getClass().getResource("/FXML/addEmployeeTemplate.fxml"));

                try {
                    Scene scene = new Scene(loader.load());
                    window.setScene(scene);
                    stage = window;
                    window.showAndWait();
                    updateEmployeeArea();
                } catch (IOException e) {
                    e.printStackTrace();
                }



            } else if(actionEvent.getSource() == editEmployeeButton){
                    selectedMember = employeeField.getSelectionModel().getSelectedItem();

                    Stage window = new Stage();
                    window.initModality(Modality.APPLICATION_MODAL);
                    window.setTitle("Edit Member");
                    window.setMinWidth(300);
                    FXMLLoader loader = new FXMLLoader();

                    loader.setLocation(getClass().getResource("/FXML/editEmployeeTemplate.fxml"));

                    try {
                        Scene scene = new Scene(loader.load());
                        window.setScene(scene);
                        stage = window;
                        window.showAndWait();
                        updateEmployeeArea();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

            } else if(actionEvent.getSource() == removeEmployeeButton){
                    selectedMember = employeeField.getSelectionModel().getSelectedItem();

                    Stage window = new Stage();
                    window.initModality(Modality.APPLICATION_MODAL);
                    window.setTitle("Remove Member");
                    window.setMinWidth(300);
                    FXMLLoader loader = new FXMLLoader();

                    loader.setLocation(getClass().getResource("/FXML/removeEmployeeTemplate.fxml"));

                    try {
                        Scene scene = new Scene(loader.load());
                        window.setScene(scene);
                        stage = window;
                        window.showAndWait();
                        updateEmployeeArea();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

            }

        }
    }

}
