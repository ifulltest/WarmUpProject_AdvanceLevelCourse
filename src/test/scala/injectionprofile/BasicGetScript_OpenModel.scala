package injectionprofile

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class BasicGetScript_OpenModel extends Simulation {

  val httpConf = http.baseUrl("http://newtours.demoaut.com")

  /*5 users arrive in 5 seconds, each user runs..
  (for duration, times, as long as condition is met)
  with a time interval of 2 seconds in each iteration*/
  // val scn = scenario("basicgetscenario").repeat(4) {

  /*5 users arrive in 5 seconds, each user runs
  All the users run for 40 seconds
  Each user runs with a time interval of 2 seconds in each iteration*/
  // val scn = scenario("basicgetscenario").forever() {


  val scn = scenario("basicgetscenario").rendezVous(5).forever() {
    pace(2)
    .exec(http("basicgetrequest").get("/mercurycruise.php"))
  }
  setUp(scn.inject(rampUsers(5) during (5 seconds))).protocols(httpConf).maxDuration(45)
}
