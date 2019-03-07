package com.nuxplanet.gatling

import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._
import io.gatling.core.Predef._

class QueueAsyncSimulation extends Simulation {


  var numberOfUsers = 100
  var rampDuration = 1
  var url = "http://localhost:8081"
  var action = "/queue/peek"

  val httpConf = http
    .baseUrl(url)
    .warmUp(url)

  val simpleRecord = scenario("OneSimpleRecord")
    .exec(http(action).get(action))
    .exec()

  setUp(simpleRecord.inject(rampUsers(numberOfUsers).during(rampDuration)))
    .protocols(httpConf)

}
