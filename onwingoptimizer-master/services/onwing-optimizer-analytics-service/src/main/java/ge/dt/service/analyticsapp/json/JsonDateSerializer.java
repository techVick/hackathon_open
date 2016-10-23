package ge.dt.service.analyticsapp.json;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@Component
public class JsonDateSerializer extends JsonSerializer<Date> {

	private final static String JSON_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

	@Override
	public void serialize(Date date, JsonGenerator jsonGenerator,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {

		SimpleDateFormat dateFormat = new SimpleDateFormat(
				JsonDateSerializer.JSON_DATE_FORMAT);

		String formattedDate = dateFormat.format(date);

		jsonGenerator.writeString(formattedDate);
	}
}