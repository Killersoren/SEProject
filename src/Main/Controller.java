package Main;

import Employee.Employee;
import Project.Project;
import Requirement.Requirement;
import Task.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import java.io.IOException;

public class Controller
{
  @FXML private Tab employeeListTab;
  @FXML private Tab projectListTab;

  @FXML private TableView<Task> taskField;
  @FXML private TableColumn<Task, String> taskName;
  @FXML private TableColumn<Task, String> taskStatus;
  @FXML private TableColumn<Task, String> taskDeadline;

  FXMLLoader loader;

  // Selected objects
  private static Employee selectedEmployee;
  private static Project selectedProject;
  private static Requirement selectedRequirement;
  private static Task selectedTask;

  public void initialize() {
    try {
      loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("/FXML/employeeListFXML.fxml"));
      employeeListTab.setContent(loader.load());
      loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("/FXML/projectListFXML.fxml"));
      projectListTab.setContent(loader.load());

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void setSelectedEmployee(Employee selectedEmployee) {
    Controller.selectedEmployee = selectedEmployee;
  }

  public static Employee getSelectedEmployee() {
    return selectedEmployee;
  }

  public static void setSelectedProject(Project selectedProject) {
    Controller.selectedProject = selectedProject;
  }

  public static Project getSelectedProject() {
    return selectedProject;
  }

  public static void setSelectedRequirement(Requirement selectedRequirement) {
    Controller.selectedRequirement = selectedRequirement;
  }

  public static Requirement getSelectedRequirement() {
    return selectedRequirement;
  }

  public static void setSelectedTask(Task selectedTask) {
    Controller.selectedTask = selectedTask;
  }

  public static Task getSelectedTask() {
    return selectedTask;
  }
}


