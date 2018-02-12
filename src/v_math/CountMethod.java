package v_math;

import java.math.BigDecimal;

public class CountMethod {
    private double a, b;
    private double accuracy;
    private double step;
    private Function func;

    public CountMethod(Function func, double a, double b, double accuracy, int stepsNumber) {
        this.a = a;
        this.b = b;
        this.func = func;
        step = Math.abs(a - b) / stepsNumber;
        this.accuracy = accuracy;
    }

    private double getArea(Double height, double step){
        return height * step;
    }

    public int CountTrueSteps(MethodType methodType, int stepsNumber){
        double delta = Math.abs(Rectangles(methodType, stepsNumber * 2) - Rectangles(methodType, stepsNumber));
        if (delta > accuracy/2)
            return CountTrueSteps(methodType, stepsNumber * 2);
        System.out.print("\ndelta = " + delta*2);
        return stepsNumber;
    }

    public double Rectangles(MethodType methodType, int stepsNumber){
        double sum = 0;
        double step = Math.abs(a - b) / stepsNumber;
        boolean signFlag = false;
        if (a == b)
            return 0;
        else{
            if (a > b){
                double buf = a;
                a = b;
                b = buf;
                signFlag = true;
            }
            for (double x = a; x <= b; x += step){
                switch (methodType){
                    case LEFT: sum += getArea(func.getValue(x), step);
                            break;
                    case RIGHT: if (x != b)
                                 {sum += getArea(func.getValue(x + step), step);}
                            break;
                    case MIDDLE: if (x != b)
                    {sum += getArea(func.getValue(x + step/2), step);}
                        break;
                }
                }
            }
            if (signFlag){
                sum = -sum;
                double buf = a;
                a = b;
                b = buf;
            }

            return sum;
        }

    }

