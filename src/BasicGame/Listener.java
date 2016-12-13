package BasicGame;

import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;

public class Listener implements KeyListener {

	@Override
	public void inputEnded() {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void inputStarted() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isAcceptingInput() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void setInput(Input arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(int arg0, char arg1) {
		// TODO Auto-generated method stub
		//System.out.println("Pressed"+arg0+","+arg1);

		if ( arg0 == Input.KEY_S) {
			if (Debug.show == true) {
				Debug.show = false;
			} else {
				Debug.show= true;
			}
		}
	}

	@Override
	public void keyReleased(int arg0, char arg1) {
		// TODO Auto-generated method stub
		//System.out.println("Released"+arg0+","+arg1);
	}

    
}