package controllers

import play.api.mvc.Action
import com.typesafe.scalalogging.slf4j.Logging
import scala.concurrent.duration.Duration

/**
 * User: warner
 * Date: 11/29/13
 * Time: 2:06 PM
 */
object MeetingAppUIController extends JadeController {

  val defaultAction = Action { implicit request =>
    render("layout.jade") match {
      case None => InternalServerError
      case Some(html) => Ok(html)
    }
  }

  def index = defaultAction

  // This method has the same effect as calling index. The path param is ignored and
  // is only here to create a default or catch-all route that redirects to the home page
  def indexWithPath(path: String) = defaultAction
}
