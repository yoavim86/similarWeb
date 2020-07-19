import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.io.IOException;

public class TagToUrlMapper extends Mapper<Object, Text, Text, Text> {

    public void map(Object key, Text line, Context context) throws IOException {
        try {
            String[] urlTag = line.toString().split("\\s+");
            Text outputValUrl = new Text(urlTag[0]);
            Text outputKeyTag = new Text(urlTag[1]);
            context.write(outputKeyTag, outputValUrl);
        } 
        catch (IOException e) {
            e.printStackTrace();
        } 
        catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
}
