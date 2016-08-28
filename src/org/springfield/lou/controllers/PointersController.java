package org.springfield.lou.controllers;


import org.json.simple.*;
import org.springfield.fs.*;
import org.springfield.lou.model.*;

public class PointersController extends Html5Controller {
	
	private FSList pointers = new FSList(); // list of pointers in this app

	/**
	 * Pointers have been attached to view
	 * @see org.springfield.lou.controllers.Html5Controller#attach(java.lang.String)
	 */
	public void attach(String sel) {
		selector = sel;
		// call onPointerChange model changes for one of the pointers 
		model.onPropertiesUpdate("/shared/pointer/*","onPointerChange",this);
	}
	
	
	/**
	 * Callback for when a pointer has changes in the model
	 */
	public void onPointerChange(ModelEvent e) {
		FsPropertySet set = (FsPropertySet)e.target; // get the target object for the event
		
		// check if we already know this pointer if not add it to our list
		System.out.println("PATH="+e.path);
		FsNode pointer = pointers.getNode(e.path); // check if we have it based on targets id
		if (pointer==null) { 
			pointer = new FsNode(""); // its a new pointer so create a node
			pointer.setPath(e.path); // set the path to the node (is ugly should be able to set in new call)
			pointers.addNode(pointer); // add node to the list for next time
		}
		
		// now we have either the found (old) or just created pointer
		pointer.setProperty("x",set.getProperty("x")); // set its new model x to the pointer for rendering
		pointer.setProperty("y",set.getProperty("y")); // set its new model y to the pointer for rendering
		
		JSONObject data = pointers.toJSONObject("en","x,y"); // convert Fs object to JSON for rendering
 		screen.get(selector).render(data); // tell the view (mustache) to render with the new data
	}
		
 	 
}

