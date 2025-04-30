public class StudentGradeCalculator {

// NOTE THAT DAPAT IBUTANG NIMO NI SA VSCODE PARA MOGANA AND NAANAPOD EXPLANATION DIHA
    
    public static void main(String[] args) {
        try {
            String fileName = "grades.txt";
            java.io.File file = new java.io.File(fileName);
            
            // Check if the file exists
            if (!file.exists()) {
                System.out.println("File '" + fileName + "' not found. Creating sample file...");
                createSampleFile(fileName);
                System.out.println("Sample file created successfully!");
            }
            
            // Calculate and print grades
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
     * Creates a sample grades.txt file with test data
     */
    public static void createSampleFile(String fileName) throws java.io.IOException {
        java.io.PrintWriter writer = new java.io.PrintWriter(new java.io.FileWriter(fileName));
        writer.println("Alice 85");
        writer.println("Bob 90");
        writer.println("Charlie 75");
        writer.close();
    }
    
    /**
     * Calculates the average grade for the class
     */
    public static double calculateAverageGrade(String fileName) throws java.io.IOException {
        java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(fileName));
        String line;
        double sum = 0;
        int count = 0;
        
        while ((line = reader.readLine()) != null) {
            // Skip empty lines
            if (line.trim().isEmpty()) {
                continue;
            }
            
            // Split the line into name and grade
            String[] parts = line.split("\\s+");
            
            // Skip lines that don't have at least two parts or if the first part is "Average"
            if (parts.length < 2 || parts[0].equals("Average")) {
                continue;
            }
            
            // Extract the grade (last part)
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
            // Skip empty lines
            if (line.trim().isEmpty()) {
                continue;
            }
            
            // Split the line into parts (name and grade)
            String[] parts = line.split("\\s+");
            
            // Skip lines that don't have at least two parts or if the first part is "Average"
            if (parts.length < 2 || parts[0].equals("Average")) {
                continue;
            }
            
            // Extract the name (all parts except the last one)
            StringBuilder name = new StringBuilder();
            for (int i = 0; i < parts.length - 1; i++) {
                name.append(parts[i]);
                if (i < parts.length - 2) {
                    name.append(" ");
                }
            }
            
            // Extract the grade (last part)
            String grade = parts[parts.length - 1];
            
            // Print the student's name and grade
            System.out.println(name + ": " + grade);
        }
        
        reader.close();
    }
}
