package injectionprofile

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class BasicGetScript_Open_rampUsersPerSec extends Simulation {

  val httpConf = http.baseUrl("http://newtours.demoaut.com")

  /*Rate of user arrival increases from 1 to 5 in 10 sec
  * Average rate of arrival=(1+2+3+4+5)/5=3
  * All the users run for 40 seconds
  * Each user runs with a time interval of 2 seconds in each iteration*/
  val scn = scenario("basicgetscenario").forever() {
    pace(2)
      .exec(http("basicgetrequest").get("/mercurycruise.php"))
  }

  setUp(scn.inject(rampUsersPerSec(1) to(5) during(10 seconds))).protocols(httpConf).maxDuration(45)
}
