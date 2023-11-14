package geiffel.da4.issuetracker.user;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import geiffel.da4.issuetracker.issue.Issue;

import java.io.IOException;

public class UserEmbeddedJSONSerializer  extends JsonSerializer<User> {
    @Override
    public void serialize(User user, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("title", user.getNom());
        gen.writeStringField("url", "/issues/"+user.getId());
        gen.writeEndObject();
    }
}
