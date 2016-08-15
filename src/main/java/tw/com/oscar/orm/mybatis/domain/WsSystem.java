/**
 * WsSystem.java
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
package tw.com.oscar.orm.mybatis.domain;

import com.google.common.base.MoreObjects;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * Title: WsSystem.java<br>
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
@Alias("WsSystem")
public class WsSystem implements Serializable {

    private Long id;
    private String name;
    private String domain;
    private String description;
    private Date dateCreated;
    private Date dateLastModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateLastModified() {
        return dateLastModified;
    }

    public void setDateLastModified(Date dateLastModified) {
        this.dateLastModified = dateLastModified;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("domain", domain)
                .add("description", description)
                .add("dateCreated", dateCreated)
                .add("dateLastModified", dateLastModified)
                .toString();
    }
}
