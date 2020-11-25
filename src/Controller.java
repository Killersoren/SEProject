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
  @FXML private TableView<Member> employeeField;
  @FXML private TableColumn<Member, String> employeeName;

  private ProjectListAdapter adapterProjects;
  private EmployeeListAdapter adapterEmployee;
  private MemberList finalMemberList;

  public void initialize()
  {
    adapterProjects = new ProjectListAdapter("Projects.bin");
    adapterEmployee = new EmployeeListAdapter("Employees.bin");
    updateEmployeeArea();
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

  private void updateEmployeeArea()
  {
    employeeField.getItems().clear();
    if (adapterEmployee != null)
    {
      finalMemberList = adapterEmployee.getAllMembers();
      for (int i = 0; i < finalMemberList.size(); i++)
      {
        System.out.println(finalMemberList.get(i));
          employeeField.getItems().add(finalMemberList.get(i));
        System.out.println(i);
      }

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
    TextField inputMemberName = new TextField();
    inputMemberName.setPromptText("Enter member name");
    nameContainer.getChildren().addAll(memberName, inputMemberName);

    Label errorMessage = new Label("");

    Button closeButton = new Button("Add new member");

    closeButton.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override public void handle(ActionEvent e)
      {
        if (!(inputMemberName.getText().isEmpty() || inputMemberName.getText()
            .equals("")))
        {
          window.close();
          Member member = new Member(inputMemberName.getText());
          System.out.println(member.getName());
          finalMemberList.addMember(member);
          System.out.println("A");
          adapterEmployee.saveMembers(finalMemberList);
          System.out.println("B");
          updateEmployeeArea();
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

  @FXML public void editEmployeeClick()
  {

  }

  @FXML public void removeEmployeeClick()
  {

  }

  @FXML public void addProjectClick()
  {

  }

  @FXML public void editProjectClick()
  {

  }

  @FXML public void removeProjectClick()
  {

  }

  @FXML public void search()
  {

  }

  @FXML public void searchClick()
  {

  }
}


