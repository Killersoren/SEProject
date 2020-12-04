import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Requirement implements Serializable
{
  private String name,userstory,status;
  private int estimatedHours,totalHoursWorked;
  private LocalDate deadline;
  private ArrayList<Task> tasks;

  public Requirement(String name, String userstory, String status,
      LocalDate deadline, int estimatedHours, int totalHoursWorked,
       ArrayList<Task> tasks) {
    this.name = name;
    this.userstory = userstory;
    this.status = status;
    this.estimatedHours = estimatedHours;
    this.deadline = deadline;
    this.totalHoursWorked = totalHoursWorked;
    this.tasks = tasks;
  }

  public String getName(){
    return name;
  }

  public String getUserstory()
  {
    return userstory;
  }

  public int getEstimatedHours()
  {
    return estimatedHours;
  }

  public int getTotalHoursWorked()
  {
    return totalHoursWorked;
  }

  public String getStatus()
  {
    return status;
  }

  public LocalDate getDeadline()
  {
    return deadline;
  }

}
