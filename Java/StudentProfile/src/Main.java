public class Main {
    public static void main(String[] args) {

        Student studentA = new Student("Emmanuel", "Ngongo", 2019,
                3.5, "Information Technology");
        Student studentB = new Student("Manu", "BlackZ",
                2022, 3.5, "MoneyMaker");
        studentB.incrementExpectedGraduationYear();

        System.out.println(studentA.firstName + " " + studentA.lastName + " " + studentA.gpa + " " +
                studentA.declaredMajor + " " + studentA.expectedGraduationYear);
        System.out.println(studentB.firstName + " " + studentB.lastName + " " + studentB.gpa + " " +
                studentB.declaredMajor + " " + studentB.expectedGraduationYear);

    }
}