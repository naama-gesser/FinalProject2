package Project;

import java.util.ArrayList;
import java.util.Collections;


public class Alumni extends User{
    protected ArrayList<String> resume;


    public Alumni(String firstName, String lastName, String[] firstCourse){
        super(firstName.trim(),lastName.trim());
        resume = new ArrayList<>();
        Collections.addAll(resume, firstCourse);

    }

    @Override
    public ArrayList<String> getCourseList() {
        return resume;
    }

    @Override
    public void setCourseList(String newCourse) {
        resume.add(newCourse);
    }

    @Override
    public void printDetails(){
        System.out.println("First name: " + firstName + System.lineSeparator() +
                "Last Name: " + lastName + System.lineSeparator() +
                "Resume: " + resume + System.lineSeparator() +
                "LinkedIn Page: " + linkedinPage + System.lineSeparator() +
                "Job search status: " + jobSearchStatus + System.lineSeparator());
    }

    @Override
    public String toString() {
        return "Alumni{" +
                "resume=" + resume +
                "} " + super.toString();
    }
}



