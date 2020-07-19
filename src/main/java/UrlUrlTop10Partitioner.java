import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class UrlUrlTop10Partitioner extends Partitioner<Text, Text> {

    @Override
    public int getPartition(Text urlurlRankKey, Text urlRank, int numberOfPartitions) {
    	// make sure that partitions are non-negative
    	String[] urlurlRank = urlurlRankKey.toString().split("\\s+");
     	String baseKey = urlurlRank[0];
     	return Math.abs(baseKey.hashCode() % numberOfPartitions);
    }
}