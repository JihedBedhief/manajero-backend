package manajero.manajerotddtasksmanagment.Controllers;

import manajero.manajerotddtasksmanagment.Services.OCRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/ocr")
@CrossOrigin(origins = "http://localhost:4200")
public class OCRController {

    private final OCRService ocrService;

    @Autowired
    public OCRController(OCRService ocrService) {
        this.ocrService = ocrService;
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file,
                             @RequestParam("testName") String testName) {
        try {
            // Save the uploaded file to a temporary location
            Path tempFilePath = Files.createTempFile(null, file.getOriginalFilename());
            file.transferTo(tempFilePath.toFile());

            // Extract text from the image
            String extractedText = ocrService.extractTextFromImage(tempFilePath.toFile());

            // Check if the specified test passed
            boolean isPassed = ocrService.isTestPassed(testName, extractedText);

            // Clean up the temporary file
            Files.deleteIfExists(tempFilePath);

            return isPassed ? "Test Passed" : "Test Not Passed or Not Found";

        } catch (IOException e) {
            return "Error uploading file: " + e.getMessage();
        }
    }
}
