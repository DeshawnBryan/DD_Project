package Java_p;

import java.util.concurrent.atomic.AtomicInteger;

public class Metrics 
{
    public final AtomicInteger appointmentsBooked = new AtomicInteger(0);
    public final AtomicInteger schedulingConflicts = new AtomicInteger(0);
    public final AtomicInteger highRiskTriage = new AtomicInteger(0);

    public void generateWeeklyReport() 
    {
        System.out.println("Weekly Report:");
        System.out.println("Appointments booked: " + appointmentsBooked.get());
        System.out.println("Scheduling conflicts: " + schedulingConflicts.get());
        System.out.println("High-risk triage count: " + highRiskTriage.get());
    }
}
