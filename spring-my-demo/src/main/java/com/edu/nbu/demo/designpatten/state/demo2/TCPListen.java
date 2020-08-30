package com.edu.nbu.demo.designpatten.state.demo2;

public class TCPListen implements TCPState{

    private static class Singleton{
        private static TCPListen INSTANCE = new TCPListen();
    }

    @Override
    public void transmit(TCPConnection connection) {
        System.out.println("TCPListen transmit");
    }

    @Override
    public void activeOpen(TCPConnection connection) {
        System.out.println("TCPListen activeOpen");
    }

    @Override
    public void passiveOpen(TCPConnection connection) {
        System.out.println("TCPListen passiveOpen");
    }

    @Override
    public void close(TCPConnection connection) {
        System.out.println("TCPListen close");
    }

    @Override
    public void synchronize(TCPConnection connection) {
        System.out.println("TCPListen synchronize");
    }

    @Override
    public void acknowledge(TCPConnection connection) {
        System.out.println("TCPListen acknowledge");
    }

    @Override
    public void send(TCPConnection connection) {
        System.out.println("TCPListen send");
    }

    @Override
    public void changeState(TCPConnection connection,TCPState state) {
        System.out.println("TCPListen send");
        connection.setState(state);
    }

    public static  TCPListen instance(){
        return Singleton.INSTANCE;
    }
}
