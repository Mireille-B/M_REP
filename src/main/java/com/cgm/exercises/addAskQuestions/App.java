package com.cgm.exercises.addAskQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App 
{
    private List<String> defaultValue; 
    private Map<String, List<String>> hm;
    
    public App()
    {
        defaultValue = new ArrayList<>();
        defaultValue.add("The answer to life, universe and everything is 42");
        hm = new HashMap<>();
    }
    public App(Map<String, List<String>> defaultHashMap)
    {
    	defaultValue = new ArrayList<>();
    	defaultValue.add("The answer to life, universe and everything is 42");
    	hm = defaultHashMap;
    }
    
    public String getInput() {
        Scanner sc = new Scanner(System.in);
        String res = sc.nextLine();
        return res;
    }
    
    public int getIntegerInput() throws InputMismatchException{
        Scanner sc = new Scanner(System.in);
        int res = sc.nextInt();
        return res;
    }
    
    
    public void Execute() 
    {
        int option = 0;
        try
        {
        	while (option != 3)
        	{
        		System.out.println("Select '1' to ask a question or '2' to add question and its answer or 3 to Exit");
        		option = getIntegerInput();
        		if (option == 1)
        		{
        			askQuestion();
        		}
        		else if (option == 2)
        		{
        			addQuestion();
        			
        		}
        		else if (option == 3)
        		{
        			System.out.println("Thank you for using this program");
        			
        		}
        		else
        		{
        			System.out.println("Option is not valid");
        		}
        	}
        }
        catch(InputMismatchException e)
        {
        	System.err.println("Invalid Input");
        }
    }


    public void askQuestion()
    {
    	System.out.println("Ask A question");
        String result = getInput();
        hm.getOrDefault(result, defaultValue).forEach(val -> System.out.print("\u2022" + val + "\n"));
    }

    public void addQuestion()
    {
    	System.out.println(
                "Add A question and its answer(the format should be : <question>? \"<answer1>\" \"<answer2>\" ...)");
        String result = getInput();
        Pattern p = Pattern.compile("[^\\?]{1,255}[?]( \\\"[^\\\"]{1,255}\\\")+");
        Matcher m = p.matcher(result);
        boolean b = m.matches();

        if (b)
        {
            String[] sp = result.split("\\? ");
            hm.put(sp[0] + "?", Arrays.asList(sp[1].substring(1, sp[1].length() - 1).split("\" \"")));

        }
        else
        {
            System.out.println("Not a valid input; The following rules must be considered");
            System.out.println("The format should be : <question>? \"<answer1>\" \"<answer2>\"");
            System.out.println("The question is a string with max 255 chars and ending with ?");
            System.out.println("The answer is a string with max 255 chars encountered with \"\"");
        }
    }

    public static void main(String[] args)
    {
        App m = new App();
        m.Execute();
    }
}
