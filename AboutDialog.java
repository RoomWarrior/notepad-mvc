import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.ImageIcon;
import javax.swing.BoxLayout;
import javax.swing.border.EmptyBorder;
import java.awt.Frame;

//The AboutDialog class about software information.
public class AboutDialog extends JDialog {

    public AboutDialog(Frame owner, String title) {
        super(owner, title);

        setIconImage(new ImageIcon("icons/icon.png").getImage());
        setSize(410, 400);
        setLocation(480, 180);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
    }

    // Add components to panel
    public void addComponents() {
        JLabel banner = new JLabel(new ImageIcon("icons/banner.png"));
        JLabel softwareName = new JLabel("Software: " + "Notepad MVC");
        JLabel softwareVersion = new JLabel("Version: " + "Beta 1");
        JLabel userName = new JLabel("User: " + System.getProperty("user.name"));
        JLabel footer = new JLabel("Copyright (C) 2021  |  Team #3  | " +
                "All rights reserved.");

        softwareName.setBorder(new EmptyBorder(15, 15, 15, 0));
        userName.setBorder(new EmptyBorder(15, 15, 15, 0));
        softwareVersion.setBorder(new EmptyBorder(15, 15, 15, 0));
        footer.setBorder(new EmptyBorder(15, 15, 15, 0));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(banner);
        panel.add(userName);
        panel.add(softwareName);
        panel.add(softwareVersion);
        panel.add(footer);

        panel.setVisible(true);

        add(panel);
    }

    // Show AboutDialog
    public void createShowGUI() {
        addComponents();
        setVisible(true);
    }


}


