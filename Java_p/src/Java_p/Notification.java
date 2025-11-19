package Java_p;

public abstract class Notification {
    protected String to;
    protected String subject;
    protected String message;

    public Notification(String to, String subject, String message) {
        this.to = to;
        this.subject = subject;
        this.message = message;
    }

    public abstract void send();
}

class EmailNotification extends Notification {
    public EmailNotification(String to, String subject, String message) {
        super(to, subject, message);
    }

    @Override
    public void send() {
        System.out.println("EMAIL to=" + to + " | " + subject + " | " + message);
    }
}

class SMSNotification extends Notification {
    public SMSNotification(String to, String subject, String message) {
        super(to, subject, message);
    }

    @Override
    public void send() {
        System.out.println("SMS to=" + to + " | " + subject + " | " + message);
    }
}

interface NotificationObserver {
    void update(String message);
}

class AppointmentObserver implements NotificationObserver {
    private Notification notification;

    public AppointmentObserver(Notification notification) {
        this.notification = notification;
    }

    @Override
    public void update(String message) {
        notification.send();
    }
}

class NotificationAdapter {
    private String vendor;
    private Notification notification;

    public NotificationAdapter(String vendor, Notification notification) {
        this.vendor = vendor;
        this.notification = notification;
    }

    public void send() {
        if ("Digicel".equals(vendor)) {
            System.out.println("Using Digicel API to send SMS.");
        } else if ("Flow".equals(vendor)) {
            System.out.println("Using Flow API to send SMS.");
        }
        notification.send();
    }
}
