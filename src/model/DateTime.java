package model;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;


@SuppressWarnings("serial")
public class DateTime implements Serializable{
	
	private long advancement;
	
	private LocalDateTime dateTime;
	
	public DateTime() {
		advancement = 0;
	}
	
	public void refresh() {
		
		long millis = System.currentTimeMillis() + advancement;	
		Instant instant = Instant.ofEpochMilli(millis);
		dateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
	}
	
	public void setDateTimeManual(int year, int month, int day, int hour, int min, int second) {
		
		LocalDateTime dtUser = LocalDateTime.of(year, month, day, hour, min, second);
		ZonedDateTime zdtUser = dtUser.atZone(ZoneId.systemDefault());
		long difference = zdtUser.toInstant().toEpochMilli() - System.currentTimeMillis();
		
		if(difference>0)
			advancement = difference;
		else {
			throw new NumberFormatException("The new date time can not be before than current date time.");
		}
	}
	
	public void setDateTimeAutomathic() {
		advancement =0;
	}
	
	public long getMillis() {
		return System.currentTimeMillis() + advancement;
	}
	
	@Override
	public String toString() {
		refresh();
		return "Date: "+dateTime.toLocalDate().toString()+"  Time:  "+dateTime.toLocalTime().toString();
	}
	
	public static LocalDateTime millisToLDT(long millis) {
		Instant instant = Instant.ofEpochMilli(millis);
		return instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
	}
	
	public static long getMillisDayOfLDT(long dt) {
		Instant instant = Instant.ofEpochMilli(dt);
		LocalDateTime ldt = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
		LocalDateTime day = LocalDateTime.of(ldt.getYear(), ldt.getMonthValue(), ldt.getDayOfMonth(), 0, 0, 0);
		return day.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
	}
}
