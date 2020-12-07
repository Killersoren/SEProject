import java.io.Serializable;
import java.util.ArrayList;

public class EmployeeList implements Serializable
{
  private ArrayList<Employee> employees;

  public EmployeeList()
  {
    this.employees = new ArrayList<>();
  }

  /**
   * Gets how many Members objects are in the list.
   *
   * @return the number of Members objects in the list
   */
  public int size()
  {
    return employees.size();
  }

  /**
   * Gets a Employee object from position index from the list.
   *
   * @param index the position in the list of the Employee object
   * @return the Employee object at position index if one exists, else null
   */
  public Employee get(int index)
  {
    return employees.get(index);
  }

  public void addMember(Employee employee)
  {
    employees.add(employee);
  }


  public void removeMember(Employee employee)
  {
    employees.remove(employee);
  }

  public void deleteMember(String name)
  {
    employees.remove(getIndexFromName(name));
  }

  public void replaceMember(String name,String newName)
  {
    employees.get(getIndexFromName(name)).setName(newName);
  }

  public int getIndexFromName(String name)
  {
    for (int i = 0; i < employees.size(); i++)
    {

      if (employees.get(i).getName().equals(name))
      {
        return i;
      }

    }
    return -1;
  }

  public ArrayList<Employee> getMembers()
  {
    return employees;
  }

  public boolean equals(Object obj)
  {
    if (obj instanceof EmployeeList)
    {
      if (this.getMembers().equals(((EmployeeList) obj).getMembers()))
      {
        return true;
      }
    }
    return false;
  }

  public String toString()
  {
    String str = "";
    if (employees.size()!=0)
    {
      for (int i = 0; i < employees.size(); i++)
      {
        str += employees.get(i).getName() + " , ";
      }
      str = str.substring(0, str.length() - 2);
    }
    return str;
  }
}
