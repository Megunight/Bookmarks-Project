package ui;

import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {
    Image background = new ImageIcon("data/leather_background.png").getImage();

    public BackgroundPanel() {
        setPreferredSize(new Dimension(600, 500));
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(background, 0, 0, 600, 500, null);
    }
}
