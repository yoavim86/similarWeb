import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class UrlUrlTop10Reducer extends Reducer<Text, Text, Text, Text> {
    
	public void reduce(Text key, Iterable<Text> values, Context output) throws IOException, InterruptedException {
        String[] urlurlRank = key.toString().split("\\s+");
        String keyStr = urlurlRank[0];
       
        Iterator<Text> it = values.iterator();
    	int i = 0;
    	while (it.hasNext() && i < 10) {
    	    Text res = it.next();
    	    String top = keyStr + " " + res.toString();
            output.write(new Text(top), new Text(""));
    	    i++;
        }
    }
}
