package cn.edu.nbu.com.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class ZKOperatorApi extends ConnectionWatcher {

    public void create(String groupName,String data) throws KeeperException, InterruptedException {
        String path = "/" + groupName;
        String createPath = zk.create(path,data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("created " + createPath);
    }

    public void join(String groupName,String memberName,String data) throws KeeperException, InterruptedException {
        String path = "/" + groupName + "/" + memberName;
        String createPath = zk.create(path,data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);
        System.out.println("created " + createPath);
    }


    public void list(String groupName){
        String path = "/" + groupName;
        try {
            List<String> children = zk.getChildren(path,false);
            if(CollectionUtils.isEmpty(children)){
                System.out.println("no members in group " + groupName);
                System.exit(1);
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void delete(String groupName){
        String path = "/" + groupName;
        try {
            List<String> children = zk.getChildren(path,false);
            if(org.apache.commons.collections4.CollectionUtils.isEmpty(children)){
                System.out.println("no members in group " + groupName);
                System.exit(1);
            }
            for(String child : children){
                zk.delete(path + "/" + child,-1);
            }
            zk.delete(path,-1);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
