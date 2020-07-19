import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class UrlUrlReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
    
    public void reduce(Text key, Iterable<IntWritable> values, Context output) throws IOException, InterruptedException {
        int resultNumber = 0;
        for (IntWritable value : values) {
            resultNumber += value.get();
        }
        IntWritable result = new IntWritable();
        result.set(resultNumber);
        output.write(key, result);
    }
}
