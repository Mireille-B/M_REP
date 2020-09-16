package com.cgm.exercises.addAskQuestions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.logging.Logger;

import org.junit.Rule;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.rules.ExpectedException;

public class AppTest
{
    App m;

    static final Logger logger = Logger.getLogger(AppTest.class.getName());

    @BeforeAll
    public static void beforeAllTests()
    {
        logger.info("Before all tests");
    }

    @AfterAll
    public static void afterAllTests()
    {
        logger.info("After all tests");
    }

    @BeforeEach
    public void beforeEachTest(TestInfo testInfo)
    {
        logger.info(() -> String.format("About to execute [%s]", testInfo.getDisplayName()));
    }

    @AfterEach
    public void afterEachTest(TestInfo testInfo)
    {
        logger.info(() -> String.format("Finished executing [%s]", testInfo.getDisplayName()));
    }

    @Test
    public void AskNonExistingQuestion()
    {
        String input = "What is Java?\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        App app = new App();
        app.askQuestion();
        String consoleOutput = "Ask A question" + System.getProperty("line.separator")
                + "\u2022The answer to life, universe and everything is 42\n";
        assertEquals(consoleOutput, out.toString());
    }

    @Test
    public void AskExistingQuestion()
    {

        String input = "What is Peters favorite food?\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

//	    	String[] args= {};
//	    	App.main(args);

        HashMap<String, List<String>> hm = new HashMap<>();
        List answer = Arrays.asList("Pizza", "Spaghetti", "Ice cream");
        hm.put("What is Peters favorite food?", answer);
        App app = new App(hm);
        app.askQuestion();

        String consoleOutput = "Ask A question" + System.getProperty("line.separator")
                + "\u2022Pizza\n\u2022Spaghetti\n\u2022Ice cream\n";
        assertEquals(consoleOutput, out.toString());
    }

    @Test
    public void addValidQuestion()
    {

        String input = "What is Peters favorite food? \"Pizza\" \"Spaghetti\" \"Ice cream\"\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        HashMap<String, List<String>> hm = new HashMap<>();
        App app = new App(hm);
        app.addQuestion();

        assertEquals(1, hm.size());
    }

    @Test
    public void addNotValidQuestionNoQuestionMark()
    {

        String input = "What is Peters favorite food \"Pizza\" \"Spaghetti\" \"Ice cream\"\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        HashMap<String, List<String>> hm = new HashMap<>();
        App app = new App(hm);
        app.addQuestion();

        String consoleOutput = "Add A question and its answer(the format should be : <question>? \"<answer1>\" \"<answer2>\" ...)"
                + System.getProperty("line.separator") + "Not a valid input; The following rules must be considered"
                + System.getProperty("line.separator")
                + "The format should be : <question>? \"<answer1>\" \"<answer2>\""
                + System.getProperty("line.separator") + "The question is a string with max 255 chars and ending with ?"
                + System.getProperty("line.separator")
                + "The answer is a string with max 255 chars encountered with \"\""
                + System.getProperty("line.separator");
        assertEquals(consoleOutput, out.toString());
        assertEquals(0, hm.size());
    }

    @Test
    public void addNotValidQuestionNoAnswer()
    {

        String input = "What is Peters favorite food? \n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        HashMap<String, List<String>> hm = new HashMap<>();
        App app = new App(hm);
        app.addQuestion();

        String consoleOutput = "Add A question and its answer(the format should be : <question>? \"<answer1>\" \"<answer2>\" ...)"
                + System.getProperty("line.separator") + "Not a valid input; The following rules must be considered"
                + System.getProperty("line.separator")
                + "The format should be : <question>? \"<answer1>\" \"<answer2>\""
                + System.getProperty("line.separator") + "The question is a string with max 255 chars and ending with ?"
                + System.getProperty("line.separator")
                + "The answer is a string with max 255 chars encountered with \"\""
                + System.getProperty("line.separator");
        assertEquals(consoleOutput, out.toString());
        assertEquals(0, hm.size());
    }

    @Test
    public void addNotValidQuestionNoValidAnswer()
    {

        String input = "What is Peters favorite food? Pizza\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        HashMap<String, List<String>> hm = new HashMap<>();
        App app = new App(hm);
        app.addQuestion();

        String consoleOutput = "Add A question and its answer(the format should be : <question>? \"<answer1>\" \"<answer2>\" ...)"
                + System.getProperty("line.separator") + "Not a valid input; The following rules must be considered"
                + System.getProperty("line.separator")
                + "The format should be : <question>? \"<answer1>\" \"<answer2>\""
                + System.getProperty("line.separator") + "The question is a string with max 255 chars and ending with ?"
                + System.getProperty("line.separator")
                + "The answer is a string with max 255 chars encountered with \"\""
                + System.getProperty("line.separator");
        assertEquals(consoleOutput, out.toString());
        assertEquals(0, hm.size());
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void throwsInputMismatchException()
    {
        exception.expect(InputMismatchException.class);
        exception.expectMessage("java.util.InputMismatchException");
        String input = "char\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        App inputOutput = new App();
        inputOutput.getIntegerInput();
    }

}
