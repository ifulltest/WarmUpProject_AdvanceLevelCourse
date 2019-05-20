package injectionprofile

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class BasicGetScript_OpenModel extends Simulation {

  val httpConf = http.baseUrl("http://newtours.demoaut.com")

  val scn = scenario("basicgetscenario").rendezVous(5).forever() {
    pace(2)
    .exec(http("basicgetrequest").get("/mercurycruise.php"))
  }
  setUp(scn.inject(rampUsers(5) during (5 seconds))).protocols(httpConf).maxDuration(45)
}
