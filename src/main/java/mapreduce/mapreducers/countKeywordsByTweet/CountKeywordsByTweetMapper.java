package mapreduce.mapreducers.countKeywordsByTweet;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.Arrays;

public class CountKeywordsByTweetMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	
	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		
		// words that are not keywords and will be counted
		// this list includes prepositions, pronouns, etc.
		// check #, @, links, emojis...
		String[] stop_words = {"a", "about", "above", "across", "after", "against", "along", "alongside", "amid", 
				"among", "amongst", "around", "as", "at", "before", "behind", "below", "beneath", "beside", "beyond", 
				"but", "by", "down", "during", "except", "for", "from", "the", "then", "in", "out", "which", "to", "on"};

		// split tweet obj in columns by attributes
//		String cols[] = value.toString().split(",");
		
		// get the tweet message
//		String text = cols[3];
		
		// split the tweet message by words
		// check commas, punctiation marks
		String words[] = value.toString().split(" ");
		
		// loop through every word in message
		for (String word: words) {
			String lowercase_word = word.toLowerCase();
			// remove hashtags
			lowercase_word.replace("#", "");
			// ignore tagged usernames
			if(lowercase_word.contains("@"))
				continue;
			// check if word is one of the keyword to be counted
			if (!Arrays.asList(stop_words).contains(lowercase_word)) {
				context.write(new Text(word), new IntWritable(1));
			}
		}
	}
}
