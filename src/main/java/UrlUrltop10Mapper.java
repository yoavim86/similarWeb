import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.io.IOException;

public class UrlUrltop10Mapper extends Mapper<Object, Text, Text, Text> {

    public void map(Object key, Text line, Context context) throws IOException {
        try {
            String[] urlurlRank = line.toString().split("\\s+");
            String rankStr = urlurlRank[1] + " " + urlurlRank[2];
            context.write(line, new Text(rankStr));
        } 
        catch (IOException e) {
            e.printStackTrace();
        } 
        catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
}
