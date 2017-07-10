import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.AbstractTableModel;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Dipto on 4/27/2017.
 */
class Mytable extends AbstractTableModel {

    private File[] files;
    private FileSystemView fileSystemView = FileSystemView.getFileSystemView();
    private String[] columns = {
            "Icon",
            "File",
            "Size",
            "Last Modified",
    };

    Mytable() {
        this(new File[0]);
    }

    Mytable(File[] files) {
        this.files = files;
    }

    public Object getValueAt(int row, int column) {
        File file = files[row];
        SimpleDateFormat sdf = new SimpleDateFormat("MM / dd / yyyy");
        String date;
        if(column==0){
            return fileSystemView.getSystemIcon(file);
        }
        else if (column==1){
            return fileSystemView.getSystemDisplayName(file);
        }
        else if(column==2){
            return file.length();
        }
        else if(column==3){
            date = sdf.format(file.lastModified());
            return date;
        }
        else if(column==7){
            return file;
        }
        return "";
    }



    public int getColumnCount() {
        return columns.length;
    }

    public Class getColumnClass(int column) {
       if(column==0)
       {
           return ImageIcon.class;
       }

        return String.class;
    }

    public String getColumnName(int column) {
        return columns[column];
    }

    public int getRowCount() {
        return files.length;
    }

    public void setFiles(File[] files) {
        this.files = files;
        fireTableDataChanged();
    }
}
