import cats.effect.{IO, Resource}
import cats.syntax.all._
import dev.profunktor.redis4cats.algebra.{KeyCommands, StringCommands}
import dev.profunktor.redis4cats.connection.{RedisClient, RedisURI}
import dev.profunktor.redis4cats.domain.RedisCodec
import dev.profunktor.redis4cats.interpreter.Redis
import dev.profunktor.redis4cats.log4cats._
import io.chrisdavenport.log4cats.Logger
import io.chrisdavenport.log4cats.slf4j.Slf4jLogger
import scala.concurrent.duration._

import scala.concurrent.duration.Duration

object RedisVamo {
  implicit val cs = IO.contextShift(scala.concurrent.ExecutionContext.global)
  implicit val logger: Logger[IO] = Slf4jLogger.unsafeCreate[IO]

  println("Hello Leleco2")

  val stringCodec: RedisCodec[String, String] = RedisCodec.Utf8

  val commandsApi: Resource[IO, KeyCommands[IO, String]] =
    for {
      uri <- Resource.liftF(RedisURI.make[IO]("redis://localhost"))
      client <- RedisClient[IO](uri)
      redis <- Redis[IO, String, String](client, stringCodec, uri)
    } yield redis

  val key = "users"

  def roda = {
    commandsApi.use { cmd =>
      for {
        _ <- cmd.del(key)
        a <- cmd.exists(key)
      } yield a
    }
  }
}
