package blackjack;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InputHandlerTest {
    @Test
    void getIntInput_rejectsNonIntegerThenAcceptsInteger() {
        InputStream sysInBackup = System.in;
        try {
            ByteArrayInputStream in = new ByteArrayInputStream("litery\n15\n".getBytes());
            System.setIn(in);

            InputHandler handler = new InputHandler();
            int result = handler.getIntInput("Enter number: ");

            assertEquals(15, result);
        } finally {
            System.setIn(sysInBackup);
        }
    }
}

