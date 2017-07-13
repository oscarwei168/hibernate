package tw.com.oscar.jdk.thread;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * <p>
 * Title: ThreadSafe.java<br>
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
 * @author ${USER_NAME}
 * @version v1, 2017/7/11
 * @since 2017/7/11
 */
@Documented
@Target(TYPE)
@Retention(RUNTIME)
public @interface ThreadSafe {
}
