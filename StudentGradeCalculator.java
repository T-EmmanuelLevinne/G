public class StudentGradeCalculator {

// Naanani sa vscode sir hehe
    
    public static void main(String[] args) {
        try {
            String fileName = "grades.txt";
            java.io.File file = new java.io.File(fileName);
            
            // Basta diri napart Mo check if ang file mag exists
            if (!file.exists()) {
                System.out.println("File '" + fileName + "' not found. Creating sample file...");
                createSampleFile(fileName);
                System.out.println("Sample file created successfully!");
            }
            
            // Diri mag calculate ang grado ug mu print chu chu
            double averageGrade = calculateAverageGrade(fileName);
            System.out.println("\nStudent Grades:");
            printStudentGrades(fileName);
            System.out.printf("Average Grade: %.2f\n", averageGrade);
            
        } catch (Exception e) {
            System.out.println("Error: " + e);
            e.printStackTrace();
        }
    }
    
    /**
     * Maoni mo buhat ug katong grade.txt sir
     */
    public static void createSampleFile(String fileName) throws java.io.IOException {
        java.io.PrintWriter writer = new java.io.PrintWriter(new java.io.FileWriter(fileName));
        writer.println("Alice 85");
        writer.println("Bob 90");
        writer.println("Charlie 75");
        writer.close();
    }
    
    /**
     * mo calculate ni ug average grade
     */
    public static double calculateAverageGrade(String fileName) throws java.io.IOException {
        java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(fileName));
        String line;
        double sum = 0;
        int count = 0;
        
        while ((line = reader.readLine()) != null) {
            // Skip ni ug empty lines
            if (line.trim().isEmpty()) {
                continue;
            }
            
            // basta mo split ang line into name and grade
            String[] parts = line.split("\\s+");
            
            // mo skip alines ni na dont have at least two parts or if ang first part kay kanang Average
            if (parts.length < 2 || parts[0].equals("Average")) {
                continue;
            }
            
            // extract ang grado sa (last part)
            try {
                double grade = Double.parseDouble(parts[parts.length - 1]);
                sum += grade;
                count++;
            } catch (NumberFormatException e) {
                // Skip lines where the grade isn't a valid number
                System.out.println("Warning: Could not parse grade in line: " + line);
            }
        }
        
        reader.close();
        
        if (count == 0) {
            return 0.0;
        }
        
        return sum / count;
    }
    
    /**
     * Prints each student's name and grade
     */
    public static void printStudentGrades(String fileName) throws java.io.IOException {
        java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(fileName));
        String line;
        
        while ((line = reader.readLine()) != null) {
            if (line.trim().isEmpty()) {
                continue;
            }
            
            // tungaon ang line into parts (name and grade) basta kana
            String[] parts = line.split("\\s+");
            
            // mo skip alines ni na dont have at least two parts or if ang first part kay kanang Average
            if (parts.length < 2 || parts[0].equals("Average")) {
                continue;
            }
            
            // Diri na mo extract ug names (all parts except sa last)
            StringBuilder name = new StringBuilder();
            for (int i = 0; i < parts.length - 1; i++) {
                name.append(parts[i]);
                if (i < parts.length - 2) {
                    name.append(" ");
                }
            }
            
            // para mo extract po ug grado (last part)
            String grade = parts[parts.length - 1];
            
            // Now diri na mo print and student name ug ang iyahang grado po
            System.out.println(name + ": " + grade);
        }
        
        reader.close();
    }
}
