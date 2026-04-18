package gui.components;

import gui.MissionPresenter;
import javax.swing.*;

public class FileOpenButton extends JButton {

    public FileOpenButton(MissionPresenter presenter) {
        super("Открыть файл миссии");
        setToolTipText("Выбрать файл миссии");
        addActionListener(e -> presenter.onOpenFileClicked());
    }

}