package cn.edu.nbu.com.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ConnectionWatcher implements Watcher {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    protected ZooKeeper zk = null;
    private static Stat stat = new Stat();
    private static final String serverAddress = "192.168.0.108";
    private static final int SESSION_TIMEOUT = 5000;


    public void connect(String hosts) throws IOException, InterruptedException {
        //参数说明 1 服务主机地址；2 毫秒为单位的会话超时参数；3 Watcher对象实例
        zk = new ZooKeeper(serverAddress,SESSION_TIMEOUT,this );

        //当一个ZooKeeper的实例被创建时，会启动一个线程连接到Zookeeper服务。
        // 由于对构造函数的调用是立即返回的，因此在使用新建的Zookeeper对象之前一定要等待其与Zookeeper服务之间的连接建立成功。
        // 使用CountDownLatch使当前线程等待，直到Zookeeper对象准备就绪
        connectedSemaphore.await();
    }


    @Override
    public void process(WatchedEvent watchedEvent) {
        //客户端与ZK建立连接后，Watcher的process方法会被调用，参数是表示该连接的事件，
        // 连接成功后调用CountDownLatch的countDown方法，计数器减为0，释放线程锁，zk对象可用
        if(watchedEvent.getState() == Event.KeeperState.SyncConnected){
            connectedSemaphore.countDown();
        }
    }

    public void close() throws InterruptedException {
        zk.close();
    }
}
