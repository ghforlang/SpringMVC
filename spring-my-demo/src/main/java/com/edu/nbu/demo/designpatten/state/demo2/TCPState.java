package com.edu.nbu.demo.designpatten.state.demo2;

public interface TCPState {
    //子类没有任何状态，所以可以是单例的，完全可以被共享
    void transmit(TCPConnection connection);
    void activeOpen(TCPConnection connection);
    void passiveOpen(TCPConnection connection);
    void close(TCPConnection connection);
    void synchronize(TCPConnection connection);
    void acknowledge(TCPConnection connection);
    void send(TCPConnection connection);

    void changeState(TCPConnection connection,TCPState state);
}
