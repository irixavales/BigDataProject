package mapreduce.mapreducers.countWordOccurrences;

import mapreduce.parser.JacksonObjectMapper;
import mapreduce.parser.Tweet;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
//import java.util.Arrays;

public class CountWordOccurrencesMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	
	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		
		// words that will be counted
		String[] keywords = {"trump", "flu", "zika", "diarrhea", "ebola", "headache", "measles"};
		
		// split tweet obj in columns by attributes
//		String cols[] = value.toString().split(",", 6);
		
		// get the tweet message
//		String text = cols[5];
//		String text = tweet.getText();
		
		// split the tweet message by words
		// check commas, punctuation marks
//		String words[] = text.split(" ");
		
		// value is the text of the tweet
		String lowercase_text = value.toString().toLowerCase();
		
		// loop through keywords to check if it is in the message
		// this method doesn't take into consideration how many times the word appears on the tweet
		for (String word: keywords) {
			if (lowercase_text.contains(word)) {
				context.write(new Text(word), new IntWritable(1));
			}
		}
		
		// loop through every word in message
//		for (String word: words) {
//			String lowercase_word = word.toLowerCase();
//			// check if word is one of the keyword to be counted
//			if (Arrays.asList(keywords).contains(lowercase_word)) {
//				context.write(new Text(word), new IntWritable(1));
//			}
//		}
	}
}
