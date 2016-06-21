package visualisation;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import org.graphstream.ui.view.Camera;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.view.ViewerListener;
import org.graphstream.ui.view.ViewerPipe;

public class NetworkViewer implements ViewerListener, MouseWheelListener {
	
	private Network network;
	protected boolean loop;
	
    public NetworkViewer(Network network) {
    	this.network = network;
        Viewer viewer = network.view();
        ViewerPipe fromViewer = viewer.newViewerPipe();
        fromViewer.addViewerListener(this);
        fromViewer.addSink(network.graph());

        while(loop) {
            fromViewer.pump();
        }
    }

	@Override
	public void buttonPushed(String id) {
		System.out.println("Button pushed on node " + id);
		
	}

	@Override
	public void buttonReleased(String id) {
		System.out.println("Button released on node " + id);
		
	}

	@Override
	public void viewClosed(String id) {
		loop = false;
		
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		int notches = e.getWheelRotation();
		Camera cam = network.view().getDefaultView().getCamera();
		if (notches < 0) {
			//zoom
			cam.setViewPercent(cam.getViewPercent() - 0.1 * notches);
		} else {
			//pan
			cam.setViewPercent(cam.getViewPercent() + 0.1 * notches);
		}
		
	}

}
