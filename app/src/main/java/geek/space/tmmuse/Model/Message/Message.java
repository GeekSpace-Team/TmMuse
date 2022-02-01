package geek.space.tmmuse.Model.Message;

public class Message {
    private int id;
    private String msg_name;
    private String mesg_desc;
    private String msg_come_date;

    public Message(int id, String msg_name, String mesg_desc, String msg_come_date) {
        this.id = id;
        this.msg_name = msg_name;
        this.mesg_desc = mesg_desc;
        this.msg_come_date = msg_come_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMsg_name() {
        return msg_name;
    }

    public void setMsg_name(String msg_name) {
        this.msg_name = msg_name;
    }

    public String getMesg_desc() {
        return mesg_desc;
    }

    public void setMesg_desc(String mesg_desc) {
        this.mesg_desc = mesg_desc;
    }

    public String getMsg_come_date() {
        return msg_come_date;
    }

    public void setMsg_come_date(String msg_come_date) {
        this.msg_come_date = msg_come_date;
    }
}
