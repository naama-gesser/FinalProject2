package Project;

import java.util.*;

public class AlumniSystem {
    private static User user;
    static HashMap<String, User> hmap = new HashMap<>();
    static boolean loggedIn;
    private static final Set<String> jobRequirements = new HashSet<>();
    public static String mentorCheck;
    static Scanner input = new Scanner(System.in);

    public static Alumni registerNewGrad(String firstName, String lastName, String[] courses) {
        Alumni alumni = new Alumni(firstName, lastName, courses);
        hmap.put(alumni.getId(), alumni);
        System.out.println("Alumni Id: " + alumni.getId());
        System.out.print("Temporary Password: ");
        System.out.print(alumni.getOtp());
        System.out.println();
        return alumni;
    }
    public static Mentor registerNewMentor(String firstName, String lastName, String[] courses) {
        Mentor mentor = new Mentor(firstName, lastName, courses);
        hmap.put(mentor.getId(), mentor);
        System.out.println("Alumni Id: " + mentor.getId());
        System.out.print("Temporary Password: ");
        System.out.print(mentor.getOtp());
        System.out.println();
        return mentor;
      }
    public static Ally registerNewAlly(String firstName, String lastName, String[] courses) {
        Ally ally = new Ally(firstName, lastName, courses);
        hmap.put(ally.getId(), ally);
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

        while (!(hmap.containsKey(id))) {
            System.out.println("Alumni not found, please try again");
            id = input.next();
        }
        user = hmap.get(id);
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

        //first login- must c0
        // hange password
        if (user.getPassword().equals(user.getOtp())) {
            user.setNewPassword();
        }

        while (loggedIn) {
            int choice;

            user.printDetails();

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
                    user.setNewPassword();
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
                    user.setJobStatus();
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

    // shouldOfferJob - returns true if the resume fits the requirements and the alumni is open for job offers, re-evaluates after details update
    public static boolean shouldOfferJob(Set<String> jobRequirements) {
        return user.getCourseList().containsAll(jobRequirements) && user.isOpenForJobOffer();
    }


    //allows changing the job requirements from main for use in shouldOfferJob()
    public static void setJobRequirements(String[] jobRequirements) {
        Collections.addAll(AlumniSystem.jobRequirements, jobRequirements);
    }



}

