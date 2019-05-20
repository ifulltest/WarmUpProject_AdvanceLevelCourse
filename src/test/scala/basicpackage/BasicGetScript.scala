package basicpackage

import io.gatling.core.Predef._
import io.gatling.http.Predef._


class BasicGetScript extends Simulation {

  val httpConf =http.baseUrl("https://api.openbrewerydb.org")

  val scn = scenario("basicgetscenario").exec(http("basicgetrequest").get("/breweries"))

  setUp(scn.inject(atOnceUsers(1))).protocols(httpConf)

}
