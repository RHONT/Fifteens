import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Game extends JFrame { // класс Main в данной работе называют прямым наследником класса JFrame

    /*При построении интерфейсов нужны компоненты-контерйнеры, которые будут содержать другие компоненты
    пользовательского интерфейса. В Swing одним из таких компонентов-контейнеров является JPanel.
    Класс GridLayout позволяет размещать компоненты в контейнере в виде таблицы. В каждой ячейке таблицы может быть размещен только один компонент.
    Количество строк и столбцов таблицы определяется или в конструкторе, или вызовом методов setColumns и setRows.*/

    private JPanel panel = new JPanel(new GridLayout(4, 4, 2, 2));

    /*Главное меню JMenuBar - компонент графического интерфейса Java Swing*/

    private JMenuBar menu = null;
    //private JMenuBar help = null;
    private final String fileMessage[] = new String[]{"Новая", "Выход"};
    private final String helpMessage = "Об Авторе";
    private static Random generator = new Random(); // генератор случайных чисел
    private int[][] numbers = new int[4][4];

    /* -=== Опредиление клиентской ширины экрана ===- */


    public Game() {
        setTitle("Пятнашки"); //Заголовок окна

        setSize(300, 300); // Задаем размеры окна приложения
        setLocationRelativeTo(null); // Окно приложения центрируется относительно экрана

        setResizable(true); // задаем возможность растягивать окно
        createMenu(); //инициализируем меню

        setJMenuBar(menu); // добавляем панель меню к окну
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // закрываем программу при закрытии окна

        //Класс container — прямой подкласс класса component, и наследует все его методы.
        //Каждый компонент перед выводом на экран помещается в контейнер (container). Контейнер "знает", как разместить компоненты на экране.
        /*Создав компонент — объект класса Component или его расширения, следует добавить его к предварительно созданному объекту класса container
        или его расширения одним из методов add (). */
        this.addKeyListener(new NewKeyListener());

        Container container = getContentPane();
        init();
        panel.setDoubleBuffered(true);
        panel.setBackground(Color.red); // устанавливаем цвет фона
        container.add(panel); // добавление компонентов в контейнер
        repaintField();
        JLabel();

    }

    public void init() { // описание метода init
        int[] invariants = Tests.getShuffleArray();
        int idx = 0;

        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers[i].length; j++) {
                numbers[i][j] = invariants[idx];
                idx++;
            }
        }
    }

    public void JLabel() {
        Border solidBorder = BorderFactory.createLineBorder(Color.BLACK, 1); // создаем границу черного цвета
        Font font = new Font("Verdana", Font.PLAIN, 12); // задаем тип шрифта, и его размер
        JLabel topLabel = new JLabel(); // создаем обьект topLabel типа JLabel
        topLabel.setBorder(solidBorder); // устанавливаем границу
        topLabel.setFont(font); // устанавливаем тип текста
        topLabel.setForeground(Color.RED); // Устанавливаем цвет текста
        menu.add(topLabel); // добавляем JLabel на пенель menu
    }


    public void repaintField() {  //метод расстановки кнопок со значениями на сетке
        panel.removeAll();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                JButton button = new JButton(Integer.toString(numbers[i][j]));
                button.setFocusable(false);
                panel.add(button);
                button.setBackground(Color.getHSBColor(0.1059322f, 0.5221239f, 0.8862745f)); // устанавливаем цвет кнопок
                if (numbers[i][j] == 0) {
                    button.setVisible(false); // сокрытие нулевого элемента массива

                } else
                    button.addActionListener(new ClickListener());
            }
        }


        panel.validate();
    }

    public boolean checkWin() { //метод проверки победы
        boolean status = true;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == 3 && j > 2) //проверка на то, что последняя ячейка в сетке пустая
                    break;
                if (numbers[i][j] != i * 4 + j + 1) { //проверка на соотвествие элементам массива координатам в сетке
                    status = false;
                }
            }
        }
        return status;
    }

    private void createMenu() {
        menu = new JMenuBar();
        NewMenuListener listener = new NewMenuListener();

        JMenu fileMenu = new JMenu("File");
        JMenuItem item_1 = new JMenuItem("New");
        item_1.setActionCommand("new");
        item_1.addActionListener(listener);


        JMenuItem item_2 = new JMenuItem("Exit");
        item_2.addActionListener(listener);
        item_2.setActionCommand("exit");
        item_2.addActionListener(listener);

        fileMenu.add(item_1);
        fileMenu.add(new JSeparator());
        fileMenu.add(item_2);

        menu.add(fileMenu);

        JMenu helpMenu = new JMenu("Help");
        JMenuItem item_3 = new JMenuItem("About author");
        item_3.setActionCommand("about");
        item_3.addActionListener(listener);
        helpMenu.add(item_3);
        menu.add(helpMenu);
    }

    private class NewKeyListener extends KeyAdapter {
//        Component[] components = panel.getComponents();

        @Override
        public void keyReleased(KeyEvent e) {

            if (e.getKeyCode() == KeyEvent.VK_UP) {
                doMovie(KeyEvent.VK_UP, panel.getComponents());
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                doMovie(KeyEvent.VK_DOWN, panel.getComponents());

            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                doMovie(KeyEvent.VK_LEFT, panel.getComponents());
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                doMovie(KeyEvent.VK_RIGHT, panel.getComponents());
            }

        }
    }

    private void doMovie(int command, Component[] components) {
        Integer findIdxPosition = findText(command);

        if (findIdxPosition != null) {
            JButton b = (JButton) components[findIdxPosition];
            b.doClick();
        }
    }


    private Integer findText(int command) {
        int calcResult;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (numbers[i][j] == 0) {

                    if (command == KeyEvent.VK_UP) {
                        calcResult = i * numbers.length + j + numbers.length;
                        if (calcResult > 15) {
                            return null;
                        } else return calcResult;
                    }

                    if (command == KeyEvent.VK_DOWN) {
                        calcResult = i * numbers.length + j - numbers.length;
                        if (calcResult < 0) {
                            return null;
                        } else return calcResult;
                    }

                    if (command == KeyEvent.VK_LEFT) {

                        if (j + 1 < 4) {
                            calcResult = i * numbers.length + j + 1;
                            return calcResult;

                        } else return null;
                    }

                    if (command == KeyEvent.VK_RIGHT) {
                        if (j - 1 >= 0) {
                            calcResult = i * numbers.length + j - 1;
                            return calcResult;

                        } else return null;
                    }
                }

            }

        }

        return null;
    }


    private class NewMenuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if ("exit".equals(command)) {
                System.exit(0);
            }
            if ("about".equals(command)) {
                JOptionPane.showMessageDialog(null, "Белова Анастасия\nГруппа 111\n2023 год");
            }
            if ("new".equals(command)) {
                init();
                repaintField();
            }//
        }
    }

    private class ClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            button.setVisible(false);
            String name = button.getText();
            change(Integer.parseInt(name));
        }
    }

    public void change(int num) { // передаем в качестве входящих параметров метода change переменную num типа int
        int i = 0, j = 0; // присваиваем переменным i и j типа int значение равное 0
        for (int k = 0; k < 4; k++) { // перебираем элементы k от 0 до 3
            for (int l = 0; l < 4; l++) { // перебираем элементы l от 0 до 3
                if (numbers[k][l] == num) { // если массив numbers[k][l] равен переменной num то,
                    i = k; // переменную i приравниваем переменной k
                    j = l; // переменную j приравниваем переменной l
                }
            }
        }

        /*реализация логики сдвигов кнопок на сетке 4 Х 4*/
        //сдвиг вверх по строкам
        if (i > 0) { // условие отвечающее за то можно ли сдвинуть кнопку по строке
            if (numbers[i - 1][j] == 0) { //сравниваем значение координат элемента массива с кнопкой которая в текущем массиве равна нулю
                numbers[i - 1][j] = num; //присваиваем переменной num значение координат элемента массива
                numbers[i][j] = 0; //присваиваем нулевой элемент массива в ячейку которая перед этим смещалась в ноль
            }
        }
        //сдвиг вниз по строкам
        if (i < 3) {
            if (numbers[i + 1][j] == 0) {
                numbers[i + 1][j] = num;
                numbers[i][j] = 0;
            }
        }
        //сдвиг влево по столбцам
        if (j > 0) {
            if (numbers[i][j - 1] == 0) {
                numbers[i][j - 1] = num;
                numbers[i][j] = 0;
            }
        }
        //сдвиг вправо по столбцам
        if (j < 3) {
            if (numbers[i][j + 1] == 0) {
                numbers[i][j + 1] = num;
                numbers[i][j] = 0;
            }
        }
        repaintField();
        if (checkWin()) {
            JOptionPane.showMessageDialog(null, "ВЫ ВЫИГРАЛИ!", "Поздравляем", 1);
            init();
            repaintField();
            setVisible(false);
            setVisible(true);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(1000);
        JFrame app = new Game();
        app.setVisible(true);
    }
}