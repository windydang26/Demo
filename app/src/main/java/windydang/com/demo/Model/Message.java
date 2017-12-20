package windydang.com.demo.Model;

/**
 * Created by windydang on 12/20/17.
 */

public class Message {
    private String time;
    private String name;
    private String content;
    private int id;

    public Message() {
    }

    public Message(String time, String name, String content, int id) {
        this.time = time;
        this.name = name;
        this.content = content;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
