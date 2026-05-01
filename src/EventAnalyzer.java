public class EventAnalyzer {
    public void analyze(SecurityEvent event) {
        int score = 0;

        String type = event.getType().toUpperCase();
        String user = event.getUser().toLowerCase();
        String message = event.getMessage().toLowerCase();

        if (type.equals("PHISHING_SMS")) {
            score += 80;
            event.addReason("Phishing SMS event detected.");
        } else if (type.equals("WEAK_PASSWORD")) {
            score += 70;
            event.addReason("Weak password event detected.");
        } else if (type.equals("SUSPICIOUS_FILE")) {
            score += 70;
            event.addReason("Suspicious file event detected.");
        } else if (type.equals("LOGIN_FAIL")) {
            score += 40;
            event.addReason("Failed login event detected.");
        } else if (type.equals("SQL_INJECTION")) {
            score += 90;
            event.addReason("SQL injection event detected.");
        } else if (type.equals("XSS_ATTEMPT")) {
            score += 80;
            event.addReason("XSS attempt event detected.");
        } else if (type.equals("NORMAL_LOGIN")) {
            score += 10;
            event.addReason("Normal login event detected.");
        } else {
            score += 20;
            event.addReason("Unknown event type.");
        }

        // Score by user
        if (user.equals("admin")) {
            score += 20;
            event.addReason("Privileged account detected: admin.");
        }

        if (user.equals("root")) {
            score += 20;
            event.addReason("Privileged account detected: root.");
        }

        // Score by message keywords
        if (message.contains("password")) {
            score += 20;
            event.addReason("Password-related keyword detected.");
        }

        if (message.contains(".exe")) {
            score += 20;
            event.addReason("Executable file detected.");
        }

        if (message.contains("or 1=1")) {
            score += 30;
            event.addReason("Common SQL injection pattern detected: OR 1=1.");
        }

        if (message.contains("<script>")) {
            score += 30;
            event.addReason("Script tag detected.");
        }

        event.setRiskScore(score);
        event.setRiskLevel(determineRiskLevel(event.getRiskScore()));
    }

    private String determineRiskLevel(int score) {
        if (score >= 80) {
            return "CRITICAL";
        } else if (score >= 60) {
            return "HIGH";
        } else if (score >= 30) {
            return "MEDIUM";
        } else {
            return "LOW";
        }
    }
}
