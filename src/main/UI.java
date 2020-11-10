package main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class UI {
    /**
     * 创建窗体
     */
    private JTable table;

    public JTable getTable() {
        return table;
    }

    public void createAndShowGUI() {
        // 创建 JFrame 实例 ,参数为窗体的title
        JFrame frame = new JFrame("Host添加工具");
        frame.setSize(700, 160);
        //禁止缩放窗口
        frame.setResizable(false);
        //jdk1.4之后的方法,输入null就会设置窗口出现在屏幕中央
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /* 创建面板，这个类似于 HTML 的 div 标签
         * 我们可以创建多个面板并在 JFrame 中指定位置
         * 面板中我们可以添加文本字段，按钮及其他组件。
         */
        JPanel panel = new JPanel();
        // 添加面板
        frame.add(panel);
        //调用用户定义的方法并添加组件到面板
        placeComponents(panel);
        //frame.pack();   使得窗口自动放大到能放下组件
        // 设置界面可见
        frame.setVisible(true);
    }

    /**
     * 给窗体 布置组件
     *
     * @param panel
     */
    private void placeComponents(JPanel panel) {
        /* 布局部分我们这边不多做介绍
         * 这边设置布局为 null
         */
        //panel.setLayout(null);
        panel.setLayout(new BorderLayout());
        // 创建 JLabel
        // JLabel为文本标签
        //JLabel userLabel = new JLabel("User:");
        /* 这个方法定义了组件的位置。
         * setBounds(x, y, width, height)
         * x 和 y 指定左上角的新位置，由 width 和 height 指定新的大小。
         */
        //userLabel.setBounds(10,20,80,25);
        //把组件加入JPanel
        //panel.add(userLabel);
        /*
         * 创建文本域用于用户输入   JTextField 输入框组件   JPasswordField 输入密码组件
         */
        //JTextField userText = new JTextField(20);
        //userText.setBounds(100,20,165,25);
        //panel.add(userText);
        // 创建登录按钮  JButton 按钮

        //设置输入表格
        setTable(panel);
        JButton addButton = new JButton("添加");
        addActionListener(addButton);
        panel.add(addButton, BorderLayout.EAST);
    }

    /**
     * 在输入的面板中加入 定制的表格
     *
     * @param panel
     */
    private void setTable(JPanel panel) {
        Object[][] tableData =
                {
                        new Object[]{"", ""},
                        new Object[]{"", ""},
                        new Object[]{"", ""},
                        new Object[]{"", ""},
                        new Object[]{"", ""},
                        new Object[]{"", ""},
                };
        //定义一维数据作为列标题
        Object[] columnTitle = {"ip地址", "域名"};
        JTable table = new JTable(tableData, columnTitle);
        //将JTable对象放在JScrollPane中，并将该JScrollPane放在窗口中显示出来
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        this.table = table;
    }

    private void addActionListener(JButton button) {
        //System.out.println(loginButton.getText());
        // 为按钮绑定监听器
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Data> list = getData(getTable());
                if(list == null || list.size() == 0){
                    JOptionPane.showMessageDialog(new Frame(),"格式错误,请确认格式正确后重试!");
                    return;
                }
                Function.writeHostsFile(list);
            }
        });
    }

    private List<Data> getData(JTable table) {
        TableModel tableModel = table.getModel();
        List<Data> list = new ArrayList<>();
        if(tableModel.getValueAt(0,0).equals("") || tableModel.getValueAt(0,1).equals(""))
            return null;
        for (int i = 0; i < 5; i++) {
            if(tableModel.getValueAt(i,0)=="" || tableModel.getValueAt(i,1)=="")
                break;
            Data data = new Data(tableModel.getValueAt(i,0).toString(),tableModel.getValueAt(i,1).toString());
            list.add(data);
        }
        return list;
    }
}
