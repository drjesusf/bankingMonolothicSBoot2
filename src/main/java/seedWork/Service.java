package seedWork;

public class Service {
	public void throwExceptionIfAny(Notification notification)
    {
        if (notification.hasErrors())
        {
            throw new IllegalArgumentException(notification.errorMessage());
        }
    }
}
