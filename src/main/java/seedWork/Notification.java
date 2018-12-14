package seedWork;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Notification {
	private List<Error> _errors = new ArrayList<Error>(); 

    public void addError(String message)
    {
        _errors.add(new Error(message));
    }

    public String errorMessage()
    {
    	return String.join(",", _errors.stream().map(x->x.getMessage()).collect(Collectors.toList()));
    }

    public boolean hasErrors()
    {
        return !_errors.isEmpty();
    }
}
