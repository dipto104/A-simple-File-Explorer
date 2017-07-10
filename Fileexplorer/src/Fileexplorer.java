/**
 * Created by Dipto on 4/23/2017.
 */




import javafx.scene.control.TableSelectionModel;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import javax.swing.table.*;
import javax.swing.filechooser.FileSystemView;

import javax.imageio.ImageIO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import java.io.*;
import java.nio.channels.FileChannel;

import java.net.URL;
import java.util.Vector;


public class Fileexplorer  extends javax.swing.JFrame {


    public JFrame scene;
    public JLabel label;
    public static Container content;
    public Container container2;
    public JMenuBar menubar;
    public JMenu menu;
    public JMenuItem menuitem1;
    public JMenuItem menuitem2;
    public static JTextField field;
    public static JList jList1;
    public int flag=0;
    public static Tablebuilder tableset= Tablebuilder.getInstance();
    public Mylistview listviewob= Mylistview.getInstance();
    public Mytree treeob= Mytree.getInstance();
    public JScrollPane both;
    //public Tilebuilder tileset=new Tilebuilder();

    public static JScrollPane tableScroll;
    public static JScrollPane listscroll;
    public static JScrollPane treeScroll;
    public static Mytable fileTableModel;




    public static FileSystemView fileSystemView;


    public static JTree tree;
    public static DefaultTreeModel treeModel;


    public static JTable table;


    public static ListSelectionListener listSelectionListener;



    public Fileexplorer()
    {
        scene=new JFrame();
        label=new JLabel();
        content=new Container();
        fileSystemView = FileSystemView.getFileSystemView();
        //content.setLayout(new GridLayout());
        content.setLayout(new BorderLayout());

        getmenu();
        treeob.gettree();
        tableset.gettable();
        //getlist();
        //tileset.addjlist(jList1,listscroll,content);
       // tableset=Tablebuilder.getInstance(table,);
        tableset.getdedaulttable();

        listviewob.addjlist();

        jList1.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()){
                    JList source = (JList)event.getSource();
                   File fils = (File)source.getSelectedValue();
                   File[] arra=fils.listFiles();
                   //listviewob.updatejlist(arra);
                    jList1.setListData(arra);
                    tableset.getupdatetable(arra);
                }
            }
        });


        String Dir=System.getProperty("user.dir");
        field=new JTextField(Dir);
        field.setSize(10,10);
        content.add(field,BorderLayout.PAGE_START);


        scene.add(content);
        scene.setVisible(true);
        tableScroll.setVisible(true);
        listscroll.setVisible(false);
        scene.setSize( 1300,
                800);

        scene.pack();

    }

    public void getmenu(){

        menubar=new JMenuBar();
        menu =new JMenu("option");
        menuitem1=new JMenuItem("Tableview");
        menuitem2=new JMenuItem("Listview");
        menu.add(menuitem1);
        menu.add(menuitem2);
        menubar.add(menu);
        scene.setJMenuBar(menubar);
        menuitem1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                //jList1.setVisible(false);
                tableScroll.setVisible(true);
                listscroll.setVisible(false);
                scene.pack();
                //content.remove(listscroll);
               // content.add(tableScroll,BorderLayout.CENTER);
                //both=new JScrollPane(tableScroll);
            }
        });
        menuitem2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                //jList1.setVisible(false);
                listscroll.setVisible(true);
                tableScroll.setVisible(false);
                scene.pack();
                //content.remove(tableScroll);
               // content.add(listscroll,BorderLayout.CENTER);
                //both=new JScrollPane(listscroll);

            }
        });




    }








    public static void main(String[] args) {



        Fileexplorer frame = new Fileexplorer();
               // frame.setVisible(true);
    }

}




