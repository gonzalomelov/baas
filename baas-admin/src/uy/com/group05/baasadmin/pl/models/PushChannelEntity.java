package uy.com.group05.baasadmin.pl.models;

public class PushChannelEntity {
	private long pushChannelId;
	
	private long entityId;
	
	private boolean associated;

	public long getPushChannelId() {
		return pushChannelId;
	}

	public void setPushChannelId(long pushChannelId) {
		this.pushChannelId = pushChannelId;
	}

	public long getEntityId() {
		return entityId;
	}

	public void setEntityId(long entityId) {
		this.entityId = entityId;
	}

	public boolean isAssociated() {
		return associated;
	}

	public void setAssociated(boolean associated) {
		this.associated = associated;
	}
	
	
}
