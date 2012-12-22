package mail.mail;

public class Mail {

    private String title;
    private String receiver;
    private String message;
    private String sender;
    private Integer id;
    private Integer spam;
    private Integer trash;
    private Integer draft;

    protected Mail(Integer id, String title, String receiver, String message, String sender, Integer spam, Integer trash, Integer draft) {
        this.message = message;
        this.title = title;
        this.receiver = receiver;
        this.sender = sender;
        this.id = id;
        this.spam = spam;
        this.trash = trash;
        this.draft = draft;
    }

    public Mail() {
    }

    public String getTitle() {
        return title;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getMessage() {
        return message;
    }

    public String getSender() {
        return sender;
    }

    public Integer getID() {
        return id;
    }

    public Integer getSpam() {
        return spam;
    }

    public Integer getTrash() {
        return trash;
    }

    public Integer getDraft() {
        return draft;
    }    
}
