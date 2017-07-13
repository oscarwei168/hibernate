package tw.com.oscar.orm.mybatis;

import com.google.common.collect.Maps;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tw.com.oscar.guice.GuiceModule;
import tw.com.oscar.guice.service.MyBatisSessionFactoryService;
import tw.com.oscar.orm.mybatis.domain.WsSystem;
import tw.com.oscar.orm.mybatis.mapper.WsSystemMapper;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by oscarwei168 on 2015/6/25.
 */
public class MyBatisTest {

    private static final Logger logger = LoggerFactory.getLogger(MyBatisTest.class);

    public static void main(String[] args) throws IOException {
//        InputStream inputStream = Resources.getResourceAsStream("tw/com/oscar/orm/mybatis/mapper/mybatis-config.xml");
        String resource = "mybatis-config.xml";
        Injector injector = Guice.createInjector(new GuiceModule());
        MyBatisSessionFactoryService sessionService = injector.getInstance(MyBatisSessionFactoryService.class);
        SqlSessionFactory sessionFactory = sessionService.getSqlSessionFactory(resource);
        SqlSession sqlSession = null;
        try {
            /** Obtain SqlSession object **/
            sqlSession = sessionFactory.openSession();
            WsSystem entity = sqlSession.selectOne("tw.com.oscar.orm.mybatis.mapper.WsSystemMapper.searchWsSystemById",
                    (long) 1);
            logger.info("" + entity);

            /** Obtain Mapper **/
            WsSystemMapper mapper = sqlSession.getMapper(WsSystemMapper.class);
            entity = mapper.searchWsSystemById((long) 2);
            logger.info("" + entity);

            List<WsSystem> allWsSystems = mapper.findAllWsSystem();
            logger.info("" + allWsSystems.size());
    
            Map<String, Object> param = Maps.newHashMap();
            param.put("name", "eRMA");
            allWsSystems = mapper.searchWsSystem(param);
            logger.info("searchWsSystem size : " + allWsSystems.size());

            WsSystem wsSystem = new WsSystem();
            wsSystem.setName("Test");
            wsSystem.setDomain("SYSTEM");
            wsSystem.setDescription("Test");
            wsSystem.setDateCreated(new Date());
            mapper.insertWsSystem(wsSystem);
            sqlSession.commit();
            Long id = wsSystem.getId();
            logger.info("" + id);

            wsSystem.setDescription("Oscar");
            wsSystem.setDateLastModified(new Date());
            int updateRowCount = mapper.updateWsSystem(wsSystem);
            sqlSession.commit();
            logger.info("" + updateRowCount);
            logger.info("" + wsSystem);

            int deleteRowCount = mapper.deleteWsSystem(id);
            sqlSession.commit();
            logger.info("" + deleteRowCount);

//            Credit newCredit = new Credit();
//            newCredit.setName("Master");
//            newCredit.setDescription("Master card");
//            newCredit.setCreditCardType(CreditCardType.MASTER);
//            // return inserted count
//            logger.info("" + sqlSession.insert("tw.com.oscar.orm.mybatis.mapper.CreditMapper.insertCredit", newCredit));
//            sqlSession.commit();
//
//            Collection<Credit> credits = sqlSession.selectList("tw.com.oscar.orm.mybatis.mapper.CreditMapper" +
//                    ".selectAllCredit");
//            logger.info("" + credits);
//
//            logger.info("" + newCredit.getPid());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != sqlSession) {
                sqlSession.close();
            }
        }
    }
}
