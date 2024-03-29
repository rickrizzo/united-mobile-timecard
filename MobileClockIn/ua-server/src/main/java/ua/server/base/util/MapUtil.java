package ua.server.base.util;

import java.util.Map;

public class MapUtil {

	public static String getString(Map<?, ?> map, String key) {
		if (map == null) return null;
		Object o = map.get(key);
		if (o == null)
			return null;
		return o.toString();
	}

	public static Long getLong(Map<?, ?> map, String key) {
		if (map == null) return null;
		Object o = map.get(key);
		if (o == null || o.toString().equals(""))
			return null;
		return Long.valueOf(o.toString());
	}

	public static Integer getInteger(Map<?, ?> map, String key) {
		if (map == null) return null;
		Object o = map.get(key);
		if (o == null || o.toString().equals(""))
			return null;
		return Integer.valueOf(o.toString());
	}

}
