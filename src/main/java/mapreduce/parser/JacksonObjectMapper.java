package mapreduce.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;


public class JacksonObjectMapper {
		
	private String inputPath = "/home/ubuntu/eclipse-workspace/bigdata-project/src/main/java/mapreduce/parser/raw_tweet100k.json";
//	private String inputPath = "/home/ubuntu/eclipse-workspace/bigdata-project/src/main/java/mapreduce/parser/dummy_input.json";
	private ObjectMapper objectMapper = new ObjectMapper();	
	
	public JacksonObjectMapper() {
		// ignore attributes that are not declared in class Tweet when mapping
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
	
	public Tweet parseTweet(String string) throws JsonParseException, JsonMappingException, IOException {			
		Tweet tweet = objectMapper.readValue(string, Tweet.class);
		return tweet;
	}
	
	public void parseTweetJsonFile(String inputPath, File newFile, File newFile2) throws IOException {
		
		System.out.println("reading file...");
		// read json data to a list of strings
		// every element is a tweet
		List<String> jsonData = Files.readAllLines(Paths.get(inputPath));		
		
		System.out.println("mappping data...");
        ArrayList<String> tweets = new ArrayList<String>();
        ArrayList<String> users = new ArrayList<String>();
		for (String jsonRecord: jsonData) {
			Tweet tweet = parseTweet(jsonRecord);
			// replace new lines in tweet messages to prevent any errors
			// Don't know if it's the most efficient way to do it but it's a quick fix
			tweet.setText(tweet.getText().replace("\n", " "));
			tweets.add(tweet.getText());
			users.add(tweet.getUser().getScreen_name());
		}        
		
		// create new file and write arrays of tweets to it
		writeTweetToJson(tweets, newFile);
		writeTweetToJson(users, newFile2);
	}
		
    private static void writeTweetToJson(ArrayList<?> array, File newFile) {
		
        try {
            PrintWriter writer = new PrintWriter(newFile, "UTF-8");
            for (Object e: array) {
            	writer.println(e);
            }
            writer.close();
            
            System.out.println("new file created at path: "+newFile.getAbsoluteFile());
        }  
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
