import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

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

  @FXML public void addEmployeeClick()
  {
    Stage window = new Stage();

    window.initModality(Modality.APPLICATION_MODAL);
    window.setTitle("Add new project");
    window.setMinWidth(300);

    // Member name input.
    HBox nameContainer = new HBox(2);
    nameContainer.setPadding(new Insets(10, 10, 0, 10));
    Label memberName = new Label("Member name: ");
    TextField inputmemberName = new TextField();
    inputmemberName.setPromptText("Enter member name");
    nameContainer.getChildren().addAll(memberName, inputmemberName);

    Label errorMessage = new Label("");

    Button closeButton = new Button("Add new project");

    closeButton.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override public void handle(ActionEvent e)
      {
        if (!(inputmemberName.getText().isEmpty() || inputmemberName.getText()
            .equals("")))
        {
          window.close();
          Project project = new Project(inputmemberName.getText());
          addProjectToList(project);
          Main.projects.add(project);
          setCurrentProject(project);
        }
        else
        {
          errorMessage.setText("ERROR: invalid project name");
          errorMessage.setTextFill(Color.RED);
        }
      }
    });

    VBox layout = new VBox(10);

    layout.getChildren().addAll(nameContainer, errorMessage, closeButton);
    layout.setAlignment(Pos.CENTER);

    Scene scene = new Scene(layout);
    window.setResizable(false);
    window.setScene(scene);
    window.showAndWait();

  }
  }
}
