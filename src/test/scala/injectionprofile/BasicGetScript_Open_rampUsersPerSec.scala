package injectionprofile

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class BasicGetScript_Open_rampUsersPerSec extends Simulation {

  val httpConf = http.baseUrl("http://newtours.demoaut.com")

  val scn = scenario("basicgetscenario").forever() {
    pace(2)
      .exec(http("basicgetrequest").get("/mercurycruise.php"))
  }

  setUp(scn.inject(rampUsersPerSec(1) to(5) during(10 seconds))).protocols(httpConf).maxDuration(45)
}
