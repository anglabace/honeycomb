package com.cmaple.honeycomb;

import com.cmaple.honeycomb.tools.FormatTime;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class test {
//    public static String byteToHexString(byte[] by){
//        StringBuffer SB = new StringBuffer();
//        int i = 0;
//        while(i < by.length){
//            int k = by[i];
//            int j = k;
//            if(k < 0){
//                j = k + 256;
//            }
//            if(j < 16){
//                SB.append("0");
//            }
//            SB.append(Integer.toHexString(j));
//            i += 1;
//        }
//        return SB.toString();
//    }
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        System.out.println(FormatTime.getFormatTime().formatY_M_DToBefor(1));


//        // 要抓取的网站
//        String url = "http://top.baidu.com/buzz?b=1";
//        // 使用爬虫获取
//        Document document = Jsoup.connect(url).get();
//        getElmentAndInsert(document,baiduNewsMapper,"实时热点");
//        // 获取新闻列表
//        Elements lis = document.select("#flist li");
//        for (int i = 2; i < lis.size(); i++) {
//            Element li = lis.get(i);
//            String title = li.select("a").attr("title");
//            String href = "http://top.baidu.com" + li.select("a").attr("href").substring(1);
//            document = Jsoup.connect(href).get();
//            getElmentAndInsert(document,baiduNewsMapper,title);
//        }



        //        AliYunPreset aliYunPreset = new AliYunPreset();
//        System.out.println(aliYunPreset.getSIGNNAME());
//        try {
//            Properties props = new Properties();                    // 参数配置
//            props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
//            props.setProperty("mail.smtp.host", "smtp.aliyun.com");   // 发件人的邮箱的 SMTP 服务器地址
//            props.setProperty("mail.smtp.auth", "true");            // 需要请求认证
//            // 2. 根据配置创建会话对象, 用于和邮件服务器交互
//            Session session = Session.getInstance(props);
//            session.setDebug(true);                                 // 设置为debug模式, 可以查看详细的发送 log
//            // 3. 创建一封邮件
//            MimeMessage message = createMimeMessage(session, "cmaple@aliyun.com", "congfeng12@163.com");
//            // 4. 根据 Session 获取邮件传输对象
//            Transport transport = session.getTransport();
//            transport.connect("cmaple@aliyun.com", "B^!Rn#wxhPx24q8i");
//            // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
//            transport.sendMessage(message, message.getAllRecipients());
//            // 7. 关闭连接
//            transport.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


//        File input = new File("/Users/congfeng/IDEA_Programe/honeycomb/src/main/resources/static/" + "account_error.html");
//        Document doc = Jsoup.parse(input, "UTF-8");
//        String[] splitemail = doc.toString().split("-p-");
//        for (int i = 0;i<splitemail.length;i++){
//            System.out.println(splitemail[i]);
//        }
//        System.out.println(Boolean.parseBoolean("fales1"));

//        OperationLogService operationLogService = null;
//        operationLogService.insert(new OperationLog(0, "HC" + FormatTime.getFormatTime().formatYMDToString(new Date()) + "-" + RandomData.getRandomData().getRandomNHData(6), new Date(), "18368080269", "normal", "account", "用户：[ 18368080269 ] 使用 iP - 61.164.40.82 ｜ 登录地点 - 浙江省杭州市  进行登录操作！"));
//        //operationLogService.insert(new OperationLog(0, "HC" + FormatTime.getFormatTime().formatYMDToString(new Date()) + "-" + RandomData.getRandomData().getRandomNHData(6), new Date(), user.getTelephonenumber(), "normal", "account", "用户：[ " + user.getTelephonenumber() + " ] 使用 iP - " + commonip + " ｜ 登录地点 - " + lastplace + "  进行登录操作！"));

        //        LocalDate localDate = LocalDate.now();
//        LocalTime localTime1 = LocalTime.now();
//        System.out.println(localDate.toString());
//        System.out.println(localTime1);
//        Map<String, Object> upmap = FileSelect.getFileSelect().readFile("/Users/congfeng/Downloads/test/milestone1","milestone20191121.txt");
//        System.out.println(upmap);
//        System.out.println(upmap.toString());
//        Map<String, Object> map = FileSelect.getFileSelect().getfileMap("/Users/congfeng/Downloads/test/IMGTI-1/V01.00.01-TR");
//        System.out.println(map);
        //Map<String, Object> map = FileSelect.getFileSelect().removeFile("/Users/congfeng/Downloads/test/IMGTI-1/V01.00.01-TR", "test.txt", "/Users/congfeng/Downloads/test/IMGTI-1/V01.00.02-TR");
        //System.out.println(new ConfigurationFile().getCACHEFILEPATH());
        //        Map<String, Object> map1 = FileSelect.getFileSelect().getfileMap("/Users/congfeng/Downloads");
//        for (int i = 0; i < ((ArrayList<ToolsFile>) map1.get("RTDATA")).size(); i++) {
//            System.out.println(((ArrayList<ToolsFile>) map1.get("RTDATA")).get(i));
//        }

//        Map<String, Object> map1 = FileSelect.getFileSelect().createFile("/Users/congfeng/Downloads", "test.txt");
//        System.out.println(map1);

//        Map<String, Object> map2 = FileSelect.getFileSelect().delFile("/Users/congfeng/Downloads/test.txt");
//        System.out.println(map2);

//        File Initializationfile = new File("/Users/congfeng/Downloads/test.txt");
//        Date date1 = FileSelect.getFileSelect().getCreateTime(Initializationfile);
//        System.out.println(FormatTime.getFormatTime().formatYMDHMSToString(date1));
//
//        Date date2 = FileSelect.getFileSelect().getModifyTime("/Users/congfeng/Downloads/test.txt");
//        System.out.println(FormatTime.getFormatTime().formatYMDHMSToString(date2));

        //System.out.println("152223199312260014".replaceAll("(?<=[\\d]{6})\\d(?=[\\d]{4})", "*"));
////
//        Map<String, String> map1 = Aliyun.getAliyun().aliyun_Idcard_Name("谈程晨", "330481199401073225", "ff7fd118f90848a992600745e96844d5", "1929109414356087", "IVYrWyJDu5B6kU", "https://safrvcert.market.alicloudapi.com", "/safrv_2meta_id_name/", "GET");
//        System.out.println(map1);

//        Map<String, String> map2 = AliTool.getAliyun().aliyun_SendSms("18368080269","29065","CMAPLE蜂巢","SMS_184826653","LTAI4FtZoNunQCMEyCSDodB7","QEh265EHuaBIqHwSooKQh3wIhAhitV");
//        System.out.println(map2);

        //{"Message":"OK","RequestId":"3D1BD085-9951-4F95-B116-F6740CC988E6","BizId":"317021683399456001^0","Code":"OK"}


        //按钮组
//        String jsons = "[{\"icon\":\"el-icon-paperclip\",\"title\":\"查看源码\",\"url\":\"https://www.baidu.com/\"},{\"icon\":\"el-icon-paperclip\",\"title\":\"查看源码\",\"url\":\"https://www.baidu.com/\"}]";
//        JSONArray jsonArray = JSON.parseArray(jsons);
//        Iterator<Object> iterator = jsonArray.iterator();
//        while (iterator.hasNext()){
//            JSONObject jsonObject = (JSONObject) iterator.next();
//            System.out.println("icon: " + jsonObject.getString("icon") + ",title: " + jsonObject.getString("title")+ ",url: " + jsonObject.getString("url"));
//
//        }
//        //进一步需要获取的信息
//        System.out.println("bizCode -  " + aliyunIDName.getValue().getBizCode());
//        System.out.println("message -  " + aliyunIDName.getValue().getMessage());

        //        String key =  ManageKey.getManageKey().obtainManageKey();
//        System.out.println(key);
//        String returnkey =  ManageKey.getManageKey().getUserMangerKey(key,"cmaplesuper","superadmin");
//        System.out.println(returnkey);
//        boolean istrue = ManageKey.getManageKey().checkManageKeys(key,"cmaplesuper","superadmin",returnkey);
//        System.out.println(istrue);
//        System.out.println(new Date().toString());
//
//        Map<String, Object> map = FileSelect.getFileSelect().checkFileExists("/Users/congfeng/Downloads/test", "ByteAiCP");
//        System.out.println(map);
//        Map<String, Object> map1 = FileSelect.getFileSelect().createDirectory("/Users/congfeng/Downloads/test/ByteAiCP", "V01.00.01-TR");
//        System.out.println(map1);
//        String path = System.getProperty("java.library.path");
//        System.out.println(path);
//        try {
//            // System信息，从jvm获取
////            System.setProperty("java.library.path", path);
//            property();
//            System.out.println("----------------------------------");
//            // cpu信息
//            cpu();
//            System.out.println("----------------------------------");
//            // 内存信息
//            memory();
//            System.out.println("----------------------------------");
//            // 操作系统信息
//            os();
//            System.out.println("----------------------------------");
//            // 用户信息
//            who();
//            System.out.println("----------------------------------");
//            // 文件系统信息
//            file();
//            System.out.println("----------------------------------");
//            // 网络信息
//            net();
//            System.out.println("----------------------------------");
//            // 以太网信息
//            ethernet();
//            System.out.println("----------------------------------");
//        } catch (Exception e1) {
//            e1.printStackTrace();
//        }


//        Test testDo1 = new Test();
//        Thread thread1 = new TestDo(testDo1);
//        thread1.setName("线程 - 1");
//        thread1.start();
//        Thread thread2 = new TestDo(testDo1);
//        thread2.setName("线程 - 2");
//        thread2.start();
//
//        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
//        for (int i = 0; i < 100; i++) {
//            final int index = i;
//            try {
//                Thread.sleep(index * 100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            cachedThreadPool.execute(new Runnable() {
//                public void run() {
//                    System.out.println(Thread.currentThread().getName());
//                }
//            });
//        }
//
//        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
//        for (int i = 0; i < 100; i++) {
//            final int index = i;
//            fixedThreadPool.execute(new Runnable() {
//                public void run() {
//                    try {
//                        System.out.println(Thread.currentThread().getName());
//                        Thread.sleep(2000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//        }
//
//        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
//        scheduledThreadPool.schedule(new Runnable() {
//            public void run() {
//                System.out.println(Thread.currentThread().getName());
//            }
//        }, 1, TimeUnit.SECONDS);
//
//        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
//        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
//            public void run() {
//                System.out.println(Thread.currentThread().getName());
//            }
//        }, 1, 3, TimeUnit.SECONDS);
//
//
//        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
//        for (int i = 0; i < 100; i++) {
//            final int index = i;
//            singleThreadExecutor.execute(new Runnable() {
//                public void run() {
//                    try {
//                        System.out.println(Thread.currentThread().getName());
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//        }
//
//        ExecutorService singleThreadExecutor = Executors.newCachedThreadPool();
//        for (int i = 0; i < 100; i++) {
//            final int index = i;
//            singleThreadExecutor.execute(new Runnable() {
//                public void run() {
//                    try {
//                        while(true) {
//                            System.out.println(Thread.currentThread().getName());
//                            Thread.sleep(10 * 1000);
//                        }
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

    }

//    private static void property() throws UnknownHostException {
//        Runtime r = Runtime.getRuntime();
//        Properties props = System.getProperties();
//        InetAddress addr;
//        addr = InetAddress.getLocalHost();
//        String ip = addr.getHostAddress();
//        Map<String, String> map = System.getenv();
//        String userName = map.get("USERNAME");// 获取用户名
//        String computerName = map.get("COMPUTERNAME");// 获取计算机名
//        String userDomain = map.get("USERDOMAIN");// 获取计算机域名
//        System.out.println("用户名:    " + userName);
//        System.out.println("计算机名:    " + computerName);
//        System.out.println("计算机域名:    " + userDomain);
//        System.out.println("本地ip地址:    " + ip);
//        System.out.println("本地主机名:    " + addr.getHostName());
//        System.out.println("JVM可以使用的总内存:    " + r.totalMemory());
//        System.out.println("JVM可以使用的剩余内存:    " + r.freeMemory());
//        System.out.println("JVM可以使用的处理器个数:    " + r.availableProcessors());
//        System.out.println("Java的运行环境版本：    " + props.getProperty("java.version"));
//        System.out.println("Java的运行环境供应商：    " + props.getProperty("java.vendor"));
//        System.out.println("Java供应商的URL：    " + props.getProperty("java.vendor.url"));
//        System.out.println("Java的安装路径：    " + props.getProperty("java.home"));
//        System.out.println("Java的虚拟机规范版本：    " + props.getProperty("java.vm.specification.version"));
//        System.out.println("Java的虚拟机规范供应商：    " + props.getProperty("java.vm.specification.vendor"));
//        System.out.println("Java的虚拟机规范名称：    " + props.getProperty("java.vm.specification.name"));
//        System.out.println("Java的虚拟机实现版本：    " + props.getProperty("java.vm.version"));
//        System.out.println("Java的虚拟机实现供应商：    " + props.getProperty("java.vm.vendor"));
//        System.out.println("Java的虚拟机实现名称：    " + props.getProperty("java.vm.name"));
//        System.out.println("Java运行时环境规范版本：    " + props.getProperty("java.specification.version"));
//        System.out.println("Java运行时环境规范供应商：    " + props.getProperty("java.specification.vender"));
//        System.out.println("Java运行时环境规范名称：    " + props.getProperty("java.specification.name"));
//        System.out.println("Java的类格式版本号：    " + props.getProperty("java.class.version"));
//        System.out.println("Java的类路径：    " + props.getProperty("java.class.path"));
//        System.out.println("加载库时搜索的路径列表：    " + props.getProperty("java.library.path"));
//        System.out.println("默认的临时文件路径：    " + props.getProperty("java.io.tmpdir"));
//        System.out.println("一个或多个扩展目录的路径：    " + props.getProperty("java.ext.dirs"));
//        System.out.println("操作系统的名称：    " + props.getProperty("os.name"));
//        System.out.println("操作系统的构架：    " + props.getProperty("os.arch"));
//        System.out.println("操作系统的版本：    " + props.getProperty("os.version"));
//        System.out.println("文件分隔符：    " + props.getProperty("file.separator"));
//        System.out.println("路径分隔符：    " + props.getProperty("path.separator"));
//        System.out.println("行分隔符：    " + props.getProperty("line.separator"));
//        System.out.println("用户的账户名称：    " + props.getProperty("user.name"));
//        System.out.println("用户的主目录：    " + props.getProperty("user.home"));
//        System.out.println("用户的当前工作目录：    " + props.getProperty("user.dir"));
//    }
//
//    private static void memory() throws SigarException {
//        Sigar sigar = new Sigar();
//        Mem mem = sigar.getMem();
//        // 内存总量
//        System.out.println("内存总量:    " + mem.getTotal() / 1024L + "K av");
//        // 当前内存使用量
//        System.out.println("当前内存使用量:    " + mem.getUsed() / 1024L + "K used");
//        // 当前内存剩余量
//        System.out.println("当前内存剩余量:    " + mem.getFree() / 1024L + "K free");
//        Swap swap = sigar.getSwap();
//        // 交换区总量
//        System.out.println("交换区总量:    " + swap.getTotal() / 1024L + "K av");
//        // 当前交换区使用量
//        System.out.println("当前交换区使用量:    " + swap.getUsed() / 1024L + "K used");
//        // 当前交换区剩余量
//        System.out.println("当前交换区剩余量:    " + swap.getFree() / 1024L + "K free");
//    }
//
//    private static void cpu() throws SigarException {
//        Sigar sigar = new Sigar();
//        CpuInfo infos[] = sigar.getCpuInfoList();
//        CpuPerc cpuList[] = null;
//        cpuList = sigar.getCpuPercList();
//        for (int i = 0; i < infos.length; i++) {// 不管是单块CPU还是多CPU都适用
//            CpuInfo info = infos[i];
//            System.out.println("第" + (i + 1) + "块CPU信息");
//            System.out.println("CPU的总量MHz:    " + info.getMhz());// CPU的总量MHz
//            System.out.println("CPU生产商:    " + info.getVendor());// 获得CPU的卖主，如：Intel
//            System.out.println("CPU类别:    " + info.getModel());// 获得CPU的类别，如：Celeron
//            System.out.println("CPU缓存数量:    " + info.getCacheSize());// 缓冲存储器数量
//            printCpuPerc(cpuList[i]);
//        }
//    }
//
//    private static void printCpuPerc(CpuPerc cpu) {
//        System.out.println("CPU用户使用率:    " + CpuPerc.format(cpu.getUser()));// 用户使用率
//        System.out.println("CPU系统使用率:    " + CpuPerc.format(cpu.getSys()));// 系统使用率
//        System.out.println("CPU当前等待率:    " + CpuPerc.format(cpu.getWait()));// 当前等待率
//        System.out.println("CPU当前错误率:    " + CpuPerc.format(cpu.getNice()));//
//        System.out.println("CPU当前空闲率:    " + CpuPerc.format(cpu.getIdle()));// 当前空闲率
//        System.out.println("CPU总的使用率:    " + CpuPerc.format(cpu.getCombined()));// 总的使用率
//    }
//
//    private static void os() {
//        OperatingSystem OS = OperatingSystem.getInstance();
//        // 操作系统内核类型如： 386、486、586等x86
//        System.out.println("操作系统:    " + OS.getArch());
//        System.out.println("操作系统CpuEndian():    " + OS.getCpuEndian());//
//        System.out.println("操作系统DataModel():    " + OS.getDataModel());//
//        // 系统描述
//        System.out.println("操作系统的描述:    " + OS.getDescription());
//        // 操作系统类型
//        // System.out.println("OS.getName():    " + OS.getName());
//        // System.out.println("OS.getPatchLevel():    " + OS.getPatchLevel());//
//        // 操作系统的卖主
//        System.out.println("操作系统的卖主:    " + OS.getVendor());
//        // 卖主名称
//        System.out.println("操作系统的卖主名:    " + OS.getVendorCodeName());
//        // 操作系统名称
//        System.out.println("操作系统名称:    " + OS.getVendorName());
//        // 操作系统卖主类型
//        System.out.println("操作系统卖主类型:    " + OS.getVendorVersion());
//        // 操作系统的版本号
//        System.out.println("操作系统的版本号:    " + OS.getVersion());
//    }
//
//    private static void who() throws SigarException {
//        Sigar sigar = new Sigar();
//        Who who[] = sigar.getWhoList();
//        if (who != null && who.length > 0) {
//            for (int i = 0; i < who.length; i++) {
//                // System.out.println("当前系统进程表中的用户名" + String.valueOf(i));
//                Who _who = who[i];
//                System.out.println("用户控制台:    " + _who.getDevice());
//                System.out.println("用户host:    " + _who.getHost());
//                // System.out.println("getTime():    " + _who.getTime());
//                // 当前系统进程表中的用户名
//                System.out.println("当前系统进程表中的用户名:    " + _who.getUser());
//            }
//        }
//    }
//
//    private static void file() throws Exception {
//        Sigar sigar = new Sigar();
//        FileSystem fslist[] = sigar.getFileSystemList();
//        for (int i = 0; i < fslist.length; i++) {
//            System.out.println("分区的盘符名称" + i);
//            FileSystem fs = fslist[i];
//            // 分区的盘符名称
//            System.out.println("盘符名称:    " + fs.getDevName());
//            // 分区的盘符名称
//            System.out.println("盘符路径:    " + fs.getDirName());
//            System.out.println("盘符标志:    " + fs.getFlags());//
//            // 文件系统类型，比如 FAT32、NTFS
//            System.out.println("盘符类型:    " + fs.getSysTypeName());
//            // 文件系统类型名，比如本地硬盘、光驱、网络文件系统等
//            System.out.println("盘符类型名:    " + fs.getTypeName());
//            // 文件系统类型
//            System.out.println("盘符文件系统类型:    " + fs.getType());
//
//            switch (fs.getType()) {
//                case 0: // TYPE_UNKNOWN ：未知
//                    break;
//                case 1: // TYPE_NONE
//                    break;
//                case 2: // TYPE_LOCAL_DISK : 本地硬盘
//                    FileSystemUsage usage = sigar.getFileSystemUsage(fs.getDirName());
//                    // 文件系统总大小
//                    System.out.println(fs.getDevName() + "总大小:    " + usage.getTotal() + "KB");
//                    // 文件系统剩余大小
//                    System.out.println(fs.getDevName() + "剩余大小:    " + usage.getFree() + "KB");
//                    // 文件系统可用大小
//                    System.out.println(fs.getDevName() + "可用大小:    " + usage.getAvail() + "KB");
//                    // 文件系统已经使用量
//                    System.out.println(fs.getDevName() + "已经使用量:    " + usage.getUsed() + "KB");
//                    double usePercent = usage.getUsePercent() * 100D;
//                    // 文件系统资源的利用率
//                    System.out.println(fs.getDevName() + "资源的利用率:    " + usePercent + "%");
//
//                    System.out.println(fs.getDevName() + "读出：    " + usage.getDiskReads());
//                    System.out.println(fs.getDevName() + "写入：    " + usage.getDiskWrites());
//                    break;
//                case 3:// TYPE_NETWORK ：网络
//                    break;
//                case 4:// TYPE_RAM_DISK ：闪存
//                    break;
//                case 5:// TYPE_CDROM ：光驱
//                    break;
//                case 6:// TYPE_SWAP ：页面交换
//                    break;
//            }
//
//        }
//        return;
//    }
//
//    private static void net() throws Exception {
//        Sigar sigar = new Sigar();
//        String ifNames[] = sigar.getNetInterfaceList();
//        for (int i = 0; i < ifNames.length; i++) {
//            String name = ifNames[i];
//            NetInterfaceConfig ifconfig = sigar.getNetInterfaceConfig(name);
//            System.out.println("网络设备名:    " + name);// 网络设备名
//            System.out.println("IP地址:    " + ifconfig.getAddress());// IP地址
//            System.out.println("子网掩码:    " + ifconfig.getNetmask());// 子网掩码
//            if ((ifconfig.getFlags() & 1L) <= 0L) {
//                System.out.println("!IFF_UP...skipping getNetInterfaceStat");
//                continue;
//            }
//            NetInterfaceStat ifstat = sigar.getNetInterfaceStat(name);
//            System.out.println(name + "接收的总包裹数:" + ifstat.getRxPackets());// 接收的总包裹数
//            System.out.println(name + "发送的总包裹数:" + ifstat.getTxPackets());// 发送的总包裹数
//            System.out.println(name + "接收到的总字节数:" + ifstat.getRxBytes());// 接收到的总字节数
//            System.out.println(name + "发送的总字节数:" + ifstat.getTxBytes());// 发送的总字节数
//            System.out.println(name + "接收到的错误包数:" + ifstat.getRxErrors());// 接收到的错误包数
//            System.out.println(name + "发送数据包时的错误数:" + ifstat.getTxErrors());// 发送数据包时的错误数
//            System.out.println(name + "接收时丢弃的包数:" + ifstat.getRxDropped());// 接收时丢弃的包数
//            System.out.println(name + "发送时丢弃的包数:" + ifstat.getTxDropped());// 发送时丢弃的包数
//        }
//    }
//
//    private static void ethernet() throws SigarException {
//        Sigar sigar = null;
//        sigar = new Sigar();
//        String[] ifaces = sigar.getNetInterfaceList();
//        for (int i = 0; i < ifaces.length; i++) {
//            NetInterfaceConfig cfg = sigar.getNetInterfaceConfig(ifaces[i]);
//            if (NetFlags.LOOPBACK_ADDRESS.equals(cfg.getAddress()) || (cfg.getFlags() & NetFlags.IFF_LOOPBACK) != 0
//                    || NetFlags.NULL_HWADDR.equals(cfg.getHwaddr())) {
//                continue;
//            }
//            System.out.println(cfg.getName() + "IP地址:" + cfg.getAddress());// IP地址
//            System.out.println(cfg.getName() + "网关广播地址:" + cfg.getBroadcast());// 网关广播地址
//            System.out.println(cfg.getName() + "网卡MAC地址:" + cfg.getHwaddr());// 网卡MAC地址
//            System.out.println(cfg.getName() + "子网掩码:" + cfg.getNetmask());// 子网掩码
//            System.out.println(cfg.getName() + "网卡描述信息:" + cfg.getDescription());// 网卡描述信息
//            System.out.println(cfg.getName() + "网卡类型" + cfg.getType());//
//        }


}