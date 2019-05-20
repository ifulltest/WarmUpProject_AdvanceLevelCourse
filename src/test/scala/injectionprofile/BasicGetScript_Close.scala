package injectionprofile

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class BasicGetScript_Close extends Simulation {

  val httpConf =http.baseUrl("https://api.openbrewerydb.org")

  val scn = scenario("basicgetscenario").forever() {
    pace(5)
      .exec(http("basicgetrequest").get("/breweries"))
  }


setUp(scn.inject(
    constantConcurrentUsers(10) during(10 seconds),
    rampConcurrentUsers(10) to(20) during(10 seconds)
  )).protocols(httpConf).maxDuration(60 seconds)
}
