package Employee;

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
     * Gets a Member object from position index from the list.
     *
     * @param index the position in the list of the Member object
     * @return the Member object at position index if one exists, else null
     */
    public Employee get(int index)
    {
        return employees.get(index);
    }

    public int getIndexFromEmployee(Employee employee){
        for(int i = 0 ; i < employees.size() ; i++){
            if(employees.get(i).equals(employee)){
                return i;
            }
        }
        return -1;
    }

    public void addMember(Employee member)
    {
        employees.add(member);
    }

    public void removeMember(Employee member)
    {
        employees.remove(member);
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
        for (int i = 0; i < employees.size(); i++)
        {
            str += employees.get(i).getName() + " , ";
        }
        str = str.substring(0, str.length() - 2);
        return str;
    }
}
