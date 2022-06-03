package injectionprofile

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class BasicGetScript_Open_constantUsersPerSec extends Simulation {

  val httpConf = http.baseUrl("http://newtours.demoaut.com")

  /*1 user arrives every 2 seconds
  * All the users run for 40 seconds
  * Each user runs with a time interval of 2 seconds in each iteration*/
  val scn = scenario("basicgetscenario").forever() {
    pace(2)
      .exec(http("basicgetrequest").get("/mercurycruise.php"))
  }

  setUp(scn.inject(constantUsersPerSec(0.5) during(10 seconds))).protocols(httpConf).maxDuration(50)
}
