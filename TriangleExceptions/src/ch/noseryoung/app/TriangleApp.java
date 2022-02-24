package ch.noseryoung.app;
import ch.noseryoung.exceptions.*;
import java.time.LocalDate;
import java.util.Scanner;
public class TriangleApp {
    private final String company;
    private final String handler;
    private boolean isRunning;
    private String sideAInput;
    private String sideBInput;
    private String sideCInput;
    private double sideA;
    private double sideB;
    private double sideC;
    private String code;
    public TriangleApp(String company, String handler) {
        this.company = company;
        this.handler = handler;
        this.isRunning = true;
    }
    public void start() {
        printHeader();
        while (isRunning) {
            System.out.println("\nTEST CASES TRIANGLE\n");
            promptSide("A");
            promptSide("B");
            promptSide("C");
            try {
                validateInput();
                code = determineTriangleType();
            } catch (TriangleException e) {
                code = e.getMessage();
            } finally {
                printResult();
                promptAction();
            }
        }
    }
    private void printHeader() {
        System.out.println("******************************");
        System.out.println("      TRIANGLE EVALUATOR      ");
        System.out.println("******************************");
        System.out.println("COMPANY: " + this.company + ", " + LocalDate.now().getYear());
        System.out.println("HANDLER: " + this.handler);
        System.out.println("******************************");
    }
    private void printResult() {
        System.out.println("-------------------------");
        System.out.println("Code:        " + code);
        System.out.println("-------------------------");
    }
    private void promptSide(String side) {
        Scanner sideInput = new Scanner(System.in);
        switch (side) {
            case "C":
                try {
                    System.out.print("Hyptoneuse " + side + ":     ");
                    sideCInput = sideInput.next();
                } catch (Exception e) {
                    sideCInput = sideInput.next();
                }
                break;
            case "A":
                try {
                    System.out.print("Cathetus " + side + ":     ");
                    sideAInput = sideInput.next();
                } catch (Exception e) {
                    sideCInput = sideInput.next();
                }
                break;
            case "B":
                try {
                    System.out.print("Cathetus " + side + ":     ");
                    sideBInput = sideInput.next();
                } catch (Exception e) {
                    sideCInput = sideInput.next();
                }
                break;
        }
    }
    private void promptAction() {
        String action;
        Scanner answer = new Scanner(System.in);
        System.out.println("<q> QUIT");
        System.out.println("<c> CONTINUE");
        try {
            action = answer.nextLine();
            {
                if (action.equals("c") || action.equals("C")) {
                    isRunning = true;
                } else if (action.equals("q") || action.equals("Q")) {
                    isRunning = false;
                } else {
                    promptAction();
                }
            }
        } catch (java.util.InputMismatchException e) {
            answer.nextLine();
        }
    }
    private void validateInput() throws TriangleException {
        try {
            sideA = Double.parseDouble(sideAInput);
            sideB = Double.parseDouble(sideBInput);
            sideC = Double.parseDouble(sideCInput);
        } catch (NumberFormatException nfe) {
            throw new IllegalTriangleSideException();
        }
        if (sideA == 0 || sideB == 0 || sideC == 0) {
            throw new ZeroTriangleSideException();
        }
        if (sideA + sideB == sideC) {
            throw new TriangleIsLineException();
        }
        if (sideA < 0 || sideB < 0 || sideC < 0) {
            throw new NegativeTriangleSideException();
        }
        if (sideA + sideB < sideC) {
            throw new ImpossibleTriangleException();
        }
    }

    private String determineTriangleType() {
        if (sideA == sideB && sideB == sideC && sideC == sideA) {
            code = "TRI66TF";
        } else if (sideA == sideB || sideB == sideC || sideC == sideA) {
            code = "TRI84TF";
        } else if ((sideA * sideA) + (sideB * sideB) == sideC * sideC) {
            code = "TRI72TF";
        } else {
            code = "TRI60TF";
        }
        return code;
    }
}
