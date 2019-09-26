package com.cmaple.honeycomb.tools;

import com.cmaple.honeycomb.model.ToolsFile;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 类名：服务器文件维护类 - FileSelect
 * 功能描述： 可以进行服务端文件的相关维护；
 * 输入参数：NULL
 * 返回值：NULL
 * 异    常：无
 * 创建人：CMAPLE
 * 创建日期：2019-09-10
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
     * @param realpath String类型的绝对路径
     *                 返回值：String[]
     *                 异    常：无
     *                 创建人：CMAPLE
     *                 创建日期：2019-09-26
     *                 修改人：
     *                 级别：NULL
     *                 修改日期：
     */
    public Map<String, Object> getfileMap(String realpath) {
        Map<String, Object> map = new HashMap<String, Object>();
        ArrayList<ToolsFile> list = new ArrayList<ToolsFile>();

        try {
            File Initializationfile = new File(realpath);
            if (Initializationfile.isDirectory()) {
                File[] filelist = Initializationfile.listFiles();
                //输出循环文件结果
                for (File file : filelist) {
                    if (file.isDirectory()) {
                        list.add(new ToolsFile(file.getName(), "Directory", file.getAbsolutePath()));
                    } else {
                        list.add(new ToolsFile(file.getName(), "File", file.getAbsolutePath()));
                    }
                }
            } else {
                list.add(new ToolsFile(Initializationfile.getName(), "File", Initializationfile.getAbsolutePath()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("RTCODE", "error");
            map.put("RTMSG", "文件列表获取失败，请联系管理员！");
            map.put("RTDATA", e.getMessage());
            return map;
        }
        map.put("RTCODE", "success");
        map.put("RTMSG", "文件列表获取成功！");
        map.put("RTDATA", list);
        return map;
    }

}
