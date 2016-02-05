package uk.gov.homeoffice.presentation.futures

import java.util.concurrent.TimeUnit
import scala.concurrent.Future
import scala.concurrent.duration._
import org.specs2.concurrent.ExecutionEnv
import org.specs2.mutable.Specification

class Futures4Spec(implicit ev: ExecutionEnv) extends Specification {
  "Futures composition" should {
    "incorrectly run sequentially" in {
      val outcome = for {
        x <- Future {
          TimeUnit.SECONDS.sleep(2)
          2
        }
        y <- Future {
          TimeUnit.SECONDS.sleep(4)
          4
        }
        z <- Future {
          TimeUnit.SECONDS.sleep(6)
          6
        }
      } yield x + y + z

      outcome must beEqualTo(12).awaitFor(13 seconds)
    }
  }
}
