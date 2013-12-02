package controllers

import de.neuland.jade4j.Jade4J
import de.neuland.jade4j.JadeConfiguration
import de.neuland.jade4j.template.{TemplateLoader, FileTemplateLoader, JadeTemplate}
import play.api.mvc.Controller
import play.api.templates.Html
import scala.collection.JavaConversions._
import java.io._
import com.typesafe.scalalogging.slf4j.Logging
import play.api.Play.current
import play.api.Mode
import org.joda.time.DateTime
import org.joda.time.DateTimeZone.UTC
import org.apache.commons.io.FilenameUtils


/**
 * A trait that handles rendering of Jade templates. Mixin this
 * trait and then call the appropriate render functions to render
 * jade templates.
 *
 * Created with IntelliJ IDEA.
 * User: gavares
 * Date: 6/15/13
 * Time: 3:45 PM
 *
 */
trait JadeController extends Controller with Logging {

  // Default map passed to every template
  val defaultMap:Map[String, Object] = Map("mode" -> current.mode.toString)

  val jadeConfig = new JadeConfiguration
  jadeConfig.setSharedVariables( defaultMap )

  val templateLoader = {
    jadeConfig.setMode(Jade4J.Mode.HTML)
    if( current.mode == Mode.Dev ) {
      jadeConfig.setTemplateLoader(new FileTemplateLoader("app/views/", "UTF-8"))
      jadeConfig.setPrettyPrint(true)
    } else {
      //because our .jade files do not get included in the app/views/ dir with 'play dist' we have
      //added app/views/ dir to unmanagedResourceDirectories in Build.scala which will place the contents
      //of app/views/ at the root of the classpath.
      jadeConfig.setTemplateLoader(ClassPathTemplateLoader(""))
    }
  }

  def render(template: String): Option[Html] = {
    render(template, defaultMap)
  }

  def render(template: String, context: Map[String, Object]): Option[Html] = {
    try {
      logger.debug(s"Render template: $template")
      val jadeTemplate: JadeTemplate = jadeConfig.getTemplate(template)
      Some(new Html(new StringBuilder(jadeConfig.renderTemplate(jadeTemplate, context))))
    }
    catch {
      case ex: IOException => {
        logger.debug("Missing file exception: ", ex)
        return None
      }
    }
  }
}

case class ClassPathTemplateLoader(basePath: String) extends TemplateLoader with Logging {
  //the modified date will always be the same because the template is loaded from the jar.
  val modifiedDate: Long = new DateTime(UTC).getMillis
  def getLastModified(name: String): Long = modifiedDate
  def getReader(name: String): Reader = {
    var templatePathStr: String = s"$basePath$name"
    if(hasNoExtension(templatePathStr))
      templatePathStr += ".jade"

    logger.debug(s"Getting Reader for template $templatePathStr")
    val is: InputStream = ClassLoader.getSystemClassLoader.getResourceAsStream(templatePathStr)
    if(is == null) {
      val error = s"ClassLoader failed to load template resource $templatePathStr"
      logger.error(error)
      throw new FileNotFoundException(error)
    }
    new InputStreamReader(is)
  }

  def hasNoExtension(filePath: String): Boolean = {
    "".equals(FilenameUtils.getExtension(filePath))
  }
}


