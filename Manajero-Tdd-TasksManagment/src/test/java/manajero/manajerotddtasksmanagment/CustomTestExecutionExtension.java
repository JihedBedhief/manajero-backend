package manajero.manajerotddtasksmanagment;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class CustomTestExecutionExtension implements AfterEachCallback {

    @Override
    public void afterEach(ExtensionContext context) {
        String testName = context.getDisplayName();
        boolean passed = context.getExecutionException().isEmpty();
        System.out.println("Test " + testName + " " + (passed ? "PASSED" : "FAILED"));
    }
}
