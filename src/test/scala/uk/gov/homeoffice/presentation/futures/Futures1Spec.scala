package uk.gov.homeoffice.presentation.futures

import scala.concurrent.Future
import org.specs2.concurrent.ExecutionEnv
import org.specs2.mutable.Specification

class Futures1Spec(implicit ev: ExecutionEnv) extends Specification {
  /**
    * A Future simply executes code.
    * The caller (of the Future) is not blocked i.e. a Future returns immediately and the caller then needs to "pull out" the Future's result.
    */
  "Future" should {
    "complete a given task" in {
      val outcome = Future {
        "done"
      }

      outcome must beEqualTo("done").await
    }
  }
}
