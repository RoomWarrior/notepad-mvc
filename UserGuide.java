import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.WindowConstants;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import java.net.URL;
import java.io.IOException;
import java.awt.GridLayout;

public class UserGuide extends JPanel implements TreeSelectionListener {

    private JEditorPane htmlPane;
    private JTree tree;
    private URL helpURL;

    public UserGuide() {
        super(new GridLayout(1, 0));

        // General node
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Getting started with the Notepad MVC Pattern");
        createNodes(top);

        //Create a tree that allows one selection at a time.
        tree = new JTree(top);
        tree.getSelectionModel().setSelectionMode
                (TreeSelectionModel.SINGLE_TREE_SELECTION);
        //Listen for when the selection changes.
        tree.addTreeSelectionListener(this);

        // Create the scroll pane and add the tree to it.
        JScrollPane treeView = new JScrollPane(tree);

        // Create the HTML viewing pane.
        htmlPane = new JEditorPane();
        htmlPane.setEditable(false);
        gettingStarted();
        JScrollPane htmlView = new JScrollPane(htmlPane);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setLeftComponent(treeView);
        splitPane.setRightComponent(htmlView);
        splitPane.setDividerLocation(300);

        // Add split pane
        add(splitPane);

    }

    public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                tree.getLastSelectedPathComponent();
        if (node == null) return;

        Object nodeInfo = node.getUserObject();
        if (node.isLeaf()) {
            ChapterInfo chapter = (ChapterInfo) nodeInfo;
            displayURL(chapter.getChapterURL());
        } else {
            displayURL(helpURL);
        }
    }

    private void gettingStarted() {
        String s = "guide/gettingstarted.html";
        helpURL = getClass().getResource(s);
        if (helpURL == null) {
            System.err.println("Couldn't open help file: " + s);
        }
        displayURL(helpURL);

    }

    private void displayURL(URL url) {
        try {
            if (url != null) {
                htmlPane.setPage(url);
            } else {
                htmlPane.setText("Sorry, Guide Not Found...");
            }
        } catch (IOException e) {
            System.err.println("Bad URL: " + url);
        }

    }


    private void createNodes(DefaultMutableTreeNode top) {
        DefaultMutableTreeNode category = null;
        DefaultMutableTreeNode chapter = null;

        category = new DefaultMutableTreeNode("Run the Notepad MVC Pattern");
        top.add(category);

        chapter = new DefaultMutableTreeNode(new ChapterInfo("Run", "guide/run.html"));
        category.add(chapter);

        chapter = new DefaultMutableTreeNode(new ChapterInfo("Understanding the Program Screen", "guide/app_screen.html"));
        category.add(chapter);

        chapter = new DefaultMutableTreeNode(new ChapterInfo("Quick Access Toolbar", "guide/toolbar.html"));
        category.add(chapter);

        chapter = new DefaultMutableTreeNode(new ChapterInfo("Using Keystroke shortcuts", "guide/shortcuts.html"));
        category.add(chapter);

        category = new DefaultMutableTreeNode("Document Basics");
        top.add(category);

        chapter = new DefaultMutableTreeNode(new ChapterInfo("Creating a New Document", "guide/new.html"));
        category.add(chapter);

        chapter = new DefaultMutableTreeNode(new ChapterInfo("Opening a Document", "guide/open.html"));
        category.add(chapter);

        chapter = new DefaultMutableTreeNode(new ChapterInfo("Saving a Document", "guide/save.html"));
        category.add(chapter);

        chapter = new DefaultMutableTreeNode(new ChapterInfo("Printing a Document", "guide/print.html"));
        category.add(chapter);

        chapter = new DefaultMutableTreeNode(new ChapterInfo("Navigating through a Document", "guide/navigating.html"));
        category.add(chapter);

        category = new DefaultMutableTreeNode("Working With and Editing Text");
        top.add(category);

        chapter = new DefaultMutableTreeNode(new ChapterInfo("Cutting, Copying, and Pasting Text", "guide/edit.html"));
        category.add(chapter);

        chapter = new DefaultMutableTreeNode(new ChapterInfo("Using Find and Clear", "guide/find_clear.html"));
        category.add(chapter);

        chapter = new DefaultMutableTreeNode(new ChapterInfo("Inserting time and date", "guide/insert_date.html"));
        category.add(chapter);

        category = new DefaultMutableTreeNode("Formatting");
        top.add(category);

        chapter = new DefaultMutableTreeNode(new ChapterInfo("Changing Font, Style, Size", "guide/formatting1.html"));
        category.add(chapter);

        chapter = new DefaultMutableTreeNode(new ChapterInfo("Word wrap", "guide/wrap.html"));
        category.add(chapter);

        category = new DefaultMutableTreeNode("View");
        top.add(category);

        chapter = new DefaultMutableTreeNode(new ChapterInfo("Status space", "guide/status_bar.html"));
        category.add(chapter);

        category = new DefaultMutableTreeNode("Copyright&License");
        top.add(category);

        chapter = new DefaultMutableTreeNode(new ChapterInfo("Copyright&License", "guide/copyright.html"));
        category.add(chapter);
    }

    public void addComponents() {

        JFrame frame = new JFrame("User guide");
        frame.setIconImage(new ImageIcon("icons/guide.png").getImage());
        frame.setSize(800, 600);
        frame.setLocation(180, 180);
        frame.add(new UserGuide());

        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        setVisible(true);

    }

    // Show Guide Frame
    public void createShowGUI() {
        addComponents();
    }

}
