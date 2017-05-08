package ua.server.base.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CollectionUtil {
	
	
	public static List<ITreeRecord> recordTreeToList(List<ITreeRecord> records) {
		List<ITreeRecord> l = new ArrayList<ITreeRecord>();		
		Map<Long,ITreeRecord> rMap = new HashMap<Long,ITreeRecord>();
		
		for (ITreeRecord r : records) {
			rMap.put(r.getRecordId(), r);
		}

		for (ITreeRecord r : records) {
			if (r.getParentRecordId() != null) {
				ITreeRecord parent = rMap.get(r.getParentRecordId());
				parent.addChild(r);
			}			
		}

		for (ITreeRecord r:rMap.values()) {
			if(r.getParentRecordId()==null){
				l.add(r);
			}
		}
		return l;		
	}
	
	
	
	
	public static boolean isStringInArray(String checkElement, String[] elements) {

		boolean found = false;

		for (int i = 0; i < elements.length; i++) {
			String e = elements[i];
			if (e.equals(checkElement)) {
				found = true;
				break;
			}
		}

		return found;
	}

	/**
	 * Splits list into parts
	 * 
	 * @param elements
	 * @param parts
	 * @return Returns list of parts
	 */
	public static <T> List<List<T>> splitList(List<T> elements, int parts) {

		int size = elements.size();
		List<List<T>> partsList;

		// if number of elements is less or equal to parts then
		// return original list as an element of the list;
		if (size <= parts) {
			partsList = new ArrayList<List<T>>(1);
			partsList.add(elements);
			return partsList;
		}

		partsList = new ArrayList<List<T>>(parts);

		int partSize = (int) Math.floor(size / parts);
		int startIndex = 0;
		int endIndex = partSize;

		while (endIndex <= size) {
			List<T> subList = elements.subList(startIndex, endIndex);
			partsList.add(subList);

			startIndex = endIndex;
			if (endIndex + partSize <= size) {
				endIndex = endIndex + partSize;
			} else if (endIndex < size) {
				endIndex = endIndex + (size - endIndex);
			} else {
				break;
			}
		}

		return partsList;
	}

	public static <T> String toString(List<T> elements) {
		StringBuffer listString = new StringBuffer();

		Iterator<T> iter = elements.iterator();
		while (iter.hasNext()) {
			Object obj = iter.next();
			if (listString.length() > 0) {
				listString.append(",").append(obj.toString());
			} else {
				listString.append(obj.toString());
			}
		}

		return listString.toString();
	}

	public static boolean isEmpty(Object[] c) {
		return c == null || c.length == 0;
	}

	public static boolean isEmpty(byte[] c) {
		return c == null || c.length == 0;
	}

	public static boolean isEmpty(Collection<?> c) {
		return c == null || c.size() == 0;
	}

	public static  boolean isEmpty(Map<?,?> m) {
		return m == null || m.size() == 0;
	}

	public static boolean isEmpty(Hashtable<?,?> ht) {
		return ht == null || ht.size() == 0;
	}

	public static List<? extends Object> arrayToList(Object[] array){
		if(array==null)
			return null;
		List<Object> result=new ArrayList<Object>();
		for (int i = 0; i < array.length; i++) {
			result.add(array[i]);
		}
		return result;
	}
	
	public static boolean isLongInCollection(Long valueToSearch, Collection<Long> collectionToSearchIn) {
		for (Long  currentValue: collectionToSearchIn) {
			if(currentValue.equals(valueToSearch))
				return true;
		}
		return false;
	}
	
}