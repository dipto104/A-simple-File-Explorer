
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.io.File;

/**
 * Created by Dipto on 4/28/2017.
 */
public class Mylistview {
    private static Mylistview listinstance = null;
    static public Mylistview getInstance() {
        if (listinstance == null) {
            listinstance = new Mylistview();
        }
        return listinstance;
    }
    public void addjlist()
    {
        String Dir=System.getProperty("user.dir");
        //jList1 = new JList(filearray);
        Fileexplorer.jList1 = new JList(new File(Dir).listFiles());

        Fileexplorer.jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        Fileexplorer.jList1.setCellRenderer(new MyCellRenderer());
        Fileexplorer.jList1.setLayoutOrientation(JList.VERTICAL_WRAP);
        Fileexplorer.jList1.setName("displayList");
        Fileexplorer.jList1.setVisibleRowCount(-1);
        Fileexplorer.jList1.setSize(400,400);
        Fileexplorer.listscroll=new JScrollPane( Fileexplorer.jList1);
        //Dimension d = Fileexplorer.listscroll.getPreferredSize();
       // Fileexplorer.listscroll.setPreferredSize(new Dimension((int)d.getWidth(), (int)d.getHeight()));
        //listscroll.setSize(70,700);*/
        Fileexplorer.content.add( Fileexplorer.listscroll, BorderLayout.CENTER);

        //both=new JScrollPane(listscroll);
    }

}






