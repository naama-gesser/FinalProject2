package Project;

import java.util.*;

public class AlumniSystem {
    private static User user;
    static HashMap<String, User> usersList = new HashMap<>();
    static boolean loggedIn;
    private static final Set<String> jobRequirements = new HashSet<>();
    public static String mentorCheck;
    static Scanner input = new Scanner(System.in);


    public static Alumni registerNewGrad(String firstName, String lastName, String[] courses) {
        Alumni alumni = new Alumni(firstName, lastName, courses);
        usersList.put(alumni.getId(), alumni);
        System.out.println("Alumni Id: " + alumni.getId());
        System.out.print("Temporary Password: ");
        System.out.print(alumni.getOtp());
        System.out.println();
        return alumni;
    }
    public static Mentor registerNewMentor(String firstName, String lastName, String[] courses) {
        Mentor mentor = new Mentor(firstName, lastName, courses);
        usersList.put(mentor.getId(), mentor);
        System.out.println("Alumni Id: " + mentor.getId());
        System.out.print("Temporary Password: ");
        System.out.print(mentor.getOtp());
        System.out.println();
        return mentor;
      }
    public static Ally registerNewAlly(String firstName, String lastName, String[] courses) {
        Ally ally = new Ally(firstName, lastName, courses);
        usersList.put(ally.getId(), ally);
        System.out.println("Alumni Id: " + ally.getId());
        System.out.print("Temporary Password: ");
        System.out.print(ally.getOtp());
        System.out.println();
        return ally;
      }


    //  Login
    public static void sheCodesLogin() {
        System.out.println("\nWelcome to She-Codes Alumni System, please log in:");
        String id;
        String password;

        System.out.println("Alumni ID: ");
        id = input.next();

        while (!(usersList.containsKey(id))) {
            System.out.println("Alumni not found, please try again");
            id = input.next();
        }
        user = usersList.get(id);
        System.out.println("Password: ");
        password = input.next();

        while (!(password.equals(user.getPassword()) || password.equals(user.getOtp()))) {
            System.out.println("Wrong Password, please try again");
            password = input.next();
        }
        loggedIn = true;
        updateDetails();
    }

    // updateDetails (not in project instructions) after login, prints details + lets user update them
    public static void updateDetails() {
        System.out.println("Hello, " + user.getFirstName() + "!");

        //first login- must change password
        if (user.getPassword().equals(user.getOtp())) {
            setNewPassword();
        }

        while (loggedIn) {
            int choice;

            printDetails();

            if (shouldOfferJob(jobRequirements)) {
                System.out.println("You have a job offer! " + System.lineSeparator());
            }

            if (user instanceof Mentoring){
                if(((Mentoring)user).canMentor(mentorCheck)){
                    System.out.println("You can mentor the course " + mentorCheck + System.lineSeparator());
                } else {
                    System.out.println("You can't mentor the course " + mentorCheck + System.lineSeparator());
                }

            }

            System.out.println("Update details: " + System.lineSeparator() +
                    "1. Change Password " + System.lineSeparator() +
                    "2. Add more courses to resume " + System.lineSeparator() +
                    "3. Update LinkedIn page " + System.lineSeparator() +
                    "4. Update job search status " + System.lineSeparator() +
                    "0. Log out " + System.lineSeparator());


            choice = input.nextInt();
            switch (choice) {
                case 1:
                    setNewPassword();
                    break;
                case 2:
                    String newCourse;
                    System.out.println("Enter the name of the course you want to add to your resume");
                    newCourse = input.next();
                    newCourse = newCourse.substring(0, 1).toUpperCase() + newCourse.substring(1); //makes sure course name is capitalised
                    if (!user.getCourseList().contains(newCourse)) {
                        user.setCourseList(newCourse);
                        System.out.println("Added " + newCourse + " to your resume");
                    } else {
                        System.out.println(newCourse + " is already in your resume");
                    }
                    break;
                case 3:
                    System.out.println("Enter your linkedIn page");
                    String linkedIn = input.next();
                    while (!(linkedIn.contains("linkedin.com"))) {  //make sure it's a linkedin page
                        System.out.println("Please make sure you enter a valid LinkedIn page");
                        linkedIn = input.next();
                    }
                    user.setLinkedinPage(linkedIn);
                    System.out.println("LinkedIn page updated");
                    break;
                case 4:
                    setJobStatus();
                    break;
                case 0:
                    System.out.println("Logging out");
                    loggedIn = false;
                    break;
                default:
                    System.out.println("Please choose one of the options");
            }
        }
    }


    //Methods


    //checks password is comprised of 8 alphanumerical character
    public static boolean checkPassword(String password) { //TODO move this to AlumniSystem
        if (password.equals(user.getPassword()) || password.equals(user.getOtp())) {
            System.out.println("can't use current or temporary password");
            return false;
        }
        char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        Arrays.sort(chars);
        for (char p : password.toCharArray()) {
            //ASCII characters
            final int ZERO = 48;
            final int NINE = 57;
            final int A = 97;
            final int Z = 122;
            final int UPPER_A = 65;
            final int UPPER_Z = 90;

            if (!((p >= ZERO && p <= NINE) || (p >= A && p <= Z) || (p >= UPPER_A && p<= UPPER_Z))) {
                System.out.println("Please make sure password is comprised only of alphanumerical characters");
                return false;
            }
        }
        if (user.getPassword().length() ==8) {
            return true;
        } else {
            System.out.println("Please make sure password is comprised of 8 characters");
            return false;
        }
    }


    //update password (after checking it)
    public static void setNewPassword() {
        String newPassword;
        System.out.println("Please enter new password:");
        newPassword = input.next();
        while (!(checkPassword(newPassword))) {
            newPassword = input.next();
        }
        user.setPassword(newPassword);
        System.out.println("Password updated \n");
    }


    public static void printDetails() {
        System.out.println("First name: " + user.firstName + System.lineSeparator() +
                "Last Name: " + user.lastName + System.lineSeparator() +
                "LinkedIn Page: " + user.linkedinPage + System.lineSeparator() +
                "Job search status: " + user.jobSearchStatus + System.lineSeparator());
    }

    // shouldOfferJob - returns true if the resume fits the requirements and the alumni is open for job offers, re-evaluates after details update
    public static boolean shouldOfferJob(Set<String> jobRequirements) {
        return user.getCourseList().containsAll(jobRequirements) && user.isOpenForJobOffer();
    }


    //allows changing the job requirements from main for use in shouldOfferJob()
    public static void setJobRequirements(String[] jobRequirements) {
        Collections.addAll(AlumniSystem.jobRequirements, jobRequirements);
    }

    //lets user choose job search option and updates (boolean) whether she's open to job offers
    public static void setJobStatus() {

        System.out.println("Enter the number of the option most fitting your job status" + System.lineSeparator() +
                "1." + "not looking for a change" + System.lineSeparator() +
                "2."+ "not looking but open for suggestions" + System.lineSeparator() +
                "3."+ "looking for a new challenge");
        int choose = input.nextInt(); //TODO move this to AlumniSystem
        switch(choose){
            case 1:
                user.setJobSearchStatus("not looking for a change");
                System.out.println("your job status was set to: " + "not looking for a change");
                user.setOpenForJobOffer(false);
                break;
            case 2:
                user.setJobSearchStatus("not looking but open for suggestions");
                System.out.println("your job status was set to: " + "not looking but open for suggestions");
                user.setOpenForJobOffer(true);
                break;
            case 3:
                user.setJobSearchStatus("looking for a new challenge");
                System.out.println("Job status was set to: " + "looking for a new challenge");
                user.setOpenForJobOffer(true);
                break;
            default:
                System.out.println("Job status was not updated");
        }
    }

}

