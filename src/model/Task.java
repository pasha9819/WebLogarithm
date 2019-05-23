package model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Task {
    private double base;
    private double argument;
    private double result;

    public Task() {
    }

    public Task(double base, double argument) {
        this.base = base;
        this.argument = argument;
    }

    public double getBase() {
        return base;
    }

    public void setBase(double base) {
        this.base = base;
    }

    public double getArgument() {
        return argument;
    }

    public void setArgument(double argument) {
        this.argument = argument;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }
}
