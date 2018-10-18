package mapreduce.mapreducers.countRetweetsByTweet;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class CountRetweetsByTweetMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

		// split tweet obj in columns by attributes
		String cols[] = value.toString().split(",");

		// get the tweet message
		String text = cols[3];
		// get the retweet count
		int retweets = Integer.valueOf(cols[4]);

		context.write(new Text(text), new IntWritable(retweets));
	}
}
