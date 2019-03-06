/**
 * SoOrder.java
 * Title: Oscar Wei Java Project
 * Copyright: Copyright(c)2017, oscarwei168
 *
 * @author Oscar Wei
 * @since 2017/10/23
 * <p>
 * H i s t o r y
 * 2017/10/23 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.orm.hibernate.domain;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NaturalId;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * Title: SoOrder.java<br>
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
 * @version v1, 2017/10/23
 * @since 2017/10/23
 */
@Entity
@Table(name = "SO_ORDER", uniqueConstraints = @UniqueConstraint(name = "UK_ORDER_NUMB", columnNames
        = {"ORDER_NUMBER"}), indexes = {@Index(name = "INX_ORDER_NUMBER", columnList = "ORDER_NUMBER",
        unique = true)})
@BatchSize(size = 5)
@DynamicInsert
@DynamicUpdate
public class SoOrder extends VersionEntity {

    private String orderNumber;
    private String orderComment;

    private List<SoLine> soLines;

    public SoOrder() {
    }

    @Column(name = "ORDER_NUMBER", nullable = false, length = 10, unique = true, updatable = false)
    @NotNull
    @Size(min = 10, max = 10)
    @NaturalId
    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Transient
    public String getOrderComment() {
        return orderComment;
    }

    public void setOrderComment(String orderComment) {
        this.orderComment = orderComment;
    }

    //    @OrderBy("dateCreated DESC")
//    @OrderColumn(name = "INX_ORDER_SO")
//    @IndexedEmbedded
    @OneToMany(mappedBy = "soOrder", cascade = CascadeType.ALL)
    public List<SoLine> getSoLines() {
        return soLines;
    }

    public void setSoLines(List<SoLine> soLines) {
        this.soLines = soLines;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SoOrder soOrder = (SoOrder) o;
        return !(getOrderNumber() != null ? !getOrderNumber().equals(soOrder.getOrderNumber()) : soOrder.getOrderNumber() != null);

    }

    @Override
    public int hashCode() {
        return getOrderNumber() != null ? getOrderNumber().hashCode() : 0;
    }

}
