package geek.space.tmmuse.Model.Constant;

import java.util.ArrayList;

public class Constant {
    private Boolean error;
    private ArrayList<ConstantBody> body=new ArrayList<>();

    public Constant(Boolean error, ArrayList<ConstantBody> body) {
        this.error = error;
        this.body = body;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public ArrayList<ConstantBody> getBody() {
        return body;
    }

    public void setBody(ArrayList<ConstantBody> body) {
        this.body = body;
    }
}
