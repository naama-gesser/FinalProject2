package Project;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public abstract class User {
    protected String firstName;
    protected String lastName;
    protected String id;
    private String otp;
    private String password;
    protected String linkedinPage;
    protected String jobSearchStatus;
    private boolean openForJobOffer;
    Scanner input = new Scanner(System.in); //ok to put scanner in this class? for the methods that used to be in AlumniSystem.

    //Constructors


    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        id = firstName + "." + lastName + ".she-codes";
        if (id.contains(" ")) {
            id = id.substring(0, id.indexOf(" ")) + id.substring(id.indexOf(" ")+1);
        }
        otp = generateOtp();
        password = otp;
        linkedinPage = "";
        jobSearchStatus = "";
    }

    //Getter and Setters


    public String getFirstName() {
        return firstName;
    }


    public String getId() {
        return id;
    }


    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void setLinkedinPage(String linkedinPage) {
        this.linkedinPage = linkedinPage;
    }

    public void setJobSearchStatus(String jobSearchStatus) {
        this.jobSearchStatus = jobSearchStatus;
    }

    public boolean isOpenForJobOffer() {
        return openForJobOffer;
    }

    public void setOpenForJobOffer(boolean openForJobOffer) {
        this.openForJobOffer = openForJobOffer;
    }

    //creates random temporary password on registration
    public String generateOtp() {
        String otp;
        char[] otpArr = new char[8];
        String chars = "abcdefghijklmnopqrstuvwxyz";
        chars = chars + chars.toUpperCase() + "0123456789";
        int l = chars.length();

        for (int i = 0; i < otpArr.length; i++) {
            Random rnd = new Random();
            otpArr[i] = chars.charAt(rnd.nextInt(l));
        }
        otp = String.valueOf(otpArr);
        return otp;
    }

    public void printDetails() {
        System.out.println("First name: " + firstName + System.lineSeparator() +
                "Last Name: " + lastName + System.lineSeparator() +
                "LinkedIn Page: " + linkedinPage + System.lineSeparator() +
                "Job search status: " + jobSearchStatus + System.lineSeparator());
    }


    public abstract ArrayList<String> getCourseList();

    public abstract void setCourseList(String newCourse);

    //checks password is comprised of 8 alphanumerical character
    public boolean checkPassword(String password) {
        if (password.equals(getPassword()) || password.equals(getOtp())) {
            System.out.println("can't use current or temporary password");
            return false;
        }
        int alphanumerical = 0;
        char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        Arrays.sort(chars);
        for (char p : password.toCharArray()) {             //counts the number of characters that are contained in chars (=are alphanumerical)
            if ((p >= 48 && p <= 57) || (p >= 65 && p <= 90) || (p >= 97 && p<= 122)) {
                alphanumerical++;
            }
        }
        if (alphanumerical == 8) {//if all 8 characters are alphanumerical
            return true;
        } else {
            System.out.println("Please make sure password is comprised of 8 alphanumerical characters");
            return false;
        }
    }


    //update password (after checking it)
    public void setNewPassword() {
        Scanner input = new Scanner(System.in);
        String newPassword;
        System.out.println("Please enter new password:");
        newPassword = input.next();
        while (!(checkPassword(newPassword))) {
            newPassword = input.next();
        }
        setPassword(newPassword);
        System.out.println("Password updated \n");
    }

    //lets user choose job search option and updates (boolean) whether she's open to job offers
    public void setJobStatus() {
        String a = "not looking for a change", b = "not looking but open for suggestions", c = "looking for a new challenge";
        System.out.println("Enter the number of the option most fitting your job status" + System.lineSeparator() +
                "1." + a + System.lineSeparator() +
                "2."+ b + System.lineSeparator() +
                "3."+ c);
        int choose = input.nextInt();
        switch(choose){
            case 1:
                setJobSearchStatus(a);
                System.out.println("your job status was set to: " + a);
                setOpenForJobOffer(false);
                break;
            case 2:
                setJobSearchStatus(b);
                System.out.println("your job status was set to: " + b);
                setOpenForJobOffer(true);
                break;
            case 3:
                setJobSearchStatus(c);
                System.out.println("Job status was set to: " + c);
                setOpenForJobOffer(true);
                break;
            default:
                System.out.println("Job status was not updated");
        }
    }
    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", id='" + id + '\'' +
                ", otp=" + otp +
                ", password=" + password +
                ", linkedinPage='" + linkedinPage + '\'' +
                ", jobSearchStatus='" + jobSearchStatus + '\'' +
                ", openForJobOffer=" + openForJobOffer +
                '}';
    }
}
