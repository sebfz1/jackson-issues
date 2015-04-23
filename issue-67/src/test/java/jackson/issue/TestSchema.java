package jackson.issue;

import jackson.issue.MySchema;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestSchema
{
	@Test
	public void testSchema()
	{
		/** jackson schema */
		String json = "{ \"type\" : \"object\" , \"id\" : \"7a2e8538-196b-423e-b714-13515848ec0c\" , \"description\" : \"My Schema\" , \"title\" : \"my-json-schema\" , \"properties\" : { \"myarray\" : { \"type\" : \"array\" , \"required\" : true , \"title\" : \"my property #2\" , \"items\" : { \"type\" : \"string\"} , \"maxItems\" : 5} , \"mystring\" : { \"type\" : \"string\" , \"required\" : true , \"title\" : \"my property #1\" , \"format\" : \"REGEX\" , \"pattern\" : \"\\\\w+\"} , \"myobject\" : { \"type\" : \"object\" , \"required\" : true , \"title\" : \"my property #3\" , \"properties\" : { \"subprop\" : { \"type\" : \"string\" , \"required\" : true , \"title\" : \"sub property #1\" , \"format\" : \"REGEX\" , \"pattern\" : \"\\\\w{3}\"}}}}}";

		/** mongo like schema */
		// String json = "{ \"_id\" : { \"$oid\" : \"55144a23771a631c581a2811\"} , \"type\" : \"object\" , \"id\" : \"7a2e8538-196b-423e-b714-13515848ec0c\" , \"description\" : \"My Schema\" , \"title\" : \"my-json-schema\" , \"properties\" : { \"myarray\" : { \"type\" : \"array\" , \"required\" : true , \"title\" : \"my property #2\" , \"items\" : { \"type\" : \"string\"} , \"maxItems\" : 5} , \"mystring\" : { \"type\" : \"string\" , \"required\" : true , \"title\" : \"my property #1\" , \"format\" : \"REGEX\" , \"pattern\" : \"\\\\w+\"} , \"myobject\" : { \"type\" : \"object\" , \"required\" : true , \"title\" : \"my property #3\" , \"properties\" : { \"subprop\" : { \"type\" : \"string\" , \"required\" : true , \"title\" : \"sub property #1\" , \"format\" : \"REGEX\" , \"pattern\" : \"\\\\w{3}\"}}}}}";

		System.out.println(json);

		ObjectMapper mapper = new ObjectMapper();

		try
		{
			// using value //

			// ObjectSchema schema = mapper.readValue(json, ObjectSchema.class); // works
			MySchema schema = mapper.readValue(json, MySchema.class); // fails

			// using tree //

			// JsonNode node = mapper.readTree(json); // works

			// ObjectSchema schema = mapper.treeToValue(node, ObjectSchema.class); // works
			// MongoSchema schema = mapper.treeToValue(node, MySchema.class); // fails
			// ObjectSchema schema = mapper.convertValue(node, SimpleType.construct(ObjectSchema.class)); // works
			// MongoSchema schema = mapper.convertValue(node, SimpleType.construct(MySchema.class)); // fails
			// MongoSchema schema = mapper.convertValue(node, mapper.getTypeFactory().constructSpecializedType(SimpleType.construct(ObjectSchema.class), MySchema.class)); // fails

			// output //

			System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(schema));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
