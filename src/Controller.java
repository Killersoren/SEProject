import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Controller
{
  @FXML private VBox projectArea;

  private ProjectListAdapter adapterProjects;
  private EmployeeListAdapter adapterEmployee;

  public void initialize()
  {
    adapterProjects = new ProjectListAdapter("");
    adapterEmployee = new EmployeeListAdapter("");
    //      updateEmployeeArea();
    updateProjectArea();
    //      updateProjectDetailsArea();
  }

  private void updateProjectArea()
  {
    //The tabChanged method might be automatically called before the initialize method,
    //as the GUI is loaded. I.e. adapter could be null, and cause a NullPointerException
    if (adapterProjects != null)
    {
      ProjectList projects = adapterProjects.getAllProjects();
    }
  }

  @FXML public void addProjectClick()
  {

  }
}
