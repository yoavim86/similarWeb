import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class TagToUrlsReducer extends Reducer<Text, Text, Text, IntWritable> {
    
    public void reduce(Text keyTag, Iterable<Text> ValUrls, Context context) throws IOException, InterruptedException {
    	Iterator<Text> it = ValUrls.iterator();
    	ArrayList<String> arr = new ArrayList<String>();
    	
    	while (it.hasNext()) {
    	    Text url = it.next();
    	    String s = url.toString();
    	    arr.add(s);
        }
    	
        for (int i = 0; i < arr.size(); i++) {
            for (int j = 0; j < arr.size(); j++) {
            	if (i != j) {
                	context.write(new Text(arr.get(i) + " " + arr.get(j)), new IntWritable(1));
            	}
            }
        } 
    }
}
