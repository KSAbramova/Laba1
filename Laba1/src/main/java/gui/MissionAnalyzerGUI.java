package gui;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import model.*;
import parser.MissionParseException;
import service.MissionService;

public class MissionAnalyzerGUI extends JFrame {
    private final JTextArea displayArea;
    private final MissionService service = new MissionService();

    public MissionAnalyzerGUI() {
        setTitle("Локальный анализатор миссий");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);

        JButton openBtn = new JButton("Открыть файл миссии (.txt / .json / .xml)");
        openBtn.addActionListener(e -> openFile());

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        setLayout(new BorderLayout());
        add(openBtn, BorderLayout.NORTH);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);
    }

    private void openFile() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try {
                Mission m = service.loadMission(file);
                showMission(m);
            } catch (MissionParseException ex) {
                JOptionPane.showMessageDialog(this,
                        "Ошибка парсинга:\n" + ex.getMessage(),
                        "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void showMission(Mission m) {
        String text = " Миссия: " + m.getMissionId() + "\n\n" +
                      "Дата: " + m.getDate() + "\n" +
                      "Локация: " + m.getLocation() + "\n" +
                      "Исход: " + m.getOutcome() + "\n" +
                      "Ущерб: " + m.getDamageCost() + "\n\n";

        if (m.getNote() != null) {
            text += "Примечание: " + m.getNote() + "\n\n";
        }

        Curse c = m.getCurse();
        text += "Проклятие:\n" +
                " • " + c.getName() + " (" + c.getThreatLevel() + ")\n\n" +
                "Маги (" + m.getSorcerersSize() + "):\n";

        for (Sorcerer s : m.getSorcerers()) {
            text += " • " + s.getName() + " — " + s.getRank() + "\n";
        }

        text += "\n" +
                "Техники (" + m.geTechniqueSize() + "):\n";

        for (Technique t : m.getTechniques()) {
            text += " • " + t.getName() +
                    " (" + t.getType() +
                    ", владелец: " + t.getOwner() +
                    ", урон: " + t.getDamage() + ")\n";
        }

        displayArea.setText(text);
    }
}