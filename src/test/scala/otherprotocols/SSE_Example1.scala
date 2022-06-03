package otherprotocols

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class SSE_Example1 extends Simulation {

  val mycheck1=sse.checkMessage("checkid").check(jsonPath("$.id").find.saveAs("id"))
  val mycheck2=sse.checkMessage("checkdata").check(jsonPath("$.data").find.saveAs("data"))

  val httpConf = http.baseUrl("http://demo.howopensource.com")
    .acceptHeader("ext/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3")
    .inferHtmlResources()
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Gatling2")
    .upgradeInsecureRequestsHeader("1")


  val scn = scenario("sse_scenario")
    .exec(sse("sse_req").connect("/sse/stocks.php")

//    .await(50 seconds)(sse.checkMessage("check_connect").check(regex(".*.*").saveAs("myresponse"))))
//    .exec(session => {
//      println(session("myresponse").as[String])
//      session
//    })
    .await(50 seconds)(sse.checkMessage("check_connect").check(regex(".*.*").exists)))

    .pause(5 seconds)
    .repeat(6) {
      exec(sse("setcheck").setCheck.await(50 seconds)(mycheck1, mycheck2))
        .exec(session => {
          println(session("id").as[String])
          println(session("data").as[String])
          session
        })
    }
    .exec(sse("sse_close").close())

  setUp(scn.inject((atOnceUsers(1)))).protocols(httpConf)
}
