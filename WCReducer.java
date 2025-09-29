// Importing libraries
import java.io.IOException;
import java.util.Iterator;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class WCReducer extends MapReduceBase implements Reducer<Text,
                                    IntWritable, Text, IntWritable> {

    // Reduce function
    public void reduce(Text key, Iterator<IntWritable> value, 
                   OutputCollector<Text, IntWritable> output, 
                            Reporter rep) throws IOException
    {

        int count = 0;

        // Counting the frequency of each words
        while (value.hasNext()) 
        {
            IntWritable i = value.next();
            count += i.get();
        }

        //
        // take only the lines with values greater than 1
        //if (count >= 2) {
        // take only the lines with values greater than 100
        if (count >= 101) {
            output.collect(key, new IntWritable(count));
        }
    }
}
