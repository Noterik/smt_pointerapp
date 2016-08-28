/* 
*SceneApplication.java
* 
* Copyright (c) 2016 Noterik B.V.

*/
package org.springfield.lou.application.types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springfield.fs.FSList;
import org.springfield.fs.FSListManager;
import org.springfield.fs.FsNode;
import org.springfield.lou.application.*;
import org.springfield.lou.controllers.*;
import org.springfield.lou.homer.LazyHomer;
import org.springfield.lou.screen.*;
import org.springfield.lou.servlet.LouServlet;

public class PointerApplication extends Html5Application {
	
	/**
	 * Main of the pointer (server) app, in this case based on
	 * a parameter becomes either a trackpad or presenter. This
	 * can now also be just 2 apps (wars) and use model to communicate.
	 */
 	public PointerApplication(String id) {
		super(id); 
	}
 	
 	/**
 	 * A new screen has joined our appliction url
 	 *  
 	 * @see org.springfield.lou.application.Html5Application#onNewScreen(org.springfield.lou.screen.Screen)
 	 */
    public void onNewScreen(Screen s) {
			s.get("#screen").attach(new ScreenController()); // add the screen tag (backwards comp)
			
    		String view = s.getParameter("view"); // read the parameter from url
    		if (view!=null && view.equals("trackpad")) { // what do we want to be ?
    			s.get("#screen").append("div","trackpad",new TrackpadController()); // become trackpad
    		} else {
				s.get("#screen").append("div","presenter",new PresenterController()); // become presenter
    		}

			
     }
    
    

}
