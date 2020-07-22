package com.nbu.edu.cn.utils;

import com.nbu.edu.cn.constant.HttpHeaderConstant;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Set;

@UtilityClass
public class HttpRequestUtils {

    private static final Set<String> LOCAL_IPS = new HashSet<>();

    static{
        LOCAL_IPS.add("127.0.0.1");
        LOCAL_IPS.add("127.0.1.1");
        LOCAL_IPS.add("locahost");
        LOCAL_IPS.add("0:0:0:0:0:0:0:0");
    }

    public static String getIp(HttpServletRequest request){
        String ip = request.getHeader(HttpHeaderConstant.X_FORWARDED_FOR);
        if(StringUtils.isBlank(ip) || HttpHeaderConstant.UN_KNOWN.equalsIgnoreCase(ip)){
            if(StringUtils.isBlank(ip) || HttpHeaderConstant.UN_KNOWN.equalsIgnoreCase(ip)){
                ip = request.getHeader(HttpHeaderConstant.PROXY_CLIENT_ID);
            }
            if(StringUtils.isBlank(ip) || HttpHeaderConstant.UN_KNOWN.equalsIgnoreCase(ip)){
                ip = request.getHeader(HttpHeaderConstant.WL_PROXY_CLIENT_IP);
            }
            if(StringUtils.isBlank(ip) || HttpHeaderConstant.UN_KNOWN.equalsIgnoreCase(ip)){
                ip = request.getHeader(HttpHeaderConstant.HTTP_CLIENT_IP);
            }
            if(StringUtils.isBlank(ip) || HttpHeaderConstant.UN_KNOWN.equalsIgnoreCase(ip)){
                ip = request.getHeader(HttpHeaderConstant.HTTP_X_FORWARDED_FOR);
            }
            if(StringUtils.isBlank(ip) || HttpHeaderConstant.UN_KNOWN.equalsIgnoreCase(ip)){
                ip = request.getRemoteAddr();
            }

            if(LOCAL_IPS.contains(ip.toLowerCase())){
                try {
                    ip = InetAddress.getLocalHost().getHostAddress();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }
        }else if (ip.length() > 15){
            String[] ips = StringUtils.split(ip,",");
            for(String tempIp : ips){
                if(!HttpHeaderConstant.UN_KNOWN.equalsIgnoreCase(tempIp)){
                    ip = tempIp;
                    break;
                }
            }
        }
       return ip;
    }
}
