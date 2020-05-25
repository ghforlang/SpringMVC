package com.edu.nbu.dal.po;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by lenovo on 2017/1/10.
 */
@Getter
@Setter
@ToString
public class UserPO {
    /**
     * 唯一主键id
     */
    private Long id;
    /**
     * 雪花id
     */
    private Long pkId;
    /**
     * 逻辑删除标志
     */
    private Integer isDeleted;
    /**
     * 创建时间
     */
    private Date gmtCreated;
    /**
     * 更新时间
     */
    private Date gmtModified;
    /**
     * 用户姓名
     */
    private String name;
}
