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

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

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
@Table(name = "SO_LINE")
@BatchSize(size = 5)
@DynamicInsert
@DynamicUpdate
public class SoLine extends VersionEntity {

    private Integer lineNumber;
    private Integer qtyOrdered;

    private SoOrder soOrder;

    public SoLine() {
    }

    @Column(name = "LINE_NUMBER", nullable = false, length = 3, updatable = false)
    @NotNull
    @Size(min = 1, max = 3)
    @NaturalId
    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    @Column(name = "QTY_ORDERED", nullable = false)
    @NotNull
    public Integer getQtyOrdered() {
        return qtyOrdered;
    }

    public void setQtyOrdered(Integer qtyOrdered) {
        this.qtyOrdered = qtyOrdered;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "PID_ORDER_SO", nullable = false)
    @org.hibernate.annotations.ForeignKey(name = "FK_SO_ORDER_SO_LINE_PID")
//    @OnDelete(action = OnDeleteAction.CASCADE)
    public SoOrder getSoOrder() {
        return soOrder;
    }

    public void setSoOrder(SoOrder soOrder) {
        this.soOrder = soOrder;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lineNumber, qtyOrdered);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final SoLine other = (SoLine) obj;
        return Objects.equals(this.lineNumber, other.lineNumber)
                && Objects.equals(this.qtyOrdered, other.qtyOrdered);
    }
}
