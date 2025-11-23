//Deshawn Bryan
package Java_p;

import java.util.LinkedList;

public class TriageAnalyzer 
{
    public enum RiskScore 
    { 
    	LOW, MEDIUM, HIGH 
    }

    private LinkedList<Double> vitalsHistory = new LinkedList<>();
    private int maxHistorySize = 5;  // Last N visits to track

    public RiskScore computeRisk(double value) {
        if (value < 40) return RiskScore.LOW;
        else if (value < 70) return RiskScore.MEDIUM;
        else return RiskScore.HIGH;
    }

    public void addVitalsRecord(double value) {
        if (vitalsHistory.size() >= maxHistorySize) 
        {
            vitalsHistory.poll(); // Remove the oldest value if the list exceeds max size
        }
        vitalsHistory.add(value);
    }

    public double computeMovingAverage() {
        double sum = 0;
        for (double vital : vitalsHistory) {
            sum += vital;
        }
        return sum / vitalsHistory.size();
    }

    public boolean checkForDeviation(double currentValue) {
        double movingAverage = computeMovingAverage();
        double deviation = Math.abs(currentValue - movingAverage);
        return deviation > 10;  // If deviation is more than 10, flag as abnormal
    }
}

