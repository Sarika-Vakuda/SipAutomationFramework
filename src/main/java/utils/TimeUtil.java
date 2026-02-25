package utils;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class TimeUtil
{	
	//private  final static ThreadLocal<LocalDateTime> executionStart = new ThreadLocal<>();

	 //private static final ThreadLocal<LocalDateTime> executionEnd = new ThreadLocal<>();
	 
    private static LocalDateTime executionStart;
    
    private static LocalDateTime executionEnd;
    
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


	public static String getStartDate(int days, String pattern)
	{
		try 
		{
			return LocalDate.now()
                .plusDays(days)
                .format(DateTimeFormatter.ofPattern(pattern));
		} 
		catch (Exception e)
		{
			throw new RuntimeException("Invalid date pattern: " + pattern, e);
		}
	}
	
	public static String getCurrentDayOfWeek()
	{
        return LocalDate.now()
                        .getDayOfWeek()
                        .getDisplayName(TextStyle.FULL, Locale.ENGLISH);
    }
	public static String getTimeStamp()
	{
		 String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
		 
		 return timestamp;
	}
	public static void markExecutionStart() 
    {
        
			executionStart = LocalDateTime.now();
		
    }

    public static void markExecutionEnd() 
    {
        executionEnd = LocalDateTime.now();
    }

    public static String getExecutionStart()
    {
        return executionStart.format(formatter);
    }

    public static String getExecutionEnd() 
    {
        return executionEnd.format(formatter);
    }

    public static String getExecutionDuration() 
    {
        Duration duration = Duration.between(executionStart, executionEnd);
        long minutes = duration.toMinutes();
        long seconds = duration.minusMinutes(minutes).getSeconds();
        return minutes + " min " + seconds + " sec";
    }

}

    

