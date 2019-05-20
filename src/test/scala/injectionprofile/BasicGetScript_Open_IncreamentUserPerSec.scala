package injectionprofile

import io.gatling.core.Predef._
import io.gatling.core.controller.inject.InjectionProfile
import io.gatling.http.Predef._

import scala.collection.mutable.MutableList
import scala.concurrent.duration._

class BasicGetScript_Open_IncreamentUserPerSec extends Simulation {

  val httpConf = http.baseUrl("http://newtours.demoaut.com")

  val scn = scenario("basicgetscenario").forever() {
    pace(2)
      .exec(http("basicgetrequest").get("/mercurycruise.php"))
  }

  setUp(scn.inject(
    incrementUsersPerSec(5)
      .times(5)
      .eachLevelLasting(10 seconds)
      .separatedByRampsLasting(10 seconds)
      .startingFrom(10)
  )).protocols(httpConf).maxDuration(300 seconds)
/*
  val rampduration = (10 seconds);
  val nothingduration=30 seconds;
  val stages = 4;
  val ratePerStage = 5;

  val steps =
    for {

      s <- 1 to stages

      step =rampUsers(5)during(10 seconds)

    } yield step

  println(steps.toString())
setUp(scn.inject(steps)).protocols(httpConf)maxDuration(300 seconds)*/

  /*
  setUp(scn.inject(
    atOnceUsers(5)
    ,nothingFor(30 seconds),rampUsers(5)during(10 seconds)
    ,nothingFor(30 seconds),rampUsers(5)during(10 seconds)
    ,nothingFor(30 seconds),rampUsers(5)during(10 seconds)
    ,nothingFor(30 seconds), rampUsers(5)during(10 seconds)
  )).protocols(httpConf).maxDuration(300 seconds)*/
}
