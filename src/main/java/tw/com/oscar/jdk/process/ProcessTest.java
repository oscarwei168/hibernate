/**
 * ProcessTest.java
 * Title: Oscar Wei Java Project
 * Copyright: Copyright(c)2017, oscarwei168
 *
 * @author Oscar Wei
 * @since 2017/5/31
 * <p>
 * H i s t o r y
 * 2017/5/31 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.jdk.process;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

/**
 * <p>
 * Title: ProcessTest.java<br>
 * </p>
 * <strong>Description:</strong> //TODO <br>
 * This function include: - <br>
 * <p>
 * Copyright: Copyright (c) 2017<br>
 * </p>
 * <p>
 * Company: oscarwei168 Inc.
 * </p>
 *
 * @author Oscar Wei
 * @version v1, 2017/5/31
 * @since 2017/5/31
 */
public class ProcessTest {
    
    private static Exception ex;
    
    public static void main(String[] args) {
        try {
            // Process p = new ProcessBuilder("ls", "-al").start();
            // InputStream is = p.getInputStream();
            // System.out.println(IOUtils.toString(is));
            // --------------
            ProcessBuilder pb = new ProcessBuilder("ls", "-al");
            // ProcessBuilder pb = new ProcessBuilder("echo", "This is ProcessBuilder Example from JCG");
            Map<String, String> env = pb.environment();
            env.put("VAR1", "myValue");
            env.remove("OTHERVAR");
            env.put("VAR2", env.get("VAR1") + "suffix");
            pb.directory(new File("/Users/oscarwei168/workspace"));
            // File log = new File("log");
            // pb.redirectErrorStream(true);
            // pb.redirectOutput(ProcessBuilder.Redirect.appendTo(log));
            Process p = pb.start();
            int errorCode = p.waitFor();
            System.out.println("Error Code: " + errorCode);
            InputStream is = p.getInputStream();
            System.out.println(IOUtils.toString(is));
        } catch (Exception ex) {
            ProcessTest.ex = ex;
            ProcessTest.ex.printStackTrace();
        }
        
    }
}
