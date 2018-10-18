package mapreduce.parser;

import java.io.File;
import java.io.IOException;

public class Parser {

	public static void main(String[] args) throws IOException {
		
		// object mapper
		JacksonObjectMapper mapper = new JacksonObjectMapper();
		
		// absolute project path
		String absPath = "/home/ubuntu/eclipse-workspace/bigdata-project/src/main/java/mapreduce";
		
		// input file path
		String inputPath = absPath+"/parser/raw_tweet100k.json";
		
		// new files
		File newFile = new File(absPath+"/input/tweets.txt");
		File newFile2 = new File(absPath+"/input/screenNames.txt");
		
		mapper.parseTweetJsonFile(inputPath, newFile, newFile2);
	}
}
