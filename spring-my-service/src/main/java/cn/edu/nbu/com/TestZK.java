package cn.edu.nbu.com;

import cn.edu.nbu.com.zookeeper.ZKOperatorApi;
import org.apache.zookeeper.KeeperException;

import java.io.IOException;

public class TestZK {

    private static final ZKOperatorApi api = new ZKOperatorApi();
    private String serverAddress = "192.168.0.108";

    public static void main(String[] args) throws InterruptedException, IOException, KeeperException {
        TestZK tzk = new TestZK();
        tzk.create();
        tzk.list();
        System.out.println("*************************");

//        tzk.join();
//        tzk.list();
//        System.out.println("*************************");

//        tzk.delete();
//        tzk.list();
//        System.out.println("*************************");
    }

    public void create() throws IOException, InterruptedException, KeeperException {
        api.connect(serverAddress);
        api.create("testApi","aaa");
        api.close();
    }


    public void join() throws IOException, InterruptedException, KeeperException {
        api.connect(serverAddress);
        api.join("testApi","testApiChild","aaaChild");
        Thread.sleep(2000);
        api.close();
    }

    public void list() throws IOException, InterruptedException {
        api.connect(serverAddress);
        api.list("testApi");
        api.close();
    }

    public void delete() throws IOException, InterruptedException {
        api.connect(serverAddress);
        api.delete("testApi");
        api.close();
    }

}
