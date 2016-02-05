package uk.gov.homeoffice.presentation.futures

import java.util.concurrent.TimeUnit
import scala.concurrent.duration._
import scala.concurrent.{Future, Promise}
import scala.util.Try
import org.specs2.concurrent.ExecutionEnv
import org.specs2.mutable.Specification

/**
  * Instead of the built in Future.firstCompletedOf, Future can be enhanced like anything else to give a nicer API.
  */
class Futures9Spec(implicit ev: ExecutionEnv) extends Specification {
  "Promise" should {
    "be used to customise future" in {
      implicit class FutureOps[T](val self: Future[T]) {
        def or(other: Future[T]): Future[T] = {
          val p = Promise[T]()

          val completePromise = (t: Try[T]) => p tryComplete t

          self onComplete completePromise
          other onComplete completePromise

          p.future
        }
      }

      val fa = Future {
        TimeUnit.SECONDS.sleep(2)
        "A"
      }

      val fb = Future {
        TimeUnit.SECONDS.sleep(1)
        "B"
      }

      (fa or fb) must beEqualTo("B").awaitFor(3 seconds)
    }
  }
}