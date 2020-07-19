import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.io.IOException;

public class UrlUrlMapper extends Mapper<Object, Text, Text, IntWritable> {

    public void map(Object key, Text line, Context context) throws IOException {
        try {
            String[] urlurl = line.toString().split("\\s+");
            String uu = urlurl[0] + " " +  urlurl[1];
            context.write(new Text(uu), new IntWritable(1));
        } 
        catch (IOException e) {
            e.printStackTrace();
        } 
        catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
}
