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
        uniqueConstraints = @UniqueConstraint(name = "UK_NAME", columnNames = { "NAME" }))
public class Story extends BaseEntity {

    private String name;

    private Set<StoryItem> storyItems;

    public Story() {
    }

    public Story(String name) {
        this.name = name;
    }

    @Column(name = "NAME", nullable = false, length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL) // mappedBy = "story"
    @JoinColumn(name = "PID_STORY",
            foreignKey = @ForeignKey(name = "FK_STORY_STORYITEM")) // uncomment when 1:N(B)
    @org.hibernate.annotations.ForeignKey(name = "FK_STORY_STORYITEM") // uncomment when 1:N(B)
    public Set<StoryItem> getStoryItems() {
        return storyItems;
    }

    public void setStoryItems(Set<StoryItem> storyItems) {
        this.storyItems = storyItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Story story = (Story) o;

        return !(name != null ? !name.equals(story.name) : story.name != null);

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
