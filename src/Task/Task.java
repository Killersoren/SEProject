package Task;

import Employee.Employee;
import Employee.EmployeeList;

import java.io.Serializable;
import java.time.LocalDate;

public class Task implements Serializable
{
  private String name, status;
  private int relatedRequirement, estimatedHours, totalHoursWorked, taskID;
  private LocalDate deadline;
  private Employee responsibleMember;
  private EmployeeList taskMembers;

  public Task(String name, int taskID,String status, LocalDate deadline,
      EmployeeList taskMembers)
  {
    this.deadline = deadline;
    this.name = name;
    this.status = status;
    relatedRequirement = 0;
    this.taskID = taskID;
    estimatedHours = 0;
    totalHoursWorked = 0;
    responsibleMember = null;
    this.taskMembers = taskMembers;
  }

  public String getName()
  {
    return name;
  }

  public String getStatus()
  {
    return status;
  }

  public LocalDate getDeadline()
  {
    return deadline;
  }

  public int getEstimatedHours()
  {
    return estimatedHours;
  }

  public int getTotalHoursWorked()
  {
    return totalHoursWorked;
  }
}
