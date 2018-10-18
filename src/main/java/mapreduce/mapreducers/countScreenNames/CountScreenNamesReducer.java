package mapreduce.mapreducers.countScreenNames;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import com.oracle.tools.packager.Log.Logger;

import java.io.IOException;
import java.util.logging.LogManager;

public class CountScreenNamesReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

	@Override
	protected void reduce(Text key, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException {

		// key : screen name
		// values : null

		
		// emit key-pair: key, count
		// key is the word that is being counted
		// count is the number of occurrences of that word

		context.write(key, null);
	}
}
