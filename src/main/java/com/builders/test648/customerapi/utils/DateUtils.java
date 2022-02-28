package com.builders.test648.customerapi.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {

	public static Integer getAge(Date birthDate) {

		if (birthDate == null) {
			return null;
		}

		Calendar dateOfBirth = new GregorianCalendar();
		dateOfBirth.setTime(birthDate);

		Calendar currentDate = Calendar.getInstance();

		int age = currentDate.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);

		dateOfBirth.add(Calendar.YEAR, age);

		if (currentDate.before(dateOfBirth)) {
			age--;
		}

		return age;
	}

	public static Date addYears(Date date, int years) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.YEAR, years);
		return c.getTime();
	}

	public static Date addDays(Date date, int days) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, days);
		return c.getTime();
	}

	/**
	 * Returns the begin date of birth period according to the age
	 * 
	 * @param age
	 * @return
	 */
	public static Date birthPeriodBeginDate(Integer age) {
		Date beginDate = new Date();
		beginDate = DateUtils.addYears(beginDate, (age * -1) - 1);
		beginDate = DateUtils.addDays(beginDate, 1);
		return beginDate;
	}

	/**
	 * Returns the end date of birth period according to the age
	 * 
	 * @param age
	 * @return
	 */
	public static Date birthPeriodEndDate(Integer age) {
		Date endDate = new Date();		
		endDate = DateUtils.addYears(endDate, age * -1);
		return endDate;
	}
	
}
