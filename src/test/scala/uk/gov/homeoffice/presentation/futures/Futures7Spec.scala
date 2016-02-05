package uk.gov.homeoffice.presentation.futures

import scala.concurrent.Future
import org.specs2.concurrent.ExecutionEnv
import org.specs2.mutable.Specification

class Futures7Spec(implicit ev: ExecutionEnv) extends Specification {
  "Future.successful" should {
    "operate on the calling thread" in {
      val thisThread = Thread.currentThread()

      Future.successful {
        Thread.currentThread()
      } must beEqualTo(thisThread).await
    }
  }
}