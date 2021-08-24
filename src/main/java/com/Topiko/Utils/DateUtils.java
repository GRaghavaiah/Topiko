package com.Topiko.Utils;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Seconds;

public final class DateUtils
{
    private DateUtils()
    {

    }

    private static final Log LOG = LogFactory.getLog(DateUtils.class);

    /**
     * static final members for various date formats useful in the application
     */
    public static final SimpleDateFormat YYYY_MM_DD_TIME = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SS");
    public static final SimpleDateFormat DD_MM_YYYY = new SimpleDateFormat("dd-MM-yyyy");
    public static final SimpleDateFormat DD_MM_YYYY_SLASH = new SimpleDateFormat("dd/MM/yyyy");
    public static final SimpleDateFormat YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat HH_MM_SS_SS = new SimpleDateFormat("hh:mm:ss.SS");
    public static final SimpleDateFormat MM_DD_YYYY_TIME = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss.SS");
    public static final SimpleDateFormat MM_DD_YYYY = new SimpleDateFormat("MM/dd/yyyy");
    public static final SimpleDateFormat MM_DD_YYYY_TIME_SHORT = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
    public static final SimpleDateFormat DD_MM_YYYY_TIME_SHORT = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
    public static final SimpleDateFormat FULL_DATE_WITH_DAY = new SimpleDateFormat("EEE MMM dd, yyyy hh:mm a");
    public static final SimpleDateFormat MM_DD_YYYY_FULL_WITH_DAY = new SimpleDateFormat("MM/dd/yyyy EEE hh:mm a");
    public static final SimpleDateFormat TIME_FORMAT_ZONE = new SimpleDateFormat("K:mm a, z");
    public static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("h:mm a");
    public static final SimpleDateFormat DD_MM_YYYY_hh_MM = new SimpleDateFormat("dd/MM/yyyy hh:mm");
    public static final SimpleDateFormat DEFAULT_TIMESTAMP_FORMAT = MM_DD_YYYY_TIME;
    public static final SimpleDateFormat DEFAULT_DATE_FORMAT = DD_MM_YYYY_SLASH;
    public static final SimpleDateFormat DEFAULT_DATETIME_FORMAT = DD_MM_YYYY_TIME_SHORT;
    public static final SimpleDateFormat YYYY_MM_DD_HH_MM = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public static final SimpleDateFormat dd_MMM_yyyy_FORMAT = new SimpleDateFormat("dd-MMM-yyyy");
    public static final SimpleDateFormat YYYY_MM_DD_HH_MM_SS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * Used to format the date from sal format to dd/mm/yyyy format
     * 
     * @param date
     * @return string in dd/mm/yyyy format
     */
    public static String formatDate(String date)
    {
        try
        {
            if (date != null)
            {
                SimpleDateFormat dbFormat = new SimpleDateFormat("yyyy-MM-dd");

                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                return df.format(dbFormat.parse(date));
            }
        }
        catch (ParseException e)
        {
            // e.printStackTrace();
            LOG.error("Date Parsing Exception : " + e.getMessage());
        }

        return date;
    }

    public static String formatDate(Date date, String dateFormat)
    {
        if (date != null)
        {
            if (dateFormat == null)
            {
                dateFormat = "dd/MM/yyyy";
            }
            DateFormat formatter = new SimpleDateFormat(dateFormat);

            return formatter.format(date);
        }
        return null;
    }

    /**
     * used to convert String date to sql date
     * 
     * @param date
     * @return
     */
    
    public static java.sql.Date getSqlDateFromStringWithFormat(String date, String dateFormat)
    {
        try
        {
            if (date != null)
            {
                if (dateFormat == null)
                {
                    dateFormat = "yyyy-MMM-dd";
                }
                DateFormat formatter = new SimpleDateFormat(dateFormat);
                java.util.Date dat = formatter.parse(date);
                Calendar c = formatter.getCalendar();
                c.setTime(dat);
                long l = c.getTimeInMillis();
                return new java.sql.Date(l);
            }
            return null;
        }
        catch (ParseException e)
        {
            LOG.error(e.getMessage());
            return null;
        }
    }
    
    
    
    public static java.sql.Date getSqlDateFromString(String date)
    {
        try
        {
            if (date != null)
            {
                DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                java.util.Date dat = formatter.parse(date);
                Calendar c = formatter.getCalendar();
                c.setTime(dat);
                long l = c.getTimeInMillis();
                return new java.sql.Date(l);
            }
            return null;
        }
        catch (ParseException e)
        {
            LOG.error(e.getMessage(), e);
            return null;
        }
        catch (Exception e)
        {
            LOG.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * used to convert String date to sql date
     * 
     * @param date
     * @return
     */
    public static java.sql.Date getSqlDateFromString(String date, String dateFormat)
    {
        try
        {
            if (date != null)
            {
                if (dateFormat == null)
                {
                    dateFormat = "dd/MM/yyyy";
                }
                DateFormat formatter = new SimpleDateFormat(dateFormat);
                java.util.Date dat = formatter.parse(date);
                Calendar c = formatter.getCalendar();
                c.setTime(dat);
                long l = c.getTimeInMillis();
                return new java.sql.Date(l);
            }
            return null;
        }
        catch (ParseException e)
        {
            LOG.error(e.getMessage());
            return null;
        }
    }

    /**
     * To get previous day's date for the entered date string
     * 
     * @param dateStr
     * @return sql date
     */
    public static java.sql.Date getPreviousDayDate(String dateStr)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date yesterday = null;
        try
        {
            GregorianCalendar gc = new GregorianCalendar();
            java.util.Date d = sdf.parse(dateStr);
            gc.setTime(d);
            int dayBefore = gc.get(Calendar.DAY_OF_YEAR);
            gc.roll(Calendar.DAY_OF_YEAR, -1);
            int dayAfter = gc.get(Calendar.DAY_OF_YEAR);
            if (dayAfter > dayBefore)
            {
                gc.roll(Calendar.YEAR, -1);
            }
            gc.get(Calendar.DATE);
            yesterday = gc.getTime();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return getSqlDateFromString(sdf.format(yesterday));
    }

    public static String getAfterDayDate(String dateStr)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String tomarrow = null;
        try
        {
            GregorianCalendar gc = new GregorianCalendar();
            java.util.Date d = sdf.parse(dateStr);
            gc.setTime(d);
            gc.add(Calendar.DATE, 1);
            tomarrow = gc.get(Calendar.YEAR) + "-" + (gc.get(Calendar.MONTH) + 1) + "-" + gc.get(Calendar.DATE);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return tomarrow;
    }

    /**
     * method to getCurrent Time Stamp as String
     * 
     * @return
     */

    public static String getCurrentTimestamp()
    {
        Date date = new Date();
        return DEFAULT_TIMESTAMP_FORMAT.format(date);
    }

    /**
     * method to get timestamp according to dateformat given
     * 
     * @param format
     * @return
     */

    public static String getCurrentTimestamp(SimpleDateFormat format)
    {
        Date date = new Date();
        return DEFAULT_TIMESTAMP_FORMAT.format(date);
    }

    /**
     * uses the default DEFAULT_DATE_FORMAT or DEFAULT_DATETIME_FORMAT based on input length
     * 
     * @param dateString
     * @return java.util.Date
     * @see DEFAULT_DATE_FORMAT
     * @see DEFAULT_DATETIME_FORMAT
     */

    public static Date getDate(String dateString)
    {
        try
        {
            if (dateString != null && dateString.length() < 13)
            {
                return YYYY_MM_DD.parse(dateString); // return date only
            }
            else
            {
                //
                return DEFAULT_DATE_FORMAT.parse(dateString); // return
                                                              // timestamp
                                                              // column
            } // end if
        }
        catch (ParseException e)
        {
            LOG.error(e.getMessage());
            return null;
        }
    } // end

    public static Date getDateWithFormat(String dateString, String format)
    {
        try
        {
            if (format == null)
            {
                format = "dd-MM-yyyy";
            }
            return new SimpleDateFormat(format).parse(dateString); // return
                                                                   // date only
        }
        catch (ParseException e)
        {
            e.printStackTrace();
            return null;
        }
    } // end
    public static java.sql.Date getCurrentSystemDate()
    {
        return new java.sql.Date(Calendar.getInstance().getTime().getTime());
    }

    public static void main(String[] args)
    {
        System.out.println(getCurrentSystemDate());
    }

    /**
     * to get java.sql.Time from current System time
     * 
     * @return
     */
    public static java.sql.Time getCurrentSystemTime()
    {
        return new java.sql.Time(Calendar.getInstance().getTime().getTime());
    }

    /**
     * to get java.sql.Timestamp from current System time
     * 
     * @return
     */
    public static java.sql.Timestamp getYesterDaySystemTimestamp()
    {
	    Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DAY_OF_MONTH, (int) -1);
	    Date newDate = cal.getTime();
	    return new java.sql.Timestamp(newDate.getTime());
    }
    
    public static java.sql.Timestamp getCurrentSystemTimestamp()
    {
        return new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
    }

    /**
     * @param date
     * @return
     */
    public static java.sql.Date getSqlDateFromUtilDate(java.util.Date date)
    {
        return new java.sql.Date(date.getTime());
    }
    public static java.util.Date getUtilDateFromSqlDate(java.sql.Date date)
    {
        return new java.util.Date(date.getTime());
    }

    /**
     * @return
     */
    public static String getCurrentTimeInStringFormat()
    {
        Date d = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMddHHmmss");
        return ft.format(d);
    }

    public static String getCurrentDateInStringFormat()
    {
        Date d = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
        return ft.format(d);
    }

    public static String getCurrentTimeInStringFormatWithMilliSec()
    {
        Date d = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return ft.format(d);
    }

    /**
     * @param monthValue
     * @param yearValue
     * @return
     */
    public static int getMaxDateInMonth(String monthValue, String yearValue)
    {
        int maxDay = 0;
        try
        {
            int month = Integer.parseInt(monthValue);
            int year = Integer.parseInt(yearValue);
            GregorianCalendar cal = new GregorianCalendar(year, (month - 1), 1); // Get
                                                                                 // the
                                                                                 // number
                                                                                 // of
                                                                                 // days
                                                                                 // in
                                                                                 // that
                                                                                 // month
            maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return 0;
        }
        return maxDay;
    }

    /**
     * @param date1
     * @param date2
     * @return
     */
    public static String compareDates(java.sql.Date date1, java.sql.Date date2)
    {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        String str = "";
        long timeinmillis1 = calendar1.getTimeInMillis();
        long timeinmillis2 = calendar2.getTimeInMillis();
        if (timeinmillis1 > timeinmillis2)
        {
            str = "DATE1";
        }
        else
        {
            str = "DATE2";
        }

        return str;
    }

    /**
     * @param dateStr
     * @param format
     * @return
     */
    public static Time getSqlTimeStamp(String dateStr, String format)
    {
        Date date = getDateWithFormat(dateStr, format);
        return new Time(date.getTime());
    }

    public static long getDateTimeInMilliSeconds(String dateStr, String timeStr)
    {
        // Make a String that has a date in it, with MEDIUM date format
        // and SHORT time format.
        String dateString = dateStr + " " + timeStr;

        // Get the default MEDIUM/SHORT DateFormat
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date = null;
        // Parse the date
        try
        {
            date = format.parse(dateString);
        }
        catch (ParseException pe)
        {
            System.out.println("ERROR: could not parse date in string \"" + dateString + "\"");
        }
        return date.getTime();
    }

    public static int daysBetween(java.sql.Date d1, java.sql.Date d2)
    {
        return ((int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24))) + 1;
    }
    
    public static int getDaysDifferenceBetweenDays(java.sql.Date fromDate, java.sql.Date toDate)
    {
        return ((int) ((toDate.getTime() - fromDate.getTime()) / (1000 * 60 * 60 * 24)));
    }
    
    @SuppressWarnings("unused")
	public static String daysHoursSecsBetweenTwoTimeStamps(Timestamp t1, Timestamp t2)
    {
    	String difference="";
        long different = t2.getTime() - t1.getTime();
		
		long secondsInMilli = 1000;
		long minutesInMilli = secondsInMilli * 60;
		long hoursInMilli = minutesInMilli * 60;
		long daysInMilli = hoursInMilli * 24;

		long elapsedDays = different / daysInMilli;
		different = different % daysInMilli;
		
		long elapsedHours = different / hoursInMilli;
		different = different % hoursInMilli;
		
		long elapsedMinutes = different / minutesInMilli;
		different = different % minutesInMilli;
		
		long elapsedSeconds = different / secondsInMilli;
		
		return  difference=elapsedDays+" days "+elapsedHours+" hours "+elapsedMinutes+" minutes "+elapsedSeconds+" seconds ";
    }
    
    /**
     * to get java.sql.Time from String time
     * 
     * @return
     */
    @SuppressWarnings("deprecation")
    public static java.sql.Time getSqlTimeFromString(String timeStr)
    {
        try
        {
            if (timeStr != null)
            {
                String[] ampmSplitArr = timeStr.split(" ");
                String time = ampmSplitArr[0];
                String[] actualTime = time.split(":");
                if (timeStr.contains("AM"))
                {
                    if (Integer.parseInt(actualTime[0]) == 12)
                    {
                        return new java.sql.Time(Integer.parseInt(actualTime[0]) + 12, Integer.parseInt(actualTime[1]), Integer.parseInt("00"));
                    }
                    else
                    {
                        return new java.sql.Time(Integer.parseInt(actualTime[0]), Integer.parseInt(actualTime[1]), Integer.parseInt("00"));
                    }
                }
                else
                {
                    if (Integer.parseInt(actualTime[0]) == 12)
                    {
                        return new java.sql.Time(Integer.parseInt(actualTime[0]), Integer.parseInt(actualTime[1]), Integer.parseInt("00"));
                    }
                    else
                    {
                        return new java.sql.Time(Integer.parseInt(actualTime[0]) - 12, Integer.parseInt(actualTime[1]), Integer.parseInt("00"));
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        return null;
    }


    public static double timeDifference(long latertime, long earliertime)
    {
        double difference = latertime - earliertime;
        double noOfHours = (difference) / (1000 * 60 * 60);
        return noOfHours;
    }

    public static long timeDifferenceInHours(long latertime, long earliertime)
    {
        long noOfHours = (latertime - earliertime) / (1000 * 60 * 60);
        return noOfHours;
    }

    public static long timeDifferenceInMinutes(long laterTime, long earliyerTime)
    {
        long noOfMinuts = (long) Math.floor((laterTime - earliyerTime) / 1000 / 60);
        return noOfMinuts;
    }

    public static long ellapsedMinutes(long laterTime, long earliyerTime)
    {
        int noOfMinuts = (int) (Math.floor((laterTime - earliyerTime) / (1000 * 60)) % 60);
        return noOfMinuts;
    }

    @SuppressWarnings("unused")
    public static long dateDifference(long latertime, long earliertime)
    {
        double difference = latertime - earliertime;

        double daysDifference = Math.floor(difference / 1000 / 60 / 60 / 24);
        difference -= daysDifference * 1000 * 60 * 60 * 24;

        double hoursDifference = Math.floor(difference / 1000 / 60 / 60);
        difference -= hoursDifference * 1000 * 60 * 60;

        double minutesDifference = Math.floor(difference / 1000 / 60);
        difference -= minutesDifference * 1000 * 60;

        double secondsDifference = Math.floor(difference / 1000);

        return (long) daysDifference;
    }

    public static String getTime(String time)
    {
        String subTime = time.substring(0, time.length() - 3);
        if (time.contains("PM"))
        {
            int hours = Integer.parseInt(subTime.substring(0, subTime.indexOf(':')));
            subTime = subTime.replaceFirst("" + hours, "" + (hours + 12));
        }
        return subTime;
    }

    public static synchronized String getUniqueId()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmssS");
        return dateFormat.format(new Date());
    }

    public static java.sql.Date addDays(Date d, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, (int) days);
        Date newDate = cal.getTime();
        return new java.sql.Date(newDate.getTime());
    }

    /**
     * used to convert String date to TimeStamp
     * 
     * @param date
     * @return
     */
    
    
    public static Timestamp getTimeStampDateFromStringTimeStamp(String timestamp)
    {
        try
        {
        	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss");
            Date parsedDate = dateFormat.parse(timestamp);
            Timestamp ts = new java.sql.Timestamp(parsedDate.getTime());
            return ts;
        }
        catch (ParseException e)
        {
            LOG.info(e.getCause(), e);
            return null;
        }
    }
    
    public static Timestamp getTimeStampDateFromStringTimeStampMicroSS(String timestamp)
    {
        try
        {
        	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSSSSS");
            Date parsedDate = dateFormat.parse(timestamp);
            Timestamp ts = new java.sql.Timestamp(parsedDate.getTime());
            return ts;
        }
        catch (ParseException e)
        {
            LOG.info(e.getCause(), e);
            return null;
        }
    }
    
    public static Timestamp getTimeStampDateFromStringTimeStamp1(String timestamp)
    {
        try
        {
        	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date parsedDate = dateFormat.parse(timestamp);
            Timestamp ts = new java.sql.Timestamp(parsedDate.getTime());
            return ts;
        }
        catch (ParseException e)
        {
            LOG.info(e.getCause(), e);
            return null;
        }
    }
    
    
    
    public static Timestamp getTimeStampDateFromStringTimeStampWithFormat(String timestamp,String frmt)
    {
        try
        {
        	String fm="";
        	if(frmt!=null)
        		fm=frmt;
        	else
        		fm="yyyy-MMM-dd hh:mm:ss";
        	SimpleDateFormat dateFormat = new SimpleDateFormat(fm);
            Date parsedDate = dateFormat.parse(timestamp);
            Timestamp ts = new java.sql.Timestamp(parsedDate.getTime());
            return ts;
        }
        catch (ParseException e)
        {
            LOG.info(e.getCause(), e);
            return null;
        }
    }
    
    
    public static String convertDateFormatyyyyMMddToddMMMyyyy(String strDate)
    {
        try
        {
        	//input date format
        	 SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
        	//output date format
        	 SimpleDateFormat dFormatFinal = new SimpleDateFormat("dd-MMM-yyyy");
        	 Date date1 = dFormat.parse(strDate);
     		String ddate = dFormatFinal.format(date1);
            return ddate;
        }
        catch (ParseException e)
        {
            LOG.info(e.getCause(), e);
            return null;
        }
    }
    
    
    public static String convertDateFormatyyyyMMMddToddMMyyyy(String strDate)
    {
        try
        {
        	//input date format
        	 SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MMM-dd");
        	//output date format
        	 SimpleDateFormat dFormatFinal = new SimpleDateFormat("dd-MM-yyyy");
        	 Date date1 = dFormat.parse(strDate);
     		String ddate = dFormatFinal.format(date1);
            return ddate;
        }
        catch (ParseException e)
        {
            LOG.info(e.getCause(), e);
            return null;
        }
    }
    
    public static String convertDateFormatyyyyMMMddToyyyyMMdd(String strDate)
    {
        try
        {
        	//input date format
        	 SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MMM-dd");
        	//output date format
        	 SimpleDateFormat dFormatFinal = new SimpleDateFormat("yyyy-MM-dd");
        	 Date date1 = dFormat.parse(strDate);
     		String ddate = dFormatFinal.format(date1);
            return ddate;
        }
        catch (ParseException e)
        {
            LOG.info(e.getCause(), e);
            return null;
        }
    }
    
    
    public static String convertDateFromStringTimeStampWithFormatyyyyMMMddtoyyyyMMdd(String strDate)
    {
        try
        {
        	//input date format
        	 SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MMM-dd");
        	//output date format
        	 SimpleDateFormat dFormatFinal = new SimpleDateFormat("yyyy-MM-dd");
        	 Date date1= dFormat.parse(strDate);
        	 return dFormatFinal.format(date1);
        }
        catch (ParseException e)
        {
            LOG.info(e.getCause(), e);
            return null;
        }
    }
    
    
    public static java.sql.Date getDateFromStringTimeStampWithFormatyyyyMMMdd(String timestamp,String fmt)
    {
        try
        {
            if (timestamp != null && timestamp.length() < 13)
            {
                Date a=  new SimpleDateFormat(fmt).parse(timestamp); // return date only
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd");
           	 	String dd = sdf.format(a);
           	 	Date date =sdf.parse(dd);
           	 	return new java.sql.Date(date.getTime());
            }
            else
            {
            	 Date a=  new SimpleDateFormat(fmt).parse(timestamp);
            	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd");
            	 String dd = sdf.format(a);
            	 Date date =sdf.parse(dd);
            	 return new java.sql.Date(date.getTime());
            }
            
            
            
        }
        catch (ParseException e)
        {
            LOG.info(e.getCause(), e);
            return null;
        }
    }
    
    
    public static java.sql.Date getDateFromStringTimeStampWithFormat(String timestamp,String fmt)
    {
        try
        {
            if (timestamp != null && timestamp.length() < 13)
            {
                Date a=  new SimpleDateFormat(fmt).parse(timestamp); // return date only
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
           	 	String dd = sdf.format(a);
           	 	Date date =sdf.parse(dd);
           	 	return new java.sql.Date(date.getTime());
            }
            else
            {
            	 Date a=  new SimpleDateFormat(fmt).parse(timestamp);
            	 SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            	 String dd = sdf.format(a);
            	 Date date =sdf.parse(dd);
            	 return new java.sql.Date(date.getTime());
            }
            
            
            
        }
        catch (ParseException e)
        {
            LOG.info(e.getCause(), e);
            return null;
        }
    }
    
    
    public static Timestamp getTimeStampDateFromString(String date)
    {
        try
        {
            if (date != null)
            {
                DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
                java.util.Date dat = formatter.parse(date);
                Calendar c = formatter.getCalendar();
                c.setTime(dat);
                long l = c.getTimeInMillis();
                return new Timestamp(new java.sql.Date(l).getTime());
            }
            return null;
        }
        catch (ParseException e)
        {
            LOG.info(e.getCause(), e);
            return null;
        }
    }
    
    
    public static Timestamp getTimeStampDateFromStringDate(String date)
    {
        try
        {
            if (date != null)
            {
            	 DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
            	    // you can change format of date
            	   Date date1 = formatter1.parse(date);
            	   Timestamp timeStampDate = new Timestamp(date1.getTime());
            	   return timeStampDate;
            }
            return null;
        }
        catch (ParseException e)
        {
            LOG.info(e.getCause(), e);
            return null;
        }
    }
    
   

    public static Timestamp getTimeStampFromString(String date, String format)
    {
        try
        {
            if (date != null)
            {
                DateFormat formatter = new SimpleDateFormat(format);
                java.util.Date dat = formatter.parse(date);
                Calendar c = formatter.getCalendar();
                c.setTime(dat);
                long l = c.getTimeInMillis();
                return new Timestamp(new java.sql.Date(l).getTime());
            }
            return null;
        }
        catch (ParseException e)
        {
            LOG.info(e.getCause(), e);
            return null;
        }
    }

    public static String convertTimeIn24hourFormat(String date)
    {
        try
        {
            if (date != null)
            {
                SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm");
                SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
                Date formatedDate = parseFormat.parse(date);
                System.out.println(parseFormat.format(formatedDate) + " = " + displayFormat.format(formatedDate));
                return displayFormat.format(formatedDate);
            }
            return null;
        }
        catch (ParseException e)
        {
            return null;
        }
    }

    public static String convertDateFormat(String dateStr, SimpleDateFormat sourceFormat, SimpleDateFormat destFormat)
    {
        try
        {
            Date date = sourceFormat.parse(dateStr);
            dateStr = destFormat.format(date);
            return dateStr;
        }
        catch (ParseException e)
        {
            return null;
        }
    }

    public static Timestamp addDays2CurrentDate(long noOfDays)
    {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, (int) noOfDays);
        Date newDate = cal.getTime();
        return new Timestamp(newDate.getTime());
    }

    
    public static int getDaysDifferenceBetwweenTwoDateTimeStamps(String startDate,String endDate)
    {
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date d1 = null;
    	Date d2 = null;
    	try 
    	{
    		d1 = format.parse(startDate);
    		d2 = format.parse(endDate);
    		DateTime dt1 = new DateTime(d1);
    		DateTime dt2 = new DateTime(d2);
    		int days = Days.daysBetween(dt1, dt2).getDays();
    		return days;
		 }
    	catch (Exception e) 
    	{
			e.printStackTrace();
			LOG.info(""+e.getStackTrace(),e);
			return 0;
		 }
    }
    
    public static long getHouresDifferenceBetwweenTwoDateTimeStamps(String startDate,String endDate)
    {
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date d1 = null;
    	Date d2 = null;
    	try 
    	{
    		d1 = format.parse(startDate);
    		d2 = format.parse(endDate);
    		DateTime dt1 = new DateTime(d1);
    		DateTime dt2 = new DateTime(d2);
    		long hours = Hours.hoursBetween(dt1, dt2).getHours() % 24;
    		return hours;
		 }
    	catch (Exception e) 
    	{
			e.printStackTrace();
			LOG.info(""+e.getStackTrace(),e);
			return 0l;
		 }
    }
    
    public static long getMinutesDifferenceBetwweenTwoDateTimeStamps(String startDate,String endDate)
    {
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date d1 = null;
    	Date d2 = null;
    	try 
    	{
    		d1 = format.parse(startDate);
    		d2 = format.parse(endDate);
    		DateTime dt1 = new DateTime(d1);
    		DateTime dt2 = new DateTime(d2);
    		long minutes = Minutes.minutesBetween(dt1, dt2).getMinutes() % 60;
    		return minutes;
		 }
    	catch (Exception e) 
    	{
			e.printStackTrace();
			LOG.info(""+e.getStackTrace(),e);
			return 0l;
		 }
    }
    
    public static long getSecondsDifferenceBetwweenTwoDateTimeStamps(String startDate,String endDate)
    {
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date d1 = null;
    	Date d2 = null;
    	try 
    	{
    		d1 = format.parse(startDate);
    		d2 = format.parse(endDate);
    		DateTime dt1 = new DateTime(d1);
    		DateTime dt2 = new DateTime(d2);
    		long secs = Seconds.secondsBetween(dt1, dt2).getSeconds() % 60;
    		return secs;
		 }
    	catch (Exception e) 
    	{
			e.printStackTrace();
			LOG.info(""+e.getStackTrace(),e);
			return 0l;
		 }
    }
    
    
    
    public static long getDaysDifference(String date)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStop = simpleDateFormat.format(new Date());

        // HH converts hour in 24 hours format (0-23), day calculation
        Date d1 = null;
        Date d2 = null;

        try
        {
            d1 = simpleDateFormat.parse(date);
            d2 = simpleDateFormat.parse(dateStop);

            // in milliseconds
            long diff = d2.getTime() - d1.getTime();

            // long diffSeconds = diff / 1000 % 60;
            // long diffMinutes = diff / (60 * 1000) % 60;
            // long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);

            return diffDays;
        }
        catch (ParseException e)
        {
            return 0L;
        }
    }

    public static java.sql.Date addDays2GivenDate(String date, int days) throws ParseException
    {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date dat = formatter.parse(date);
        Calendar cal = Calendar.getInstance();
        cal.setTime(dat);
        cal.add(Calendar.DAY_OF_MONTH, (int) days);
        Date newDate = cal.getTime();
        return new java.sql.Date(newDate.getTime());
    }

    public static java.sql.Date addDaysToGivenDate(Date givenDate, int days)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(givenDate);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        Date newDate = calendar.getTime();
        return new java.sql.Date(newDate.getTime());
    }

    public static java.sql.Date getCurrentSqlDate()
    {
        Date d = new Date();
        return new java.sql.Date(d.getTime());
    }

    public static boolean validateDateFormat(String date)
    {
        if (date.matches("([0-9]{2})-([0-9]{2})-([0-9]{4})")) return true;
        else return false;
    }

    public static String format(Timestamp timestamp, String format)
    {
        try
        {
            if (format == null)
            {
                format = "yyyyMMdd-HHmm";
            }
            SimpleDateFormat sdf = YYYY_MM_DD_TIME;
            Date utilDate = sdf.parse(timestamp + "");
            return DateUtils.formatDate(utilDate, format);
        }
        catch (Exception e)
        {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }

    public static String convertDateFormat(java.sql.Date timestamp,String format)
    {
        try
        {
        	if (timestamp != null)
        	{
        		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(format);
    	        return  DATE_FORMAT.format(timestamp);
        	}
        	
        }
        catch (Exception e)
        {
        }
        return null;
    }
    public static String convertTimestampFormat(Timestamp timestamp,String format)
    {
        try
        {
        	java.sql.Date date = new java.sql.Date(timestamp.getTime());
        	SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(format);
	        return  DATE_FORMAT.format(date);
        }
        catch (Exception e)
        {
            return null;
        }
    }
    
    public static String formatSqlDate(java.sql.Date date, String format)
    {
        SimpleDateFormat simpDate = new SimpleDateFormat(format);
        return simpDate.format(date);
    }

    public static String formatTo12Hour(Timestamp timestamp)
    {
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat("h:mma");
            return sdf.format(timestamp);
        }
        catch (Exception e)
        {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }

    public static String convertStringToDate(Date indate, String format)
    {
        String dateString = null;
        SimpleDateFormat sdfr = new SimpleDateFormat(format);
        /*
         * you can also use DateFormat reference instead of SimpleDateFormat like this: DateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
         */
        try
        {
            dateString = sdfr.format(indate);
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
        return dateString;
    }

    /**
     * @author rabindranath.s
     * @param date
     * @return {int} Day Of Month
     */
    public static int getDayOfMonth(Date date)
    {
        try
        {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
            return calendar.get(Calendar.DAY_OF_MONTH);
        }
        catch (Exception e)
        {
            LOG.info(e.getCause(), e);
            return 0;
        }
    }

    /**
     * @author rabindranath.s
     * @param value
     * @return @return {String} Day Of Month Suffix
     */
    public static String getOrdinalSuffix(int value)
    {
        int hunRem = value % 100;
        int tenRem = value % 10;

        if (hunRem - tenRem == 10)
        {
            return "th";
        }
        switch (tenRem)
        {
            case 1:
                return "st";
            case 2:
                return "nd";
            case 3:
                return "rd";
            default:
                return "th";
        }
    }
    
    public static String getdatefromTimeStamp(Timestamp timestamp) {
    	if(timestamp!=null)
        return YYYY_MM_DD.format(timestamp.getTime());
    	else
    		return null;
    }
    public static java.sql.Timestamp addHoursToTimeStamp(int hrs) throws ParseException
    {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, (int) hrs);
        Date newDate = cal.getTime();
        return new java.sql.Timestamp(newDate.getTime());
    }
    
    public static long getDifferenceDaysFromTimeStamp(Timestamp t1, Timestamp t2) {
	    long diff = t2.getTime() - t1.getTime();
	    return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
    
    public static long getDifferenceDays(Date d1, Date d2) {
	    long diff = d2.getTime() - d1.getTime();
	    return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
	
    public static String formatTime(String timeStr)
    {
        String[] timeArr = timeStr.split(":");
        String time = "";
        int hours = Integer.parseInt(timeArr[0]);
        if (hours < 12 && hours != 0)
        {
            time = hours + ":" + timeArr[1] + " AM";
        }
        else if (hours == 12)
        {
            time = hours + ":" + timeArr[1] + " PM";
        }
        else if (hours == 0)
        {
            time = 12 + ":" + timeArr[1] + " AM";
        }
        else
        {
            hours = hours - 12;
            time = hours + ":" + timeArr[1] + " PM";
        }
        return time;
    }
    
    public static long getMinutesFromHHMMtime(String hourFormat)
    {
	    long minutes = 0;  
	    String[] split = hourFormat.split(":");  
	      
	    try {  
	          
	        minutes += Double.parseDouble(split[0])*60;  
	        minutes += Double.parseDouble(split[1]);  
	        //minutes += Double.parseDouble(split[2])/60;  
	    } catch (Exception e) 
	    {  
	        LOG.info(""+e.getStackTrace(),e);
	    }  
	    return minutes;
    }
    /**
     * @param format
     * @return
     */
    
    public static String getCurrentDateAsString(String format)
    {
        Date data = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(data);
    }
    
    public static Date getCurrentDate(String format)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        try
        {
            return simpleDateFormat.parse(getCurrentDateAsString(format));
        }
        catch (ParseException e)
        {
            LOG.error(e.getStackTrace());
        }
        return null;
    }
    public static String getDateStringFromTimeStampLongValue(long millisec)
    {
    	SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		return ft.format(millisec);
    }
    public static Date getDateFromTimeStampLongValue(long millisec)
    {
    	try
    	{
	    	SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
			String date2String= ft.format(millisec);
			Date date2 = ft.parse(date2String);
			return date2;
    	}
    	 catch (Exception e) 
	    {  
	        LOG.info(""+e.getStackTrace(),e);
	        return null;
	    }  
    }
    
    public static String addDaysToGivenDate(String dd)
    {
    	String newDate ="";
    	try{
    		String oldDate = dd;  
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    		Calendar c = Calendar.getInstance();
    		c.setTime(sdf.parse(oldDate));
    		
    		//Incrementing the date by 1 day
    		c.add(Calendar.DAY_OF_MONTH, 1);  
    		newDate = sdf.format(c.getTime());  
    		
    		}catch(Exception e){
    			   e.printStackTrace();
    			 }
    	return newDate;
    }
    
    public static String addDaysToCurrentDate(int days)
    {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	//Getting current date
    	Calendar cal = Calendar.getInstance();
    	//Displaying current date in the desired format
    	//Number of Days to add
            cal.add(Calendar.DAY_OF_MONTH, days);  
    	//Date after adding the days to the current date
    	String newDate = sdf.format(cal.getTime());  
    	return newDate;
    }
    
    
    public static boolean compareToDayDateWithTimestamps(Long timestmp)
    {
    	boolean flag=false;
    	try
		{
			Date date = new Date();
			Timestamp timestamp1 = new Timestamp(timestmp);
			
			//SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
			String date1String = ft.format(date);
			String date2String = ft.format(timestamp1);
			
			Date date1 = ft.parse(date1String);
			Date date2 = ft.parse(date2String);
			
			if(date1.equals(date2))
				flag=true;
			
			//System.out.println(date1.compareTo(date2) == 0); //true
			//System.out.println(date2.compareTo(date1) == 0); //true
			//System.out.println(date2.equals(date1)); // true
		}
		catch(Exception e)
		{
			LOG.info(""+e.getStackTrace(),e);
		}
    	return flag;
    }
    
    public static String convertDateStringyyyyMMdd_to_ddMMyyyy(String start_dt)
    {
	   try
	   {
			DateFormat parser = new SimpleDateFormat("yyyy-MM-dd"); 
			Date dd = (Date) parser.parse(start_dt);
			DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy"); 
			return formatter.format(dd);
	   }
		catch(Exception e)
		{
			LOG.info(""+e.getStackTrace(),e);
			return "";
		}
    }
    
    public static String convertDateStringddMMyyyy_to_yyyyMMdd(String start_dt)
    {
	   try
	   {
			DateFormat parser = new SimpleDateFormat("dd-MM-yyyy"); 
			Date dd = (Date) parser.parse(start_dt);
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
			return formatter.format(dd);
	   }
		catch(Exception e)
		{
			LOG.info(""+e.getStackTrace(),e);
			return "";
		}
    }
    
    public static String getShortFormOfHrsMintsFromMinutes(Long minutess) 
   	{
	    int hours=0;
	    int mins=0;
	    hours=(int)(minutess/60);
	    mins =(int)(minutess%60);
	    String resp ="";
	    /*if(hours>0)
	    {
	    	if(mins>0)*/
	    		resp=String.format("%d h: %d m",hours,mins);
	    	/*else
	    		resp=String.format("%d H ",hours);
	    }
	    else if(mins>0)
	    {
	    	resp=String.format("%d M ",mins);
	    }*/
	    return resp;
   	}
    
    public static String getHrsMintsFromMinutes(Long minutess) 
   	{
	    int hours=0;
	    int mins=0;
	    hours=(int)(minutess/60);
	    mins =(int)(minutess%60);
	    String resp ="";
	    if(hours>0)
	    {
	    	if(mins>0)
	    		resp=String.format("%d Hour : %d Minutes",hours,mins);
	    	else
	    		resp=String.format("%d Hour ",hours);
	    }
	    else if(mins>0)
	    {
	    	resp=String.format("%d Minutes ",mins);
	    }
	    return resp;
   	}
    
    
    public static int getHrsFromMilliseconds(Long timeTakenToAns) 
   	{
   		try 
   		{
   			long milliseconds = timeTakenToAns;
   			int seconds = (int) milliseconds / 1000;
   			int hours = seconds / 3600;
   			seconds = (seconds % 3600) % 60;
   			return hours;
   		}
   		catch(Exception e)
   		{
   			LOG.info(""+e.getStackTrace(),e);
   			return 0;
   		}
   	}
    
    public static long getMillisecondsFromMinutes(Long minutes) 
	{
	    long milliSeconds = minutes * 60 * 1000;
	    return milliSeconds;
	}
    
    public static long getMinutesFromMilliseconds(Long timeTakenToAns) 
	{
		try 
		{
			long milliseconds = timeTakenToAns;
			long seconds =  milliseconds / 1000;
			long minutes = (seconds % 3600) / 60;
			long hours = seconds / 3600;
			seconds = (seconds % 3600) % 60;
			if(hours>0)
				minutes=minutes+(hours*60);
			return minutes;
		}
		catch(Exception e)
		{
			LOG.info(""+e.getStackTrace(),e);
			return 0;
		}
	}
    
    public static String getTimeFormatHMSFromMilliseconds(Long timeTakenToAns) 
	{
		try 
		{
			long milliseconds = timeTakenToAns;
			int seconds=0,hours=0,minutes=0;
			seconds = (int) milliseconds / 1000;
			hours = seconds / 3600;
			minutes = (seconds % 3600) / 60;
			seconds = (seconds % 3600) % 60;
			String time ="";
			//time =String.format("%02d: %02d: %02d",  hours, minutes,seconds);
			time =String.format("%d h: %d m",  hours, minutes);
			return time;
		}
		catch(Exception e)
		{
			LOG.info(""+e.getStackTrace(),e);
			return "";
		}
	}
    
	public static String getTimeFromMilliseconds(Long timeTakenToAns) 
	{
		try 
		{
			long milliseconds = timeTakenToAns!=null?timeTakenToAns:0;
			int seconds = (int) milliseconds / 1000;
			int hours = seconds / 3600;
			int minutes = (seconds % 3600) / 60;
			seconds = (seconds % 3600) % 60;
			String time ="";
			if(hours>0)
			{
				if(minutes>0)
				{
					if(seconds>0)
						time =String.format("%02d h: %02d m: %02d s",  hours, minutes,seconds);
					else
						time =String.format("%02d h: %02d m",  hours, minutes);
				}
				else
				{
					if(seconds>0)
						time =String.format("%02d h: %02d s",  hours, seconds);
					else
						time =String.format("%02d h",  hours);
				}
			}
			else
			{
				if(minutes>0)
				{
					if(seconds>0)
						time =String.format("%02d m: %02d s",  minutes,seconds);
					else
						time =String.format("%02d m", minutes);
				}
				else
				{
					if(seconds>0)
						time =String.format("%02d s", seconds);
					else
						time =String.format("%02d ms",  milliseconds);
				}
			}
			return time;
		}
		catch(Exception e)
		{
			LOG.info(""+e.getStackTrace(),e);
			return "";
		}
	}
    
	
	public static String getTimeFromMilliseconds_v2(Long timeTakenToAns) 
	{
		try 
		{
			long milliseconds = timeTakenToAns;
			int seconds = (int) milliseconds / 1000;
			int hours = seconds / 3600;
			int minutes = (seconds % 3600) / 60;
			seconds = (seconds % 3600) % 60;
			String time ="";
			if(hours>0)
			{
				if(minutes>0)
				{
					if(seconds>0)
						time =String.format("%02d : %02d : %02d ",  hours, minutes,seconds);
					else
						time =String.format("%02d : %02d ",  hours, minutes);
				}
				else
				{
					if(seconds>0)
						time =String.format("%02d : %02d ",  hours, seconds);
					else
						time =String.format("%02d : %02d ",  hours, seconds);
				}
			}
			else
			{
				if(minutes>0)
				{
					if(seconds>0)
						time =String.format("%02d : %02d ",  minutes,seconds);
					else
						time =String.format("%02d : %02d ",  minutes,seconds);
				}
				else
				{
					if(seconds>0)
						time =String.format("%02d : %02d ", minutes,seconds);
					else
						time =String.format("%02d : %02d ", 00,00);
				}
			}
			return time;
		}
		catch(Exception e)
		{
			LOG.info(""+e.getStackTrace(),e);
			return "";
		}
	}
	
	
	public static String getStringTimeStampfromTimeStamp(Timestamp timestamp) {
    	if(timestamp!=null)
        return YYYY_MM_DD_HH_MM_SS.format(timestamp.getTime());
    	else
    		return null;
    }
    
}
