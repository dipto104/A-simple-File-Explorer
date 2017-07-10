import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.io.File;
import java.util.*;

/**
 * Created by Dipto on 4/28/2017.
 */
public class Mytree {
    private static  Mytree instance = null;
    static public  Mytree getInstance() {
        if (instance == null) {
            instance = new  Mytree();
        }
        return instance;
    }
    public void gettree() {
        try{
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("MY COMPUTER");
        Fileexplorer.treeModel = new DefaultTreeModel(root);

        TreeSelectionListener treeSelectionListener = new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent tse) {
                DefaultMutableTreeNode node =
                        (DefaultMutableTreeNode) tse.getPath().getLastPathComponent();
                makeleaf(node);
                File file1 = ((File) node.getUserObject());
                Fileexplorer.field.setText(file1.getPath().toString());
            }
        };



        //File[] roots = Fileexplorer.fileSystemView.getRoots();
        File[] roots = File.listRoots();

        for (File fileSystemRoot : roots) {
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(fileSystemRoot);
            root.add(node);


            File[] files = Fileexplorer.fileSystemView.getFiles(fileSystemRoot, true);
            for (File file : files) {
                if (file.isDirectory() || file.isFile()) {
                    node.add(new DefaultMutableTreeNode(file));
                    //field.setText(file.getPath().toString());
                }
            }
            //
        }

        Fileexplorer.tree = new JTree(Fileexplorer.treeModel);
        Fileexplorer.tree.setRootVisible(true);
        //tree.setSize(200,700);

        Fileexplorer.tree.addTreeSelectionListener(treeSelectionListener);


        Fileexplorer.treeScroll = new JScrollPane(Fileexplorer.tree);


        Fileexplorer.tree.setVisibleRowCount(15);


        Fileexplorer.content.add(Fileexplorer.treeScroll, BorderLayout.WEST);
    }catch (Exception e){

        }


    }
    public void makeleaf( DefaultMutableTreeNode node) {



        SwingWorker<Void, File> worker = new SwingWorker<Void, File>() {
            @Override
            public Void doInBackground() {
                File file = (File) node.getUserObject();
                if (file.isDirectory()) {
                    File[] files = Fileexplorer.fileSystemView.getFiles(file, true); //!!
                    if (node.isLeaf()) {
                        for (File child : files) {
                            if (child.isDirectory() || child.isFile()) {
                                publish(child);

                            }
                        }
                    }
                    //flag = 1;
                    Fileexplorer.tableset.getupdatetable(files);
                    Fileexplorer.jList1.setListData(files);

                }
                return null;


            }

            @Override
            protected void process(java.util.List<File> chunks) {
                for (File child : chunks) {
                    node.add(new DefaultMutableTreeNode(child));
                }
            }


        };
        worker.execute();

    }

}
