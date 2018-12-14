package banking.ads.application.users.dtos;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
			Set<UserClaimDto> claims = new HashSet<UserClaimDto>();
			try {
	    		ObjectCodec objectCodec = jsonParser.getCodec();
	            JsonNode node = objectCodec.readTree(jsonParser);
	            String name = node.get("name").asText();
	            String password = node.get("password").asText();
	            /*Iterator<JsonNode> nodes = node.get("claims").elements();
	            while(nodes.hasNext()) {
	            	JsonNode claimNode = nodes.next();
	            	String type = claimNode.get("type").asText();
	            	String value = claimNode.get("value").asText();
	            	UserClaimDto userClaim = new UserClaimDto();
	            	userClaim.setType(type);
	            	userClaim.setValue(value);
	            	claims.add(userClaim);
	            }*/
	            
	            userDto = new UserDto(name, password);
	            //userDto.setClaims(claims);
	            
	    	} catch(Exception ex) {
	    		userDto = new UserDto(RequestBodyType.INVALID.toString(), RequestBodyType.INVALID.toString());
	    	}
	        return userDto;
		}	
}



