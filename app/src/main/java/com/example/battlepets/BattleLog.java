package com.example.battlepets;

public class BattleLog {
    private int id; //Primary Key
    private String winnerName;
    private int winnerStrength;
    private String loserName;
    private int loserStrength;
    private String timeStamp;  //Store as text instead of DATETIME

    public BattleLog (int id, String winnerName, int winnerStrength, String loserName,
                      int loserStrength, String timeStamp){
        this.id = id;
        this.winnerName = winnerName;
        this.winnerStrength = winnerStrength;
        this.loserName = loserName;
        this.loserStrength = loserStrength;
        this.timeStamp = timeStamp;
    }

    public String getSummary(){
        return winnerName + " (" + winnerStrength + ") DEFEATED " +
                loserName + " (" + loserStrength + ") on " + timeStamp;
    }
}
