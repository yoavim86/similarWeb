import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class UrlUrlTop10Sort extends WritableComparator {
    protected UrlUrlTop10Sort() {
        super(Text.class, true);
    }   
    @SuppressWarnings("rawtypes")
    @Override
    public int compare(WritableComparable w1, WritableComparable w2) {
    	Text wt1 = (Text)w1;
    	Text wt2 = (Text)w2;
        String[] urlurlRank1 = wt1.toString().split("\\s+");
        String[] urlurlRank2 = wt2.toString().split("\\s+");

        String baseKey1 = urlurlRank1[0];
        String baseKey2 = urlurlRank2[0];

        int comp = baseKey1.compareTo(baseKey2);
        if (comp != 0) {
        	return comp;
        }
        
        int rank1 = Integer.parseInt(urlurlRank1[2]);
        int rank2 = Integer.parseInt(urlurlRank2[2]);
        return -1 * Integer.compare(rank1, rank2);
    }
}