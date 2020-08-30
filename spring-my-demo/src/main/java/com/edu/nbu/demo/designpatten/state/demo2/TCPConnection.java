package com.edu.nbu.demo.designpatten.state.demo2;

public class TCPConnection {

    private TCPState state;

    public void activeOpen(){
        state.activeOpen(this);
    }

    public void passiveOpen(){
        state.passiveOpen(this);
    }

    public void close(){
        state.close(this);
    }

    public void acknowledge(){
        state.acknowledge(this);
    }

    public void changeState(TCPState state){
        this.state = state;
    }

    public TCPState getState() {
        return state;
    }

    public void setState(TCPState state) {
        this.state = state;
    }
}
