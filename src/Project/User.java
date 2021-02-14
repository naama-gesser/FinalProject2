package Project;


import java.util.ArrayList;
import java.util.Random;


public abstract class User {
    protected String firstName;
    protected String lastName;
    protected String id;
    private String otp;
    private String password;
    protected String linkedinPage;
    protected String jobSearchStatus;
    private boolean openForJobOffer;


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




    public abstract ArrayList<String> getCourseList();

    public abstract void setCourseList(String newCourse);




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
