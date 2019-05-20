package basicpackage

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.util.Random

class POSTExample_StringBody_CustomFeeder extends Simulation {


  val apiKey = "9d02943b6f854cd893ea667e6a5a40ac"
  val linkshorten = csv("data/linkshortenUpdated.csv").circular //this csv is stored at data folder

  def randString=Random.alphanumeric.take(4).mkString
  var randomString = Iterator.continually(Map("slashtag" -> s"myslashtag_$randString","title" -> s"mytitle_$randString"))

  //Create http configuration
  val httpProtocol = http.baseUrl("https://api.rebrandly.com").header("apiKey", apiKey)


  val scn = scenario("CreateLink").repeat(2) {
    feed(linkshorten).feed(randomString)
      .exec(
        session=>
      {
        println(session("destination").as[String])
        println(session("slashtag").as[String])
        println(session("title").as[String])
        session
      })
     .exec(http("createnewlink").post("/v1/links").body(StringBody("""{"destination":"${destination}","slashtag":"${slashtag}","title":"${title}"}""")).asJson) //send post request
  }
  //inject user to send http request
  setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol) //inject one user
}
