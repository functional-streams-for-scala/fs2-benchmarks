package fs2
package benchmarks

import cats.effect.IO
import org.openjdk.jmh.annotations.{Benchmark, BenchmarkMode, Mode, OutputTimeUnit, Scope, State}
import java.util.concurrent.TimeUnit

@State(Scope.Thread)
class TextParsingBenchmark {

  @Benchmark @BenchmarkMode(Array(Mode.AverageTime)) @OutputTimeUnit(TimeUnit.MILLISECONDS)
  def parseBigFileSync: Int = {
    fs2.io.file.readAll[IO](TestData.largeJsonFile, 2048).through(text.utf8Decode).through(text.lines).compile.fold(0)((acc, _) => acc + 1).unsafeRunSync
  }
}