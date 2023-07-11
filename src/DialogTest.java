import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class DialogTest {
    public static void main(String[] args)
    {
        DialogFrame frame = new DialogFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
 class DialogFrame extends JFrame {
    public DialogFrame() {
        setTitle("DialogTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
//Создание меню File
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
//добавление пунктов меню About и Exit
//при выборе пункта About отображается
//диалоговое окно About
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event)
            {
                if (dialog == null) //первый раз
                    dialog = new AboutDialog(DialogFrame.this);
                dialog.setVisible(true);
            }
        });
        fileMenu.add(aboutItem);
//при активизации пункта Exit программа завершается
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event)
            {
                System.exit(0);
            }
        });
        fileMenu.add(exitItem);
    }
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 200;
    private AboutDialog dialog;
}
 class AboutDialog extends JDialog {
    public AboutDialog(JFrame owner) {
        super(owner, "About DialogTest", true);
//метка, содержащая HTML-форматирование, выравнивается
//по центру
        add(new JLabel("<html><hl><i>Сообщение</i></hl></html>"),
                BorderLayout.CENTER);
//при активизации кнопки OK диалоговое окно закрывается
        JButton ok = new JButton("OK");
        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event)
            {
                setVisible(false);
            }
        });
        JPanel panel = new JPanel();
        panel.add(ok);
        add(panel, BorderLayout.SOUTH);
        setSize (250, 150);
    }
}