package banking.ads.api.reponses;

import banking.ads.api.enumeration.RequestBodyType;

public class RequestBaseDto {
	protected RequestBodyType requestBodyType;

	public RequestBodyType getRequestBodyType() {
		return requestBodyType;
	}

	public void setRequestBodyType(RequestBodyType requestBodyType) {
		this.requestBodyType = requestBodyType;
	}
}
