package uk.gov.homeoffice.presentation.futures

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import org.specs2.concurrent.ExecutionEnv
import org.specs2.mutable.Specification

class Futures6Spec(implicit ev: ExecutionEnv) extends Specification {
  "Result of future" should {
    "not be manipulated incorrectly" in {
      val future = Future {
        "Hello"
      }

      val outcome = Await.result(future, 1 second)

      outcome + " World!" must beEqualTo("Hello World!")
    }
  }
}
