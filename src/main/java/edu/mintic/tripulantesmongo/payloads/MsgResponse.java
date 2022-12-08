package edu.mintic.tripulantesmongo.payloads;

public class MsgResponse {
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public MsgResponse(String msg) {
        this.msg = msg;
    }
    
}
