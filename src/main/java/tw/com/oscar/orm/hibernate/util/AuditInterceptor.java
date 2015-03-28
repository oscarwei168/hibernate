/**
 * Title: Acer Internal Project
 * Copyright: (c) 2015, Acer Inc.
 * Name: AuditInterceptor
 *
 * @author Oscar Wei
 * @since 2015/3/15
 * <p>
 * H i s t o r y
 * <p>
 * 2015/3/15 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.orm.hibernate.util;

import org.hibernate.EmptyInterceptor;
import org.hibernate.Transaction;
import org.jboss.logging.Logger;
import tw.com.oscar.orm.hibernate.domain.Account;
import tw.com.oscar.orm.hibernate.domain.Credit;
import tw.com.oscar.orm.hibernate.domain.Role;

import java.io.Serializable;
import java.lang.reflect.Type;

/**
 * <strong>Description:</strong><br>
 * This function include: - TODO <br>
 *
 * @author Oscar Wei
 * @version v1, 2015/3/15
 * @since 2015/3/15
 */
public class AuditInterceptor extends EmptyInterceptor {

    private static final Logger LOGGER = Logger.getLogger(AuditInterceptor.class);
    private int updates;
    private int creates;
    private int loads;

    public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames,
                         Type[] types) {

        LOGGER.info("ON DELETE");
        // do nothing
    }

    public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState,
                                String[] propertyNames, Type[] types) {

        LOGGER.info("ON FLUSH DIRTY");
        if (entity instanceof Credit) {
            updates++;
            for (int i = 0; i < propertyNames.length; i++) {
                if ("description".equals(propertyNames[i])) {
                    currentState[i] = "XXXXXXXXXX";
                    return true;
                }
            }
        }
        return false;
    }

    public boolean onLoad(Object entity, Serializable id, Object[] state, String[] propertyNames,
                          Type[] types) {
        LOGGER.info("ON LOAD");
        if (entity instanceof Account) {
            loads++;
        }
        return false;
    }

    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames,
                          Type[] types) {

        LOGGER.info("ON SAVE");
        if (entity instanceof Role) {
            creates++;
            for (int i = 0; i < propertyNames.length; i++) {
                if ("description".equals(propertyNames[i])) {
                    state[i] = "XXXX";
                    return true;
                }
            }
        }
        return false;
    }

    public void afterTransactionCompletion(Transaction tx) {
        if (tx.wasCommitted()) {
            LOGGER.info("Creations: " + creates + ", Updates: " + updates + ", Loads: " + loads);
        }
        updates = 0;
        creates = 0;
        loads = 0;
    }
}
