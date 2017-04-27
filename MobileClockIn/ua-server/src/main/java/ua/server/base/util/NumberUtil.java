package ua.server.base.util;


public class NumberUtil {

	public static final int INT_TYPE = 0;

	public static final int LONG_TYPE = 1;

	public static final int FLOAT_TYPE = 2;

	public static final int DOUBLE_TYPE = 3;

	public static double min(double val1, double val2) {
		if (val1 <= val2)
			return val1;
		else
			return val2;
	}

	public static double max(double val1, double val2) {
		if (val1 >= val2)
			return val1;
		else
			return val2;
	}

	public static boolean inRange(int value, int begin, int end) {
		return value >= begin && value <= end;
	}

	public static boolean inRange(long value, long begin, long end) {
		return value >= begin && value <= end;
	}

	public static boolean isLongEmpty(Long val) {
		if (val == null)
			return true;
		return val.longValue() == 0;
	}

	public static boolean isIntegerEmpty(Integer val) {
		if (val == null)
			return true;
		return val.intValue() == 0;
	}

	public static boolean isDoubleEmpty(Double val) {
		if (val == null)
			return true;
		return val.doubleValue() == 0;
	}

	public static double truncate(double value, int decimalPlaces) {
		// this code truncates to decimalPlaces
		return value > 0 ? Math.floor(value * 100) / 100.0 : Math
				.ceil(value * 100) / 100.0;
	}

	/**
	 * Rounds 'd' to number of decimal 'places'
	 */
	public static double round(double d, int places) {
		return Math.round(d * Math.pow(10, (double) places))
				/ Math.pow(10, (double) places);
	}

	public static double round(float d, int places) {
		return Math.round(d * Math.pow(10, (double) places))
				/ Math.pow(10, (double) places);
	}

	public static double toDouble(String strNumber) {
		return Double.parseDouble(strNumber);
	}

	public static long toLong(String strNumber) {
		return Long.parseLong(strNumber);
	}

	public static float toFloat(String strNumber) {
		return Float.parseFloat(strNumber);
	}

	public static int toInt(String strNumber) {
		return Integer.parseInt(strNumber);
	}

	public static boolean isIntValid(String strNumber) {
		return isValid(strNumber, INT_TYPE);
	}

	public static boolean isFloatValid(String strNumber) {
		return isValid(strNumber, FLOAT_TYPE);
	}

	public static boolean isDoubleValid(String strNumber) {
		return isValid(strNumber, DOUBLE_TYPE);
	}

	public static boolean isLongValid(String strNumber) {
		return isValid(strNumber, LONG_TYPE);
	}

	public static boolean isValid(String strNumber, int type) {
		try {
			if(StringUtil.isStringEmpty(strNumber))
				return false;
			switch (type) {
			case INT_TYPE:
				Integer.parseInt(strNumber);
				break;
			case LONG_TYPE:
				Long.parseLong(strNumber);
				break;
			case FLOAT_TYPE:
				Float.parseFloat(strNumber);
				break;
			case DOUBLE_TYPE:
				Double.parseDouble(strNumber);
				break;
			default:
				return false;
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static long longValueOf(String val) {
		if (val == null)
			return 0;
		return Long.parseLong(val);
	}

	public static int intValueOf(String val) {
		if (val == null)
			return 0;
		return Integer.parseInt(val);
	}

	public static float floatValueOf(String val) {
		if (val == null)
			return 0;
		return Float.parseFloat(val);
	}

	public static double doubleValueOf(String val) {
		if (val == null)
			return 0;
		return Double.parseDouble(val);
	}

	public static Long longObjValueOf(String val) {
		if (StringUtil.isStringEmpty(val))
			return null;
		return Long.valueOf(val);
	}

	public static Integer intObjValueOf(String val) {
		if (StringUtil.isStringEmpty(val))
			return null;
		return Integer.valueOf(val);
	}

	public static Float floatObjValueOf(String val) {
		if (StringUtil.isStringEmpty(val))
			return null;
		return Float.valueOf(val);
	}

	public static Double doubleObjValueOf(String val) {
		if (StringUtil.isStringEmpty(val))
			return null;
		return Double.valueOf(val);
	}

	public static long getIfLongValid(String strNumber) {
		if (!isLongValid(strNumber))
			return 0;
		return longValueOf(strNumber);
	}

	public static int getIfIntValid(String strNumber) {
		if (!isIntValid(strNumber))
			return 0;
		return intValueOf(strNumber);
	}

	public static double getIfDoubleValid(String strNumber) {
		if (!isDoubleValid(strNumber))
			return 0;
		return doubleValueOf(strNumber);
	}

	public static double addDoubles(double d1, double d2, int scale){
		d1=NumberUtil.round(d1, scale);
		d2=NumberUtil.round(d2, scale);
		return NumberUtil.round(d1+d2,scale);
	}

	public static double substractDoubles(double d1, double d2, int scale){
		d1=NumberUtil.round(d1, scale);
		d2=NumberUtil.round(d2, scale);
		return NumberUtil.round(d1-d2,scale);
	}
}