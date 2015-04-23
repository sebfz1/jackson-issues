package jackson.issue;

import java.io.IOException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.types.ObjectSchema;

public class TestSchema
{
	@Test
	public void testSchema()
	{
		try
		{
			parse("TestSchema.json");
		}
		catch (Exception e)
		{
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testSchema$()
	{
		try
		{
			parse("TestSchema$.json");
		}
		catch (Exception e)
		{
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testSchemaRequired()
	{
		try
		{
			parse("TestSchemaRequired.json");
		}
		catch (Exception e)
		{
			Assert.fail(e.getMessage());
		}
	}

	static void parse(String filename) throws IOException
	{
		URL url = TestSchema.class.getResource(filename);

		ObjectMapper mapper = new ObjectMapper();
		ObjectSchema schema = mapper.readValue(url.openStream(), ObjectSchema.class);

		// output //
		System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(schema));
	}
}
