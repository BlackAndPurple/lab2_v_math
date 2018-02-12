package v_math;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int u = 0;
        String ustr = null;
        do{
            System.out.print("Choose a function to integrate: \n 1) f = sin(x)\n 2) f = cos(x)\n 3) f = 2x\nOption: ");
            ustr = in.nextLine();
            try {
                u = Integer.parseInt(ustr);
            }catch(Exception e){}
        }while((u < 1) || (u > 3));
        double a, b, accuracy;
        Scanner in2 = new Scanner(System.in);
        System.out.println("\nLimits of integration: " );


        /*System.out.println("a = ");
        a = in2.nextDouble();
        System.out.print(" b = ");
        b = in2.nextDouble();*/

        String aStr;
        a = 0;
        b = 0;
        boolean flag = false;
        do{
            System.out.print("a = ");
            aStr = in2.nextLine();
            aStr = aStr.replace(",",".");
            try{
                a = Double.parseDouble(aStr);
                flag = true;
            }catch (Exception e){
                flag = false;
            }
        }while (!flag);
        flag = false;
        do{
            System.out.print("b = ");
            aStr = in2.nextLine();
            aStr = aStr.replace(",",".");
            try{
                b = Double.parseDouble(aStr);
                flag = true;
            }catch (Exception e){
                flag = false;
            }
        }while (!flag);

        do{
            System.out.print("\nInput accuracy: ");
            //accuracy = in2.nextDouble();
            Scanner in10 = new Scanner(System.in);
            String accStr = in10.nextLine();
            accStr = accStr.replace(",",".");
            try {
                accuracy = Double.parseDouble(accStr);
            }catch (Exception e){
                accuracy = -1;
            }

        } while (accuracy < 0);

        Function func = FunctionsKit::getSin; //set default function
        switch (u){
            case 1: func = FunctionsKit::getSin;
                    break;
            case 2: func = FunctionsKit::getCos;
                    break;
            case 3: func = FunctionsKit::get2x;
                    break;
        }
        CountMethod count = new CountMethod(func, a, b, accuracy, 1000);
        System.out.println("\nRESULTS");
       // System.out.print("Left rectangles method: " + count.leftRectangle());
        int stepsNumber = count.CountTrueSteps(MethodType.LEFT, 10);
        System.out.print("\nLeft rectangles method: " + count.Rectangles(MethodType.LEFT, stepsNumber));
        System.out.println("\nReal steps number: " + stepsNumber);

        stepsNumber = count.CountTrueSteps(MethodType.RIGHT, 10);
        System.out.print("\nRight rectangles method: " + count.Rectangles(MethodType.RIGHT, stepsNumber));
        System.out.println("\nReal steps number: " + stepsNumber);

        stepsNumber = count.CountTrueSteps(MethodType.MIDDLE, 10);
        System.out.print("\nMiddle rectangles method: " + count.Rectangles(MethodType.MIDDLE, stepsNumber));
        System.out.print("\nReal steps number: " + stepsNumber);

    }
}
