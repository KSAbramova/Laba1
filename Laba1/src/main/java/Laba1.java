import gui.MissionAnalyzerGUI;
import javax.swing.SwingUtilities;

/**
 *
 * @author 79031
 */

public class Laba1 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MissionAnalyzerGUI().setVisible(true);
        });
    }
}
