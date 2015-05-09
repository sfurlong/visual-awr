//  This class is used to log runtime data.
//  It has been implemented as a Singleton pattern.
//  NOTE:  This class should not be instantiated directly.
//          New instances should be initialized to null and
//          the getInstance() method should be used to get a
//          handle to this class.
package dai.shared.cmnSvcs;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.altaprise.vawr.utils.SessionMetaData;


public class daiFormatUtil
{
    private static DecimalFormat decFormat = new DecimalFormat("#,##0.00");
    private static SimpleDateFormat daiDateFormat = new SimpleDateFormat("MM/dd/yyyy");
    private static SimpleDateFormat daiDateTimeFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");

	static public void main(String args[]) {
//		if (args.length == 1)
//		{
//            Double amt = new Double(args[0]);
//			System.out.println(doubleToCurrency(amt.doubleValue(), true));
//			System.out.println(doubleToCurrency(amt.doubleValue(), false));
            System.out.println(convCurrencyToText(new Double(args[0])));
            System.out.println(adjustDate("03/31/2000", 30));
//		}
	}

	public daiFormatUtil()
	{
	}

	/**
	  * Convert an integer to a String, with left zeroes.
	  * @param i the integer to be converted
	  * @param len the length of the resulting string.
	  * Warning.  It will chop the result on the left if it is too long.
	  * @return String representation of the int e.g. 007
	  */
	public final static String padIntLeft( int i, int len) {
		// Since String is final, we could not add this method there.
		String s = Integer.toString(i);
		if ( s.length() > len )	return s.substring(0,len);
		else if ( s.length() < len )
			// pad on left with zeros
			return "000000000000000000000000000000".substring(0,len  - s.length()) + s;
		else return s;
	}

	public static String doubleToCurrency(double d, boolean useSymbol)
	{
        String ret = "";

        if (useSymbol) {
    		ret = NumberFormat.getCurrencyInstance().format(d);
        } else {
            ret = decFormat.format(d);
        }

        return ret;
	}

	public static String convCurrencyToText(Double amt) {

		long tempa, tempb;
		int tenThous, thous, huns;
		int hunThous;
		int tens, ones, cents;

		String str = "";

		String[] tensTable = {"", "", "TWENTY", "THIRTY", "FORTY", "FIFTY", "SIXTY", "SEVENTY", "EIGHTY", "NINTY"};
		String[] onesTable = {"", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX",
			"SEVEN", "EIGHT", "NINE", "TEN", "ELEVEN", "TWELVE", "THIRTEEN", "FOURTEEN", "FIFTEEN", "SIXTEEN", "SEVENTEEN",
			"EIGHTEEN", "NINETEEN"};

		int intAmt = amt.intValue();

        Float dd = new Float(amt.doubleValue() * 100.0);
		cents = dd.intValue() % 100;

		hunThous = amt.intValue() / 100000;
		intAmt = amt.intValue() % 100000;
		tenThous = intAmt / 10000;
		intAmt = amt.intValue() % 10000;
		thous = intAmt / 1000;
		intAmt = amt.intValue() % 1000;
		huns = intAmt / 100;
		intAmt = amt.intValue() % 100;
		tens = intAmt / 10;
		intAmt = amt.intValue() % 10;
		ones = intAmt;

		if (hunThous > 9)
		{
			System.out.println("amt over million");
			return null;
		}


		if (hunThous > 0)
		{
			str = onesTable[hunThous] + " hundred ";
			if (tenThous > 1)
			{
				str = str + tensTable[tenThous];
			}
			if (thous > 0)
			{
				str = str + "-";
			}
		} else if (tenThous == 1)
		{
			thous = thous + 10;
		}

		if (thous > 0)
		{
			str = str + onesTable[thous] + " thousand ";
		} else if (hunThous > 0 || tenThous > 0)
		{
			str = str + " thousand ";
		}

		if (huns > 0)
		{
			str = str + onesTable[huns] + " hundred ";
			if (tens > 1)
			{
				str = str + tensTable[tens];
			}
    		if (tens == 1)
	    	{
		    	ones = ones + 10;
            }

			if (ones > 0)
			{
				str = str + "";
				//str = str + "-"; commented out because format looked bad
			}
		} else {
    		if (tens == 1)
	    	{
		    	ones = ones + 10;
            } else {
                str = str + tensTable[tens];
            }
        }

		if (ones > 0)
		{
			str = str + onesTable[ones];
		}

		if (cents == 0)
		{
			str = str + " dollars and NO cents";
		} else
		{
			str = str + " dollars and " + cents + " cents";
		}

		return str;
	}

    //Convinience function for getting current date
    public static String DateTimeStamp() {

		Calendar now = Calendar.getInstance();

		String ret = Integer.toString(now.get(now.MONTH)+1) +
					 now.get(now.DAY_OF_MONTH) +
					 now.get(now.YEAR) +
                     now.get(now.HOUR_OF_DAY) +
                     now.get(now.MINUTE) +
                     now.get(now.SECOND) +
                     now.MILLISECOND;
        return ret;
    }

    //Convinience function for getting current date
    public static String getCurrentDate() {

		Calendar now = Calendar.getInstance();

        return daiDateFormat.format(now.getTime());
    }

    /**
     * Converts the provided date to the same date, but formatted for the
     * database sql opperations.
     * @param s_date A date in the system display format.
     * @return the date formatted for the specific database we are using.
     * @throws RuntimeException
     */
    public static String cnvtToDBDateFormat(String s_date) throws RuntimeException {

    	try {
    		daiDateFormat.parse(s_date);
    	} catch (Exception e) {
    		throw new RuntimeException(e);
    	}
        SimpleDateFormat dbDateFormat = new SimpleDateFormat(SessionMetaData.getInstance().getDBDateFormat());

        Calendar c_date = daiDateFormat.getCalendar();

        return dbDateFormat.format(c_date.getTime());
    }

    //Convinience function for getting current date plus/minus the
    //number of days provided as a parameter.
    public static String getCurrentDatePlusNumDays(int numDays) {

		Calendar now = Calendar.getInstance();
        now.add(Calendar.DATE, numDays);

        return daiDateFormat.format(now.getTime());
    }

    //Convinience function for getting current date plus/minus the
    //number of days provided as a parameter.
    public static String adjustDate(String s_date, int numDays) {

       SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");

        try {
            sdf.parse(s_date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Calendar c_date = sdf.getCalendar();

        c_date.add(Calendar.DATE, numDays);

        return daiDateFormat.format(c_date.getTime());
    }
}


