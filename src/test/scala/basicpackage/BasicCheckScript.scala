package basicpackage

import io.gatling.core.Predef._
import io.gatling.http.Predef._


class BasicCheckScript extends Simulation {

  val httpConf =http.baseUrl("https://api.openbrewerydb.org")

  val scn = scenario("basicgetscenario").exec(http("basicgetrequest").get("/breweries")
    .check(status.is(200)).check(regex(".*state.*")))

  setUp(scn.inject(atOnceUsers(1))).protocols(httpConf)

}
