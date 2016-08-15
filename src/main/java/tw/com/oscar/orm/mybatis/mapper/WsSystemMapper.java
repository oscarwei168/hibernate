/**
 * WsSystemMapper.java
 * Title: Oscar Wei Java Project
 * Copyright: Copyright(c)2016, oscarwei168
 *
 * @author Oscar Wei
 * @since 2016/8/13
 * <p>
 * H i s t o r y
 * 2016/8/13 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.orm.mybatis.mapper;

import tw.com.oscar.orm.mybatis.domain.WsSystem;

import java.util.List;

/**
 * <p>
 * Title: WsSystemMapper.java<br>
 * </p>
 * <strong>Description:</strong> //TODO <br>
 * This function include: - <br>
 * <p>
 * Copyright: Copyright (c) 2016<br>
 * </p>
 * <p>
 * Company: oscarwei168 Inc.
 * </p>
 *
 * @author Oscar Wei
 * @version v1, 2016/8/13
 * @since 2016/8/13
 */
public interface WsSystemMapper {

    List<WsSystem> findAllWsSystem();

    WsSystem searchWsSystemById(Long id);

    // List<WsSystem> searchWsSystem(Map<String, Object> params);

    void insertWsSystem(WsSystem entity);

    int updateWsSystem(WsSystem entity);

    int deleteWsSystem(Long id);
}
