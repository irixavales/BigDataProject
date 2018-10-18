package mapreduce.mapreducers.countTweetsByUser;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class CountTweetsByUser {

	public static void main(String[] args) throws Exception {
		if (args.length != 2) {
			System.err.println("Usage: CountTweetsByUser <input path> <output path>");
			System.exit(-1);
		}
		
		Configuration conf = new Configuration();
		Job job = new Job(conf);
		
		job.setJobName("Count Tweets By User");
		job.setJarByClass(CountTweetsByUser.class);
		job.setMapperClass(CountTweetsByUserMapper.class);
		job.setReducerClass(CountTweetsByUserReducer.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		Path outputPath = new Path(args[1]);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		outputPath.getFileSystem(conf).delete(outputPath, true);
		

		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
	
}
