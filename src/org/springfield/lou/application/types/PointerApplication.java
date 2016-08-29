package org.springfield.lou.application.types;
/* 
*SceneApplication.java
* 
* Copyright (c) 2016 Noterik B.V.

*/

/**
 * TODO: Comments by David
 * 
 * This application has a lot less code then my application does, and I'm not going to deny that that's a big pro over my example. 
 * But I also think that's because a lot of the code that's not here is actually put away into LOU and EDDIE, and these will become huge 
 * monsters over time. My application could also just consist of a couple of lines of code, if I put a lot stuff into other applications (or components that 
 * can be loaded when needed). 
 * 
 * The thing that's happening is that in Eddie there's a lot of code like this:
 * 
 *	var dragPosition = draggedElement.position();
 *	var elementPositionLeft = dragPosition.left;
 *	var elementPositionTop = dragPosition.top;
 *
 *	var elementWidth = draggedElement[0].clientWidth;
 *	var elementHeight = draggedElement[0].clientHeight;
 *
 *	edata += ",elementWidth=" + elementWidth;
 *	edata += ",elementHeight=" + elementHeight;
 *	edata += ",elementOffsetTop=" + elementOffsetTop;
 *	edata += ",elementOffsetLeft=" + elementOffsetLeft;
 *	edata += ",elementPositionTop=" + elementPositionTop;
 *	edata += ",elementPositionLeft=" + elementPositionLeft;
 *								
 * 
 * While the concept of having to implement every type of user interaction into just a single file once is enticing, the reality is that
 * it probably won't be possible (or efficient) to put all these functionalities (and combinations of these) into a single file. I think 
 * the actual handling of user input should be handled inside the specific component, so if you want to have a Trackpad component (for 
 * tracking user touches over a certain area) then you should put all the touch/mouse/form/(keyboard/3d mouse) event handling into that 
 * Trackpad component. If a user wants to create a new type of Trackpad, he can extend the Trackpad. 
 *
 * If we put the client specific functionality into a component, we can load this specific component into the specific application and there will 
 * be no dependencies or issues with compatiblity across other applications. 
 * 
 * The pro's of putting all this functionalities into their specific components is that an application builder can really select what types of user
 * interaction they require, instead of loading a huge file in the form of eddie that can do everything. In my opinion Eddie should purely focus on being
 * an interface to the server and really the only function that's required to be reachable on the client side is: eddie.putLou()
 * 
 * What happens if we want to do animations? Are we also going to do this on the server? 
 * 
 * Another issue with the single big file is that if we need to implement some new type of user interaction, we need to implement this into the eddie
 * that is shared among multiple applications. I had to make changes to eddie.js to get it to work properly for the new QANDR version,
 * but I couldn't put it on production yet, because we don't know what will happen to all existing applications. I can't put my application on production yet either
 * because there's a different LOU running there. So, in any application there's a lot of dependencies to outside components (Lou, Eddie, Smithers). If we can 
 * package all dependencies into an application, deployment would also get a lot easier. It's just a matter of deploying an application on a server and all dependencies 
 * are already packaged into the app. The problem might be that the application becomes a bit big, but is this is really an issue? Maven and NPM allow
 * you to define your dependencies in a POM or package.json file and when you actually build the application that's when the dependencies are downloaded.
 * 
 * So every application becomes a portable piece of code that can run on it's own, instead of an application requiring a lot of infrastructure to be set up 
 * before deploying. 
 */
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
 	 * A new screen has joined our application url
 	 *  
 	 * @see org.springfield.lou.application.Html5Application#onNewScreen(org.springfield.lou.screen.Screen)
 	 */
    public void onNewScreen(Screen s) {
		s.get("#screen").attach(new ScreenController()); // add the screen tag (backwards comp)
		
		String view = s.getParameter("view"); // read the parameter from url
		if (view!=null && view.equals("trackpad")) { // what do we want to be ?
			
			//TODO: Hard dependency to HTML, why not just expose an interface with functions that can be called from the client (any type of client)?
			//Why do we want to do this stuff on the server? Because it takes less lines of code?
			s.get("#screen").append("div","trackpad",new TrackpadController()); // become trackpad
		} else {
			s.get("#screen").append("div","presenter",new PresenterController()); // become presenter
		}			
     }
    
    

}
