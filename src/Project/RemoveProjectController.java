package Project;

import Employee.EmployeeController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class RemoveProjectController {

    @FXML Button yesButton;
    @FXML Button noButton;

    private ProjectListAdapter projectListAdapter = new ProjectListAdapter("Projects.bin");

    public void initialize()
    {
        yesButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                ProjectListController.getProjectList().removeProject(ProjectListController.getSelectedProject());
                projectListAdapter.saveProjects(ProjectListController.getProjectList());
                ProjectListController.getStage().close();

            }
        });
        noButton.setOnAction(e->ProjectListController.getStage().close());
    }

}
