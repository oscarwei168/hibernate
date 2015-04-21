package tw.com.oscar.jdk.v8.lambda.functionalinterface;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * <strong>Description:</strong><br>
 * This function include: - TODO <br>
 *
 * @author Oscar Wei
 * @version v1, 2015/4/18
 * @since 2015/4/18
 */
@FunctionalInterface
public interface BufferedReaderProcessor {

    String process(BufferedReader reader) throws IOException;
}
