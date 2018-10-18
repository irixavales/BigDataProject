package mapreduce.mapreducers.countWordOccurrences;

import org.apache.hadoop.io.IntWritable;
//import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

//import com.oracle.tools.packager.Log.Logger;

import java.io.IOException;
//import java.util.logging.LogManager;

public class CountWordOccurrencesReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

	@Override
	protected void reduce(Text key, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException {

		// key : word that is being counted
		// values : list of 1's, one for each occurrence found for the word

		// setup a counter
		int count = 0;
		// iterate over list of 1's, to count them (no size() or length() method available)
		while (values.iterator().hasNext()) {
			count++;
			values.iterator().next();
		}
		
		// emit key-pair: key, count
		// key is the word that is being counted
		// count is the number of occurrences of that word
		
		context.write(key, new IntWritable(count));
	}
}
