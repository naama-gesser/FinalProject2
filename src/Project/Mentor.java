package Project;

import java.util.ArrayList;
import java.util.Collections;


public class Mentor extends Alumni implements Mentoring{
    ArrayList<String> mentorResume;



    public Mentor(String firstName, String lastName, String[] courses) {
        super(firstName.trim(), lastName.trim(), courses);
        Collections.addAll(resume, courses);
        mentorResume = new ArrayList<>();
        mentorResume.addAll(resume);
    }

    @Override
    public boolean canMentor(String course) {

        return mentorResume.contains(course);

    }

    @Override
    public ArrayList<String> getCourseList() {
        return mentorResume;
    }

    @Override
    public void setCourseList(String newCourse) {
        mentorResume.add(newCourse);
    }

    public void printDetails(){
        System.out.println( "First name: " + firstName + System.lineSeparator() +
                "Last Name: " + lastName + System.lineSeparator() +
                "Resume: " + resume + System.lineSeparator() +
                "Courses you can mentor: " + mentorResume + System.lineSeparator() +
                "LinkedIn Page: " + linkedinPage + System.lineSeparator() +
                "Job search status: " + jobSearchStatus + System.lineSeparator());

    }

    @Override
    public String toString() {
        return "Mentor{" +
                "mentorResume=" + mentorResume +
                "} " + super.toString();
    }
}
