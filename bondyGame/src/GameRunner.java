import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 ** @author javier
 */
public class GameRunner {

    private static leGame foo;

    public static void main(String[] args) {
        //... Create an initialize the applet.
        foo = new leGame();
        foo.init();
        foo.start();

        JFrame window = new JFrame("leGame");
        window.setPreferredSize(new Dimension(1680, 1050));
        window.setResizable(false);
        window.setContentPane(foo);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);
    }

    public static leGame getMainApplet() {
        return foo;
    }
}