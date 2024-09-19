package creditscore

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import scala.io.StdIn
import scala.concurrent.ExecutionContextExecutor
import io.circe.generic.auto._
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport._

case class CreditScore(userId: String, score: Int)

object CreditScoreService {
  def main(args: Array[String]): Unit = {
    implicit val system: ActorSystem = ActorSystem("credit-score-system")
    implicit val materializer: ActorMaterializer = ActorMaterializer()
    implicit val executionContext: ExecutionContextExecutor = system.dispatcher

    // Sample data (you can replace this with real database calls)
    val userCreditScores = Map(
      "user1" -> CreditScore("user1", 750),
      "user2" -> CreditScore("user2", 680),
      "user3" -> CreditScore("user3", 800)
    )

    val route: Route =
      path("creditScore" / Segment) { userId =>
        get {
          userCreditScores.get(userId) match {
            case Some(creditScore) => complete(creditScore)
            case None =>
              complete(
                StatusCodes.NotFound,
                s"Credit score for user $userId not found."
              )
          }
        }
      }

    val bindingFuture = Http().newServerAt("localhost", 8080).bind(route)

    println(s"Server online at http://localhost:8080/")
    StdIn.readLine() // Wait for user to press ENTER
    bindingFuture
      .flatMap(_.unbind())
      .onComplete(_ => system.terminate())
  }
}
