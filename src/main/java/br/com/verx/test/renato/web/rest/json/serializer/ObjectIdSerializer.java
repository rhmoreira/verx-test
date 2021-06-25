package br.com.verx.test.renato.web.rest.json.serializer;

import java.io.IOException;

import org.bson.types.ObjectId;
import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@JsonComponent
public class ObjectIdSerializer extends JsonSerializer<ObjectId> {

	@Override
	public void serialize(ObjectId value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		gen.writeString(value.toHexString());
	}
}
