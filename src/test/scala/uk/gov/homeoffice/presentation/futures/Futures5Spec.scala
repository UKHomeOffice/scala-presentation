package uk.gov.homeoffice.presentation.futures

import scala.concurrent.Future
import org.specs2.concurrent.ExecutionEnv
import org.specs2.mutable.Specification

class Futures5Spec(implicit ev: ExecutionEnv) extends Specification {
  "Result of future" should {
    "be manipulated" in {
      val future = Future {
        "Hello"
      }

      future map { _ + " World!"} must beEqualTo("Hello World!").await
    }
  }
}
