import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class SimilarWebJob {
    public static void main(String[] args) throws Exception {
        
    	int cores = Runtime.getRuntime().availableProcessors();
    	
        /***** JOB 1 *****/
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "tagUrl");
        job.setJarByClass(SimilarWebJob.class);
        
        job.setMapperClass(TagToUrlMapper.class);
        //job.setCombinerClass(UrlTagReducer.class);
        job.setReducerClass(TagToUrlsReducer.class);
        
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        // Check for if we skip anything.
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path("out1"));

        job.setNumReduceTasks(cores);

        boolean b1 = job.waitForCompletion(true);
        assert(b1 == true);
        		
        /***** JOB 2 *****/
        Configuration conf2 = new Configuration();
        Job job2 = Job.getInstance(conf2, "urlurl");
        job2.setJarByClass(SimilarWebJob.class);
        job2.setMapperClass(UrlUrlMapper.class);
        job2.setReducerClass(UrlUrlReducer.class);
        
        job2.setOutputKeyClass(Text.class);
        job2.setOutputValueClass(IntWritable.class);

        // Check for if we skip anything.
        FileInputFormat.addInputPath(job2, new Path("out1"));
        FileOutputFormat.setOutputPath(job2, new Path("out2"));
		job2.setNumReduceTasks(cores);

        boolean b2 = job2.waitForCompletion(true);
        assert(b2 == true);
        
        /***** JOB 3 *****/
        Configuration conf3 = new Configuration();
        Job job3 = Job.getInstance(conf3, "urlurlRankSort");
        job3.setJarByClass(SimilarWebJob.class);

        job3.setMapperClass(UrlUrltop10Mapper.class);
        
        job3.setPartitionerClass(UrlUrlTop10Partitioner.class);
        job3.setSortComparatorClass(UrlUrlTop10Sort.class);
        job3.setGroupingComparatorClass(UrlUrlTop10Grouping.class);

        job3.setReducerClass(UrlUrlTop10Reducer.class);
        
        job3.setOutputKeyClass(Text.class);
        job3.setOutputValueClass(Text.class);

        // Check for if we skip anything.
        FileInputFormat.addInputPath(job3, new Path("out2"));
        FileOutputFormat.setOutputPath(job3, new Path(args[1]));

		job3.setNumReduceTasks(cores);
        System.exit(job3.waitForCompletion(true) ? 0 : 1);
    }
}
