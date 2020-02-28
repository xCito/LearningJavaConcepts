import java.util.List;
import java.util.ArrayList;

/*
    Example of when the generics wildcard comes in handy.


    Diagram of the class hierarchy, where Employee is the base class
    and the other classes extend from Employee. 

                    **************
                    *  Employee  *
                  / ************** \ 
                 /                  \
        **************         ***************
        *   Staff    *         *  Developer  *
        **************         ***************
              |
        **************
        *   Janitor  *
        **************
 */



public class LearningGenericsPartOne {
    public static void main(String[] args) {
        List<String> report = new ArrayList<>();

        Company google = new Company();
        google.staff.add( new Janitor("Jim") );
        google.staff.add( new Security("Joe") );
        google.staff.add( new Staff("Jake", false) );

        google.devs.add( new Developer("Rob") );
        google.devs.add( new Developer("Bob") );
        google.devs.add( new Developer("Tod") );
    
        google.generalEmp.add( new Employee("NewGuy1") );
        google.generalEmp.add( new Employee("NewGuy2") );
        google.generalEmp.add( new Employee("NewGuy3") );


        // Use case of Generics Wildcard
        addToEmployeeReport(google.devs, report);
        addToEmployeeReport(google.staff, report);
        addToEmployeeReport(google.generalEmp, report);

        displayReport(report);
    }

    // Take any List<T> where T is any object that extends from Employee.
    public static void addToEmployeeReport(List<? extends Employee> workers, List<String> report) {
        for(Employee e: workers) {
            String str = String.format("Name: %-10s Id: %3d", e.name, e.id);
            report.add(str);
        }
    } 

    // To print out what has been collected.
    public static void displayReport(List<String> report) {
        report.forEach(System.out::println);
    }

}

/*
    A company that likes to have their employees
    in specific groups for meeting reasons.
*/
class Company {
    List<Staff> staff;
    List<Developer> devs;
    List<Employee> generalEmp;

    public Company() {
        staff = new ArrayList<>();
        devs = new ArrayList<>();
        generalEmp = new ArrayList<>();
    }

    // To have a staff only meeting
    public void startStaffMeeting() {
        staff.forEach( s -> s.sayHere() );
    }

    // To have a developer only scrum meeting
    public void startDeveloperScrum() {
        devs.forEach( d -> d.sayHere() );
    }

    // To have a meeting new/general employees
    public void startOnBoarding() {
        generalEmp.forEach( g -> g.sayHere() );
    }
}

// This is the BASE class
class Employee {
    String name;
    int id;
    static int idIncrement = 0;

    public Employee(String n) {
        name = n;
        id = idIncrement++;
    }

    public void sayHere() {
        System.out.println(name + " (#"+id+") is Here!");
    }
}

//  --------- Sub Employee objects below -------
class Developer extends Employee {
    public Developer(String n) {
        super(n);
    }
}
class Staff extends Employee {
    boolean hasUniquePass;

    public Staff(String n, boolean h) {
        super(n);
        hasUniquePass = h;
    }
}
class Security extends Staff {
    public Security(String n) {
        super(n, true);
    }
}
class Janitor extends Staff {
    public Janitor(String n) {
        super(n, true);
    }
}