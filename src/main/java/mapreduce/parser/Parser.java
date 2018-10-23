package mapreduce.parser;

import java.io.File;
import java.io.IOException;

/**
 * @author Irixa Vales Torres
 *
 */
public class Parser {

	public static void main(String[] args) throws IOException {
		
		// object mapper
		JacksonObjectMapper mapper = new JacksonObjectMapper();
		
		// absolute project path
		String absPath = "/home/ubuntu/eclipse-workspace";
		
		// input file path
		String inputPath = absPath+"/raw_tweet100k.json";
		// testing input file
//		String inputPath = absPath+"/src/main/java/mapreduce/parser/dummy_input.json";
		
		// parse tweets
		mapper.parseTweetJsonFile(inputPath);

		// new files
		File newFile = new File(absPath+"/bigdata-project/input/retweets+tweets.txt");
		File newFile2 = new File(absPath+"/input/screenNames.txt");
		
		// write text to new file
		mapper.writeTweetToJson(newFile);
		
	}
}
