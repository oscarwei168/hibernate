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

//    private Story story;

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

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "PID_STORY", nullable = false)
//    @org.hibernate.annotations.ForeignKey(name = "FK_STORYITEM_STORY")
//    public Story getStory() {
//        return story;
//    }
//
//    public void setStory(Story story) {
//        this.story = story;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StoryItem storyItem = (StoryItem) o;

        return !(name != null ? !name.equals(storyItem.name) : storyItem.name != null);

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
