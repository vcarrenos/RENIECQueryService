package pe.gob.midis.sisfoh.utils;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Formatter;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * <p>
 * Title: DateUtils
 * </p>
 * <p>
 * Description: Clase de Herramientas
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: Sector Code
 * </p>
 * 
 * @author Josué López
 * @version 1.0
 */

public class DateUtil implements java.io.Serializable
{

	/**
     * 
     */
	private static final long	serialVersionUID		= -316809041964018105L;

	public static String		DATE_FORMAT				= "dd/MM/yyyy";
	public static String		TIME_FORMAT				= "H:mm:ss";
	public static String		DATE_TIME_FORMAT		= "dd/MM/yyyy hh:mm:ss";
	public static String		SQL_DATE_FORMAT			= "dd/MM/yyyy";
	public static String		SQL_TIME_FORMAT			= "HH24:MI:SS";
	public static String		SQL_DATE_TIME_FORMAT	= "dd/MM/yyyy HH24:MI:SS";

	private static String[]		dayOfWeek				= { "Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado" };
	private static String[]		nameMonths				= { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Setiembre", "Octubre", "Noviembre", "Diciembre" };

	/**
	 * Obtiene la fecha actual del sistema
	 * 
	 * @return fecha
	 */

	/**
	 * Constructor si se desea usar los formatos del archivo de propiedades
	 */

	public DateUtil()
	{
	}

	/**
	 * Obtiene la fecha en formato String
	 * 
	 * @return
	 */
	public static String getDate()
	{
		Calendar calendar = new GregorianCalendar();
		Date today = new java.sql.Date(calendar.getTime().getTime());
		return getDate(today);
	}

	/**
	 * Obtiene la fecha en formato String
	 * 
	 * @return
	 */
	public static String getDateTime()
	{
		Calendar calendar = new GregorianCalendar();
		Date today = new java.sql.Date(calendar.getTime().getTime());
		return getDate(DATE_TIME_FORMAT, today);
	}

	public static String getDateTimeGMT()
	{
		Calendar calendar = new GregorianCalendar();
		Date today = new java.sql.Date(calendar.getTime().getTime());
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat(DATE_TIME_FORMAT);
		formatter.setTimeZone(TimeZone.getTimeZone("GMT"));

		return formatter.format(today.getTime());
	}

	/**
	 * Obtiene la fecha en formato String
	 * 
	 * @return
	 */
	public static String getDateTime(Date date)
	{
		return date != null ? getDate(DATE_TIME_FORMAT, date) : null;
	}

	/**
	 * Obtiene la fecha en formato String
	 * 
	 * @return
	 */
	public static String getDateTime(Timestamp date)
	{
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat(DATE_TIME_FORMAT);
		return date != null ? formatter.format(date.getTime()) : null;
	}

	/**
	 * Obtiene la fecha en formato String
	 * 
	 * @return
	 */
	public static String getDateTime(Time date)
	{
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat(DATE_TIME_FORMAT);
		return date != null ? formatter.format(date.getTime()) : null;
	}

	/**
	 * Obtiene el dia de la fecha actual
	 * 
	 * @return
	 */
	public static int getDay()
	{
		Calendar calendar = new GregorianCalendar();
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * Obtiene el mes de la fecha actual
	 * 
	 * @return
	 */
	public static int getMonth()
	{
		Calendar calendar = new GregorianCalendar();
		return calendar.get(Calendar.DAY_OF_MONTH) + 1;
	}

	/**
	 * Obtiene el año de la fecha actual
	 * 
	 * @return
	 */
	public static int getYear()
	{
		Calendar calendar = new GregorianCalendar();
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * Obtiene el dia de la fecha ingresada
	 * 
	 * @param date
	 *            fecha
	 * @return
	 */
	public static int getDay(Date date)
	{
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * Obtiene el mes de la fecha ingresada
	 * 
	 * @param date
	 *            fecha
	 * @return
	 */
	public static int getMonth(Date date)
	{
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH) + 1;
	}

	/**
	 * Obtiene el año de la fecha ingresada
	 * 
	 * @param date
	 *            fecha
	 * @return
	 */
	public static int getYear(Date date)
	{
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * Obtiene la hora de la fecha actual
	 * 
	 * @return
	 */
	public static int getHour()
	{
		Calendar calendar = new GregorianCalendar();
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * Obtiene los minutos de la fecha actual
	 * 
	 * @return
	 */
	public static int getMinute()
	{
		Calendar calendar = new GregorianCalendar();
		return calendar.get(Calendar.MINUTE);
	}

	/**
	 * Obtiene los segundos de la fecha actual
	 * 
	 * @return
	 */
	public static int getSecond()
	{
		Calendar calendar = new GregorianCalendar();
		return calendar.get(Calendar.SECOND);
	}

	/**
	 * Obtiene los milisegundos de la fecha actual
	 * 
	 * @return
	 */
	public static int getMillisecond()
	{
		Calendar calendar = new GregorianCalendar();
		return calendar.get(Calendar.MILLISECOND);
	}

	/**
	 * Obtiene la hora de la fecha ingresada
	 * 
	 * @param date
	 * @return
	 */
	public static int getHour(Date date)
	{
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * Obtiene los minutos de la fecha ingresada
	 * 
	 * @param date
	 * @return
	 */
	public static int getMinute(Date date)
	{
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar.get(Calendar.MINUTE);
	}

	/**
	 * Obtiene los segundos de la fecha ingresada
	 * 
	 * @param date
	 * @return
	 */
	public static int getSecond(Date date)
	{
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar.get(Calendar.SECOND);
	}

	/**
	 * Obtiene los milisegundos de la fecha ingresada
	 * 
	 * @param date
	 * @return
	 */
	public static int getMillisecond(Date date)
	{
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar.get(Calendar.MILLISECOND);
	}

	/**
	 * Obtiene un objeto fecha a partir de una cadena del tipo dd-MM-yyyy
	 * 
	 * @param date
	 * @return
	 */
	public static String getDate(String date)
	{
		if (date == null)
			return null;

		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
		try
		{
			long dateValue = formatter.parse(date).getTime();
			java.sql.Date today = new java.sql.Date(dateValue);
			return formatter.format(today);
		}
		catch (Exception e)
		{
			return null;
		}
	}

		
	/**
	 * Obtiene un objeto fecha a partir de una cadena del tipo dd-MM-yyyy
	 * 
	 * @param date
	 * @return
	 */
	public static java.sql.Date toDate(String format, String date)
	{
		if (date == null)
			return null;

		SimpleDateFormat formatter = new SimpleDateFormat(format);

		try
		{
			long dateValue = formatter.parse(date).getTime();
			java.sql.Date today = new java.sql.Date(dateValue);
			return today;
		}
		catch (Exception e)
		{
			return null;
		}
	}

	/**
	 * Obtiene un objeto fecha
	 * 
	 * @return
	 */
	public static java.sql.Date toDate()
	{
		Calendar calendar = new GregorianCalendar();
		Date today = new java.sql.Date(calendar.getTime().getTime());
		return today;
	}

	/**
	 * Obtiene un objeto fecha con tiempo
	 * 
	 * @return
	 */
	public static java.sql.Timestamp toDateTime()
	{
		Calendar calendar = new GregorianCalendar();
		java.sql.Timestamp today = new java.sql.Timestamp(calendar.getTime().getTime());
		return today;
	}

	/**
	 * Obtiene un objeto fecha a partir de una cadena del tipo dd/MM/yyyy
	 * 
	 * @param date
	 * @return
	 */
	public static java.sql.Date toDate(String date)
	{
		return toDate(DATE_FORMAT, date);
	}

	/**
	 * Obtiene un objeto fecha a partir de una cadena del tipo dd/MM/yyyy
	 * H:mm:ss
	 * 
	 * @param date
	 * @return
	 */
	public static java.sql.Timestamp toDateTime(String date)
	{
		if (date == null)
			return null;

		SimpleDateFormat formatter = new SimpleDateFormat(DATE_TIME_FORMAT);
		try
		{
			long dateValue = formatter.parse(date).getTime();
			java.sql.Timestamp today = new java.sql.Timestamp(dateValue);
			return today;
		}
		catch (Exception e)
		{
			return null;
		}
	}

	/**
	 * Obtiene la hora en formato time a partir de un valor en milisegundos
	 * 
	 * @param timeMillis
	 * @return
	 */
	public static java.sql.Time toTime(long timeMillis)
	{
		if (timeMillis == 0)
			return null;
		try
		{
			return new java.sql.Time(timeMillis);
		}
		catch (Exception e)
		{
			return null;
		}
	}

	/**
	 * formatea la hora ingresada en formato timeMillis a una cadena
	 * 
	 * @param timeMillis
	 * @return
	 */
	public static String toExpressionTime(long timeMillis)
	{
		if (timeMillis == 0)
			return null;

		Formatter f = new Formatter();
		
		try
		{
			int days = (int) (timeMillis / (24L * 60 * 60 * 1000));
			int remdr = (int) (timeMillis % (24L * 60 * 60 * 1000));
			int hours = remdr / (60 * 60 * 1000);
			remdr %= 60 * 60 * 1000;
			int minutes = remdr / (60 * 1000);
			remdr %= 60 * 1000;
			int seconds = remdr / 1000;

			f.format("%2d días %02d:%02d:%02d seg", days, hours, minutes, seconds);
			return f.toString();
		}
		catch (Exception e)
		{
			return null;
		}
		finally {
			f.close();			
		}
	}
	
	

	/**
	 * Obtiene una cadena fecha a partir de un formato y objeto fecha el cual
	 * puede ser yyyy-MM-dd 2006-10-09 dd.MM.yy 09.04.98 yyyy.MM.dd G 'at'
	 * hh:mm:ss z 1998.04.09 AD at 06:15:55 PDT EEE, MMM d, ''yy Thu, Apr 9, '98
	 * h:mm a 6:15 PM H:mm 18:15 H:mm:ss:SSS 18:15:55:624 K:mm a,z 6:15 PM,PDT
	 * yyyy.MMMMM.dd GGG hh:mm aaa 1998.April.09 AD 06:15 PM
	 * 
	 * @param format
	 * @param date
	 * @return
	 */
	public static String getDate(String format, Date date)
	{
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat(format);
		return date != null ? formatter.format(date) : null;
	}

	/**
	 * Convierte la fecha de cadena a fecha
	 * 
	 * @param date
	 *            fecha en formato cadena
	 * @return fecha convertida
	 */
	public static String getDate(Date date)
	{
		return getDate(DATE_FORMAT, date);
	}

	/**
	 * Addciona n dias a una fecha
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date addDay(Date date, int days)
	{
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, days);
		return new java.sql.Date(calendar.getTime().getTime());
	}

	/**
	 * Diferencias de fecha
	 * 
	 * @param d1
	 *            fecha mayor
	 * @param d2
	 *            fecha menor
	 * @return Diferencia en dias
	 */

	public static long diffDate(Date date1, Date date2)
	{
		long dt = date1.getTime() - date2.getTime();
		if (dt < 0)
			dt = dt * -1;
		dt /= 1000;
		dt /= 60;
		dt /= 60;
		dt /= 24;
		return dt;
	}

	/**
	 * Crea un objeto fecha a partir de tres valores numericos año, mes y dia
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static Date getDate(int year, int month, int day)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DAY_OF_MONTH, day);
		return new java.sql.Date(calendar.getTime().getTime());
	}

	/**
	 * Obtiene la fecha en formato long
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static long getTime(int year, int month, int day)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DAY_OF_MONTH, day);
		return (new java.sql.Date(calendar.getTime().getTime())).getTime();
	}

	/**
	 * Obtiene la fecha en formato long
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static long getTime(String year, String month, String day)
	{
		return getTime(Integer.valueOf(year).intValue(), Integer.valueOf(month).intValue(), Integer.valueOf(day).intValue());
	}

	/**
	 * Obtiene el tiempo en formato long a partir de una cadena
	 * 
	 * @param date
	 * @return tiempo
	 */
	public static long getTime(String date)
	{
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
		try
		{
			long dateValue = formatter.parse(date).getTime();
			java.sql.Date today = new java.sql.Date(dateValue);
			return today.getTime();
		}
		catch (Exception e)
		{
			return -1;
		}
	}

	/**
	 * Obtiene la fecha formateada segun el año mes y día
	 * 
	 * @param format
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static String getDate(String format, int year, int month, int day)
	{
		Locale currentLocale = new Locale("en", "US");
		SimpleDateFormat formatter = new SimpleDateFormat(format, currentLocale);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DAY_OF_MONTH, day);
		return formatter.format(calendar.getTime());
	}

	/**
	 * Obtiene la fecha formateada segun el año mes y día
	 * 
	 * @param format
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static String getDate(String format, String year, String month, String day)
	{
		return getDate(format, Integer.valueOf(year).intValue(), Integer.valueOf(month).intValue(), Integer.valueOf(day).intValue());
	}

	/**
	 * Obtiene el nombre del día de la semana
	 * 
	 * @param date
	 * @return
	 */
	public static String getLongDayOfWeek(Date date)
	{
		Calendar calendar = new GregorianCalendar();
		return dayOfWeek[calendar.get(Calendar.DAY_OF_WEEK)];
	}

	/**
	 * Obtiene el nombre del día de la semana
	 * 
	 * @param date
	 * @return
	 */
	public static String getLongNameOfMonth(Date date)
	{
		Calendar calendar = new GregorianCalendar();
		return nameMonths[calendar.get(Calendar.MONTH)];
	}

	/**
	 * Obtiene el nombre del día de la semana en formato corto
	 * 
	 * @param date
	 * @return
	 */
	public static String getShortDayOfWeek(Date date)
	{
		Calendar calendar = new GregorianCalendar();
		return dayOfWeek[calendar.get(Calendar.DAY_OF_WEEK)].substring(3);
	}

	/**
	 * Obtiene el nombre del mes en formato corto
	 * 
	 * @param date
	 * @return
	 */
	public static String getShortNameOfMonth(Date date)
	{
		Calendar calendar = new GregorianCalendar();
		return nameMonths[calendar.get(Calendar.MONTH)].substring(3);
	}

	/**
	 * Obtiene el nombre del día de la semana en curso
	 * 
	 * @param date
	 * @return
	 */
	public static String getLongDayOfWeek()
	{
		Calendar calendar = new GregorianCalendar();
		return dayOfWeek[calendar.get(Calendar.DAY_OF_WEEK)];
	}

	/**
	 * Obtiene el nombre del día del mes en curso
	 * 
	 * @param date
	 * @return
	 */
	public static String getLongNameOfMonth()
	{
		Calendar calendar = new GregorianCalendar();
		return nameMonths[calendar.get(Calendar.MONTH)];
	}

	/**
	 * Obtiene el nombre del día de la semana en curso en formato corto
	 * 
	 * @param date
	 * @return
	 */
	public static String getShortDayOfWeek()
	{
		Calendar calendar = new GregorianCalendar();
		return dayOfWeek[calendar.get(Calendar.DAY_OF_WEEK)].substring(3);
	}

	/**
	 * Obtiene el nombre del mes en curso en formato corto
	 * 
	 * @param date
	 * @return
	 */
	public static String getShortNameOfMonth()
	{
		Calendar calendar = new GregorianCalendar();
		return nameMonths[calendar.get(Calendar.MONTH)].substring(3);
	}
}