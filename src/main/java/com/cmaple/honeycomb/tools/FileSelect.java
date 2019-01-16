package com.cmaple.honeycomb.tools;

import java.io.File;
import java.io.FilenameFilter;

/**
 * 类名：服务器文件维护类 - FileSelect
 * 功能描述： 可以进行服务端文件的相关维护；
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：无
 * 创建人：CMAPLE
 * 创建日期：2018-09-30
 * 修改人：
 * 级别：NULL
 * 修改日期：
 */
public class FileSelect implements FilenameFilter {
    //私有化的构造函数
    private FileSelect() {
    }

    //内部类实现实例的创建
    public static class FileSelectInternal {
        private static FileSelect fileSelect = new FileSelect();
    }

    //重写readResolve()方法，防止序列化及反序列化破坏单利模式
    private Object readResolve() {
        return FileSelectInternal.fileSelect;
    }

    //返回实例的方法
    public static FileSelect getFileSelect() {
        return FileSelectInternal.fileSelect;
    }

    //实现FilenameFilter接口需要复写的方法
    @Override
    public boolean accept(File dir, String name) {
        return false;
    }

    /**
     * 函数名：查询函数-返回指定目录下的所有文件列表 - get（）
     * 功能描述： 获取指定目录下的所有文件名称，并返回数据
     * 输入参数：<按照参数定义顺序>
     *
     * @param string String类型字符串
     *               返回值：String[]
     *               异    常：无
     *               创建人：CMAPLE
     *               创建日期：2018-10-27
     *               修改人：
     *               级别：NULL
     *               修改日期：
     */





}
