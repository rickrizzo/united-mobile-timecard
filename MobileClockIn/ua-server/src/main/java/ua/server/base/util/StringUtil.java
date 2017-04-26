package ua.server.base.util;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;

public class StringUtil {

	/**
	 * Check if string is not empty, is not null and not filled with spaces only
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isStringEmpty(String str) {
		if (str == null)
			return true;
		return str.trim().equals("");
	}

	/**
	 * Removes all non-digit characters from a number string from the right side
	 * until a digit is reached
	 * 
	 * @param number
	 * @return
	 */
	public static String rtrimCharsFromNumber(String number) {

		if (number == null)
			return null;

		number=number.trim();
		String result = "";

//		for (int i = result.length(); i > 0; i--) {
//			char current = result.charAt(i - 1);
//			if ("0123456789".indexOf(current) != -1) {
//				break;
//			} else {
//				result = result.substring(0, i - 1);
//			}
//		}

		for (int i = 0; i < number.length(); i++) {
			char current = number.charAt(i );
			if ("0123456789".indexOf(current) == -1) {
				break;
			} else {
				result = number.substring(0, i+1 );
			}
		}
		return result;
	}

	public static String ltrimDigitsFromChars(String number) {

		if (number == null)
			return null;

		number=number.trim();
		String result = "";

//		for (int i = result.length(); i > 0; i--) {
//			char current = result.charAt(i - 1);
//			if ("0123456789".indexOf(current) != -1) {
//				break;
//			} else {
//				result = result.substring(0, i - 1);
//			}
//		}

		for (int i = 0; i < number.length(); i++) {
			char current = number.charAt(i );
			if ("0123456789".indexOf(current) == -1) {
				result = number.substring(i);
				break;
			}
		}
		return result;
	}

	/**
	 * Converts string with unicode characters to string with UTF-8 encoding
	 * 
	 * @param src
	 *            String source string
	 * @return String if the src is empty, or any error happend while converting
	 *         then return empty string. Else, reurn conveted string
	 */
	static public String toUTF8(String src) {
		String dest = "";

		try {
			if (src.length() > 0) {

				ByteArrayOutputStream outbytes = new ByteArrayOutputStream(src
						.length());
				OutputStreamWriter writer = new OutputStreamWriter(outbytes,
						"UTF-8");

				writer.write(src, 0, src.length());
				writer.flush();
				writer.close();
				dest = new String(outbytes.toByteArray(), "ISO-8859-1");
			}
		} catch (Exception ex) {
		}

		return dest;
	}

	public static String roundedDoubleString(String doubledString) {
		String result = "0.00";
		BigDecimal bd = new BigDecimal(doubledString);
		bd = bd.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		doubledString = bd.toString();

		result = doubledString;
		return result;
	}

	public static String valueOf(Object val) {
		if (val == null)
			return null;
		return String.valueOf(val);
	}

	public static String valueOf(int val) {
		return String.valueOf(val);
	}

	public static String valueOf(double val) {
		return String.valueOf(val);
	}

	public static String valueOf(float val) {
		return String.valueOf(val);
	}

	public static String valueOf(long val) {
		return String.valueOf(val);
	}

//	public static String utf8ToAcadNusx(String utf8) {
//
//		StringBuffer newString = new StringBuffer();
//
//		for (int i = 0; i < utf8.length(); i++) {
//			
//			char ch = utf8.charAt(i);
//			switch (ch) {			
//			case '?': { newString.append('a'); break;	}			
//			case '?': { newString.append('b'); break;	}			
//			case '?': { newString.append('g'); break;	}			
//			case '?': { newString.append('d'); break;	}			
//			case '?': { newString.append('e'); break;	}			
//			case '?': { newString.append('v'); break;	}			
//			case '?': { newString.append('z'); break;	}			
//			case '?': { newString.append('T'); break;	}			
//			case '?': { newString.append('i'); break;	}			
//			case '?': { newString.append('k'); break;	}			
//			case '?': { newString.append('l'); break;	}			
//			case '?': { newString.append('m'); break;	}			
//			case '?': { newString.append('n'); break;	}			
//			case '?': { newString.append('o'); break;	}		
//			case '?': { newString.append('p'); break;	}			
//			case '?': { newString.append('J'); break;	}			
//			case '?': { newString.append('r'); break;	}			
//			case '?': { newString.append('s'); break;	}			
//			case '?': { newString.append('t'); break;	}			
//			case '?': { newString.append('u'); break;	}			
//			case '?': { newString.append('f'); break;	}			
//			case '?': { newString.append('q'); break;	}			
//			case '?': { newString.append('R'); break;	}			
//			case '?': { newString.append('y'); break;	}			
//			case '?': { newString.append('S'); break;	}			
//			case '?': { newString.append('C'); break;	}			
//			case '?': { newString.append('c'); break;	}			
//			case '?': { newString.append('Z'); break;	}			
//			case '?': { newString.append('w'); break;	}			
//			case '?': { newString.append('W'); break;	}			
//			case '?': { newString.append('x'); break;	}			
//			case '?': { newString.append('j'); break;	}			
//			case '?': { newString.append('h'); break;	}	
//			default:
//				{ newString.append(ch); break;	}
//			}
//		}
//
//		return newString.toString();
//	}

	public static String trim(String str) {
		if (str == null)
			return null;
		return str.trim();
	}

	public static String getNotEmptyString(String str) {
		if (isStringEmpty(str))
			return null;
		return str.trim();
	}
}