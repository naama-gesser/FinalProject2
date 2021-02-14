package Project;

import java.util.ArrayList;
import java.util.Collections;


public class Ally extends User implements Mentoring{

    private ArrayList<String> courses;

    public Ally(String firstName, String lastName, String[] mentorCourses){
        super(firstName.trim(),lastName.trim());
        courses = new ArrayList<>();
        Collections.addAll(courses, mentorCourses);
    }

    public boolean canMentor(String course){
        return courses.contains(course);

    }

    @Override
    public ArrayList<String> getCourseList() {
        return courses;
    }

    @Override
    public void setCourseList(String newCourse) {
        courses.add(newCourse);
    }


    @Override
    public String toString() {
        return "Ally{" +
                "courses=" + courses +
                "} " + super.toString();
    }
}
