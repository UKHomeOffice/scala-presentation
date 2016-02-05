package uk.gov.homeoffice.presentation.futures

import scala.concurrent.Future
import org.specs2.concurrent.ExecutionEnv
import org.specs2.mutable.Specification

class Futures2Spec(implicit ev: ExecutionEnv) extends Specification {
  "Future" should {
    "complete with failure" in {
      val outcome = Future {
        throw new Exception
      }

      outcome must throwAn[Exception].await
    }
  }
}
