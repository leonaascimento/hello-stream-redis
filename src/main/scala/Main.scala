import cats.effect.{ExitCode, IO, IOApp}
import cats.syntax.all._

object Main extends IOApp {
  val program = RedisVamo.roda.map(println)

  override def run(args: List[String]): IO[ExitCode] =
    program.as(ExitCode.Success)
}
