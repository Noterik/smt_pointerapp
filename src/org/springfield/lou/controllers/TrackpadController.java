package org.springfield.lou.controllers;



import org.json.simple.JSONObject;
import org.springfield.fs.*;
import org.springfield.lou.screen.*;

public class TrackpadController extends Html5Controller {
	
	/**
	 * This controller has been attached to view
	 * @see org.springfield.lou.controllers.Html5Controller#attach(java.lang.String)
	 */
	public void attach(String sel) {
		selector = sel;
 		screen.get(selector).render(); // render this view using mustache
 		
 		// track (condensed) the mouse movement over this object and call pointerMove on change
		screen.get("#trackpad_image").track("mousemove","pointerMove", this); 
	}
	
	 /**
	  * Pointer in this view moved react to it
	  */
	 public void pointerMove(Screen s,JSONObject data) {
		String[] posxy = ((String)data.get("clientXY")).split(","); // unpack from condensed form
		
		 // copy the properties we want from view event to model
		 FsPropertySet ps = new FsPropertySet(); // create a property set
		 ps.setProperty("x",posxy[2]); // add x percentage to property set
		 ps.setProperty("y",posxy[3]); // add y percentage to property set
		 model.setProperties("/shared/pointer/"+screen.getShortId(),ps); // store the properties in the model
	 }
	
 	 
}

