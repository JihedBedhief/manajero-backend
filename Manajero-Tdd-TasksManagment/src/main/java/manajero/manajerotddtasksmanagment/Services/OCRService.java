package manajero.manajerotddtasksmanagment.Services;

import lombok.extern.slf4j.Slf4j;
import manajero.manajerotddtasksmanagment.Entities.Tests;
import manajero.manajerotddtasksmanagment.Repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

@Service
@Slf4j
public class OCRService {

    private final ITesseract tesseract;

    @Autowired
    TestRepository testRepository;

    public OCRService() {
        this.tesseract = new Tesseract();
        // Set the path to the Tesseract data folder
        this.tesseract.setDatapath("C:/Program Files/Tesseract-OCR/tessdata");
    }

    public String extractTextFromImage(File imageFile) {
        try {
            if (imageFile == null || !imageFile.exists()) {
                throw new IllegalArgumentException("Image file is invalid.");
            }
            log.info("ezedz"+tesseract.doOCR(imageFile));
            return tesseract.doOCR(imageFile);
        } catch (TesseractException e) {
            log.error("Error processing image: " + e.getMessage(), e);
            return "Error processing image: " + e.getMessage();
        }
    }

    public boolean isTestPassed(String testName, String extractedText) {
        Tests t = testRepository.findTestsByTitle(testName);
        String normalizedTestName = testName.trim().toLowerCase();
        String[] lines = extractedText.split("\n");

        for (String line : lines) {
            String normalizedLine = line.trim().toLowerCase();
            if (normalizedLine.contains("test " + normalizedTestName + "()")) {
                if (normalizedLine.contains("passed")) {
                    t.setStatus(true);
                    testRepository.save(t);
                    return true;
                } else if (normalizedLine.contains("failed")) {
                    return false;
                }
            }
        }
        return false;
    }
}
