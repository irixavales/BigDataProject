package mapreduce.mapreducers.countTweetsByUser;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.Arrays;

public class CountTweetsByUserMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

		// split tweet obj in columns by attributes
		String cols[] = value.toString().split(",");

		// get the user id
		String usr_id = cols[1];

		context.write(new Text(usr_id), new IntWritable(1));


	}
}
