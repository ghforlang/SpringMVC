package com.edu.nbu.demo.designpatten.state.demo2;

public class TCPEstablished implements TCPState{

    private static class Singleton{
        private static TCPEstablished INSTANCE = new TCPEstablished();
    }

    @Override
    public void transmit(TCPConnection connection) {
        System.out.println("TCPEstablished transmit");
    }

    @Override
    public void activeOpen(TCPConnection connection) {
        System.out.println("TCPEstablished activeOpen");
    }

    @Override
    public void passiveOpen(TCPConnection connection) {
        System.out.println("TCPEstablished passiveOpen");
    }

    @Override
    public void close(TCPConnection connection) {
        System.out.println("TCPEstablished close");
    }

    @Override
    public void synchronize(TCPConnection connection) {
        System.out.println("TCPEstablished synchronize");
    }

    @Override
    public void acknowledge(TCPConnection connection) {
        System.out.println("TCPEstablished acknowledge");
    }

    @Override
    public void send(TCPConnection connection) {
        System.out.println("TCPEstablished send");
    }

    @Override
    public void changeState(TCPConnection connection,TCPState state) {
        System.out.println("TCPEstablished changeState");
        connection.setState(state);
    }

    public static TCPEstablished instance(){
        return Singleton.INSTANCE;
    }
}
