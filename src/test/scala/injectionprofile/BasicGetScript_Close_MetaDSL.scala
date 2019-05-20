package injectionprofile

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class BasicGetScript_Close_MetaDSL extends Simulation {

  val httpConf =http.baseUrl("https://api.openbrewerydb.org")

  val scn = scenario("basicgetscenario").forever() {
    pace(5)
      .exec(http("basicgetrequest").get("/breweries"))
  }

  setUp(scn.inject(
    incrementConcurrentUsers(5)
      .times(5)
      .eachLevelLasting(10 seconds)
      .separatedByRampsLasting(10 seconds)
      .startingFrom(10)
  )).protocols(httpConf).maxDuration(100 seconds)
}
