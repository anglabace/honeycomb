package com.cmaple.honeycomb.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 类名：服务器信息实体类 - ServersInformation
 * 功能描述： 服务器信息实体类
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：无
 * 创建人：CMAPLE
 * 创建日期：2019-09-19
 * 修改人：
 * 级别：NULL
 * 修改日期：
 */

@Data //给实体提供读写属性, 此外还提供了 equals()、hashCode()、toString() 方法
@TableName("CS_ServersInformation")  //对应数据库表名称
@Accessors(chain = true)  //chain 若为true，则setter方法返回当前对象()
@NoArgsConstructor  //提供无参构造器
@AllArgsConstructor //提供全参构造器
public class ServersInformation {

    /**
     * 服务器顺序号
     * 长度：24
     * 是否为null：N
     * 自动递增
     * 主键
     **/
    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    /**
     * 服务器编号
     * 长度：12
     * 是否为null：Y
     **/
    private String serverid;
    /**
     * 服务器属性
     * 长度：12
     * 是否为null：Y
     **/
    private String roperties;
    /**
     * 服务器标记
     * 长度：12
     * 是否为null：Y
     **/
    private String affairs;
    /**
     * CPU数量
     * 长度：2
     * 是否为null：Y
     **/
    private int cpunumber;
    /**
     * CPU型号
     * 长度：24
     * 是否为null：Y
     **/
    private String cputype;
    /**
     * 内存大小
     * 长度：12
     * 是否为null：Y
     **/
    private String memorysize;
    /**
     * 磁盘大小
     * 长度：24
     * 是否为null：Y
     **/
    private String harddisksize;
    /**
     * 服务器ip
     * 长度：12
     * 是否为null：Y
     **/
    private String ipnumber;
    /**
     * MAC地址
     * 长度：24
     * 是否为null：Y
     **/
    private String macaddr;
    /**
     * 操作系统
     * 长度：12
     * 是否为null：Y
     **/
    private String osname;
    /**
     * 操作系统版本
     * 长度：12
     * 是否为null：Y
     **/
    private String osversion;
    /**
     * 操作用户
     * 长度：12
     * 是否为null：Y
     **/
    private String pcusername;
    /**
     * 服务器创建时间
     * 长度：0
     * 是否为null：Y
     **/
    private Date createtime;
    /**
     * 服务器创建用户
     * 长度：24
     * 是否为null：Y
     **/
    private String creatuser;
    /**
     * 服务器状态
     * 长度：12
     * 是否为null：Y
     **/
    private String type;
}
