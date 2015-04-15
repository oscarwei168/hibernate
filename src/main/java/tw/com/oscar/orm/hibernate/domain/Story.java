/**
 * Title: Acer Internal Project
 * Copyright: (c) 2015, Acer Inc.
 * Name: Story
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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import tw.com.oscar.orm.hibernate.domain.enums.Status;

import javax.persistence.*;
import java.util.Set;

/**
 * <strong>Description:</strong><br>
 * This function include: - TODO <br>
 *
 * @author Oscar Wei
 * @version v1, 2015/3/28
 * @since 2015/3/28
 */
@Entity
@Table(name = "STORY",
        uniqueConstraints = @UniqueConstraint(name = "UK_NAME", columnNames = {"NAME"}))
@Access(AccessType.PROPERTY)
//@Proxy
public class Story extends BaseEntity {

    private String name;
    private String description;
    private Status status = Status.NEW;

    private Set<StoryItem> storyItems;

    public Story() {
    }

    public Story(String name) {
        this.name = name;
    }

    public Story(String name, Status status) {
        this.name = name;
        this.status = status;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "story")
//    @JoinColumn(name = "PID_STORY",
//            foreignKey = @ForeignKey(name = "FK_STORY_STORYITEM")) // uncomment when 1:N(B)
//    @org.hibernate.annotations.ForeignKey(name = "FK_STORY_STORYITEM") // uncomment when 1:N(B)
//    @Fetch(FetchMode.SELECT) // by default
//    @Fetch(FetchMode.JOIN) // disable lazy-loading
//    @BatchSize(size = 5)
    @Fetch(FetchMode.SUBSELECT)
    public Set<StoryItem> getStoryItems() {
        return storyItems;
    }

    public void setStoryItems(Set<StoryItem> storyItems) {
        this.storyItems = storyItems;
    }

    @Column(name = "NAME", nullable = false, unique = true, length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "DESCRIPTION", length = 100)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "STATUS", nullable = false)
    @Enumerated
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Story story = (Story) o;

        if (getName() != null ? !getName().equals(story.getName()) : story.getName() != null) return false;
        return getStatus() == story.getStatus();

    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Story{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
