package pgodi.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

	public List<HashMap<String, String>> getJsonDataToMap() throws IOException {
		
		String path = System.getProperty("user.dir") + "\\src\\test\\java\\pgodi\\data\\PurchaseOrder.json";
		System.out.println(path);
		String jsonContent = FileUtils.readFileToString(new File (path),StandardCharsets.UTF_8);
		
		//String to HashMap using Jackson databind
		ObjectMapper mapper = new ObjectMapper();		
		List<HashMap<String,String>>data= mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){;
		});
		return data;
	}
	
	
}
