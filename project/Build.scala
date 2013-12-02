import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "meetingapp"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    jdbc,
    anorm,
    "com.typesafe" %% "scalalogging-slf4j" % "1.0.1",
    "de.neuland" % "jade4j" % "0.3.15" from "https://raw.github.com/neuland/jade4j/master/releases/de/neuland/jade4j/0.3.15/jade4j-0.3.15.jar",
    "com.googlecode.concurrentlinkedhashmap" % "concurrentlinkedhashmap-lru" % "1.3.1",
    "org.apache.commons" % "commons-io" % "1.3.2",
    "org.apache.commons" % "commons-jexl" % "2.1.1"
  )


  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here      
    unmanagedResourceDirectories in Compile <+= baseDirectory(_/"app/views")
  )

}
