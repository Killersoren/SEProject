import java.io.Serializable;
import java.util.ArrayList;

public class Requirement implements Serializable
{
  private String name,userstory,status;
  private int estimatedHours,totalHoursWorked;
  private ArrayList<Task> tasks;

  public String getStatus()
  {
    return status;
  }

  public Requirement(String name, String userstory, String status
      ) {
    this.name = name;
    this.userstory = userstory;
    this.status = status;
    this.estimatedHours = 0;
    this.totalHoursWorked = 0;
    this.tasks = new ArrayList<>();
  }

  public String getName(){
    return name;
  }


}
