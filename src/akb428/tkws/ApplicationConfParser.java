package akb428.tkws;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import akb428.tkws.model.HbaseConfModel;

public class ApplicationConfParser {

	public JsonNode rootNode;

	public ApplicationConfParser(String confFilename)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		rootNode = mapper.readValue(new File(confFilename), JsonNode.class);

	}

	public HbaseConfModel getHbaseConfModel() {

		HbaseConfModel hbaseConfModel = new HbaseConfModel();
		JsonNode node = rootNode.get("HBase");
		
		
		hbaseConfModel.setExecute(node.get("execute").getBooleanValue());
		// resource
		JsonNode resouceNode = node.get("resource");

		List<String> resouceArray = new ArrayList<String>();

		for (int i = 0; i < resouceNode.size(); i++) {
			resouceArray.add(resouceNode.path(i).getTextValue());
		}

		hbaseConfModel.setResource(resouceArray);

		return hbaseConfModel;
	}
}