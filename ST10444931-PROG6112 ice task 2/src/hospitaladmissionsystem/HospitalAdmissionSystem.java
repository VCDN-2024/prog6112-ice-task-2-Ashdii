package hospitaladmissionsystem;

import java.util.ArrayList;
import java.util.Scanner;

// Abstract class for Patient
abstract class Patient {
    String name;
    int age;
    String gender;

    public Patient(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public abstract String checkEligibility();
}

// Subclass for Female patients
class FemalePatient extends Patient {

    public FemalePatient(String name, int age) {
        super(name, age, "Female");
    }

    @Override
    public String checkEligibility() {
        if (this.age < 18) {
            return "Not eligible for treatment at the emergency room. Please go to Durban (DBN) Hospital.";
        } else {
            return "Eligible for treatment at the emergency room.";
        }
    }
}

// Subclass for Male patients
class MalePatient extends Patient {
    boolean hasChronicDisorder;

    public MalePatient(String name, int age, boolean hasChronicDisorder) {
        super(name, age, "Male");
        this.hasChronicDisorder = hasChronicDisorder;
    }

    @Override
    public String checkEligibility() {
        if (this.age > 18 && hasChronicDisorder) {
            return "Not eligible for treatment at the emergency room. Please go to Johannesburg (JHB) Hospital for specialized care.";
        } else if (this.age > 18) {
            return "Eligible for treatment at the emergency room.";
        } else {
            return "Not eligible for treatment at the emergency room. Please go to Durban (DBN) Hospital.";
        }
    }
}

public class HospitalAdmissionSystem {

    
    private static ArrayList<Patient> patients = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Sample data
        patients.add(new FemalePatient("Samantha", 16));
        patients.add(new MalePatient("Johnny", 19, false));
        patients.add(new MalePatient("Sam", 18, false));
        patients.add(new MalePatient("Ricky", 20, false));
        patients.add(new MalePatient("Brock", 16, false));
        patients.add(new FemalePatient("Julia", 15));
        patients.add(new FemalePatient("Tricksi", 20));
        patients.add(new FemalePatient("Stephanie", 21));

        // Login section
        System.out.print("Enter Username: ");
        String username = sc.nextLine();
        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        if (username.equals("Admin") && password.equals("St@77")) {
            System.out.println("Login successful!");

            // Search patient by name
            System.out.print("Enter patient name to search: ");
            String searchName = sc.nextLine();
            boolean found = false;

            for (Patient patient : patients) {
                if (patient.name.equalsIgnoreCase(searchName)) {
                    found = true;
                    System.out.println("Patient found: " + patient.name);
                    System.out.println("Eligibility: " + patient.checkEligibility());
                    break;
                }
            }

            if (!found) {
                System.out.println("Patient not found.");
            }

            // Display all patients and their eligibility
            System.out.println("\n--- Patient List ---");
            for (Patient patient : patients) {
                System.out.println("Name: " + patient.name + ", Age: " + patient.age + ", Gender: " + patient.gender);
                System.out.println("Eligibility: " + patient.checkEligibility());
                System.out.println("-------------------------");
            }

        } else {
            System.out.println("Invalid login credentials.");
        }
        sc.close();
    }
}
