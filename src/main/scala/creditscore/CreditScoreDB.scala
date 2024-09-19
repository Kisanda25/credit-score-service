import slick.jdbc.PostgresProfile.api._

case class CreditScore(userId: String, score: Int)

class CreditScores(tag: Tag) extends Table[CreditScore](tag, "creditscores") {
  def userId = column[String]("user_id", O.PrimaryKey)
  def score = column[Int]("score")
  def * = (userId, score) <> (CreditScore.tupled, CreditScore.unapply)
}

object CreditScoreDB {
  val db = Database.forConfig("mydb")
  val scores = TableQuery[CreditScores]

  def getCreditScore(userId: String) = {
    db.run(scores.filter(_.userId === userId).result.headOption)
  }
}
