import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller
{
  private ProjectListAdapter adapter;


    public void initialize()
    {
      adapter = new ProjectListAdapter("students.bin");
//      updateEmployeeArea();
//      updateProjectArea();
//      updateProjectDetailsArea();
    }

}
