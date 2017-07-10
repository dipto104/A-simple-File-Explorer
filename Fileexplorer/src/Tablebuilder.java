import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Vector;

/**
 * Created by Dipto on 4/26/2017.
 */
 class Tablebuilder {

    private static Tablebuilder instance = null;
    public static File currentfile;
    static public Tablebuilder getInstance() {
        if (instance == null) {
            instance = new Tablebuilder();
        }
        return instance;
    }

    public void gettable()
    {
        Fileexplorer.table = new JTable();
        //table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        Fileexplorer.table.setAutoCreateRowSorter(true);
        Fileexplorer.table.setShowVerticalLines(false);

        Fileexplorer.table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable)e.getSource();
                    int row = target.getSelectedRow();
                    int column = target.getSelectedColumn();
                    currentfile=(File)Fileexplorer.fileTableModel.getValueAt(row,7);
                    File [] arra=currentfile.listFiles();
                    folderopener(arra);
                }
            }
        });


        Fileexplorer.table.setSize(400,400);
        Fileexplorer.table.setDefaultEditor(Object.class, null);
        Fileexplorer.tableScroll = new JScrollPane(Fileexplorer.table);
        Dimension d = Fileexplorer.tableScroll.getPreferredSize();
        Fileexplorer.tableScroll.setPreferredSize(new Dimension((int)d.getWidth(), (int)d.getHeight()));
        //tableScroll.setSize(70,700);
        Fileexplorer.content.add(Fileexplorer.tableScroll, BorderLayout.EAST);
        //both=new JScrollPane(tableScroll);
        // the File tree


    }



    public void getdedaulttable() {
        //System.out.println(Dir);
        String Dir = System.getProperty("user.dir");
        File file2 = new File(Dir);
        File[] listOfFiles = file2.listFiles();
        if (Fileexplorer.fileTableModel == null) {
            Fileexplorer.fileTableModel = new Mytable();
            Fileexplorer.table.setModel(Fileexplorer.fileTableModel);
        }
        //Fileexplorer.table.getSelectionModel().removeListSelectionListener(Fileexplorer.listSelectionListener);
        Fileexplorer.fileTableModel.setFiles(listOfFiles);

       //Fileexplorer.table.getSelectionModel().addListSelectionListener(Fileexplorer.listSelectionListener);


    }
    public  void folderopener(File [] array)
    {

        //File file2 = new File(s);
        //File[] listOfFiles = file2.listFiles();
        if (Fileexplorer.fileTableModel == null) {
            Fileexplorer.fileTableModel = new Mytable();
            Fileexplorer.table.setModel(Fileexplorer.fileTableModel);
        }
        Fileexplorer.jList1.setListData(array);
        //Fileexplorer.table.getSelectionModel().removeListSelectionListener(Fileexplorer.listSelectionListener);
        Fileexplorer.fileTableModel.setFiles(array);
        //Fileexplorer.table.getSelectionModel().addListSelectionListener(Fileexplorer.listSelectionListener);

    }




    public void getupdatetable( File[] files) {


        if (Fileexplorer.fileTableModel == null) {
            Fileexplorer.fileTableModel = new Mytable();
            Fileexplorer.table.setModel(Fileexplorer.fileTableModel);
        }
        //Fileexplorer.table.getSelectionModel().removeListSelectionListener(Fileexplorer.listSelectionListener);
        Fileexplorer.fileTableModel.setFiles(files);
       // Fileexplorer.table.getSelectionModel().addListSelectionListener(Fileexplorer.listSelectionListener);




    }

}



