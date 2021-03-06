package com.cmaple.honeycomb.tools;

import com.cmaple.honeycomb.model.ToolsFile;
import com.cmaple.honeycomb.util.ConfigurationFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributeView;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
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
     * 函数名：私有函数 - 取得文件夹大小 - getFileSize（）
     * 功能描述： 取得文件夹大小(字节数)
     * 输入参数：<按照参数定义顺序>
     *
     * @param f File类型的绝对路径
     *          返回值：long
     *          异    常：无
     *          创建人：CMAPLE
     *          创建日期：2019-09-27
     *          修改人：
     *          级别：NULL
     *          修改日期：
     */
    private long getDirectorySize(File f) {
        long size = 0;
        File flist[] = f.listFiles();
        for (int i = 0; i < flist.length; i++) {
            if (flist[i].isDirectory()) {
                size = size + getDirectorySize(flist[i]);
            } else {
                size = size + flist[i].length();
            }
        }
        return size;
    }

    /**
     * 函数名：私有函数 - 文件大小转化为最接近的大小 - FormetFileSize（）
     * 功能描述： 文件大小转化为最接近的大小
     * 输入参数：<按照参数定义顺序>
     *
     * @param fileS long类型的绝对路径
     *              返回值：String
     *              异    常：无
     *              创建人：CMAPLE
     *              创建日期：2019-09-27
     *              修改人：
     *              级别：NULL
     *              修改日期：
     */
    private String FormetFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "K";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }

    /**
     * 函数名：私有函数 - 分割文件名和后缀 - FormetFileSize（）
     * 功能描述： 文件大小转化为最接近的大小
     * 输入参数：<按照参数定义顺序>
     *
     * @param filename long类型的绝对路径
     * @param location long类型的绝对路径
     *                 返回值：String
     *                 异    常：无
     *                 创建人：CMAPLE
     *                 创建日期：2019-09-27
     *                 修改人：
     *                 级别：NULL
     *                 修改日期：
     */
    private String fileNameSplit(String filename, int location) {
        //非法参数返回null
        if (null == filename) {
            return null;
        }
        //定义变量
        String returnname;
        String location0 = "";
        String location1 = "";
        //分割文件名
        String[] temp = filename.split("\\.");
        //非法参数返回null
        if (1 == temp.length) {
            return null;
        }
        if (2 == temp.length) {
            location0 = temp[0];
            location1 = temp[1];
        }
        if (2 < temp.length) {
            for (int i = 0; i < temp.length - 1; i++) {
                if (i != temp.length - 2) {
                    location0 = location0 + temp[i] + ".";
                } else {
                    location0 = location0 + temp[i];
                }
            }
            location1 = temp[temp.length - 1];
        }
        //设置返回位置
        if (0 == location) {
            //返回前缀
            returnname = location0;
        } else {
            //返回后缀
            returnname = location1;
        }
        return returnname;
    }

    /**
     * 函数名：私有函数 - 获取文件创建时间 - getCreateTime（）
     * 功能描述： 获取文件创建时间
     * 输入参数：<按照参数定义顺序>
     *
     * @param file File类型的绝对路径
     *             返回值：Date
     *             异    常：无
     *             创建人：CMAPLE
     *             创建日期：2019-09-27
     *             修改人：
     *             修改日期：
     */
    private Date getCreateTime(File file) throws Exception {
        return new Date(Files.getFileAttributeView(Paths.get(file.getAbsolutePath()), BasicFileAttributeView.class, LinkOption.NOFOLLOW_LINKS).readAttributes().creationTime().toMillis());
    }

    /**
     * 函数名：私有函数 - 获取文件最后修改时间 - getModifyTime（）
     * 功能描述： 获取文件最后修改时间
     * 输入参数：<按照参数定义顺序>
     *
     * @param file File类型的绝对路径
     *             返回值：Date
     *             异    常：无
     *             创建人：CMAPLE
     *             创建日期：2019-09-27
     *             修改人：
     *             级别：NULL
     *             修改日期：
     */
    private Date getModifyTime(File file) throws Exception {
        return new Date(Files.getFileAttributeView(Paths.get(file.getAbsolutePath()), BasicFileAttributeView.class, LinkOption.NOFOLLOW_LINKS).readAttributes().lastAccessTime().toMillis());
    }

    /**
     * 函数名：工具函数-返回指定目录下的所有文件列表 - getfileMap（）
     * 功能描述： 获取指定目录下的所有文件名称，并返回数据
     * 输入参数：<按照参数定义顺序>
     *
     * @param realpath String类型的绝对路径
     *                 返回值： Map<String, Object>
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
            //判断文件路径是否正确
            if (Initializationfile.exists()) {
                //判断查看文件的类型
                if (Initializationfile.isDirectory()) {
                    File[] filelist = Initializationfile.listFiles();
                    //输出循环文件结果
                    for (File file : filelist) {
                        if (file.isDirectory()) {
                            list.add(new ToolsFile(file.getName(), "Directory", file.getAbsolutePath(), file.getParent(), FormetFileSize(getDirectorySize(file)), null, FormatTime.getFormatTime().formatYMDHMSToString(getCreateTime(file)), FormatTime.getFormatTime().formatYMDHMSToString(getModifyTime(file))));
                        } else {
                            list.add(new ToolsFile(file.getName(), "File", file.getAbsolutePath(), file.getParent(), FormetFileSize(file.length()), fileNameSplit(file.getName(), 1), FormatTime.getFormatTime().formatYMDHMSToString(getCreateTime(file)), FormatTime.getFormatTime().formatYMDHMSToString(getModifyTime(file))));
                        }
                    }
                } else {
                    list.add(new ToolsFile(Initializationfile.getName(), "File", Initializationfile.getAbsolutePath(), Initializationfile.getParent(), FormetFileSize(Initializationfile.length()), fileNameSplit(Initializationfile.getName(), 1), FormatTime.getFormatTime().formatYMDHMSToString(getCreateTime(Initializationfile)), FormatTime.getFormatTime().formatYMDHMSToString(getModifyTime(Initializationfile))));
                }
            } else {
                map.put("RTCODE", "error");
                map.put("RTMSG", "文件目录错误！请输入正确的文件目录再获取文件列表！");
                map.put("RTDATA", null);
                return map;
            }
        } catch (Exception e) {
            map.put("RTCODE", "Exception");
            map.put("RTMSG", "文件列表获取交易异常，请联系管理员！");
            map.put("RTDATA", e.getMessage());
            return map;
        }
        map.put("RTCODE", "success");
        map.put("RTMSG", "文件列表获取成功！");
        map.put("RTDATA", list);
        return map;
    }

    /**
     * 函数名：工具函数-删除绝对路径下的文件（不可删除文件夹） - delFile（）
     * 功能描述： 删除绝对路径下的文件（不可删除文件夹）
     * 输入参数：<按照参数定义顺序>
     *
     * @param realpath String类型的绝对路径
     *                 返回值：Map<String, Object>
     *                 异    常：无
     *                 创建人：CMAPLE
     *                 创建日期：2019-09-27
     *                 修改人：
     *                 级别：NULL
     *                 修改日期：
     */
    public Map<String, Object> delFile(String realpath) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            File Initializationfile = new File(realpath);
            //判断文件路径是否正确
            if (Initializationfile.exists()) {
                //判断查看文件的类型
                if (!Initializationfile.isDirectory()) {
                    boolean isdel = Initializationfile.delete();
                    if (!isdel) {
                        map.put("RTCODE", "error");
                        map.put("RTMSG", "文件删除失败！");
                        map.put("RTDATA", null);
                        return map;
                    }
                } else {
                    map.put("RTCODE", "error");
                    map.put("RTMSG", "不可以删除文件夹！");
                    map.put("RTDATA", null);
                    return map;
                }
            } else {
                map.put("RTCODE", "error");
                map.put("RTMSG", "文件目录错误！请输入正确的文件目录再删除文件！");
                map.put("RTDATA", null);
                return map;
            }
        } catch (Exception e) {
            map.put("RTCODE", "Exception");
            map.put("RTMSG", "删除文件交易异常，请联系管理员！");
            map.put("RTDATA", e.getMessage());
            return map;
        }
        map.put("RTCODE", "success");
        map.put("RTMSG", "文件删除成功！");
        map.put("RTDATA", null);
        return map;
    }

    /**
     * 函数名：工具函数-在指定目录下创建指定名称的文件（空白文件） - createFile（）
     * 功能描述： 根据绝对路径和文件名创建空白文件
     * 输入参数：<按照参数定义顺序>
     *
     * @param realpath String类型的绝对路径
     * @param filename String类型的文件名
     *                 返回值：Map<String, Object>
     *                 异    常：无
     *                 创建人：CMAPLE
     *                 创建日期：2019-09-27
     *                 修改人：
     *                 级别：NULL
     *                 修改日期：
     */
    public Map<String, Object> createFile(String realpath, String filename) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            File Initializationfile = new File(realpath);
            //判断文件路径是否正确
            if (Initializationfile.exists()) {
                File file = new File(realpath + "/" + filename);
                //判断需要创建的文件是否有同名文件
                if (!file.exists()) {
                    boolean iscreate = file.createNewFile();
                    if (!iscreate) {
                        map.put("RTCODE", "error");
                        map.put("RTMSG", "文件创建失败！");
                        map.put("RTDATA", null);
                        return map;
                    }
                } else {
                    map.put("RTCODE", "error");
                    map.put("RTMSG", "文件已经存在，请勿重复创建！");
                    map.put("RTDATA", null);
                    return map;
                }
            } else {
                map.put("RTCODE", "error");
                map.put("RTMSG", "文件目录错误！请输入正确的文件目录再创建文件！");
                map.put("RTDATA", null);
                return map;
            }
        } catch (Exception e) {
            map.put("RTCODE", "Exception");
            map.put("RTMSG", "创建文件交易异常，请联系管理员！");
            map.put("RTDATA", e.getMessage());
            return map;
        }
        map.put("RTCODE", "success");
        map.put("RTMSG", "文件创建成功！");
        map.put("RTDATA", null);
        return map;
    }

    /**
     * 函数名：工具函数-上传文件到指定目录 - uploadFile（）
     * 功能描述： 上传文件到指定目录
     * 输入参数：<按照参数定义顺序>
     *
     * @param uploadfile MultipartFile类型的文件
     * @param realpath   String类型的上传路径
     * @param filename   String类型的文件名
     *                   返回值：Map<String, Object>
     *                   异    常：无
     *                   创建人：CMAPLE
     *                   创建日期：2019-09-27
     *                   修改人：
     *                   级别：NULL
     *                   修改日期：
     */
    public Map<String, Object> uploadFile(MultipartFile uploadfile, String realpath, String filename) {
        Map<String, Object> map = new HashMap<String, Object>();
        String updatepath = "";
        if (null != filename) {
            updatepath = realpath + "/" + filename;
        } else {
            updatepath = realpath;
        }
        try {
            if (!new File(realpath).exists() && new File(realpath).isDirectory()) {
                map.put("RTCODE", "error");
                map.put("RTMSG", "文件目录错误！请输入正确的文件目录！");
                map.put("RTDATA", null);
                return map;
            }
            if (new File(updatepath).exists()) {
                map.put("RTCODE", "error");
                map.put("RTMSG", "存在同名文件，请更换文件名!");
                map.put("RTDATA", null);
                return map;
            }
            Files.copy(uploadfile.getInputStream(), Paths.get(updatepath), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("RTCODE", "Exception");
            map.put("RTMSG", "上传文件交易异常！请联系管理员！");
            map.put("RTDATA", e.getMessage());
            return map;
        }
        map.put("RTCODE", "success");
        map.put("RTMSG", "文件上传成功！");
        map.put("RTDATA", null);
        return map;
    }

    /**
     * 函数名：工具函数-检查绝对路径下是否存在该文件 - checkFileExists（）
     * 功能描述： 检查绝对路径下是否存在该文件
     * 输入参数：<按照参数定义顺序>
     *
     * @param realpath String类型的绝对路径
     * @param filename String类型的文件名
     *                 返回值：Map<String, Object>
     *                 异    常：无
     *                 创建人：CMAPLE
     *                 创建日期：2019-09-30
     *                 修改人：
     *                 级别：NULL
     *                 修改日期：
     */
    public Map<String, Object> checkFileExists(String realpath, String filename) {
        Map<String, Object> map = new HashMap<String, Object>();
        String updatepath = "";
        if (null != filename) {
            updatepath = realpath + "/" + filename;
        } else {
            updatepath = realpath;
        }
        File checkrealFile = new File(realpath);
        File checkupdateFile = new File(updatepath);
        try {
            if (!checkrealFile.exists() && checkrealFile.isDirectory()) {
                map.put("RTCODE", "error");
                map.put("RTMSG", "文件目录错误！请输入正确的文件目录！");
                map.put("RTDATA", null);
                return map;
            }
            if (checkupdateFile.exists() && !checkupdateFile.isDirectory()) {
                map.put("RTCODE", "error");
                map.put("RTMSG", "存在同名文件，请更换文件名!");
                map.put("RTDATA", null);
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("RTCODE", "Exception");
            map.put("RTMSG", "检查文件是否存在交易异常！请联系管理员！");
            map.put("RTDATA", e.getMessage());
            return map;
        }
        //删除引用
        checkrealFile = null;
        checkupdateFile = null;
        //返回数据
        map.put("RTCODE", "success");
        map.put("RTMSG", "检查文件成功！");
        map.put("RTDATA", "该目录下，不存在同名文件！");
        return map;
    }

    /**
     * 函数名：工具函数-检查绝对路径下文件的大小 - checkFileSize（）
     * 功能描述： 检查绝对路径下文件的大小
     * 输入参数：<按照参数定义顺序>
     *
     * @param realpath String类型的绝对路径
     * @param filename String类型的文件名
     *                 返回值：Map<String, Object>
     *                 异    常：无
     *                 创建人：CMAPLE
     *                 创建日期：2019-09-30
     *                 修改人：
     *                 级别：NULL
     *                 修改日期：
     */
    public Map<String, Object> checkFileSize(String realpath, String filename) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if (!new File(realpath).exists()) {
                map.put("RTCODE", "error");
                map.put("RTMSG", "文件目录错误！请输入正确的文件目录！");
                map.put("RTDATA", null);
                return map;
            }
            if (new File(realpath + "/" + filename).exists()) {
                map.put("RTCODE", "error");
                map.put("RTMSG", "存在同名文件，请更换文件名!");
                map.put("RTDATA", null);
                return map;
            }
            String filesize = FormetFileSize(getDirectorySize(new File(realpath + "/" + filename)));
            map.put("RTCODE", "success");
            map.put("RTMSG", "检查文件成功！");
            map.put("RTDATA", filesize);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("RTCODE", "Exception");
            map.put("RTMSG", "检查文件是否存在交易异常！请联系管理员！");
            map.put("RTDATA", e.getMessage());
            return map;
        }
        return map;
    }

    /**
     * 函数名：工具函数-创建文件夹 - createDirectory（）
     * 功能描述： 检查绝对路径下文件的大小
     * 输入参数：<按照参数定义顺序>
     *
     * @param realpath      String类型的绝对路径
     * @param directoryname String类型的文件夹名
     *                      返回值：Map<String, Object>
     *                      异    常：无
     *                      创建人：CMAPLE
     *                      创建日期：2019-09-30
     *                      修改人：
     *                      级别：NULL
     *                      修改日期：
     */
    public Map<String, Object> createDirectory(String realpath, String directoryname) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            File directory = new File(realpath + "/" + directoryname);
            if (directory.exists()) {
                map.put("RTCODE", "error");
                map.put("RTMSG", "存在同名文件夹，请更换文件名再创建!");
                map.put("RTDATA", null);
                return map;
            }
            boolean issuccess = directory.mkdir();
            if (false == issuccess) {
                map.put("RTCODE", "error");
                map.put("RTMSG", "创建文件夹失败!");
                map.put("RTDATA", null);
                return map;
            }
            map.put("RTCODE", "success");
            map.put("RTMSG", "创建文件夹成功！");
            map.put("RTDATA", null);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("RTCODE", "Exception");
            map.put("RTMSG", "检查文件是否存在交易异常！请联系管理员！");
            map.put("RTDATA", e.getMessage());
            return map;
        }
        return map;
    }

    /**
     * 函数名：工具函数-移动文件到指定目录（不可移动文件夹） - removeFile（）
     * 功能描述： 删除绝对路径下的文件（不可删除文件夹）
     * 输入参数：<按照参数定义顺序>
     *
     * @param realpath   String类型的文件原来路径
     * @param fileName   String类型的文件名
     * @param targetpath String类型的文件目标路径
     *                   返回值：Map<String, Object>
     *                   异    常：无
     *                   创建人：CMAPLE
     *                   创建日期：2019-11-25
     *                   修改人：
     *                   级别：NULL
     *                   修改日期：
     */
    public Map<String, Object> removeFile(String realpath, String fileName, String targetpath) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (!new File(realpath + "/" + fileName).exists()) {
            map.put("RTCODE", "error");
            map.put("RTMSG", "文件目录错误！请输入正确的文件目录！");
            map.put("RTDATA", null);
            return map;
        }
        if (new File(realpath + "/" + fileName).isDirectory()) {
            map.put("RTCODE", "error");
            map.put("RTMSG", "目录为文件夹，不可以复制!");
            map.put("RTDATA", null);
            return map;
        }
        try {
            //复制文件到指定目录（使用IO流）
            FileInputStream infile = new FileInputStream(new File(realpath + "/" + fileName));
            FileOutputStream outfile = new FileOutputStream(new File(targetpath + "/" + fileName));
            FileOutputStream cacheoutfile = new FileOutputStream(new File(new ConfigurationFile().getCACHEFILEPATH() + "/" + fileName));
            byte[] buff = new byte[1024];
            int n = 0;
            while ((n = infile.read(buff)) != -1) {
                outfile.write(buff, 0, n);
                //将文件复制到缓存目录
                cacheoutfile.write(buff, 0, n);
            }
            outfile.flush();
            infile.close();
            outfile.close();
            //删除原油目录文件
            new File(realpath + "/" + fileName).delete();
            map.put("RTCODE", "success");
            map.put("RTMSG", "文件复制成功！");
            map.put("RTDATA", null);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("RTCODE", "Exception");
            map.put("RTMSG", "复制文件存在交易异常！请联系管理员！");
            map.put("RTDATA", e.getMessage());
            return map;
        }
        return map;
    }

    /**
     * 函数名：工具函数-下载指定目录的文件（ - downloadFile（）
     * 功能描述： 下载指定目录的文件
     * 输入参数：<按照参数定义顺序>
     *
     * @param bufferedOutputStream String类型的文件绝对路径
     * @param bufferedInputStream  HttpServletResponse类型的请求回应
     *                             返回值：Map<String, Object>
     *                             异    常：无
     *                             创建人：CMAPLE
     *                             创建日期：2019-11-25
     *                             修改人：
     *                             级别：NULL
     *                             修改日期：
     */
    public void downloadFile(BufferedOutputStream bufferedOutputStream, BufferedInputStream bufferedInputStream) throws IOException {
        byte[] buff = new byte[1024];
        int n = 0;
        while ((n = bufferedInputStream.read(buff)) != -1) {
            bufferedOutputStream.write(buff, 0, buff.length);
        }
        bufferedOutputStream.flush();
    }

    /**
     * 函数名：工具函数- 读取文件将文件读取为string - readFile（）
     * 功能描述： 读取文件将文件读取为string
     * 输入参数：<按照参数定义顺序>
     *
     * @param filePath String类型的文件原来路径
     * @param fileName String类型的文件名
     *                 返回值：Map<String, Object>
     *                 异    常：无
     *                 创建人：CMAPLE
     *                 创建日期：2019-12-25
     *                 修改人：
     *                 级别：NULL
     *                 修改日期：
     */
    public Map<String, Object> readFile(String filePath, String fileName) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (!new File(filePath + "/" + fileName).exists()) {
            map.put("RTCODE", "error");
            map.put("RTMSG", "文件目录错误！请输入正确的文件目录！");
            map.put("RTDATA", null);
            return map;
        }
        if (new File(filePath + "/" + fileName).isDirectory()) {
            map.put("RTCODE", "error");
            map.put("RTMSG", "目录为文件夹，不可以复制!");
            map.put("RTDATA", null);
            return map;
        }
        String returndata = "";
        try {
            FileReader fileReader = new FileReader(filePath + "/" + fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            returndata = returndata + line;
            while (line != null) {
                line = bufferedReader.readLine();
                if (line != null) {
                    returndata = returndata + line;
                }
            }
            //关闭流
            fileReader.close();
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
            map.put("RTCODE", "Exception");
            map.put("RTMSG", "读取文件内容交易异常！请联系管理员！");
            map.put("RTDATA", e.getMessage());
            return map;
        }
        map.put("RTCODE", "success");
        map.put("RTMSG", "文件读取成功！");
        map.put("RTDATA", returndata);
        return map;
    }

}
