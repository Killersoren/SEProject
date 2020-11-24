import java.util.ArrayList;

public class Project
{
  private String name;
  private ArrayList<Requirement> requirements;
  private MemberList team;

  public Project(String name, ArrayList<Requirement> requirements,
      MemberList team)
  {
    this.name = name;
    this.requirements = requirements;
    this.team = team;
  }

  public void editProject()
  {

  }

  /**
   * Gets the project's name.
   * @return the project's name
   */
  public String getName()
  {
    return name;
  }
}
