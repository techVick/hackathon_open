package ge.dt.service.predixapp.json;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

@Component
public class JsonDateDeSerializer extends JsonDeserializer<Date> {

	private final static String JSON_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

	@Override
	public Date deserialize(JsonParser jp, DeserializationContext deContext)
			throws IOException, JsonProcessingException {
		SimpleDateFormat format = new SimpleDateFormat(
				JsonDateDeSerializer.JSON_DATE_FORMAT);
		String date = jp.getText().trim();
		try {
			return format.parse(date);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
}