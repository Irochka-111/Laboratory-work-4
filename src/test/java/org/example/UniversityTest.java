package org.example;

import org.example.controller.*;
import org.example.model.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.File;
import java.util.Arrays;

public class UniversityTest {
    @Test
    public void testJsonSerializationDeserialization() {
        // Create oldUniversity
        Human rector = new HumanCreator("Олександр", "Азюковський", "Олександрович", Sex.MALE).create();
        Human dean = new HumanCreator("Михайло", "Алексєєв", "Олександрович", Sex.MALE).create();
        Human curator = new HumanCreator("Андрій", "Мартиненко", "Андрійович", Sex.MALE).create();
        Human specialtyHead = new HumanCreator("Тімур", "Желдак", "Анатолійович", Sex.MALE).create();

        // Create students
        Student student1 = new StudentCreator("Ірина", "Чепурна", "Олександрівна", Sex.FEMALE, "ST001").create();
        Student student2 = new StudentCreator("Генадій", "Носов", "Петрович", Sex.MALE, "ST002").create();

        // Create groups
        Group group1 = new GroupCreator("124-21-2", curator, Arrays.asList(student1, student2)).create();

        // Create specialty
        Specialty specialty = new SpecialtyCreator("Системний аналіз", specialtyHead).create();

        // Create faculty
        Faculty faculty = new FacultyCreator("Інформаційні технології", dean,
                Arrays.asList(specialty),
                Arrays.asList(group1)).create();

        // Create university
        University oldUniversity = new UniversityCreator("Дніпровська політехніка", rector,
                Arrays.asList(faculty)).create();

        // Create test file path
        String testFilePath = "test_university.json";

        // Initialize JsonManager
        JsonManager jsonManager = new JsonManager();

        // Write university to file
        jsonManager.writeToFile(oldUniversity, testFilePath);

        // Read university from file
        University newUniversity = jsonManager.readFromFile(testFilePath);

        // Compare universities
        assertTrue(oldUniversity.equals(newUniversity));

        // Clean up test file
        new File(testFilePath).delete();
    }
}