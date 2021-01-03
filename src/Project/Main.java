package Project;


public class Main {

    public static void main(String[] args) {//
        String[] jobRequirements = {"Java", "Python"}; //
        AlumniSystem.mentorCheck = "Java";
        AlumniSystem.setJobRequirements(jobRequirements);
        AlumniSystem.registerNewGrad("Example", "One", new String[] {"Java"});
        AlumniSystem.registerNewAlly("Example", "Two", new String[]{"Python"});
        AlumniSystem.registerNewMentor("Example", "Three",new String[]{"Web"});
        while (!AlumniSystem.loggedIn){
            AlumniSystem.sheCodesLogin();
        }
    }
}
