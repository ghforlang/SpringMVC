package com.edu.nbu.demo.designpatten.state.demo2;

public class TCPClosed implements TCPState{

    private static class Singleton{
        private static TCPClosed INSTANCE = new TCPClosed();
    }

    @Override
    public void transmit(TCPConnection connection) {
        System.out.println("TCPClosed transmit");
    }

    @Override
    public void activeOpen(TCPConnection connection) {
        System.out.println("TCPClosed activeOpen");
        changeState(connection,TCPEstablished.instance());
    }

    @Override
    public void passiveOpen(TCPConnection connection) {
        System.out.println("TCPClosed passiveOpen");
        changeState(connection,TCPListen.instance());
    }

    @Override
    public void close(TCPConnection connection) {
        System.out.println("TCPClosed close");
        changeState(connection,TCPListen.instance());
    }

    @Override
    public void synchronize(TCPConnection connection) {
        System.out.println("TCPClosed synchronize");
    }

    @Override
    public void acknowledge(TCPConnection connection) {
        System.out.println("TCPClosed acknowledge");
    }

    @Override
    public void send(TCPConnection connection) {
        System.out.println("TCPClosed send");
        changeState(connection,TCPEstablished.instance());
    }

    @Override
    public void changeState(TCPConnection connection, TCPState state) {
        connection.setState(state);
    }

    public static TCPClosed instance(){
        return Singleton.INSTANCE;
    }
}
