import java.util.Date;

public class Deadline
{
  private Date startDate,currentDate,endDate;

  public Deadline(Date startDate, Date endDate)
  {
    this.startDate = startDate;
    this.currentDate = new Date();
    this.endDate = endDate;
  }

  public int getRemainingDays(){
    return 0;
  }


}
