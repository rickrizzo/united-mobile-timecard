package ua.server.base.util;

import java.nio.ByteBuffer;
import java.util.UUID;

//import com.epg.common.cache.StaticCache;

public class GUIDUtil {

	public static byte[] newUUID() {
		UUID uuid = UUID.randomUUID();
		long hi = uuid.getMostSignificantBits();
		long lo = uuid.getLeastSignificantBits();
		return ByteBuffer.allocate(16).putLong(hi).putLong(lo).array();
	}
	
//{{ byteArrayToUuid
	public static UUID ByteArrayToUuidDotNetCompatible(byte[] bytes) {
		return byteArrayToUuid(bytes, UUIDType.DOTNET_TYPE);
	}
	
	public static UUID byteArrayToUuid(byte[] bytes) {
		return byteArrayToUuid(bytes, UUIDType.JAVA_TYPE);
	}
	
	public static UUID byteArrayToUuid(byte[] bytes, int type) {
		if(type==UUIDType.DOTNET_TYPE){
			byte[] newBytes=new byte[]{
				bytes[3],	
				bytes[2],	
				bytes[1],	
				bytes[0],
				
				bytes[5],
				bytes[4],	
				
				bytes[7],
				bytes[6],	
				
				bytes[8],	
				bytes[9],	
				bytes[10],	
				bytes[11],	
				bytes[12],	
				bytes[13],	
				bytes[14],	
				bytes[15],	
			};
			ByteBuffer bb = ByteBuffer.wrap(newBytes);
			long high = bb.getLong();
			long low = bb.getLong();
			return new UUID(high, low);
		}
		else{
			ByteBuffer bb = ByteBuffer.wrap(bytes);
			long high = bb.getLong();
			long low = bb.getLong();
			return new UUID(high, low);
		}
	}
//}}
	
//{{	uuidToByteArray
	public static byte[] uuidToByteArrayDotNetCompatible(UUID uuid) {
		return uuidToByteArray(uuid, UUIDType.DOTNET_TYPE);
	}

	public static byte[] uuidToByteArray(UUID uuid) {
		return uuidToByteArray(uuid, UUIDType.JAVA_TYPE);
	}
	
	public static byte[] uuidToByteArray(UUID uuid, int type) {
		if(type==UUIDType.DOTNET_TYPE){
			long longOne = uuid.getMostSignificantBits();
			long longTwo = uuid.getLeastSignificantBits();
 
			return new byte[] {
				(byte)(longOne <<24>>56),
				(byte)(longOne <<16>>> 56),
				(byte)(longOne <<8>>> 56),
				(byte)(longOne >>> 56),  
				  
				(byte)(longOne <<40>>> 56),
				(byte)(longOne <<32>>> 56),
				  
				(byte)(longOne <<56>>> 56),
				(byte)(longOne <<48>>> 56),
				  
				(byte)(longTwo >>> 56),
				(byte)(longTwo <<8>>> 56),
				  
				(byte)(longTwo <<16>>> 56),
				(byte)(longTwo <<24>>> 56),
				(byte)(longTwo <<32>>> 56),
				(byte)(longTwo <<40>>> 56),
				(byte)(longTwo <<48>>> 56),
				(byte)(longTwo <<56>>> 56)
			};
		}
		ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
		bb.putLong(uuid.getMostSignificantBits());
		bb.putLong(uuid.getLeastSignificantBits());

		return bb.array();
	}

//}}	

//{{ stringToByteArray
	public static byte[] stringToByteArrayDotNetCompatible22(String uuid) {
		return uuidToByteArray(stringToUuid(uuid), UUIDType.DOTNET_TYPE);
	}

	public static byte[] stringToByteArray(String uuid) {
		return uuidToByteArray(stringToUuid(uuid), UUIDType.JAVA_TYPE);
	}

	public static byte[] stringToByteArray(String uuid, int type ) {
		return uuidToByteArray(stringToUuid(uuid), type);
	}

//}}
	
//{{ byteArrayToString
	public static String byteArrayToStringDotNetCompatible(byte[] bytes) {
		return byteArrayToString(bytes, UUIDType.DOTNET_TYPE);
	}

	public static String byteArrayToString(byte[] bytes) {
		return byteArrayToString(bytes, UUIDType.JAVA_TYPE);
	}

	public static String byteArrayToString(byte[] bytes, int type) {
		return uuidToString(byteArrayToUuid(bytes, type));
	}
//}}
	
	public static UUID stringToUuid(String uuid) {
		return UUID.fromString(uuid);
	}

	public static String uuidToString(UUID uuid) {
		return uuid.toString();
	}
	
	public class UUIDType{
		public static final int JAVA_TYPE=1;
		public static final int DOTNET_TYPE=2;
	}
}
