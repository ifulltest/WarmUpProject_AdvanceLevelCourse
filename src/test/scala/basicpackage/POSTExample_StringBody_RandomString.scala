package basicpackage

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.util.Random

class POSTExample_StringBody_RandomString extends Simulation {


  val apiKey = "9d02943b6f854cd893ea667e6a5a40ac"
  val linkshorten = csv("data/linkshortenUpdated.csv").circular //this csv is stored at data folder
  var title = "" //keep it as it is and add to session as attribute using set method
  var slashtag=""
  var destination=""
  def randString=Random.alphanumeric.take(4).mkString
  //Create http configuration
  val httpProtocol = http.baseUrl("https://api.rebrandly.com").header("apiKey", apiKey)


  val scn = scenario("CreateLink").repeat(2) {
    feed(linkshorten)
      .exec(session => {
        destination = session("destination").as[String]
        slashtag = s"myslashtag_$randString"
        title = s"mytitle_$randString"
        println(s"""{"destination":"$destination","slashtag":"$slashtag","title":"$title"}""")
        session
      })
      .exec(http("createnewlink").post("/v1/links").body(StringBody(session => s"""{"destination":"$destination","slashtag":"$slashtag","title":"$title"}""")).asJson) //send post request
  }
  //inject user to send http request
  setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol) //inject one user
}
