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


/**
 * @author Irixa Vales Torres
 *
 */
public class JacksonObjectMapper {

	private ObjectMapper objectMapper = new ObjectMapper();	
	private ArrayList<Tweet> tweets;

	public JacksonObjectMapper() {
		// ignore attributes that are not declared in class Tweet when mapping
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		tweets = new ArrayList<Tweet>();
	}

	/**
	 * @param string : representing the tweet in json format
	 * @return Tweet : object
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public Tweet parseTweet(String string) throws JsonParseException, JsonMappingException, IOException {			
		Tweet tweet = objectMapper.readValue(string, Tweet.class);
		return tweet;
	}

	/**
	 * @param inputPath : of the file to read
	 * @throws IOException
	 */
	public void parseTweetJsonFile(String inputPath) throws IOException {

		System.out.println("reading file...");
		// read json data to a list of strings
		// every element is a tweet
		List<String> jsonData = Files.readAllLines(Paths.get(inputPath));		

		System.out.println("mappping data...");
		for (String jsonRecord: jsonData) {
			Tweet tweet = parseTweet(jsonRecord);
			// replace new lines in tweet messages to prevent any errors
			// Don't know if it's the most efficient way to do it but it's a quick fix
			tweet.setText(tweet.getText().replaceAll("\n", " "));
			// remove links from text
			// also not the most efficient way but works
			tweet.setText(tweet.getText().concat(" "));
			tweet.setText(tweet.getText().replaceAll("http.*?\\s", ""));
			tweets.add(tweet);
		}        
	}

	/**
	 * @param newFile : path of the new file to write to
	 */
	public void writeTweetToJson(File newFile) {

		try {
			PrintWriter writer = new PrintWriter(newFile, "UTF-8");
			System.out.println("writing txt file...");
			for (Tweet t: tweets) {
				writer.println(t.toString());
			}
			writer.close();

			System.out.println("new file created at path: "+newFile.getAbsoluteFile());
		}  
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
