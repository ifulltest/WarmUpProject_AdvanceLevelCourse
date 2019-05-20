package basicpackage

import ch.qos.logback.classic.{Level, LoggerContext}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.slf4j.LoggerFactory

class BasicGetScript_Logback extends Simulation {

  val context: LoggerContext = LoggerFactory.getILoggerFactory.asInstanceOf[LoggerContext]

  // Log all HTTP requests
  context.getLogger("io.gatling.http").setLevel(Level.valueOf("TRACE"))

  val httpConf =http.baseUrl("https://api.openbrewerydb.org")

  val scn = scenario("basicgetscenario").exec(http("basicgetrequest").get("/breweries"))

  setUp(scn.inject(atOnceUsers(1))).protocols(httpConf)

}
