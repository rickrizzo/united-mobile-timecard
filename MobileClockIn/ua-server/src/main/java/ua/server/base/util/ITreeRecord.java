package ua.server.base.util;

public interface ITreeRecord {
	
	public Long getRecordId();
	
	public Long getParentRecordId();
		
	public void addChild(ITreeRecord record);
	
}
