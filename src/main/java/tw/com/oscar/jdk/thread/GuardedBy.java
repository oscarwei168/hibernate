package tw.com.oscar.jdk.thread;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * <p>
 * Title: GuardedBy.java<br>
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
@Target(value = {FIELD, METHOD})
@Retention(RUNTIME)
public @interface GuardedBy {
    String value();
}
