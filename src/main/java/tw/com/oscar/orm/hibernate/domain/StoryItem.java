/**
 * Title: Acer Internal Project
 * Copyright: (c) 2015, Acer Inc.
 * Name: StoryItem
 *
 * @author Oscar Wei
 * @since 2015/3/28
 * <p>
 * H i s t o r y
 * <p>
 * 2015/3/28 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.orm.hibernate.domain;

import tw.com.oscar.orm.hibernate.domain.enums.Priority;
import tw.com.oscar.orm.hibernate.domain.enums.Status;

import javax.persistence.*;

/**
 * <strong>Description:</strong><br>
 * This function include: - TODO <br>
 *
 * @author Oscar Wei
 * @version v1, 2015/3/28
 * @since 2015/3/28
 */
@Entity
@Table(name = "STORY_ITEM")
public class StoryItem extends BaseEntity {

    private String name;
    private Priority priority = Priority.NORMAL;
    private Status status = Status.TO_DO;

    private Story story;

    public StoryItem() {
    }

    public StoryItem(String name) {
        this.name = name;
    }

    @Column(name = "NAME", nullable = false, length = 20)
//    @NaturalId(mutable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "PRIORITY", nullable = false)
    @Enumerated
    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Column(name = "STATUS")
    @Enumerated
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PID_STORY", nullable = false)
    @org.hibernate.annotations.ForeignKey(name = "FK_STORYITEM_STORY")
    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StoryItem storyItem = (StoryItem) o;

        if (getName() != null ? !getName().equals(storyItem.getName()) : storyItem.getName() != null) return false;
        if (getPriority() != storyItem.getPriority()) return false;
        return getStatus() == storyItem.getStatus();

    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getPriority() != null ? getPriority().hashCode() : 0);
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "StoryItem{" +
                "status=" + status +
                ", priority=" + priority +
                ", name='" + name + '\'' +
                '}';
    }
}
