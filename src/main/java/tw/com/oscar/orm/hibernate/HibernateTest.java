/**
 * Title: Acer Internal Project
 * Copyright: (c) 2015, Acer Inc.
 * Name: HibernateTest
 *
 * @author Oscar Wei
 * @since 2015/3/14
 * <p>
 * H i s t o r y
 * <p>
 * 2015/3/14 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.orm.hibernate;

import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.*;
import org.hibernate.proxy.HibernateProxyHelper;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.hibernate.stat.Statistics;
import org.jboss.logging.Logger;
import tw.com.oscar.orm.hibernate.domain.*;
import tw.com.oscar.orm.hibernate.util.HibernateUtil;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <strong>Description:</strong><br>
 * This function include: - TODO <br/>
 *
 * @author Oscar Wei
 * @version v1, 2015/3/14
 * @since 2015/3/14
 */
public class HibernateTest {

    private static final Logger LOGGER = Logger.getLogger(HibernateTest.class);
    private static final String ADMIN = "DTS.Admin";
    // private Session session;

    public static void main(String[] args) {

        Statistics statistics = null;
        try {
            statistics = HibernateUtil.getSessionFactory().getStatistics();
            statistics.setStatisticsEnabled(true);
            statistics.clear();
            statistics.getEntityStatistics(SoOrder.class.getName());

            // DO NOT use proxy explicitly
            HibernateProxyHelper.getClassWithoutInitializingProxy(Account.class);

            // Criteria examples
            // Session object is not a thread-safe object
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createSQLQuery("SELECT * FROM SO_ORDER so").addEntity(SoOrder.class);
            List<SoOrder> soOrderList = query.list();
            LOGGER.info(String.format("Result counts %s: %s", 1, soOrderList.size()));
            query = session.createSQLQuery("SELECT PID as pid, ORDER_NUMBER as orderNo FROM SO_ORDER so")
                    .addScalar("pid").addScalar("orderNo");
            List<Object[]> soOrderObjs = query.list();
            LOGGER.info(String.format("Result counts %s: %s", 2, soOrderObjs.size()));
            soOrderList = (List<SoOrder>) session.createCriteria(SoOrder.class).setFetchMode("soLines",
                    FetchMode.JOIN).list();
            LOGGER.info(String.format("Result counts %s: %s", 3, soOrderList.size()));
            soOrderList = session.createSQLQuery("SELECT {so.*} FROM SO_ORDER so JOIN SO_LINE line ON so.PID = line" +
                    ".PID_ORDER_SO")
                    .addEntity("so", SoOrder.class).list();
            LOGGER.info(String.format("Result counts %s: %s", 4, soOrderList.size()));
            soOrderObjs = session.createSQLQuery("SELECT so.*, line.* FROM SO_ORDER so, SO_LINE line " +
                    "WHERE so.PID = line.PID_ORDER_SO").addEntity("so", SoOrder.class).addJoin("line", "so.soLines")
                    .list();
            LOGGER.info(String.format("Result counts %s: %s", 5, soOrderObjs.size()));
            soOrderObjs = session.createSQLQuery("SELECT {so.*}, line.LINE_NUMBER AS lineNumber FROM SO_ORDER so JOIN SO_LINE line " +
                    "ON so.PID = line.PID_ORDER_SO").addEntity
                    ("so", SoOrder.class).addScalar("lineNumber").list();
            LOGGER.info(String.format("Result counts %s: %s", 6, soOrderObjs.size()));
            session.close();
            LOGGER.info(String.format("SoOrder load counts: %s", statistics.getEntityLoadCount()));

//            // Normal insert
//            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//            Transaction tx = session.beginTransaction();
//            Long pid = testCase1(session);
//            tx.commit();
//            EntityStatistics roleStatic = statistics.getEntityStatistics(Role.class.getName());
//            LOGGER.info("InsertCount : " + roleStatic.getInsertCount());
//            statistics.clear();
//
//            // Dynamic insert/update testing
//            session = HibernateUtil.getSessionFactory().getCurrentSession();
//            tx = session.beginTransaction();
//            Role role = testCase2(session, pid);
//            tx.commit();
//            LOGGER.info("UpdateCount : " + roleStatic.getUpdateCount());
//            statistics.clear();
//
//            // same-session cache testing
//            session = HibernateUtil.getSessionFactory().getCurrentSession();
//            HibernateUtil.getSessionFactory().getCache().evictEntity(Role.class, pid);
//            tx = session.beginTransaction();
//            session.setCacheMode(CacheMode.PUT);
//            List<Role> roles = testCache1(session, pid); // comment class-level cache setting
//            tx.commit();
//            statistics.clear();
//
//            // cross-session cache testing
//            session = HibernateUtil.getSessionFactory().getCurrentSession();
//            tx = session.beginTransaction();
//            HibernateUtil.getSessionFactory().getCache().evictEntity(Role.class, pid);
//            session.setCacheMode(CacheMode.NORMAL); // default cache mode
//            Role role3 = testCache2(session, pid); // uncomment class-level cache setting
//            tx.commit();
//            statistics.clear();
//
//            boolean isContain = roles.contains(role3);
//            assert isContain : "WRONG REFERENCE???";
//
//            // update() and merge() examples
//            role3.setDescription("Xxxxx"); // state?
//
//            session = HibernateUtil.getSessionFactory().getCurrentSession();
//            tx = session.beginTransaction();
//            Role role4 = testCache2(session, pid);
//            role4.setDescription("Yyyyyy");
//            session.update(role3);
//            Role role5 = (Role) session.merge(role3);
//            LOGGER.info(session.contains(role3)); // role3 is detached
//            LOGGER.info(session.contains(role4));
//            LOGGER.info(session.contains(role5)); // role5 is persist
//            LOGGER.info(session.isDirty());
//            session.refresh(role3);
//            LOGGER.info(session.contains(role3));
//            tx.commit();
//            statistics.clear();
//            // Composition-id searching
//            session = HibernateUtil.getSessionFactory().getCurrentSession();
//            tx = session.beginTransaction();
//            CompanyId companyId = new CompanyId("EMEA", "0001");
//            Company company = (Company) session.get(Company.class, companyId);
//            LOGGER.info("Desc : " + company.getDescription());
//            tx.commit();
//            statistics.clear();
//
//            session = HibernateUtil.getSessionFactory().getCurrentSession();
//            tx = session.beginTransaction();
//            Credit credit = createCredit(session);
//            session.delete(role); // TODO
//            tx.commit();
//            statistics.clear();
//
//            // Persisting example
//            session = HibernateUtil.getSessionFactory().getCurrentSession();
//            tx = session.beginTransaction();
//            testCase3(session, credit);
//            tx.commit();
//            statistics.clear();
//
//            session = HibernateUtil.getSessionFactory().getCurrentSession();
//            tx = session.beginTransaction();
//            accountSummary(session);
//            tx.commit();
//            statistics.clear();
//
//            session = HibernateUtil.getSessionFactory().getCurrentSession();
//            tx = session.beginTransaction();
//            formula(session);
//            tx.commit();
//            statistics.clear();
//
//            // Named-query example
//            session = HibernateUtil.getSessionFactory().getCurrentSession();
//            tx = session.beginTransaction();
//            testCase4(session);
//            tx.commit();
//            statistics.clear();
//
//            // Natural-Id searching example
//            session = HibernateUtil.getSessionFactory().getCurrentSession();
//            tx = session.beginTransaction();
//            testNaturalId(session);
//            tx.commit();
//            statistics.clear();
//
//            // Full-Text searching example
//            session = HibernateUtil.getSessionFactory().getCurrentSession();
//            tx = session.beginTransaction();
//            testFullText(session);
//            tx.commit();
//            statistics.clear();

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            if (null != statistics) {
                statistics.setStatisticsEnabled(false);
            }
            HibernateUtil.getSessionFactory().close();
            LOGGER.info("Hibernate session is closed");
        }

    }

    private static List<Role> testCache1(Session session, Long pid) {
        Role role1 = (Role) session.get(Role.class, pid);
        Role role2 = (Role) session.get(Role.class, pid);

        boolean isSame = role1 == role2;
        assert isSame;

        List<Role> roles = Arrays.asList(role1);
        return roles;
    }

    private static Role testCache2(Session session, Long pid) {
        Role role1 = (Role) session.get(Role.class, pid);
        return role1;
    }

    /**
     * Test dynamic insert/update
     *
     * @param session a hibernate session object
     */
    private static Long testCase1(Session session) {
        Role role = new Role();
        role.setRoleName("ROLE_ADMIN");
        role.setUserCreated(ADMIN);
        role.setDateCreated(new Date());
        return (Long) session.save(role);
    }

    /**
     * Test dynamic insert/update and cache
     *
     * @param session a hibernate session object
     * @param rolePid a role pid
     */
    private static Role testCase2(Session session, Long rolePid) {
        Role role = (Role) session.get(Role.class, rolePid);
        role.setRoleName("ROLE_USER");
        role.setUserCreated("Oscar.Admin"); // Nothing happen...
        role.setDateCreated(new Date());
        role.setUserLastModified(ADMIN);
        role.setDateLastModified(new Date());
        session.save(role);
        return role;
    }

    private static void testCase3(Session session, Credit credit) {

        // Self-join example
        Department it = new Department("IT");
        it.setUserCreated(ADMIN);
        it.setDateCreated(new Date());

        Department pg = new Department("PG");
        pg.setUserCreated(ADMIN);
        pg.setDateCreated(new Date());
        it.addSubDepartment(pg);

        Department service = new Department("SERVICE");
        service.setUserCreated(ADMIN);
        service.setDateCreated(new Date());
        it.addSubDepartment(service);

        Account account = new Account();
        account.setUsername("oscarwei");
        account.setPassword("12345");
        account.setFirstName("Oscar");
        account.setLastName("Wei");
        account.setEmail("oscar.wei@acer.com");
        account.setSalary(new BigDecimal(1000000L));
        account.setUserCreated(ADMIN);
        account.setDateCreated(new Date());

        LobHelper helper = session.getLobHelper();
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File
                    ("D:/Oscar/ImageSmall.gif")));
            account.setPhoto(helper.createBlob(bis, bis.available()));
            account.setDescription(helper.createNClob("我是Oscar Wei,大家好"));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

        Address home = new Address();
        home.setCity("Taipei");
        home.setStreet("信義豪宅");
        home.setZipCode("101");

        Address work = new Address();
        work.setCity("Taipei");
        work.setStreet("Taipei 101");
        work.setZipCode("110");

        account.setHomeAddress(home);
        account.setWorkAddress(work);

        Role adminRole = new Role();
        adminRole.setRoleName("ROLE_ADMIN");
        adminRole.setUserCreated(ADMIN);
        adminRole.setDateCreated(new Date());

        Role userRole = new Role();
        userRole.setRoleName("ROLE_USER");
        userRole.setUserCreated(ADMIN);
        userRole.setDateCreated(new Date());

        Instant instant = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant();
        ToDo do1 = new ToDo("打混", "Desc1", Date.from(instant));
        ToDo do2 = new ToDo("摸魚", "Desc2", Date.from(instant));
        do1.setUserCreated(ADMIN);
        do2.setUserCreated(ADMIN);
        do1.setDateCreated(new Date());
        do2.setDateCreated(new Date());
        account.addToDo(do1);
        account.addToDo(do2);

//        session.save(account);
//        session.save(admin);
//        session.save(user);

        session.save(pg);
        session.save(service);
        session.save(it);
//        session.merge(userRole);

        account.setDepartment(pg);
        account.setCredit(credit);
        credit.setAccount(account);

//        Hibernate.initialize(userRole.getAccounts());
//        userRole.getAccounts().add(account);
//        Set<Role> roles = new HashSet<>(Arrays.asList(userRole));
        account.addRole(adminRole);
        account.addRole(userRole);
//        account.setRoles(roles);
        session.save(account);
    }

    private static void formula(Session session) {
        Account account = (Account) session.get(Account.class, 1L);
        LOGGER.info("$$$ : " + account.getYearEndBonus()); // read-only
    }

    private static void testCase4(Session session) {
        Query query = session.getNamedQuery(Role.SQL_ROLE_FIND_BY_ROLE_NAME);
        query.setParameter("roleName", "ROLE_USER");
        query.setCacheable(true).setCacheRegion("samp   leCache1");
        Role user = (Role) query.uniqueResult();
        session.delete(user);
    }

    private static void testNaturalId(Session session) {
        NaturalIdLoadAccess naturalIdentifier = session.byNaturalId(Account.class);
        naturalIdentifier.using("username", "oscarwei");
        Account account = (Account) naturalIdentifier.load();
        if (null != account) {
            LOGGER.info("Account email : " + account.getEmail());
        }
    }

    private static void testFullText(Session session) {
        FullTextSession textSession = Search.getFullTextSession(session);
        QueryBuilder queryBuilder = textSession.getSearchFactory().buildQueryBuilder()
                .forEntity(Account.class).get();
        org.apache.lucene.search.Query luceneQuery = queryBuilder
                .keyword().onFields("username")
                .matching("oscarwei").createQuery();
        Query query = textSession.createFullTextQuery(luceneQuery, Account.class);
        List<Account> accounts = query.list();
        if (CollectionUtils.isNotEmpty(accounts)) {
            LOGGER.info("Full-Text searching size : " + accounts.size());
            Account account = accounts.get(0);
            LOGGER.info("Account email : " + account.getEmail());
            account.getToDoSet().stream().map(ToDo::getSubject).forEach(System.out::println);
        }
    }

    private static Credit createCredit(Session session) {
        Credit credit = new Credit();
        credit.setName("Normal");
        credit.setDescription("A normal credit...");
        credit.setUserCreated(ADMIN);
        credit.setDateCreated(new Date());
        long id = (Long) session.save(credit);
//        session.persist(credit);
        return credit;
    }

    private static void accountSummary(Session session) {
//        Query query = session.createQuery("FROM AccountSummary");
//        List<AccountSummary> list = query.list();
//        list.stream().forEach(summary -> System.out.println(summary.getFirstName() + " : " + summary.getSalary()));
    }
}
