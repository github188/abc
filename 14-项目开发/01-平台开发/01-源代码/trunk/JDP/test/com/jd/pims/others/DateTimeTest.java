package com.jd.pims.others;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class DateTimeTest {
	@Test
	public void testDateTime(){
		Calendar currentTime = Calendar.getInstance();
		currentTime.setTime(new Date());
		currentTime.set(Calendar.HOUR_OF_DAY, 00);
		currentTime.set(Calendar.MINUTE, 20);
		Integer timePeriod=currentTime.get(Calendar.HOUR_OF_DAY);
		if(timePeriod<=0){
			timePeriod=24;
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:00:00");
		currentTime.set(Calendar.HOUR_OF_DAY, currentTime.get(Calendar.HOUR_OF_DAY));
		String end = df.format(currentTime.getTime());
		currentTime.add(Calendar.HOUR_OF_DAY, -1);
		String begin = df.format(currentTime.getTime());
		
		System.out.println("begin:"+begin+",end:"+end);
	}
}
