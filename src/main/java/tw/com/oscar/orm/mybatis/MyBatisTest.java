package tw.com.oscar.orm.mybatis;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tw.com.oscar.guice.GuiceModule;
import tw.com.oscar.guice.service.MyBatisSessionFactoryService;
import tw.com.oscar.orm.hibernate.domain.Credit;
import tw.com.oscar.orm.hibernate.domain.enums.CreditCardType;

import java.io.IOException;
import java.util.Collection;

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
        SqlSessionFactory sqlMapper = sessionService.getSqlSessionFactory(resource);
        SqlSession sqlSession = null;
        try {
            /** Obtain SqlSession object **/
            sqlSession = sqlMapper.openSession();
            Credit credit = sqlSession.selectOne("tw.com.oscar.orm.mybatis.mapper.CreditMapper.selectCredit", new
                    Long(1));
            logger.info("" + credit);

            Credit newCredit = new Credit();
            newCredit.setName("Master");
            newCredit.setDescription("Master card");
            newCredit.setCreditCardType(CreditCardType.MASTER);
            // return inserted count
            logger.info("" + sqlSession.insert("tw.com.oscar.orm.mybatis.mapper.CreditMapper.insertCredit", newCredit));
            sqlSession.commit();

            Collection<Credit> credits = sqlSession.selectList("tw.com.oscar.orm.mybatis.mapper.CreditMapper" +
                    ".selectAllCredit");
            logger.info("" + credits);

            logger.info("" + newCredit.getPid());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != sqlSession) {
                sqlSession.close();
            }
        }

    }
}
