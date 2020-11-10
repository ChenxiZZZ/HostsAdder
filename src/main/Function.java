package main;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Function {

    public static void writeHostsFile(List<Data> dataList){
        FileWriter writer = null;
        String hostsPath = "C:\\Windows\\System32\\drivers\\etc\\hosts";
        //C:\Users\72724\Desktop\hosts
        try {
            //
            writer = new FileWriter(hostsPath,true);
            writer.write("\n");
            for(Data data:dataList){
                writer.write(data.getIp());
                writer.write(" ");
                writer.write(data.getDomain());
                writer.write("\n");
            }
            writer.flush();
            //刷新dns
            cmdFlushDns();
            JOptionPane.showMessageDialog(new Frame(),"添加成功!已刷新dns解析缓存!");
        } catch (IOException e) {
            //添加失败,请尝试手动修改
            e.printStackTrace();
            JOptionPane.showMessageDialog(new Frame(),"添加失败,请尝试手动修改!");
        }finally {
            try {
                if(writer != null)
                    writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 调用cmd 刷新dns
     */
    private static void cmdFlushDns(){
        //这里的&&在多条语句的情况下使用，表示等上一条语句执行成功后在执行下一条命令，
        //也可以使用&表示执行上一条后台就立刻执行下一条语句
        try {
            Process p = Runtime.getRuntime().exec("ipconfig /flushdns");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
