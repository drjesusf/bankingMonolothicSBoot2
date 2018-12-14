package banking.ads.application.users.dtos;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import banking.ads.api.enumeration.RequestBodyType;
import banking.ads.application.users.dtos.UserDto;


public class UserDtoDeserializer extends JsonDeserializer<UserDto> {
		@Override
		public UserDto deserialize(JsonParser jsonParser, DeserializationContext ctxt)
				throws IOException, JsonProcessingException {
			UserDto userDto = null;
			try {
	    		ObjectCodec objectCodec = jsonParser.getCodec();
	            JsonNode node = objectCodec.readTree(jsonParser);
	            String name = node.get("name").asText();
	            String password = node.get("password").asText();
	            List<UserClaimDto> claims = (List<UserClaimDto>) node.get("claims").elements();
	            userDto = new UserDto(name, password);
	    	} catch(Exception ex) {
	    		userDto = new UserDto(RequestBodyType.INVALID.toString(), RequestBodyType.INVALID.toString());
	    	}
	        return userDto;
		}	
}



