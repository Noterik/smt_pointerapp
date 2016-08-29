package org.springfield.lou.controllers;

public class PresenterController extends Html5Controller {

	/**
	 * This controller has been attached to view
	 * @see org.springfield.lou.controllers.Html5Controller#attach(java.lang.String)
	 */
	public void attach(String sel) {
		selector = sel;
 		screen.get(selector).render(); // render this view using mustache
 		
 		//TODO: Setting a <div> on the server side? Why not let the client handle this? This is a hard dependency to HTML.
		screen.get(selector).append("div","pointers",new PointersController());	 // attach the (overlay) pointers
	}
	
 	 
}

