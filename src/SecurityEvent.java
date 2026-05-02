import java.util.ArrayList;

public class SecurityEvent {
    private int id;
    private String type;
    private String user;
    private String ip;
    private String message;

    private int riskScore;
    private int priority;
    private String riskLevel;
    private ArrayList<String> reasons;

    public SecurityEvent(int id, String type, String user, String ip, String message) {
        this.id = id;
        this.type = type;
        this.user = user;
        this.ip = ip;
        this.message = message;

        this.riskScore = 0;
        this.priority = 100;
        this.riskLevel = "UNKNOWN";
        this.reasons = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getUser() {
        return user;
    }

    public String getIp() {
        return ip;
    }

    public String getMessage() {
        return message;
    }

    public int getRiskScore() {
        return riskScore;
    }

    public int getPriority() {
        return priority;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public ArrayList<String> getReasons() {
        return reasons;
    }

    public void setRiskScore(int riskScore) {
        if (riskScore < 0) {
            this.riskScore = 0;
        } else if (riskScore > 100) {
            this.riskScore = 100;
        } else {
            this.riskScore = riskScore;
        }

        this.priority = 100 - this.riskScore;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public void addReason(String reason) {
        reasons.add(reason);
    }

    public void resetAnalysis() {
        this.riskScore = 0;
        this.priority = 100;
        this.riskLevel = "UNKNOWN";
        this.reasons.clear();
    }

    public String getShortSummary() {
        return "[" + riskLevel + "] "
                + type
                + " | User: " + user
                + " | Risk: " + riskScore
                + " | Priority: " + priority;
    }

    public String getDetailedSummary() {
        StringBuilder sb = new StringBuilder();

        sb.append("Event ID: ").append(id).append("\n");
        sb.append("Type: ").append(type).append("\n");
        sb.append("User: ").append(user).append("\n");
        sb.append("IP: ").append(ip).append("\n");
        sb.append("Message: ").append(message).append("\n");
        sb.append("Risk Score: ").append(riskScore).append("\n");
        sb.append("Priority: ").append(priority).append("\n");
        sb.append("Risk Level: ").append(riskLevel).append("\n");

        sb.append("Reasons:\n");
        if (reasons.isEmpty()) {
            sb.append("- No reasons recorded yet.\n");
        } else {
            for (String reason : reasons) {
                sb.append("- ").append(reason).append("\n");
            }
        }

        return sb.toString();
    }

    @Override
    public String toString() {
        return getShortSummary();
    }
}
