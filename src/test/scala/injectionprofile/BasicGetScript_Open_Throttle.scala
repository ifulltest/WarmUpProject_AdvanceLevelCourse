package injectionprofile

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class BasicGetScript_Open_Throttle extends Simulation {

  val httpConf = http.baseUrl("http://newtours.demoaut.com")

  val scn = scenario("basicgetscenario").forever() {
    pace(2)
      .exec(http("basicgetrequest").get("/mercurycruise.php"))
  }

  setUp(scn.inject(constantUsersPerSec(1) during (2 minutes))).protocols(httpConf).throttle(
    reachRps(20) in (60 seconds),
    holdFor(1 minute),
    jumpToRps(10),
    holdFor(1 minute)
  ).maxDuration(3 minutes)
}
