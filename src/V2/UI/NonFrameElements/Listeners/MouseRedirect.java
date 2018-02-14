package V2.UI.NonFrameElements.Listeners;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Aaron on 14.02.2018.
 */
public class MouseRedirect  implements MouseListener {

    Container container;

    public MouseRedirect(Container containerTargetFrame) {
        this.container= containerTargetFrame;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        container.dispatchEvent(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        container.dispatchEvent(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        container.dispatchEvent(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        container.dispatchEvent(e);
    }
}

