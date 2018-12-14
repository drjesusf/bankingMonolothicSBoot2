package seedWork;

import java.util.Date;

public abstract class Entity {
	private long _id;
	private Date _createdAt;
	private Date _updateAt;
	
	
	
	public long get_id() {
		return _id;
	}
	protected void set_id(long _id) {
		this._id = _id;
	}
	public Date get_createdAt() {
		return _createdAt;
	}
	public void set_createdAt(Date _createdAt) {
		this._createdAt = _createdAt;
	}
	public Date get_updateAt() {
		return _updateAt;
	}
	public void set_updateAt(Date _updateAt) {
		this._updateAt = _updateAt;
	}
	@Override public boolean equals(Object obj) {
		if(!(obj instanceof Entity)) return false;
		return true;
		
	}
	public void ThrowExceptionIfAny(Notification notification) {
        if (notification.hasErrors())
        {
            throw new IllegalArgumentException(notification.errorMessage());
        }
    }
}
