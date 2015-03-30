/**
 * Title: Acer Internal Project
 * Copyright: (c) 2015, Acer Inc.
 * Name: Course
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
import java.util.HashSet;
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
@Table(name = "COURSE")
public class Course extends BaseEntity {

    private String name;

    private Set<Student> students = new HashSet<>();

    public Course() {
    }

    @Column(name = "NAME", nullable = false, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @org.hibernate.annotations.ForeignKey(name = "FK_ACCOUNT_ROLE")
//    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinTable(name = "COURSE_STUDENT",
            joinColumns = { @JoinColumn(name = "PID_COURSE") },
            inverseJoinColumns = { @JoinColumn(name = "PID_STUDENT") })
    public Set<Student> getStudents() {
        return students;
    }

    protected void setStudents(Set<Student> students) {
        this.students = students;
    }

    public void addStudent(Student student) {
        this.getStudents().add(student);
        student.getCourses().add(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        return !(name != null ? !name.equals(course.name) : course.name != null);

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
