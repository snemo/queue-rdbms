package com.nuxplanet.gatling.reactive

import com.nuxplanet.gatling.ManyItemsSimulation

class ReactiveManyItemsSim extends ManyItemsSimulation {
  scenarioName = "reactive-many-items"
  url = "http://18.185.68.188:8081"
  //url = "http://127.0.0.1:8090"
  action = "/reactive/items"

  run()
}
