import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class UrlUrlTop10Grouping extends WritableComparator {
	protected UrlUrlTop10Grouping() {
		 super(Text.class, true);
	}
   
	@SuppressWarnings("rawtypes")
    @Override
    public int compare(WritableComparable w1, WritableComparable w2) {
    	Text wt1 = (Text)w1;
    	Text wt2 = (Text)w2;
        String[] urlurlRank1 = wt1.toString().split("\\s+");
        String[] urlurlRank2 = wt2.toString().split("\\s+");

        String k1 = urlurlRank1[0];
        String k2 = urlurlRank2[0];
        return k1.compareTo(k2);
    }
}
