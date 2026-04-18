import gui.MissionAnalyzerGUI;
import javax.swing.SwingUtilities;
import service.FacadeMissionService;
import service.MissionService;

/**
 *
 * @author 79031
 */

public class Laba1 {
    public static void main(String[] args) {
        FacadeMissionService service = new MissionService();
        SwingUtilities.invokeLater(() -> new MissionAnalyzerGUI(service).setVisible(true));
    }
}
