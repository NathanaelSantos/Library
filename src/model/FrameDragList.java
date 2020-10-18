package model;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

public class FrameDragList extends javax.swing.JFrame {

    public static class FrameDragListener extends MouseAdapter {

        static int x;
        static int y;
        private final JFrame frame;
        private Point mouseDownCompCoords = null;

        public FrameDragListener(JFrame frame) {
            this.frame = frame;
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            mouseDownCompCoords = null;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            mouseDownCompCoords = e.getPoint();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            Point currCoords = e.getLocationOnScreen();

            this.x = currCoords.x - mouseDownCompCoords.x;
            this.y = currCoords.y - mouseDownCompCoords.y;

            frame.setLocation(x, y);
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }
    }
}
