package mapreduce.mapreducers.countScreenNames;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.Arrays;

public class CountScreenNamesMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

		// split tweet obj in columns by attributes
//		String cols[] = value.toString().split(",", 6);

		// get the user screen name
//		String screen_name = cols[2];

		context.write(new Text(value), null);

	}
}
