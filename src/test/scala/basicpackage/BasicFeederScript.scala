package basicpackage

import io.gatling.core.Predef._
import io.gatling.http.Predef._


class BasicFeederScript extends Simulation {

  val states= csv("data/state.csv").circular

  val httpConf =http.baseUrl("https://api.openbrewerydb.org")

  val scn = scenario("basicgetscenario").feed(states).exec(http("basicgetrequest").get("/breweries?by_state=${state}"))

  setUp(scn.inject(atOnceUsers(4))).protocols(httpConf)

}
