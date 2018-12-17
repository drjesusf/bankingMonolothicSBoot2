package banking.ads.api.swagger;

import io.swagger.annotations.Contact;
import io.swagger.annotations.ExternalDocs;
import io.swagger.annotations.Info;
import io.swagger.annotations.License;
import io.swagger.annotations.SwaggerDefinition;

@SwaggerDefinition(
        info = @Info(
                description = "Listado de endpoints Transferencias bancarias (API)",
                version = "1.0",
                title = "Curso Ambientes de Desarrollo de Software",
                contact = @Contact(
                   name = "Grupo 01 - Jesus Ramirez, Vladimir Espinoza, Alfredo munares", 
                   email = "drjesusf@gmail.com", 
                   url = "#"
                ),
                license = @License(
                   name = "Apache 2.0", 
                   url = "http://www.apache.org/licenses/LICENSE-2.0"
                )
        ),
        consumes = {"application/json", "application/xml"},
        produces = {"application/json", "application/xml"},
        schemes = {SwaggerDefinition.Scheme.HTTP, SwaggerDefinition.Scheme.HTTPS},
        externalDocs = @ExternalDocs(value = "Read This For Sure", url = "http://in28minutes.com")
)
public interface ApiDocumentationConfig {
	
}
