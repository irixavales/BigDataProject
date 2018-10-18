package mapreduce.mapreducers.countRetweetsByTweet;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import com.oracle.tools.packager.Log.Logger;

import java.io.IOException;
import java.util.logging.LogManager;

public class CountRetweetsByTweetReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

	@Override
	protected void reduce(Text key, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException {

		// key : tweet
		// values : number of retweets in tweet
		
		IntWritable value = values.iterator().next();

		context.write(key, value);
	}
}
