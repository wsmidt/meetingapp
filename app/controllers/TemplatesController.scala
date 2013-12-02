package controllers

import play.api.mvc.Action
import com.typesafe.scalalogging.slf4j

object TemplatesController extends JadeController with slf4j.Logging {
  
  def at(fName: String) = Action { implicit request =>
    val templateStr: String = fName + ".jade"
    logger.debug(s"Retrieving template: $templateStr")
    render(templateStr) match {
      case Some(html) => Ok(html)
      case None => NotFound
    }
  }

}
