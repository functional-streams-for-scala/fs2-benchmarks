package fs2
package benchmarks

import org.openjdk.jmh.annotations.{Benchmark, BenchmarkMode, Mode, OutputTimeUnit, Scope, State}
import java.util.concurrent.TimeUnit

@State(Scope.Thread)
class TextParsingBenchmark {

  @Benchmark @BenchmarkMode(Array(Mode.AverageTime)) @OutputTimeUnit(TimeUnit.MILLISECONDS)
  def parseBigFileSync: Int = {
    fs2.io.file.readAll[Task](TestData.largeJsonFile, 2048).through(text.utf8Decode).through(text.lines).runFold(0)((acc, _) => acc + 1).unsafeRun
  }
}